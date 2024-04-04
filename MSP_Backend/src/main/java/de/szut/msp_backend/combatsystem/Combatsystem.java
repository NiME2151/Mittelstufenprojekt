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

    //TODO: Bitte gebt i einen vernünftigen, descriptiven Namen. Eventuell auch mit einem Enum arbeiten?
    public static void characterTurn(Character character, GenericEnemy enemy, Consumable consumable, int i)
    {
        switch (i)
        {
            case 1:
                if(enemy != null)
                {
                    characterAttack(character, enemy);
                }
                break;
            case 2:
                if(consumable != null)
                {
                    character.eat(consumable);
                }
                break;
            case 3:
                characterFlee(character);
                break;
            default:
                break;
        }
    }

    //TODO: Bitte gebt i einen vernünftigen, descriptiven Namen. Eventuell auch mit einem Enum arbeiten?
    public static void enemyTurn(GenericEnemy enemy, Character character, int i)
    {
        switch (i)
        {
            case 1:
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
        enemyTurn(enemy, character, randTurnNumEnemy);

        int randTurnNumChar = rand.nextInt(4);

        characterTurn(character, enemy, null, randTurnNumChar);
    }
}
