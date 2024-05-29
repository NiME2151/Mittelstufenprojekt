/**
 * @description Class representing a trade item.
 */
export class TradeItem {
  /**
   * @description ID for the trade item.
   */
  private _itemID: number;

  /**
   * @description Name or description of the trade item.
   */
  private _displayName: string;

  /**
   * @description The base value at which the item can be bought.
   */
  private _buyValue: number;

  /**
   * @description The base value at which the item can be sold.
   */
  private _sellValue: number;


  /**
   * @description Creates a new TradeItem instance.
   * @param itemID - ID for the trade item.
   * @param displayName - Name of the trade item.
   * @param buyValue - The base value at which the item can be bought.
   * @param sellValue - The base value at which the item can be sold.
   */
  constructor(itemID: number, displayName: string, buyValue: number, sellValue: number) {
    this._itemID = itemID;
    this._displayName = displayName;
    this._buyValue = buyValue;
    this._sellValue = sellValue;
  }


  /**
   * @description Getter for the itemID property.
   * @returns The ID of the trade item.
   */
  get itemID(): number {
    return this._itemID;
  }

  /**
   * @description Setter for the itemID property.
   * @param value - The new value for the itemID.
   */
  set itemID(value: number) {
    this._itemID = value;
  }

  /**
   * @description Getter for the displayName property.
   * @returns The name or description of the trade item.
   */
  get displayName(): string {
    return this._displayName;
  }

  /**
   * @description Setter for the displayName property.
   * @param value - The new value for the displayName.
   */
  set displayName(value: string) {
    this._displayName = value;
  }

  /**
   * @description Getter for the buyValue property.
   * @returns The base value at which the item can be bought.
   */
  get buyValue(): number {
    return this._buyValue;
  }

  /**
   * @description Setter for the buyValue property.
   * @param value - The new value for the buyValue.
   */
  set buyValue(value: number) {
    this._buyValue = value;
  }

  /**
   * @description Getter for the sellValue property.
   * @returns The base value at which the item can be sold.
   */
  get sellValue(): number {
    return this._sellValue;
  }

  /**
   * @description Setter for the sellValue property.
   * @param value - The new value for the sellValue.
   */
  set sellValue(value: number) {
    this._sellValue = value;
  }
}
