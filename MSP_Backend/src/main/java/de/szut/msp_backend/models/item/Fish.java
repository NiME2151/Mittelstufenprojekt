package de.szut.msp_backend.models.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import static de.szut.msp_backend.models.item.ItemType.FISH;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Fish extends GenericItem
{
  public Fish(int itemId, String displayName, String description, int standardValue, Rarity rarity)
  {
    super(itemId, displayName, description, standardValue, FISH, rarity);
  }
}
