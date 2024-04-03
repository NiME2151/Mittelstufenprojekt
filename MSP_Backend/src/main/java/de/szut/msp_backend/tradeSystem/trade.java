@Data
public class trade {
    
    // Berechnungsgrundlage für alle "zeit"basierten Änderungen
    int lastTrade = 0;
    
    // Basis Modifikator für ungewöhnliche Gegenstände
    byte rarityCommon = 1;
    byte rarityUncommon = 2;
    byte rarityRare = 3;
    byte rarityEpic = 4;
    byte rarityLegendary = 5;
    
    public static void buyItemFromTrader(Item item, Charakter charakter, Trader trader){
        Inventory charakterInventory = charakter.getInventory()
        if (charakterInventory.getEmptySlots() > 0 || item.isIn(charakterInventory)){
            int price = getBuyValue(item);
            int money = charakter.getGold();
            if (money >= price){
                buy(item, charakter, trader);
            }
            else {
                // TODO Platzhalter 
                System.out.println("Dafür hast du nicht genug Gold!");
            }

        }
        else {
            // TODO Platzhalter 
            System.out.println("Kein Platz im Inventar!!!!");
        }
    }

    public static void sellItemToTrader(Item item, Charakter charakter, Trader trader){
        int price = getSellValue(item);
        int tradersMoney = trader.getGold();
        
        if (tradersMoney >= price){
            sell(item, charakter, trader, price);
        }
        else {
            // TODO Plattzhalter
            System.out.println("Das kann sich der Händler gerade leider nihct leisten!");
        }
    }

    // Hilfsmethoden
    private enum METHOD {
        Buy,
        Sell
    }
    
    private static void sell(Item item, Charakter charakter, Trader trader, int price) {
        trader.addItem(item);
        charakter.deleteItem(item);
        trader.subtractGold(price);
        charakter.addGold(price);
        setCounters(item, METHOD.Sell);
    }

    private static void buy(Item item, Charakter charakter, Trader trader) {
        charakter.addItem(item);
        trader.deleteItem(item);
        charakter.subtractGold(getBuyValue(item));
        trader.addGold(getBuyValue(item));
        setCounters(item, METHOD.Buy);
    }
    private static void setCounters(Item item, METHOD method){
        int faktor = METHOD.Sell ? 1 : -1;
        
        // counter am ItemType
        ItemType itemType = item.getType();
        itemType.count += faktor;
        
        // counter am RarityType
        
    }
    
    private static int getBuyValue(Item item){
        
    }
    
    private static int getSellValue(Item item){
        
    }
    
}