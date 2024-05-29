package de.szut.msp_backend.models.enemy;

import de.szut.msp_backend.models.item.GenericItem;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
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

    /**
     * The Enemy can regain healthpoints.
     *
     * @param healing the points that get added to the healthpoints.
     */
    public void regainHealthPoints(int healing)
    {
        this.healthPoints += healing;
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
}
