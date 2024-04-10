class Character {
  private _healthPoints: number;
  private _maxHealthPoints: number;
  private _strength: number;
  private _luck: number;
  private _charisma: number;
  private _gold: number;
  private inventory: Inventory;

  constructor(healthPoints: number, maxHealthPoints: number, strength: number, luck: number, charisma: number, gold: number, inventory: Inventory) {
    this._healthPoints = healthPoints;
    this._maxHealthPoints = maxHealthPoints;
    this._strength = strength;
    this._luck = luck;
    this._charisma = charisma;
    this._gold = gold;
    this.inventory = inventory;
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

  get gold(): number {
    return this._gold;
  }

  set gold(value: number) {
    this._gold = value;
  }

  public eat(consumable: Consumable) {
    if (this._maxHealthPoints > this._healthPoints + consumable.healthGain) {
      this._healthPoints = this._healthPoints + consumable.healthGain;
    }
  }

  public addItemToInventory(item: GenericItem, amount: number) {
    this.inventory.appendItem(item, amount);
  }

  public removeItemInInventory(item: GenericItem, amount: number) {
    this.inventory.removeItem(item, amount);
  }
}
