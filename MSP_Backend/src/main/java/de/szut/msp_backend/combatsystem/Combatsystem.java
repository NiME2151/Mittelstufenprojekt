package de.szut.msp_backend.combatsystem;


import de.szut.msp_backend.item;
import de.szut.msp_backend.character;
import de.szut.msp_backend.enemy;

public class Combatsystem
{
    public void characterAttack(Character attacker, Enemy defender)
    {
        defender.healthpoints -= (attacker.strength + attacker.inventory.weapon.strength);        //what is attacker.inventory.weapon.strength?
        if(defender.health <= 0)
        {
            defender.health = 0;
        }
    }

    public void enemyAttack(Enemy attacker, Character defender)
    {
        defender.healthpoints -= (attacker.strength + attacker.inventory.weapon.strength);        //what is attacker.inventory.weapon.strength?
        if(defender.health <= 0)
        {
            defender.health = 0;
        }
    }

    public boolean isCharacterDead(Character character)
    {
        if(character.healthpoints == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isEnemyDead(Enemy enemy)
    {
        if(enemy.healthpoints == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
