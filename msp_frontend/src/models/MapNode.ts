import { GenericItem } from "./items/GenericItem";
import { Direction } from "../enums/Direction";

/**
 * @description Class representing a node in a map.
 */
export class MapNode {

  /**
   * @description A description of the map node.
   */
  private _description: string;

  /**
   * @description An array of generic items that can be found at this map node.
   */
  private _findableItems: GenericItem[];

  /**
   * @description A map containing the neighboring map nodes for each direction.
   */
  private _neighbourMap: Map<Direction, string>;

  /**
   * @description The name of the loot table for items that can be dropped in this map node.
   */
  private _itemLootTableName: string;

  /**
   * @description The name of the loot table for entities that can be encountered in this map node.
   */
  private _entityLootTableName: string;
  private _nodeId: string;
  private _displayName: string;

  /**
   * @description Creates a new MapNode instance.
   * @param {string} nodeId - The id of the node
   * @param {string} displayName - the displayed name of the node
   * @param {string} description - A description of the map node.
   * @param {GenericItem[]} findableItems - An array of generic items that can be found at this map node.
   * @param {Map<Direction, MapNode>} neighbourMap - A map containing the neighboring map nodes for each direction.
   * @param {string} itemLootTableName - The name of the loot table for items that can be dropped in this map node.
   * @param {string} entityLootTableName - The name of the loot table for entities that can be encountered in this map node.
   */
  constructor(
    nodeId: string,
    displayName: string,
    description: string,
    findableItems: GenericItem[],
    neighbourMap: Map<Direction, string>,
    itemLootTableName: string,
    entityLootTableName: string
  ) {
    this._description = description;
    this._findableItems = findableItems;
    this._neighbourMap = neighbourMap;
    this._itemLootTableName = itemLootTableName;
    this._entityLootTableName = entityLootTableName;
    this._nodeId = nodeId;
    this._displayName = displayName;
  }
  
  get nodeId(): string {
    return this._nodeId;
  }

  set nodeId(value: string) {
    this._nodeId = value;
  }
  
  get displayName(): string {
    return this._displayName;
  }
  
  set displayName(value: string) {
    this._displayName = value;
  }
  
  /**
   * @description Gets the description of the map node.
   * @returns {string} - The description of the map node.
   */
  get description(): string {
    return this._description;
  }

  /**
   * @description Sets the description of the map node.
   * @param {string} value - The new description for the map node.
   */
  set description(value: string) {
    this._description = value;
  }

  /**
   * @description Gets the array of generic items that can be found at this map node.
   * @returns {GenericItem[]} - An array of generic items.
   */
  get findableItems(): GenericItem[] {
    return this._findableItems;
  }

  /**
   * @description Sets the array of generic items that can be found at this map node.
   * @param {GenericItem[]} value - A new array of generic items.
   */
  set findableItems(value: GenericItem[]) {
    this._findableItems = value;
  }

  /**
   * @description Gets the map containing the neighboring map nodes for each direction.
   * @returns {Map<Direction, string>} - A map containing directions and their corresponding map nodes.
   */
  get neighbourMap(): Map<Direction, string> {
    return this._neighbourMap;
  }

  /**
   * @description Sets the map containing the neighboring map nodes for each direction.
   * @param {Map<Direction, string>} value - A new map containing directions and their corresponding map nodes.
   */
  set neighbourMap(value: Map<Direction, string>) {
    this._neighbourMap = value;
  }

  /**
   * @description Gets the name of the loot table for items that can be dropped in this map node.
   * @returns {string} - The name of the item loot table.
   */
  get itemLootTableName(): string {
    return this._itemLootTableName;
  }

  /**
   * @description Sets the name of the loot table for items that can be dropped in this map node.
   * @param {string} value - The new name of the item loot table.
   */
  set itemLootTableName(value: string) {
    this._itemLootTableName = value;
  }

  /**
   * @description Gets the name of the loot table for entities that can be encountered in this map node.
   * @returns {string} - The name of the entity loot table.
   */
  get entityLootTableName(): string {
    return this._entityLootTableName;
  }

  /**
   * @description Sets the name of the loot table for entities that can be encountered in this map node.
   * @param {string} value - The new name of the entity loot table.
   */
  set entityLootTableName(value: string) {
    this._entityLootTableName = value;
  }
}
