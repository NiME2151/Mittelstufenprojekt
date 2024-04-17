import {Inventory} from "./Inventory";

export class Trader {
  private _name: string;
  private _money: number;
  private _inventory: Inventory

  constructor(name: string, money: number, inventory: Inventory) {
    this._name = name;
    this._money = money;
    this._inventory = inventory;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get money(): number {
    return this._money;
  }

  set money(value: number) {
    this._money = value;
  }

  get inventory(): Inventory {
    return this._inventory;
  }

  set inventory(value: Inventory) {
    this._inventory = value;
  }
}
