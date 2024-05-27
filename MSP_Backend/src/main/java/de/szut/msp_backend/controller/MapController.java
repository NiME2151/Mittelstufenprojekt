package de.szut.msp_backend.controller;

import de.szut.msp_backend.events.ChangeLocationGameAction;
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
@RequestMapping("/api/map")
@CrossOrigin("*")
public class MapController
{
  @GetMapping("/current_node")
  public ResponseEntity<Node> getPlayerNode()
  {
      Node playerLocation = GAME.getMap().getPlayerLocation();
      return ResponseEntity.status(HttpStatus.OK).body(playerLocation);
  }
  
  @PostMapping("/current_node")
  public ResponseEntity<?> changeCurrentNode(@RequestBody String newNodeId)
  {
      ChangeLocationGameAction changeLocationGameAction = new ChangeLocationGameAction(newNodeId);
      if (changeLocationGameAction.doAction(1) == 0){
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok().build();
  }

  @GetMapping("/node/{nodeId}")
  public ResponseEntity<Node> getNode(@PathVariable String nodeId)
  {
      final Node node = Map.getNodeById(nodeId);
      return ResponseEntity.status(HttpStatus.OK).body(node);
  }

  @GetMapping("/nodes")
  public ResponseEntity<List<Node>> getAllNodes()
  {
    final List<Node> nodes = Map.getAllNodes();
    return ResponseEntity.ok(nodes);
  }

  @GetMapping("/node/neighbours")
  public ResponseEntity<List<Node>> getNeighbours()
  {
    final List<Node> nodes = Map.getAllNodes();
    if (nodes.isEmpty())
    {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(nodes);
  }
}
