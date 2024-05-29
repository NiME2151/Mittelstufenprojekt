package de.szut.msp_backend.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class InventoryFullExceptionTest
{
    @Test
    void testException()
    {
        assertThrowsExactly(InventoryFullException.class, () -> {throw new InventoryFullException("This inventory is full....");});
    }
}
