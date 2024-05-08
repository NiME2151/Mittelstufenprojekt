import {GenericItem} from "./items/GenericItem";
import {Direction} from "../enums/Direction";

export class MapNode {

  private _description: string;
  private _findableItems: GenericItem[]
  private _neighbourMap: Map<Direction, MapNode>
  private _itemLootTableName: string
  private _entityLootTableName: string

  constructor(description: string, findableItems: GenericItem[], neighbourMap: Map<Direction, MapNode>, itemLootTableName: string, entityLootTableName: string) {
    this._description = description;
    this._findableItems = findableItems;
    this._neighbourMap = neighbourMap;
    this._itemLootTableName = itemLootTableName;
    this._entityLootTableName = entityLootTableName;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get findableItems(): GenericItem[] {
    return this._findableItems;
  }

  set findableItems(value: GenericItem[]) {
    this._findableItems = value;
  }

  get neighbourMap(): Map<Direction, MapNode> {
    return this._neighbourMap;
  }

  set neighbourMap(value: Map<Direction, MapNode>) {
    this._neighbourMap = value;
  }

  get itemLootTableName(): string {
    return this._itemLootTableName;
  }

  set itemLootTableName(value: string) {
    this._itemLootTableName = value;
  }

  get entityLootTableName(): string {
    return this._entityLootTableName;
  }

  set entityLootTableName(value: string) {
    this._entityLootTableName = value;
  }
}
