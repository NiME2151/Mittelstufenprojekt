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
    
    // counter für Verkaufte Gegenstände
    int scarcityItem = 0;
    int scarcityFood = 0;
    int scarcityWapon = 0;
    
    // counter für verkaufte Luxusobjekte (gleich oder besser als Rare)
    int rarityRare = 0;
    int rarityEpic = 0;
    int rarityLegendary = 0;
    
    public static void buyItem(Item item, Charakter charakter, Trader trader){
        Inventory charakterInventory = charakter.getInventory()
        if (charakterInventory.getEmptySlots() > 0 || item.isIn(charakterInventory)){
            charakter.addItem(item);
            trader.deleteItem(item);
            charakter.setGold(-(item.getBuyValue));
            trader.setGold(item.getBuyValue);
            
        }
        else {
            // TODO Platzhalter 
            System.out.println("Kein Platz im Inventar!!!!");
        }
    }
    
    public static void sellItem(Item item, Charakter charakter, Trader trader){
        
    }
    
    // Hilfsmethoden
    
}