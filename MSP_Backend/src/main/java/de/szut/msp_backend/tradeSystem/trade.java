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
}