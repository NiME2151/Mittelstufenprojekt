package de.szut.msp_backend.models.item;

public enum Rarity
{

    COMMON(0),
    UNCOMMON(0),
    RARE(0),
    EPIC(0),
    LEGENDARY(0);

    private int count;

    public int getCount()
    {
        return count;
    }

    public void setCount(int count) 
    {
        // TODO: im Polishing die Zahl prüfen und bei Bedarf auch in TradeService anpassen
        if (this.count < 100) 
        {
            this.count = count;
        }
    }
    
    Rarity(int count)
    {
        this.count = count;
    }
}
