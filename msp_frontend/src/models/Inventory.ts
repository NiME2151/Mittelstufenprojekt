import {GenericItem} from "./GenericItem";

export class Inventory {
  private _items: Map<GenericItem, number>
  private _maxSize: number

  constructor(items: Map<GenericItem, number>, maxSize: number) {
    this._items = items;
    this._maxSize = maxSize;
  }

  get items(): Map<GenericItem, number> {
    return this._items;
  }

  set items(value: Map<GenericItem, number>) {
    this._items = value;
  }

  get maxSize(): number {
    return this._maxSize;
  }

  set maxSize(value: number) {
    this._maxSize = value;
  }
}
