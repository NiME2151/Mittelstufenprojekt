package de.szut.msp_backend;

import de.szut.msp_backend.models.character.Character;
import de.szut.msp_backend.models.inventory.Inventory;
import de.szut.msp_backend.models.item.Consumable;
import de.szut.msp_backend.models.item.Rarity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MspBackendApplication {

    private static final Inventory inventory = new Inventory(10);
    public static Character player = new Character(
            100,
            100,
            5,
            5,
            5,
            50,
            inventory
    );

    public static void main(String[] args) {
        SpringApplication.run(MspBackendApplication.class, args);
        Consumable item = new Consumable(1, "Apple", "an apple", 100, Rarity.COMMON, 10);
        inventory.addItem(item, 64);
    }

}
