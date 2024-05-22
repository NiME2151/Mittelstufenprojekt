package de.szut.msp_backend.models.map;

import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.tradesystem.Trader;

import java.util.*;

public class Map
{
  public final Node lake;
  public final Node tavern;
  public final Node forest;
  public final Node market;
  public final Node arena;

  private Node playerLocation;

  public Map()
  {
    //TODO: After the Names for the Loottables are set in Stone update the given names here to utilize them!
    lake = new Node("Lake", "This small Lake seems to be soothing your mind and make you feel at peace. With its constant Water flowing it makes you forget all the hardships of living.", "nodeItemsLake", "nodeEnemiesLake", "1");
    tavern = new Node("Tavern", "The local Tavern seems to have seen livelier days, but its still comforting, + you can get a good beer here :)", "nodeItemsTavern", "nodeEnemiesTavern", "2");
    forest = new Node("Forest", "The Forest is dense and full of life. There is some dark places you might want to avoid. A good source of Mushrooms, too.", "nodeItemsForest", "nodeEnemiesForest", "3");
    market = new Node("Market", "The Market is tidy and smells really nice. All the fresh picked goods, the fresh bakery goods, all the smells you'd need to keep your mouth watering like the Niagara Falls.", "nodeItemsMarket", "nodeEnemiesMarket", "4");
    arena = new Node("Arena", "The floor is still stained by blood. You are unsure if you should ever be here. This place makes you feel uneasy.", "nodeItemsArena", "nodeEnemiesArena", "5");

    setNeighbours();
  }

  private void setNeighbours()
  {
    lake.addNeighbour(Direction.EAST, forest);

    forest.addNeighbour(Direction.WEST, lake);
    forest.addNeighbour(Direction.SOUTH, market);

    market.addNeighbour(Direction.NORTH, forest);
    market.addNeighbour(Direction.SOUTH, arena);
    market.addNeighbour(Direction.WEST, tavern);

    tavern.addNeighbour(Direction.EAST, market);
    tavern.addNeighbour(Direction.SOUTH, arena);

    arena.addNeighbour(Direction.NORTH, market);
  }

  public List<Node> getAllNodes()
  {
    final List<Node> nodes = new ArrayList<>();
    nodes.add(lake);
    nodes.add(arena);
    nodes.add(forest);
    nodes.add(market);
    nodes.add(tavern);
    return nodes;
  }

  public Node getPlayerLocation()
  {
    return this.playerLocation;
  }
  
    public void changePlayerLocation(Node location)
    {
        this.playerLocation = location;
    }

    public Direction getDirectionOfGivenNeighbour(final Node givenNeighbour)
    {
        final java.util.Map<Direction, Node> currentNeighbours = playerLocation.getNeighbours();
        final Set<Direction> directions = currentNeighbours.keySet();
        final Collection<Node> nodes = currentNeighbours.values();
        for (int i = 0; i < currentNeighbours.size(); i++)
        {
            if (nodes.stream().toList().get(i).getNodeID().equals(givenNeighbour.getNodeID()))
            {
                return (Direction) directions.toArray()[i];
            }
        }
        throw new RuntimeException("The currently active Node does not have the given node as a neighbour.");
    }

    public Trader getTraderById(final String traderID)
    {
        for (final Node node : getAllNodes())
        {
            final Trader trader = node.getTraderByIDOrNull(traderID);
            if (trader != null)
            {
                return trader;
            }
        }
        return null;
    }

    public GenericEnemy getEnemyByID(final String enemyID)
    {
        for (final Node node : getAllNodes())
        {
            final GenericEnemy enemy = node.getEnemyByIDOrNull(enemyID);
            if (enemy != null)
            {
                return enemy;
            }
        }
        return null;
    }
}
