package de.szut.msp_backend.events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameActionTest
{
    @Test
    void doAction()
    {
        final GameActionImpl gameActionImpl = new GameActionImpl();

        assertEquals(2, gameActionImpl.doAction(2));
        assertEquals(7, gameActionImpl.doAction(7));
        assertEquals(2555, gameActionImpl.doAction(2555));
        assertEquals(-7980, gameActionImpl.doAction(-7980));
    }
}

class GameActionImpl implements GameAction
{
    @Override
    public int doAction(final int clicks)
    {
        return clicks;
    }
}
