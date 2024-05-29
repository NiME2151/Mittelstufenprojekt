package de.szut.msp_backend.models.enemy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GenericEnemyTest
{
    @Test
    void testGetID()
    {
        final GenericEnemy enemy = new GenericEnemy("TestEnemy0", 20, 10);
        final GenericEnemy enemy2 = new GenericEnemy("TestEnemy1", 20, 10);

        assertNotEquals(enemy.getID(), enemy2.getID());
    }

    @Test
    void testRegainHealthPoints()
    {
        final GenericEnemy enemy = new GenericEnemy("TestEnemy0", 20, 10);
        enemy.regainHealthPoints(10);
        assertEquals(30, enemy.getHealthPoints());
    }

    @Test
    void testTakeDamage()
    {
        final GenericEnemy enemy = new GenericEnemy("TestEnemy0", 20, 10);
        enemy.takeDamage(10);
        assertEquals(10, enemy.getHealthPoints());
    }

    /*
    @Test
    void testGetName()
    {
    }

    @Test
    void testSetName()
    {
    }

    @Test
    void testGetHealthPoints()
    {
    }

    @Test
    void testSetHealthPoints()
    {
    }

    @Test
    void testGetDamage()
    {
    }

    @Test
    void testSetDamage()
    {
    }*/
}
