package de.szut.msp_backend.models.map;

import java.util.ArrayList;
import java.util.List;

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
    lake = new Node("1","Lake", "This small Lake seems to be soothing your mind and make you feel at peace. With its constant Water flowing it makes you forget all the hardships of living.", "nodeItemsLake", "nodeEnemiesLake");
    tavern = new Node("2","Tavern", "The local Tavern seems to have seen livelier days, but its still comforting, + you can get a good beer here :)", "nodeItemsTavern", "nodeEnemiesTavern");
    forest = new Node("3", "Forest", "The Forest is dense and full of life. There is some dark places you might want to avoid. A good source of Mushrooms, too.", "nodeItemsForest", "nodeEnemiesForest");
    market = new Node("4","Market", "The Market is tidy and smells really nice. All the fresh picked goods, the fresh bakery goods, all the smells you'd need to keep your mouth watering like the famous Falls of Offler.", "nodeItemsMarket", "nodeEnemiesMarket");
    arena = new Node("5", "Arena", "The floor is still stained by blood. You are unsure if you should ever be here. This place makes you feel uneasy.", "nodeItemsArena", "nodeEnemiesArena");

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
}
