package de.szut.msp_backend.models.map;

import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.tradesystem.Trader;

import java.util.*;

public class Map
{
  public static Node apartments;
  public static Node square;
  public static Node streets;
  public static Node market;
  public static Node Club;

  private Node playerLocation;

  public Map()
  {
    apartments = new Node("1","Apartments", "The apartments of Eastside, New Eden City", "nodeItemsApartments", "nodeEnemiesApartments");
    square = new Node("2","Square", "Some square of New Eden City.", "nodeItemsSquare", "nodeEnemiesSquare");
    streets = new Node("3", "Streets", "The beautiful streets of New Eden City.", "nodeItemsStreets", "nodeEnemiesStreets");
    market = new Node("4","Market", "A small and nice market.", "nodeItemsMarket", "nodeEnemiesMarket");
    Club = new Node("5", "Club", "A famous night club on Westend of New Eden City.", "nodeItemsClub", "nodeEnemiesClub");

    playerLocation = square;
    setNeighbours();
  }

  private void setNeighbours()
  {
    apartments.addNeighbour(Direction.EAST, "3");

    streets.addNeighbour(Direction.WEST, "1");
    streets.addNeighbour(Direction.SOUTH, "4");

    market.addNeighbour(Direction.NORTH, "3");
    market.addNeighbour(Direction.SOUTH, "5");
    market.addNeighbour(Direction.WEST, "2");

    square.addNeighbour(Direction.EAST, "4");
    square.addNeighbour(Direction.SOUTH, "5");

    Club.addNeighbour(Direction.NORTH, "4");
  }

  public static List<Node> getAllNodes()
  {
    final List<Node> nodes = new ArrayList<>();
    nodes.add(apartments);
    nodes.add(Club);
    nodes.add(streets);
    nodes.add(market);
    nodes.add(square);
    return nodes;
  }
  
  public static Node getNodeById(String nodeId){
      List<Node> nodes = getAllNodes();
      System.out.println("nodes");
      System.out.println(nodes);
      Optional<Node> node = nodes.stream().filter(n -> n.getNodeId().equals(nodeId)).findFirst();
      
      return node.orElse(null);
  }

  public Node getPlayerLocation()
  {
    return this.playerLocation;
  }
  
    public int changePlayerLocation(Node location)
    {
        if (playerLocation == null || playerLocation.getNeighbourMap().containsValue(location.getNodeId()))
        {
            this.playerLocation = location;
            return 1;
        }
        return 0;
    }
    
    public Trader getTraderById(final int traderID)
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

    public void update(final int clicks)
    {
      for (Node node : getAllNodes())
      {
        node.update(clicks);
      }
    }

    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      for (Node node : getAllNodes())
      {
        builder.append(node.toString());
      }
      builder.append("\n\nCurrent Node:\n\n");
      if (playerLocation != null)
      {
        builder.append(playerLocation.toString());
      }
      else
      {
        builder.append("null");
      }
      return builder.toString();
    }
}
