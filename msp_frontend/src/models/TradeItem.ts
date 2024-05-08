import {GenericItem} from "./items/GenericItem";
import {ItemType} from "../enums/ItemType";
import {Rarity} from "../enums/Rarity";

export class TradeItem implements GenericItem {
  private _description: string;
  private _displayName: string;
  private _itemID: number;
  private _itemType: ItemType;
  private _rarity: Rarity;
  private _standardValue: number;
  private _marketBuyValue: number;
  private _marketSellValue: number;

  constructor(description: string, displayName: string, itemID: number, itemType: ItemType, rarity: Rarity, standardValue: number, marketBuyValue: number, marketSellValue: number) {
    this._description = description;
    this._displayName = displayName;
    this._itemID = itemID;
    this._itemType = itemType;
    this._rarity = rarity;
    this._standardValue = standardValue;
    this._marketBuyValue = marketBuyValue;
    this._marketSellValue = marketSellValue;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get displayName(): string {
    return this._displayName;
  }

  set displayName(value: string) {
    this._displayName = value;
  }

  get itemID(): number {
    return this._itemID;
  }

  set itemID(value: number) {
    this._itemID = value;
  }

  get itemType(): ItemType {
    return this._itemType;
  }

  set itemType(value: ItemType) {
    this._itemType = value;
  }

  get rarity(): Rarity {
    return this._rarity;
  }

  set rarity(value: Rarity) {
    this._rarity = value;
  }

  get standardValue(): number {
    return this._standardValue;
  }

  set standardValue(value: number) {
    this._standardValue = value;
  }

  get marketBuyValue(): number {
    return this._marketBuyValue;
  }

  set marketBuyValue(value: number) {
    this._marketBuyValue = value;
  }

  get marketSellValue(): number {
    return this._marketSellValue;
  }

  set marketSellValue(value: number) {
    this._marketSellValue = value;
  }
}
