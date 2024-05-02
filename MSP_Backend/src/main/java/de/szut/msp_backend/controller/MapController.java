package de.szut.msp_backend.controller;

import de.szut.msp_backend.models.map.Map;
import de.szut.msp_backend.models.map.Node;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/map/")
public class MapController
{
  public static Map map;

  @RequestMapping("/current")
  public ResponseEntity<Node> getPlayerNode()
  {
    final Node playerLocation = map.getPlayerLocation();
    return ResponseEntity.status(HttpStatus.OK).body(playerLocation);
  }

  @RequestMapping("/current/description")
}
