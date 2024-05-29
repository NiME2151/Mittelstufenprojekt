import {Inventory} from "./Inventory";

/**
 * @class Class representing the player.
 */
export class Character {
  /**
   * @description The character's current health points.
   */
  private _healthPoints: number;

  /**
   * @description The character's maximum health points.
   */
  private _maxHealthPoints: number;

  /**
   * @description The character's strength attribute.
   */
  private _strength: number;

  /**
   * @description The character's luck attribute.
   */
  private _luck: number;

  /**
   * @description The character's charisma attribute.
   */
  private _charisma: number;

  /**
   * @description The amount of money the character has.
   */
  private _money: number;

  /**
   * @description The character's inventory.
   */
  private _inventory: Inventory;

  /**
   * @description Creates a new Character instance.
   * @param {number} healthPoints The character's starting health points.
   * @param {number} maxHealthPoints The character's maximum health points.
   * @param {number} strength The character's strength attribute.
   * @param {number} luck The character's luck attribute.
   * @param {number} charisma The character's charisma attribute.
   * @param {number} money The character's starting money.
   * @param {Inventory} inventory The character's inventory.
   */
  constructor(
    healthPoints: number,
    maxHealthPoints: number,
    strength: number,
    luck: number,
    charisma: number,
    money: number,
    inventory: Inventory
  ) {
    this._healthPoints = healthPoints;
    this._maxHealthPoints = maxHealthPoints;
    this._strength = strength;
    this._luck = luck;
    this._charisma = charisma;
    this._money = money;
    this._inventory = inventory;
  }

  /**
   * @description Gets the character's current health points.
   * @returns The character's current health points.
   */
  get healthPoints(): number {
    return this._healthPoints;
  }

  /**
   * @description Sets the character's current health points.
   * @param value The new health points for the character.
   */
  set healthPoints(value: number) {
    this._healthPoints = value;
  }

  /**
   * @description Gets the character's maximum health points.
   * @returns The character's maximum health points.
   */
  get maxHealthPoints(): number {
    return this._maxHealthPoints;
  }

  /**
   * @description Sets the character's maximum health points.
   * @param value The new maximum health points for the character.
   */
  set maxHealthPoints(value: number) {
    this._maxHealthPoints = value;
  }

  /**
   * @description Gets the character's strength attribute.
   * @returns The character's strength attribute.
   */
  get strength(): number {
    return this._strength;
  }

  /**
   * @description Sets the character's strength attribute.
   * @param value The new strength value for the character.
   */
  set strength(value: number) {
    this._strength = value;
  }

  /**
   * @description Gets the character's luck attribute.
   * @returns The character's luck attribute.
   */
  get luck(): number {
    return this._luck;
  }

  /**
   * @description Sets the character's luck attribute.
   * @param value The new luck value for the character.
   */
  set luck(value: number) {
    this._luck = value;
  }

  /**
   * @description Gets the character's charisma attribute.
   * @returns The character's charisma attribute.
   */
  get charisma(): number {
    return this._charisma;
  }

  /**
   * @description Sets the character's charisma attribute.
   * @param value The new charisma value for the character.
   */
  set charisma(value: number) {
    this._charisma = value;
  }

  /**
   * @description Gets the character's current amount of money.
   * @returns The character's current amount of money.
   */
  get money(): number {
    return this._money;
  }

  /**
   * @description Sets the character's charisma attribute.
   * @param value The new charisma value for the character.
   */
  set money(value: number) {
    this._money = value;
  }

  /**
   * @description Gets the character's charisma attribute.
   * @returns The character's charisma attribute.
   */
  get inventory(): Inventory {
    return this._inventory;
  }

  /**
   * @description Sets the character's charisma attribute.
   * @param value The new charisma value for the character.
   */
  set inventory(value: Inventory) {
    this._inventory = value;
  }
}
