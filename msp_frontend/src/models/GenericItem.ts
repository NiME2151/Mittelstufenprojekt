import { ItemType } from "../enums/ItemType"
import { Rarity } from "../enums/Rarity"

export interface GenericItem {
  itemID: number;
  displayName: string;
  description: string;
  standardValue: number;
  itemType: ItemType;
  rarity: Rarity;
}
