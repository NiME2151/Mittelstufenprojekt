package de.szut.msp_backend.models.combatsystem;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.ItemType;
import de.szut.msp_backend.models.item.Weapon;

import java.util.List;
import java.util.Map;

public class Combatsystem
{
  private static Game game = Game.getInstance();
  private static de.szut.msp_backend.models.map.Map map = game.getMap();

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
    } else
    {
      defender.setHealthPoints((defender.getHealthPoints() - attackerStrength));
    }
  }

  public static void enemyAttack(GenericEnemy attacker, Character defender)
  {
    if ((defender.getHealthPoints() - attacker.getDamage()) <= 0)
    {
      defender.setHealthPoints(0);
    } else
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

  public static void enemyTurn(GenericEnemy enemy, Character character)
  {
    enemyAttack(enemy, character);
  }

  public static boolean checkForFightEnd(Character character, GenericEnemy enemy)
  {
    if (isCharacterDead(character))
    {
      character.setHealthPoints(character.getMaxHealthPoints() / 2);
      for (Map.Entry<GenericItem, Integer> entry : character.getInventory().getItems().entrySet())
      {
        for (int i = 0; i < entry.getValue(); i++)
        {
          map.getPlayerLocation().addFindableItem(entry.getKey());
        }
      }
      map.changePlayerLocation(map.tavern);
      character.setMoney(0);
      //TODO: Wait for character basic items?
      //character.addItemToInventory(); <- add basic Items
      return true;
    }
    if (isEnemyDead(enemy))
    {
      for (GenericItem loot : map.getPlayerLocation().getFindableItems())
      {
        character.addItemToInventory(loot, 1);
        map.getPlayerLocation().removeFindableItem(loot);
      }
      //TODO: Add Logic/balancing for the Money reward for winning fights
      character.addMoney(10);
      character.setMaxHealthPoints(character.getMaxHealthPoints() + 5);
      character.setHealthPoints(character.getHealthPoints() / 2);
      if (character.getHealthPoints() > character.getMaxHealthPoints())
      {
        character.setHealthPoints(character.getMaxHealthPoints());
      }
      return true;
    }
    return false;
  }
}
