package de.szut.msp_backend.controller;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.map.Node;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapControllerTest
{
    @Test
    void testGetPlayerNode()
    {
        changeToNode(Map.lake);
        final ResponseEntity<Node> response = new MapController().getPlayerNode();
        assertEquals(Map.lake, response.getBody());
        changeToNode(Map.market);
    }

    @Test
    void testChangeCurrentNode()
    {
        changeToNode(Map.lake);
        ResponseEntity<?> response = new MapController().changeCurrentNode(Map.lake.getNodeId());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        response = new MapController().changeCurrentNode(Map.forest.getNodeId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        changeToNode(Map.market);
    }

    @Test
    void testGetNode()
    {
        final ResponseEntity<Node> response = new MapController().getNode("3");
        assertEquals(Map.forest, response.getBody());
    }

    @Test
    void testGetAllNodes()
    {
        final ResponseEntity<List<Node>> response = new MapController().getAllNodes();
        assertEquals(Map.getAllNodes(), response.getBody());
    }

    private void changeToNode(final Node node)
    {
        Class<?> klasse = Game.getInstance().getMap().getClass();
        try
        {
            Field field = klasse.getDeclaredField("playerLocation");
            field.setAccessible(true);
            field.set(Game.getInstance().getMap(), node);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
