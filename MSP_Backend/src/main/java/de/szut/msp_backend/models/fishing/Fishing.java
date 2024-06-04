package de.szut.msp_backend.models.fishing;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.map.Map;

import java.util.Random;

import static de.szut.msp_backend.parser.ItemParser.getGenericItemById;

public class Fishing
{
    private static final Random rand = new Random();

    /**
     *This function adds a random fish to the character's inventory
     * @param character The Player that is trying to fish
     * @throws ItemNotFoundException when the given Item cannot be found within the loaded Items (Note: this will never occur at the moment)
     */
    public static void fishing(final Character character) throws ItemNotFoundException
    {
        if(Game.getInstance().getMap().getPlayerLocation() == Map.apartments)
        {
            switch (rand.nextInt(10 - 0) + 0)
            {
                case 0:
                    character.addItemToInventory(getGenericItemById(35), 1);
                    break;
                case 1:
                    character.addItemToInventory(getGenericItemById(36), 1);
                    break;
                case 2:
                    character.addItemToInventory(getGenericItemById(37), 1);
                    break;
                case 3:
                    character.addItemToInventory(getGenericItemById(38), 1);
                    break;
                case 4:
                    character.addItemToInventory(getGenericItemById(39), 1);
                    break;
                case 5:
                    character.addItemToInventory(getGenericItemById(40), 1);
                    break;
                case 6:
                    character.addItemToInventory(getGenericItemById(41), 1);
                    break;
                case 7:
                    character.addItemToInventory(getGenericItemById(42), 1);
                    break;
                case 8:
                    character.addItemToInventory(getGenericItemById(43), 1);
                    break;
                case 9:
                    character.addItemToInventory(getGenericItemById(44), 1);
                    break;
                default:
                    break;
            }
        }
    }
}
