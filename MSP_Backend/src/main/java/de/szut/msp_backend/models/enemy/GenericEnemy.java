package de.szut.msp_backend.models.enemy;

import java.util.UUID;

public class GenericEnemy
{
    private UUID id;
    private String name;
    private int healthPoints;
    private int damage;

    public GenericEnemy(String name, int healthPoints, int damage)
    {
        this.id = UUID.randomUUID();
        this.name = name;
        this.healthPoints = healthPoints;
        this.damage = damage;
    }

    public String getID()
    {
        return this.id.toString();
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
    /**
     * The Enemy can regain healthpoints.
     * @param healing the points that get added to the healthpoints.
     */
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
    /**
     * The damage the enemy takes.
     * @param damage The damage gets directly subtracted from the healthpoints.
     */
    public void takeDamage(int damage)
    {
        this.healthPoints -= damage;
    }
}
