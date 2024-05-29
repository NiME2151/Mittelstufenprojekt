package de.szut.msp_backend.events;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.combatsystem.CombatMoves;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.Rarity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FightGameActionTest
{
    @Test
    void testDoAction()
    {
        final Game game = Game.getInstance();
        game.getMap().changePlayerLocation(game.getMap().getAllNodes().get(3)); //get market and set it as the starting point of the game

        game.getPlayer().setMaxHealthPoints(100);
        game.getPlayer().setHealthPoints(100);

        final GenericEnemy enemy = new GenericEnemy("TestEnemy1", 20, 1);
        FightGameAction gameAction = new FightGameAction(enemy, CombatMoves.ATTACK, null);

        assertEquals(1, gameAction.doAction(0));
        assertEquals(game.getPlayer().getMaxHealthPoints()-1, game.getPlayer().getHealthPoints());
        assertEquals(20-5, enemy.getHealthPoints());

        gameAction = new FightGameAction(enemy, CombatMoves.EAT, null);

        assertEquals(1, gameAction.doAction(0));
        assertEquals(game.getPlayer().getMaxHealthPoints()-2, game.getPlayer().getHealthPoints());
        assertEquals(20-5, enemy.getHealthPoints());

        final Consumable consumable = new Consumable(50, "TestConsumable", "I am a test-object that exists for testing purposes.", 0, Rarity.LEGENDARY, 20);
        gameAction = new FightGameAction(enemy, CombatMoves.EAT, consumable);

        int maxhealth = game.getPlayer().getMaxHealthPoints();

        assertEquals(1, gameAction.doAction(0));
        assertEquals(maxhealth-1, game.getPlayer().getHealthPoints());
        assertEquals(20-5, enemy.getHealthPoints());

        gameAction = new FightGameAction(enemy, CombatMoves.FLEE, null);

        assertEquals(1, gameAction.doAction(0));
        assertEquals(((maxhealth-1)/2)-1, game.getPlayer().getHealthPoints());
        assertEquals(20-5, enemy.getHealthPoints());

        gameAction = new FightGameAction(enemy, CombatMoves.ATTACK, null);

        gameAction.doAction(0);
        assertEquals((int)((maxhealth-1)/2)-1-1, game.getPlayer().getHealthPoints());
        assertEquals(20-10, enemy.getHealthPoints());

        gameAction.doAction(0);
        assertEquals((int)((maxhealth-1)/2)-1-2, game.getPlayer().getHealthPoints());
        assertEquals(20-15, enemy.getHealthPoints());

        final int healthpoints = game.getPlayer().getHealthPoints();

        gameAction.doAction(0);
        assertEquals(Math.min(healthpoints+(int)(((int)(maxhealth*0.8)+5)/2), (int)(maxhealth*0.8)+5), game.getPlayer().getHealthPoints());
        assertEquals((int)(maxhealth*0.8)+5, game.getPlayer().getMaxHealthPoints());
        assertEquals(20-20, enemy.getHealthPoints());
    }
}
