import {Inventory} from "./Inventory";

export interface Character {
  healthPoints: number
  maxHealthPoints: number
  strength: number
  luck: number
  charisma: number
  money: number
  inventory: Inventory
}
