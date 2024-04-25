package de.szut.msp_backend.models.map;

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
        //TODO: If better descriptions are available: use them!
        //TODO: After the Names for the Loottables are set in Stone update the given names here to utilize them!
        lake = new Node("temporary lake description", "nodeItemsLake", "nodeEnemiesLake");
        tavern = new Node("temporary tavern description", "nodeItemsTavern", "nodeEnemiesTavern");
        forest = new Node("temporary forest description", "nodeItemsForest", "nodeEnemiesForest");
        market = new Node("temporary market description", "nodeItemsMarket", "nodeEnemiesMarket");
        arena = new Node("temporary arena description", "nodeItemsArena", "nodeEnemiesArena");

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

    public Node getPlayerLocation()
    {
        return this.playerLocation;
    }

    public void changePlayerLocation(Node location)
    {
        this.playerLocation = location;
    }
}
