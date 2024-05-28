package de.szut.msp_backend.controller;

import de.szut.msp_backend.models.enemy.GenericEnemy;
import de.szut.msp_backend.models.map.Map;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyControllerTest
{
    @Test
    void testGetEnemy()
    {
        ResponseEntity<?> response = new EnemyController().getEnemy("12");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        final GenericEnemy enemy = new GenericEnemy("testenemy", 20, 12);
        final String enemyID = enemy.getID();

        Map.lake.addEnemy(enemy);

        response = new EnemyController().getEnemy(enemyID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(enemy, response.getBody());
    }
}
