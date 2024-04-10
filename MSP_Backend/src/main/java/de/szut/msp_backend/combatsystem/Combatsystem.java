package de.szut.msp_backend.combatsystem;


import de.szut.msp_backend.item.*;
import de.szut.msp_backend.character.Character;
import de.szut.msp_backend.enemy.GenericEnemy;

import java.util.List;
import java.util.Random;

public class Combatsystem
{
    public static void characterAttack(Character attacker, GenericEnemy defender)
    {
        int attackerStrength = attacker.getStrength();
        List<GenericItem> weapons = attacker.getInventory().getItemsOfType(ItemType.Weapon);
        for (GenericItem weapon: weapons)
        {
            attackerStrength += ((Weapon)weapon).getDamage();
        }

        if((defender.getHealthPoints() - attackerStrength) <= 0)
        {
            defender.setHealthPoints(0);
        }
        else
        {
            defender.setHealthPoints((defender.getHealthPoints() - attackerStrength));
        }
    }

    public static void enemyAttack(GenericEnemy attacker, Character defender)
    {
        if((defender.getHealthPoints() - attacker.getDamage()) <= 0)
        {
            defender.setHealthPoints(0);
        }
        else
        {
            defender.setHealthPoints((defender.getHealthPoints() - attacker.getDamage()));
        }
    }

    public static void characterFlee(Character character)
    {
        character.setHealthPoints(character.getHealthPoints() / 2);
        character.setMaxHealthPoints(character.getMaxHealthPoints() - (int) (character.getMaxHealthPoints() * 0.2));
    }

    public static boolean isCharacterDead(Character character)
    {
        return character.getHealthPoints() == 0;
    }

    public static boolean isEnemyDead(GenericEnemy enemy)
    {
        return enemy.getHealthPoints() == 0;
    }

    //TODO: @Neele warum übergeben wir consumable ?
    public static void characterTurn(Character character, GenericEnemy enemy, Consumable consumable, CombatMoves combatMove)
    {
        switch (combatMove)
        {
            case Attack:
                if(enemy != null)
                {
                    characterAttack(character, enemy);
                }
                break;
            case Eat:
                if(consumable != null)
                {
                    character.eat(consumable);
                }
                break;
            case Flee:
                characterFlee(character);
                break;
            default:
                break;
        }
    }

    public static void enemyTurn(GenericEnemy enemy, Character character, CombatMoves combatMove)
    {
        switch (combatMove)
        {
            case Attack:
                if(enemy != null)
                {
                    enemyAttack(enemy, character);
                }
                break;
            default:
                break;
        }

    }

    public static void fight(Character character, GenericEnemy enemy)
    {
        if(isCharacterDead(character))
        {
            /*
            kampfort.items += character.items;

            charakter.place = tavern;
            character.setHealthPoints = character.maxHealtPoints / 2;
            //TODO:
            - character.items = basic items;
            - weapon
            - food
            - money
            */
            return;
        }
        if(isEnemyDead(enemy))
        {
            //character.items += kampfort.items;
            character.setMaxHealthPoints(character.getMaxHealthPoints() + 5);
            character.setHealthPoints(character.getHealthPoints() / 2);
            if(character.getHealthPoints() > character.getMaxHealthPoints())
            {
                character.setHealthPoints(character.getMaxHealthPoints());
                /*
                TODO:
                - geld
                - weapons
                - items
                - food
                */
            }
            return;
        }
        /*if(kampfort == arena)
        {
            characterTurn();
        }
        */

        Random rand = new Random();

        int randTurnNumEnemy = rand.nextInt(2);
        CombatMoves combatMoves = CombatMoves.values()[randTurnNumEnemy];
        enemyTurn(enemy, character, combatMoves);

        int randTurnNumChar = rand.nextInt(3);
        combatMoves = CombatMoves.values()[randTurnNumChar];
        characterTurn(character, enemy, null, combatMoves);
    }
}
