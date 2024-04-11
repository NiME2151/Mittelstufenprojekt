package de.szut.msp_backend.combatsystem;

public enum Action
{
    ATTACK(1),
    EAT(2),
    FLEE(3);

    private final int value;

    Action(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
