package de.szut.msp_backend.models.enemy;

import de.szut.msp_backend.models.item.GenericItem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GenericEnemy
{
    private final UUID id;
    private String name;
    private int healthPoints;
    private int damage;
    private final Map<GenericItem, Integer> lootItems;

    public GenericEnemy(String name, int healthPoints, int damage)
    {
        this.id = UUID.randomUUID();
        this.name = name;
        this.healthPoints = healthPoints;
        this.damage = damage;
        this.lootItems = new HashMap<>();
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
     *
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
     *
     * @param damage The damage gets directly subtracted from the healthpoints.
     */
    public void takeDamage(int damage)
    {
        this.healthPoints -= damage;
    }

    public Map<GenericItem, Integer> getLootItems()
    {
        return this.lootItems;
    }
}
