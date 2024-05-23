export class TradeItem {
  private _itemID: number;
  private _displayName: string;
  private _buyValue: number;
  private _sellValue: number;


  constructor(itemID: number, standardValue: string, buyValue: number, sellValue: number) {
    this._itemID = itemID;
    this._displayName = standardValue;
    this._buyValue = buyValue;
    this._sellValue = sellValue;
  }


  get itemID(): number {
    return this._itemID;
  }

  set itemID(value: number) {
    this._itemID = value;
  }

  get displayName(): string {
    return this._displayName;
  }

  set displayName(value: string) {
    this._displayName = value;
  }

  get buyValue(): number {
    return this._buyValue;
  }

  set buyValue(value: number) {
    this._buyValue = value;
  }

  get sellValue(): number {
    return this._sellValue;
  }

  set sellValue(value: number) {
    this._sellValue = value;
  }
}
