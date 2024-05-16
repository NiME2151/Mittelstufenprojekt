package de.szut.msp_backend.models.fishing;

import de.szut.msp_backend.Game;

import java.util.Random;

public class Fishing
{
    private static Game game = Game.getInstance();
    private static de.szut.msp_backend.models.map.Map map = game.getMap();

    public static void tryFishing(Character character)
    {
        if(map.getPlayerLocation() == map.lake)
        {

        }
    }

    /**
     * This gives you a random number in between min (inclusive) and max (exclusive)
     * @param min
     * @param max
     * @return random Int
     */
    private int rndNumber(int min, int max)
    {
        Random r = new Random();
        return r.nextInt((max)-min) + min;
    }
}
