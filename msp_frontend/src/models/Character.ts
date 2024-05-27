import {Inventory} from "./Inventory";

export class Character {
  private _healthPoints: number
  private _maxHealthPoints: number
  private _strength: number
  private _luck: number
  private _charisma: number
  private _money: number
  private _inventory: Inventory
  
  constructor(healthPoints: number, maxHealthPoints: number, strength: number, luck: number, charisma: number, money: number, inventory: Inventory) {
    this._healthPoints = healthPoints;
    this._maxHealthPoints = maxHealthPoints;
    this._strength = strength;
    this._luck = luck;
    this._charisma = charisma;
    this._money = money;
    this._inventory = inventory;
  }

  get healthPoints(): number {
    return this._healthPoints;
  }

  set healthPoints(value: number) {
    this._healthPoints = value;
  }

  get maxHealthPoints(): number {
    return this._maxHealthPoints;
  }

  set maxHealthPoints(value: number) {
    this._maxHealthPoints = value;
  }

  get strength(): number {
    return this._strength;
  }

  set strength(value: number) {
    this._strength = value;
  }

  get luck(): number {
    return this._luck;
  }

  set luck(value: number) {
    this._luck = value;
  }

  get charisma(): number {
    return this._charisma;
  }

  set charisma(value: number) {
    this._charisma = value;
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
