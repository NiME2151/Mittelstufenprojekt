package de.szut.msp_backend.models.item;

public enum ItemType
{
    WEAPON(0), 
    CONSUMABLE(0),
    ITEM(0);

    private int count;

    ItemType(int count) {
        this.count = count;
    }

    public int getCount(){
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
