package de.szut.msp_backend.combatsystem;


import de.szut.msp_backend.item.*;
import de.szut.msp_backend.character.Character;
import de.szut.msp_backend.enemy.GenericEnemy;

import java.util.List;

public class Combatsystem
{
    void characterAttack(Character attacker, GenericEnemy defender)
    {
        int attackerStrength = attacker.getStrength();
        List<Weapon> weapons = attacker.getInventory().getItemsOfType(ItemType.Weapon);
        for (Weapon weapon: weapons)
        {
            attackerStrength += weapon.getDamage();
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

    void characterFlee(Character character){
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
// parameter müssen in eigenen klassen auf default = NULL gesetz werden
    void characterTurn(Character character, GenericEnemy enemy, Consumable consumable){
        int i = 0;
        switch (i){
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
        }

    }

    void fight(Character character, GenericEnemy enemy){
        if(isCharacterDead(character)){
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
        }
        if(isEnemyDead(enemy)){
            //character.items += kampfort.items;
            character.setMaxHealthPoints(character.getMaxHealthPoints() + 5);
            character.setHealthPoints(character.getHealthPoints() / 2);
            if(character.getHealthPoints() > character.getMaxHealthPoints()){
                character.setHealthPoints(character.getMaxHealthPoints());
                /*
                TODO:
                - geld
                - weapons
                - items
                - food
                */
            }
        }
    }
}
