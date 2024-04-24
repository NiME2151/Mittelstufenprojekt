package de.szut.msp_backend.models.item;

public enum Rarity
{
    Common (0),
    Uncommon (0), 
    Rare (0),
    Epic(0), 
    Legendary(0);
    private int count;

    public int getCount(){
        return count;
    }

    public void setCount(int count) {
        // TODO: im Polishing die Zahl prüfen und bei Bedarf auch in Trade anpassen
        if (this.count < 100) {
            this.count = count;
        }
    }
    
    Rarity(int count){
        this.count = count;
    }
}
