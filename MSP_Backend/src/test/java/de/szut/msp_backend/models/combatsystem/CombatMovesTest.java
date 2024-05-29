package de.szut.msp_backend.models.combatsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CombatMovesTest
{
    @Test
    void testValues()
    {
        assertEquals(3, CombatMoves.values().length);
    }

    @Test
    void testValueOf()
    {
        assertEquals(CombatMoves.valueOf("ATTACK"), CombatMoves.valueOf("ATTACK"));
        assertNotEquals(CombatMoves.valueOf("FLEE"), CombatMoves.valueOf("EAT"));
    }
}
