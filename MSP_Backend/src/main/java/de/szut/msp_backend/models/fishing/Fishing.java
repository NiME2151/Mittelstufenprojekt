package de.szut.msp_backend.models.fishing;

import de.szut.msp_backend.Game;

import java.util.Random;

public class Fishing
{
  private static Game game = Game.getInstance();
  private static de.szut.msp_backend.models.map.Map map = game.getMap();

  public static void fishing(Character character)
  {
      if(map.getPlayerLocation() == map.lake)
      {
          switch (rndNumber(0, 10))
          {
              case 0:
                  break;
              case 1:
                  break;
              case 2:
                  break;
              case 3:
                  break;
              case 4:
                  break;
              case 5:
                  break;
              case 6:
                  break;
              case 7:
                  break;
              case 8:
                  break;
              case 9:
                  break;
          }
      }
  }

  /**
   * This function returns a random number in between min (inclusive) and max (exclusive)
   * @param min
   * @param max
   * @return random Int
   */
  private static int rndNumber(int min, int max)
  {
    Random r = new Random();
    return r.nextInt((max) - min) + min;
  }
}
