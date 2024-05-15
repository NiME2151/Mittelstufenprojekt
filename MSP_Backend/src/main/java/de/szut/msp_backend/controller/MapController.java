package de.szut.msp_backend.controller;

import de.szut.msp_backend.models.map.Direction;
import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.map.Node;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/map/")
public class MapController
{
  public static Map map;

  @GetMapping("/current_node")
  public ResponseEntity<Node> getPlayerNode()
  {
    final Node playerLocation = map.getPlayerLocation();
    return ResponseEntity.status(HttpStatus.OK).body(playerLocation);
  }

  @GetMapping("/nodes")
  public ResponseEntity<List<Node>> getAllNodes()
  {
    final List<Node> nodes = map.getAllNodes();
    return ResponseEntity.ok(nodes);
  }

  @GetMapping("/node/neighbours")
  public ResponseEntity<java.util.Map<Direction, Node>> getNeighbours(@RequestParam final String id)
  {
    final List<Node> nodes = map.getAllNodes();
    for (Node node : nodes)
    {
      if (node.getNodeID().equals(id))
      {
        return ResponseEntity.ok(node.getNeighbours());
      }
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/node/neighbour")
  public ResponseEntity<Node> getNeighbour(@RequestParam final String id, @RequestParam final Direction direction)
  {
    final List<Node> nodes = map.getAllNodes();
    for (Node node : nodes)
    {
      if (node.getNodeID().equals(id))
      {
        final Node retNode = node.getNeighbour(direction);
        return retNode == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(retNode);
      }
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/node")
  public ResponseEntity<Node> getNode(@RequestParam final String id)
  {
    final List<Node> nodes = map.getAllNodes();
    for (Node node : nodes)
    {
      if (node.getNodeID().equals(id))
      {
        return ResponseEntity.ok(node);
      }
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/current_node")
  public ResponseEntity changeNode(@RequestParam final String id)
  {
    final List<Node> nodes = map.getAllNodes();
    for (Node node : nodes)
    {
      if (node.getNodeID().equals(id))
      {
        map.changePlayerLocation(node);
        return ResponseEntity.ok().build();
      }
    }
    return ResponseEntity.notFound().build();
  }
}
