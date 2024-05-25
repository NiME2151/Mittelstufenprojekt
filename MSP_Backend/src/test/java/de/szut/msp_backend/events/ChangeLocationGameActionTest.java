package de.szut.msp_backend.events;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.map.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeLocationGameActionTest
{
    @Test
    void doAction()
    {
        final Game game = Game.getInstance();
        game.getMap().changePlayerLocation(game.getMap().getAllNodes().get(3)); //get market and set it as the starting point of the game
        final ChangeLocationGameAction gameAction = new ChangeLocationGameAction(Direction.NORTH); //try to go to the forest
        assertEquals(1, gameAction.doAction(2)); //should go to forest and increase clicks by 1

        assertEquals(0, gameAction.doAction(3)); //should try to go from forest to north but doesnt exist, so clicks stay the same
    }
}
