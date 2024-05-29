import {GenericItem} from "./GenericItem";
import {ItemType} from "../../enums/ItemType";
import {Rarity} from "../../enums/Rarity";

/**
 * @class Class representing a weapon item, implementing the GenericItem interface.
 */
export class Weapon implements GenericItem {
  /**
   * @description Description of the item.
   */
  private _description: string;
  /**
   * @description The item's name.
   */
  private _displayName: string;
  /**
   * @description The item's internal id.
   */
  private _itemID: number;
  /**
   * @description the item's type. In this case a WEAPON.
   */
  private _itemType: ItemType;
  /**
   * @description The item's rarity.
   */
  private _rarity: Rarity;
  /**
   * @description the default buy/sell value of the item.
   */
  private _standardValue: number;
  /**
   * @description the damage dealt by the weapon item.
   */
  private _damage: number;

  /**
   * @description Creates a new Weapon instance.
   *
   * @param {number} damage The base damage dealt by the weapon.
   * @param {string} description A textual description of the weapon.
   * @param {string} displayName The name displayed in the UI for the weapon.
   * @param {number} itemID A unique identifier for the weapon item.
   * @param {ItemType} itemType The type of item the weapon is categorized as (e.g., sword, axe, mace).
   * @param {Rarity} rarity The rarity tier of the weapon (e.g., common, uncommon, rare, epic, legendary).
   * @param {number} standardValue The base value of the weapon used for trading or selling.
   */
  constructor(
    damage: number,
    description: string,
    displayName: string,
    itemID: number,
    itemType: ItemType,
    rarity: Rarity,
    standardValue: number
  ) {
    this._damage = damage;
    this._description = description;
    this._displayName = displayName;
    this._itemID = itemID;
    this._itemType = itemType;
    this._rarity = rarity;
    this._standardValue = standardValue;
  }


  /**
   * @description Getter for the damage attribute.
   * @returns {string} damage dealt by the weapon
   */
  get damage(): number {
    return this._damage;
  }

  /**
   * @description Setter for the damage attribute.
   * @param {number} value how much damage the weapon does
   */
  set damage(value: number) {
    this._damage = value;
  }

  /**
   * @description Getter for the description attribute.
   * @returns {string} description of the instance
   */
  get description(): string {
    return this._description;
  }

  /**
   * @description Setter for the healthGain attribute.
   * @param {string} value the description of the item to be set
   */
  set description(value: string) {
    this._description = value;
  }

  /**
   * @description Getter for the healthGain attribute.
   * @returns {string} display name of the instance
   */
  get displayName(): string {
    return this._displayName;
  }

  /**
   * @description Setter for the healthGain attribute.
   * @param {string} value the name of the item to be set
   */
  set displayName(value: string) {
    this._displayName = value;
  }

  /**
   * @description Getter for the itemID attribute.
   * @returns {number} the item's id
   */
  get itemID(): number {
    return this._itemID;
  }

  /**
   * @description Setter for the itemID attribute.
   * @param {number} value the itemID of the item to be set
   */
  set itemID(value: number) {
    this._itemID = value;
  }

  /**
   * @description Getter for the itemType attribute.
   * @returns {ItemType} the item's type
   */
  get itemType(): ItemType {
    return this._itemType;
  }

  /**
   * @description Setter for the itemType attribute.
   * @param {ItemType} value the item type of the item to be set
   */
  set itemType(value: ItemType) {
    this._itemType = value;
  }

  /**
   * @description Getter for the rarity attribute.
   * @returns {Rarity} the item's rarity
   */
  get rarity(): Rarity {
    return this._rarity;
  }

  /**
   * @description Setter for the rarity attribute.
   * @param {Rarity} value the rarity of the item to be set
   */
  set rarity(value: Rarity) {
    this._rarity = value;
  }

  /**
   * @description Getter for the standardValue attribute.
   * @returns {number} the item's base buy/sell value
   */
  get standardValue(): number {
    return this._standardValue;
  }

  /**
   * @description Setter for the standardValue attribute.
   * @param {number} value the base buy/sell value of the item to be set
   */
  set standardValue(value: number) {
    this._standardValue = value;
  }
}
