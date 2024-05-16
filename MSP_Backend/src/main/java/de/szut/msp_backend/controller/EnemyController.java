package de.szut.msp_backend.controller;

import de.szut.msp_backend.Game;
import de.szut.msp_backend.models.enemy.GenericEnemy;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/enemy/")
public class EnemyController
{
  @GetMapping()
  public ResponseEntity<GenericEnemy> getEnemy(@RequestParam final String enemyID)
  {
    final GenericEnemy enemy = Game.getEnemyByID(enemyID);
    return enemy == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(enemy);
  }
}