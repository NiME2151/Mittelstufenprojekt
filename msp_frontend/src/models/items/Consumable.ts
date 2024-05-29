import {GenericItem} from "./GenericItem";
import {ItemType} from "../../enums/ItemType";
import {Rarity} from "../../enums/Rarity";

/**
 * @class Class representing a consumable item, implementing the GenericItem interface.
 */
export class Consumable implements GenericItem {
  /**
   * @description How much health points are gained back from consuming the item.
   */
  private _healthGain: number;
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
   * @description the item's type. In this case a CONSUMABLE.
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
   * @description The constructor (all args constructor) of the consumable item class.
   */
  constructor(
    healthGain: number,
    description: string,
    displayName: string,
    itemID: number,
    itemType: ItemType,
    rarity: Rarity,
    standardValue: number
  ) {
    this._healthGain = healthGain;
    this._description = description;
    this._displayName = displayName;
    this._itemID = itemID;
    this._itemType = itemType;
    this._rarity = rarity;
    this._standardValue = standardValue;
  }

  /**
   * @description Getter for the healthGain attribute.
   * @returns {string} health gain of the instance when consumed
   */
  get healthGain(): number {
    return this._healthGain;
  }

  /**
   * @description Setter for the healthGain attribute.
   * @param {number} value how many health points should be regained when consuming this item
   */
  set healthGain(value: number) {
    this._healthGain = value;
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
   * @returns {number} the item's default buy/sell value
   */
  get standardValue(): number {
    return this._standardValue;
  }

  /**
   * @description Setter for the standardValue attribute.
   * @param {number} value the default buy/sell value of the item to be set
   */
  set standardValue(value: number) {
    this._standardValue = value;
  }
}
