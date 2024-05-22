package de.szut.msp_backend.controller;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.events.ChangeLocationGameAction;
import de.szut.msp_backend.models.map.Direction;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.map.Node;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.szut.msp_backend.MspBackendApplication.GAME;

@AllArgsConstructor
@RestController
@RequestMapping("/api/map/")
@CrossOrigin("*")
public class MapController
{
  @GetMapping("/current_node")
  public ResponseEntity<Node> getPlayerNode()
  {
    final Node playerLocation = GAME.getMap().getPlayerLocation();
    return ResponseEntity.status(HttpStatus.OK).body(playerLocation);
  }

  @GetMapping("/nodes")
  public ResponseEntity<List<Node>> getAllNodes()
  {
    final List<Node> nodes = GAME.getMap().getAllNodes();
    return ResponseEntity.ok(nodes);
  }

  @GetMapping("/node/neighbours")
  public ResponseEntity<java.util.Map<Direction, Node>> getNeighbours(@RequestParam final String nodeIDToGetNeighboursFrom)
  {
    final List<Node> nodes = GAME.getMap().getAllNodes();
    for (Node node : nodes)
    {
      if (node.getNodeID().equals(nodeIDToGetNeighboursFrom))
      {
        return ResponseEntity.ok(node.getNeighbours());
      }
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/node/neighbour")
  public ResponseEntity<Node> getNeighbour(@RequestParam final String nodeIDToGetNeighbourFrom, @RequestParam final Direction direction)
  {
    final List<Node> nodes = GAME.getMap().getAllNodes();
    for (Node node : nodes)
    {
      if (node.getNodeID().equals(nodeIDToGetNeighbourFrom))
      {
        final Node retNode = node.getNeighbour(direction);
        return retNode == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(retNode);
      }
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/node")
  public ResponseEntity<Node> getNode(@RequestParam final String nodeID)
  {
    final List<Node> nodes = GAME.getMap().getAllNodes();
    for (Node node : nodes)
    {
      if (node.getNodeID().equals(nodeID))
      {
        return ResponseEntity.ok(node);
      }
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/current_node")
  public ResponseEntity<?> changeNode(@RequestParam final String nodeIDOfNodeToChangePlayerLocationTo)
  {
    final List<Node> nodes = GAME.getMap().getAllNodes();
    for (Node node : nodes)
    {
      if (node.getNodeID().equals(nodeIDOfNodeToChangePlayerLocationTo))
      {
        final Direction whereDoIWantToGo = GAME.getMap().getDirectionOfGivenNeighbour(node);
        final ChangeLocationGameAction changeLocation = new ChangeLocationGameAction(whereDoIWantToGo);
        Game.getInstance().parseGameAction(changeLocation);
        return ResponseEntity.ok().build();
      }
    }
    return ResponseEntity.notFound().build();
  }
}
