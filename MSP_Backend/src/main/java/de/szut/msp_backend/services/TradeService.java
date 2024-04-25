package de.szut.msp_backend.services;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.ItemType;
import de.szut.msp_backend.models.item.Rarity;
import de.szut.msp_backend.models.tradesystem.Trader;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TradeService
{
    
    public static void buyItemFromTrader(GenericItem item, Character character, Trader trader) throws ItemNotFoundException 
    {
        Inventory characterInventory = character.getInventory();
        if (characterInventory.isNotFull() || characterInventory.isItemPresent(item))
        {
            int price = getBuyValue(item);
            int charisma = character.getCharisma();
            
            // TODO Charisma anpassen
            if (charisma > 0)
            {
                double faktor = (double) (10 - charisma)/10;
                price = (int) (price * faktor);
            }
            int money = character.getMoney();
            if (money >= price)
            {
                buy(item, character, trader, price);
            }
            else 
            {
                // TODO FrontendMann fragen!
                System.out.println("Dafür hast du nicht genug Gold!");
            }

        }
        else 
        {
            // TODO FrontendMann fragen!
            System.out.println("Kein Platz im Inventar!!!!");
        }
    }

    public static void sellItemToTrader(GenericItem item, Character charakter, Trader trader) throws ItemNotFoundException 
    {
        int price = getSellValue(item);
        int tradersMoney = trader.getMoney();
        
        if (tradersMoney >= price)
        { 
            if (charakter.getInventory().isItemPresent(item))
            {
                sell(item, charakter, trader, price);
            }
            else throw new ItemNotFoundException("Du kannst nichts verkaufen was du nicht hast");
        }
        else 
        {
            // TODO FrontendMann fragen!
            System.out.println("Das kann sich der Händler gerade leider nicht leisten!");
        }
    }

    // Hilfsmethoden
    private enum METHOD 
    {
        BUY,
        SELL
    }
    
    private static void sell(GenericItem item, Character charakter, Trader trader, int price) 
    {
        trader.playerSellsItem(item, price);
        charakter.sellItemToTrader(item, price);
        setCounters(item, METHOD.SELL);
    }

    private static void buy(GenericItem item, Character charakter, Trader trader, int price) throws ItemNotFoundException 
    {
        trader.playerBuysItem(item, price);
        charakter.buyItemFromTrader(item, price);
        setCounters(item, METHOD.BUY);
    }
    
    private static void setCounters(GenericItem item, METHOD method)
    {
        int factor;
        
        if (method == METHOD.SELL) 
        {
            factor = 1;
        } 
        else 
        {
            factor = -1;
        }
        
        // counter am ItemType
        ItemType itemType = item.getItemType();
        int newCount = itemType.getCount() + factor;
        itemType.setCount(newCount);
        
        // counter am RarityType
        Rarity rarity = item.getRarity();
        int newRarityCount = rarity.getCount() + factor;
        rarity.setCount(newRarityCount);
        
    }
    
    public static int getBuyValue(GenericItem item)
    {
        int price;
        int standardValue = item.getStandardValue();
        if (isRare(item))
        {
            // Annahme: die raren Items sind immer im 100er oder weniger Bereich
            int factor = item.getRarity().getCount();
            price = standardValue - (standardValue/100 * factor) + 1;
            return Math.max(price, 1);
        }
        double availability = item.getItemType().getCount();
        double availabilityFactor = (availability/1000) * 10;
        price = (int)(standardValue - (standardValue * availabilityFactor));
        return price;
    }
    
    public static int getSellValue(GenericItem item)
    {
        int price;
        int standardValue = item.getStandardValue();
        if (isRare(item))
        {
            int factor = item.getRarity().getCount();
            price = standardValue - (standardValue/100 * factor) -1;
            return Math.max(price, 1);
        }
        double availability = item.getItemType().getCount();
        double availabilityFactor = (availability/1000) * 10;
        price = (int)(standardValue - (standardValue * availabilityFactor));
        return price;
    }
    
    private static boolean isRare(GenericItem item)
    {
        return (item.getRarity() != Rarity.COMMON) || (item.getRarity() != Rarity.UNCOMMON);
    }
    
}
