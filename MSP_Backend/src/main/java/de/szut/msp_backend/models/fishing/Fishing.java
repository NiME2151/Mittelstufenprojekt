package de.szut.msp_backend.models.fishing;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.character.Character;

import java.util.Random;

public class Fishing
{
  private static Game game = Game.getInstance();
  private static de.szut.msp_backend.models.map.Map map = game.getMap();

    /**
     *This function adds a random fish to the character's inventory
     * @param character
     */
  public static void fishing(Character character)
  {
      if(map.getPlayerLocation() == map.lake)
      {
          switch (rndNumber(0, 10))
          {
              case 0:
                  character.addItemToInventory(getGenericItemById(35));
                  break;
              case 1:
                  character.addItemToInventory(getGenericItemById(36));
                  break;
              case 2:
                  character.addItemToInventory(getGenericItemById(37));
                  break;
              case 3:
                  character.addItemToInventory(getGenericItemById(38));
                  break;
              case 4:
                  character.addItemToInventory(getGenericItemById(39));
                  break;
              case 5:
                  character.addItemToInventory(getGenericItemById(40));
                  break;
              case 6:
                  character.addItemToInventory(getGenericItemById(41));
                  break;
              case 7:
                  character.addItemToInventory(getGenericItemById(42));
                  break;
              case 8:
                  character.addItemToInventory(getGenericItemById(43));
                  break;
              case 9:
                  character.addItemToInventory(getGenericItemById(44));
                  break;
              default:
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
