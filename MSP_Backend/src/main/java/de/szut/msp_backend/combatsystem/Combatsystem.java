package de.szut.msp_backend.combatsystem;


import de.szut.msp_backend.item.*;
import de.szut.msp_backend.character.Character;
import de.szut.msp_backend.enemy.GenericEnemy;
import de.szut.msp_backend.combatsystem.Action;

import java.util.List;

public class Combatsystem
{
    void characterAttack(Character attacker, GenericEnemy defender)
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

    void enemyAttack(GenericEnemy attacker, Character defender)
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

    void characterFlee(Character character)
    {
        character.setHealthPoints(character.getHealthPoints() / 2);
        character.setMaxHealthPoints(character.getMaxHealthPoints() - (int) (character.getMaxHealthPoints() * 0.2));
    }

    boolean isCharacterDead(Character character)
    {
        return character.getHealthPoints() == 0;
    }

    boolean isEnemyDead(GenericEnemy enemy)
    {
        return enemy.getHealthPoints() == 0;
    }

    void characterTurn(Character character, GenericEnemy enemy, Consumable consumable, Action action)
    {
        switch (action)
        {
            case ATTACK:
                if(enemy != null)
                {
                    characterAttack(character, enemy);
                }
                break;
            case EAT:
                if(consumable != null)
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

    void enemyTurn(GenericEnemy enemy, Character character)
    {
        enemyAttack(enemy, character);
    }

    void fight(Character character, GenericEnemy enemy)
    {
        if(isCharacterDead(character))
        {
            character.setHealthPoints(character.getMaxHealthPoints() / 2); ;
            /*
            //TODO:
            kampfort.items += character.items;
            charakter.place = tavern;
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
        /*if(kampfort == arena){
            characterTurn();
        }
        */

        enemyTurn(enemy, character);

        characterTurn(character, enemy, , );
    }
}
