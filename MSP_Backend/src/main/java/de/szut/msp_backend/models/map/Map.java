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
        lake = new Node("Lake", "temporary lake description", "nodeItemsLake", "nodeEnemiesLake", "1");
        tavern = new Node("Tavern", "temporary tavern description", "nodeItemsTavern", "nodeEnemiesTavern", "2");
        forest = new Node("Forest", "temporary forest description", "nodeItemsForest", "nodeEnemiesForest","3");
        market = new Node("Market", "temporary market description", "nodeItemsMarket", "nodeEnemiesMarket", "4");
        arena = new Node("Arena", "temporary arena description", "nodeItemsArena", "nodeEnemiesArena", "5");

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
