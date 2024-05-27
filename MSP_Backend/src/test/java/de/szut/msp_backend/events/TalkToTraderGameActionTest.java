package de.szut.msp_backend.events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TalkToTraderGameActionTest
{
    @Test
    void testDoAction()
    {
        final TalkToTraderGameAction gameAction = new TalkToTraderGameAction();

        assertEquals(0, gameAction.doAction(123));
    }
}
