package de.szut.msp_backend.events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TalkToNPCGameActionTest
{
    @Test
    void doAction()
    {
        final TalkToNPCGameAction gameAction = new TalkToNPCGameAction();

        assertEquals(0, gameAction.doAction(123));
    }
}
