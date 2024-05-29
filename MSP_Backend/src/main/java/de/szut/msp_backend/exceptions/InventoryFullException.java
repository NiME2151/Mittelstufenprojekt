package de.szut.msp_backend.exceptions;

public class InventoryFullException extends Exception
{
    public InventoryFullException(String message)
    {
        super(message);
    }
}