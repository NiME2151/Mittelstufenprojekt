package de.szut.msp_backend.enemy;

public abstract class GenericEnemy
{
    private String name;
    private int health;
    private int damage;

    public GenericEnemy(String name, int health, int damage)
    {
        this.name = name;
        this.health = health;
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

    public int getHealth()
    {
        return this.health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public void regainHealth(int healing)
    {
        this.health += health;
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
        this.health -= damage;
    }
}
