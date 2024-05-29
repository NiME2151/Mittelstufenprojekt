import { GenericItem } from "./items/GenericItem";

/**
 * @description Class representing an inventory that holds items with a maximum capacity.
 */
export class Inventory {
  /**
   * @description A map of generic items to their corresponding amounts in the inventory.
   */
  private _items: Map<GenericItem, number>;

  /**
   * @description The maximum number of items that the inventory can hold.
   */
  private _maxSize: number;

  /**
   * @description Creates a new Inventory instance.
   * @param items A map representing the initial items in the inventory and their amount.
   * @param maxSize The maximum capacity of the inventory.
   */
  constructor(items: Map<GenericItem, number>, maxSize: number) {
    this._items = items;
    this._maxSize = maxSize;
  }

  /**
   * @description Getter for the internal items map.
   * @returns the inventory's items and their amount.
   */
  get items(): Map<GenericItem, number> {
    return this._items;
  }

  /**
   * @description Setter for the internal items map.
   * @param value A map representing the new state of the inventory items and their amount.
   */
  set items(value: Map<GenericItem, number>) {
    this._items = value;
  }

  /**
   * @description Getter for the maximum capacity of the inventory.
   * @returns The maximum number of items that the inventory can hold.
   */
  get maxSize(): number {
    return this._maxSize;
  }

  /**
   * @description Setter for the maximum capacity of the inventory.
   * @param value The new maximum capacity of the inventory.
   */
  set maxSize(value: number) {
    this._maxSize = value;
  }
}
