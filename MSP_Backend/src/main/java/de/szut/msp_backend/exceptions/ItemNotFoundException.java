package de.szut.msp_backend.exceptions;

public class ItemNotFoundException extends Exception
{
    public ItemNotFoundException()
    {
    }
    public ItemNotFoundException(String message)
    {
        super(message);
    }
}
