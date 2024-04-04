package de.szut.msp_backend.item;

public enum ItemType
{
    Weapon (0), 
    Consumable(0),
    Item(0);

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
