package de.szut.msp_backend.models.combatsystem;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.ItemType;
import de.szut.msp_backend.models.item.Weapon;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static de.szut.msp_backend.Game.LOGGER;

public class Combatsystem
{
    /**
     * An attack from the character. In this method the healthpoints of the defender get updated.
     * The healthpoints of the defender can't get lower than 0.
     *
     * @param attacker the character with its attackpoints,
     *                 put together by strength and optional weapons.
     * @param defender the defender with its healthpoints.
     */
    public static void characterAttack(Character attacker, GenericEnemy defender)
    {
        int attackerStrength = attacker.getStrength();
        List<GenericItem> weapons = attacker.getInventory().getItemsOfType(ItemType.WEAPON);
        for (GenericItem weapon : weapons)
        {
            attackerStrength += ((Weapon) weapon).getDamage();
        }

        if ((defender.getHealthPoints() - attackerStrength) <= 0)
        {
            defender.setHealthPoints(0);
        }
        else
        {
            defender.setHealthPoints((defender.getHealthPoints() - attackerStrength));
        }
    }

    /**
     * An attack from the enemy. In this method the healthpoints of the Character get updated.
     * The healthpoints of the defender can't get lower than 0.
     *
     * @param attacker the enemy with its attackpoints.
     * @param defender the Character with its healthpoints.
     */
    public static void enemyAttack(GenericEnemy attacker, Character defender)
    {
        if ((defender.getHealthPoints() - attacker.getDamage()) <= 0)
        {
            defender.setHealthPoints(0);
        }
        else
        {
            defender.setHealthPoints((defender.getHealthPoints() - attacker.getDamage()));
        }
    }

    /**
     * Says what happens when the character flees.
     * The characters healthpoints gets halved. And the max healthpoints get lowered by 20%.
     *
     * @param character the character with its healthpoints and max healthpoints.
     */
    public static void characterFlee(Character character)
    {
        character.setHealthPoints(character.getHealthPoints() / 2);
        character.setMaxHealthPoints(character.getMaxHealthPoints() - (int) (character.getMaxHealthPoints() * 0.2));
    }

    /**
     * Checks the healthpoints of the player, if 0 the player is dead.
     *
     * @param character the player with its healthpoints.
     * @return dead (true) or alive (false).
     */
    public static boolean isCharacterDead(Character character)
    {
        return character.getHealthPoints() == 0;
    }

    /**
     * Checks the healthpoints of the enemy, if 0 the enemy is dead.
     *
     * @param enemy the enemy with its healthpoints.
     * @return dead (true) or alive (false).
     */
    public static boolean isEnemyDead(GenericEnemy enemy)
    {
        return enemy.getHealthPoints() == 0;
    }

    /**
     * The player can decide between three moves EAT, ATTACK or FLEE,
     * in this method on of them get executed.
     *
     * @param character  the player with its damagepoints, healthpoints.
     * @param enemy      the enemy with its healthpoints.
     * @param consumable for possible healing (eat) of the player
     * @param combatMove the move EAT, ATTACK or FLEE.
     */
    public static void characterTurn(Character character, GenericEnemy enemy, Consumable consumable, CombatMoves combatMove)
    {
        switch (combatMove)
        {
            case ATTACK:
                if (enemy != null)
                {
                    characterAttack(character, enemy);
                }
                break;
            case EAT:
                if (consumable != null)
                {
                    character.eat(consumable);
                }
                break;
            case FLEE:
                characterFlee(character);
                break;
            default:
                break;
        }
    }

    /**
     * The move of the enemy it can attack.
     *
     * @param enemy     with its attackpoints.
     * @param character with its healthpoints.
     */
    public static void enemyTurn(GenericEnemy enemy, Character character)
    {
        enemyAttack(enemy, character);
    }

    /**
     * If the character or the enemy is dead (healthpoint equal 0) the fight ends.
     * This method executes and sets what happens after the death.
     * If Player dies he spawns at tavern with basic items and no money.
     * If enemy dies the player gets loot, 5 more max healthpoints
     * and most of the healthpoints recharge.
     *
     * @param character with its healthpoints
     * @param enemy     with its healthpoints
     * @return true, if at least one of the fighters is dead. False, if both are alive.
     */
    public static boolean checkForFightEnd(Character character, GenericEnemy enemy)
    {
        if (isCharacterDead(character))
        {
            character.setHealthPoints(character.getMaxHealthPoints() / 2);
            for (Map.Entry<GenericItem, Integer> entry : character.getInventory().getItems().entrySet())
            {
                for (int i = 0; i < entry.getValue(); i++)
                {
                    try
                    {
                        Game.getInstance().getMap().getPlayerLocation().addPlayerItem(entry.getKey());
                        character.removeItemFromInventory(entry.getKey(), entry.getValue());
                    }
                    catch (final ItemNotFoundException ex)
                    {
                        LOGGER.warn(ex.getMessage());
                    }
                }
            }
            changePlayerLocationInMapToTavern();
            character.setMoney(0);
            //TODO: Wait for character basic items?
            //character.addItemToInventory(); <- add basic Items
            return true;
        }
        if (isEnemyDead(enemy))
        {
            for (GenericItem loot : enemy.getLootItems().keySet())
            {
                character.addItemToInventory(loot, enemy.getLootItems().get(loot));
            }
            //TODO: Add Logic/balancing for the Money reward for winning fights
            character.addMoney(10);
            character.setMaxHealthPoints(character.getMaxHealthPoints() + 5);
            character.setHealthPoints(character.getHealthPoints() + (int)(character.getMaxHealthPoints() / 2));
            if (character.getHealthPoints() > character.getMaxHealthPoints())
            {
                character.setHealthPoints(character.getMaxHealthPoints());
            }
            return true;
        }
        return false;
    }

    private static void changePlayerLocationInMapToTavern()
    {
        Class<?> klasse = Game.getInstance().getMap().getClass();
        try
        {
            Field field = klasse.getDeclaredField("playerLocation");
            field.setAccessible(true);
            field.set(Game.getInstance().getMap(), de.szut.msp_backend.models.map.Map.square);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
