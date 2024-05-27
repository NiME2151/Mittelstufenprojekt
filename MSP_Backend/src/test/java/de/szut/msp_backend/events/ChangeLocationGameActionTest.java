package de.szut.msp_backend.events;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.map.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeLocationGameActionTest
{
    @Test
    void testDoAction()
    {
        final Game game = Game.getInstance();
        game.getMap().changePlayerLocation(Map.market); //get market and set it as the starting point of the game
        ChangeLocationGameAction gameAction = new ChangeLocationGameAction(Map.forest.getNodeId()); //try to go to the forest
        assertEquals(1, gameAction.doAction(2)); //should go to forest and increase clicks by 1

        assertEquals(0, gameAction.doAction(3)); //should try to go from forest to forest but should not increase the clicks as im already there
        gameAction = new ChangeLocationGameAction(Map.arena.getNodeId());
        assertEquals(0, gameAction.doAction(3)); //should try to go from forest to arena but it isnt possible as arena is not a neighbour of forest


        gameAction = new ChangeLocationGameAction("22");
        assertEquals(0, gameAction.doAction(3)); //should try to go from forest to a non-existing node
    }
}
