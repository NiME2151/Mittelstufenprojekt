package de.szut.msp_backend.enemy;

public abstract class GenericEnemy
{
    private String name;
    private int healthPoints;
    private int damage;

    public GenericEnemy(String name, int healthPoints, int damage)
    {
        this.name = name;
        this.healthPoints = healthPoints;
        this.damage = damage;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getHealthPoints()
    {
        return this.healthPoints;
    }

    public void setHealthPoints(int healthPoints)
    {
        this.healthPoints = healthPoints;
    }

    public void regainHealthPoints(int healing)
    {
        this.healthPoints += healing;
    }

    public int getDamage()
    {
        return this.damage;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    public void takeDamage(int damage)
    {
        this.healthPoints -= damage;
    }
}
