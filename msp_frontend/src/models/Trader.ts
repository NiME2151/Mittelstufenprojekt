import { Inventory } from "./Inventory";

/**
 * @description Class representing a trader.
 */
export class Trader {
  /**
   * @description The name of the trader.
   */
  private _name: string;

  /**
   * @description The amount of money the trader has.
   */
  private _money: number;

  /**
   * @description The inventory of items the trader possesses.
   */
  private _inventory: Inventory;

  /**
   * @description Creates a new Trader instance.
   * @param name The name of the trader.
   * @param money The amount of money the trader starts with.
   * @param inventory The initial inventory of the trader.
   */
  constructor(name: string, money: number, inventory: Inventory) {
    this._name = name;
    this._money = money;
    this._inventory = inventory;
  }

  /**
   * @description Gets the name of the trader.
   * @returns The trader's name as a string.
   */
  get name(): string {
    return this._name;
  }

  /**
   * @description Sets the name of the trader.
   * @param value The new name for the trader.
   */
  set name(value: string) {
    this._name = value;
  }

  /**
   * @description Gets the amount of money the trader has.
   * @returns The trader's current money as a number.
   */
  get money(): number {
    return this._money;
  }

  /**
   * @description Sets the amount of money the trader has.
   * @param value The new amount of money for the trader.
   */
  set money(value: number) {
    this._money = value;
  }

  /**
   * @description Gets the inventory of the trader.
   * @returns The trader's inventory.
   */
  get inventory(): Inventory {
    return this._inventory;
  }

  /**
   * @description Sets the inventory of the trader.
   * @param value The new inventory for the trader.
   */
  set inventory(value: Inventory) {
    this._inventory = value;
  }
}
