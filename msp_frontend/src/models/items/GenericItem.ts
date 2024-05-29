import { ItemType } from "../../enums/ItemType"
import { Rarity } from "../../enums/Rarity"

/**
 * @interface interface representing a generic item.
 */
export interface GenericItem {
  /**
   * @description The item's internal id.
   */
  itemID: number;
  /**
   * @description The item's name.
   */
  displayName: string;
  /**
   * @description Description of the item.
   */
  description: string;
  /**
   * @description the default buy/sell value of the item.
   */
  standardValue: number;
  /**
   * @description the item's type. In this case a CONSUMABLE.
   */
  itemType: ItemType;
  /**
   * @description The item's rarity.
   */
  rarity: Rarity;
}
