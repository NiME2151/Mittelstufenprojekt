package de.szut.msp_backend.tradesystem;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.inventory.Inventory;
import de.szut.msp_backend.item.Item;
import de.szut.msp_backend.item.ItemType;
import de.szut.msp_backend.item.Rarity;
import de.szut.msp_backend.character.Character;
import lombok.Data;

@Data
public class Trade {
    
    // Berechnungsgrundlage für alle "Zeit"basierten Änderungen
    public int lastTrade = 0;
    
    public static void buyItemFromTrader(Item item, Character character, Trader trader)
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
        else {
            // TODO FrontendMann fragen!
            System.out.println("Kein Platz im Inventar!!!!");
        }
    }

    public static void sellItemToTrader(Item item, Character charakter, Trader trader) throws ItemNotFoundException {
        int price = getSellValue(item);
        int tradersMoney = trader.getMoney();
        
        if (tradersMoney >= price)
        { 
            if (charakter.getInventory().isItemPresent(item)){
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
    
    private static void sell(Item item, Character charakter, Trader trader, int price) {
        trader.playerSellsItem(item, price);
        charakter.sellItemToTrader(item, price);
        setCounters(item, METHOD.SELL);
    }

    private static void buy(Item item, Character charakter, Trader trader, int price) throws ItemNotFoundException {
        trader.playerBuysItem(item, price);
        charakter.buyItemFromTrader(item, price);
        setCounters(item, METHOD.BUY);
    }
    
    private static void setCounters(Item item, METHOD method)
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
    
    private static int getBuyValue(Item item)
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
    
    private static int getSellValue(Item item)
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
    
    private static boolean isRare(Item item)
    {
        return (item.getRarity() != Rarity.Common) || (item.getRarity() != Rarity.Uncommon);
    }
    
}
