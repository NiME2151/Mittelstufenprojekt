import {ApiEndpoint} from "../enums/ApiEndpoint";
import {RequestMethod} from "../enums/RequestMethod";
import {CharacterAction} from "../enums/CharacterAction";
import {Consumable} from "../models/Consumable";
import {GenericItem} from "../models/GenericItem";
import {Inventory} from "../models/Inventory";
import {InventoryAction} from "../enums/InventoryAction";
import {Character} from "../models/Character";

export abstract class CharacterApiService {

  public static async getCharacter(): Promise<Character> {
    return await fetch(ApiEndpoint.CHARACTER, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: Character) => {
      return response;
    })
  }

  public static async addMoney(amount: number): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER + CharacterAction.ADD_MONEY, {
      method: RequestMethod.POST,
      body: JSON.stringify(amount)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async removeMoney(amount: number): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER + CharacterAction.REMOVE_MONEY, {
      method: RequestMethod.POST,
      body: JSON.stringify(amount)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async eat(consumable: Consumable): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER + CharacterAction.EAT, {
      method: RequestMethod.POST,
      body: JSON.stringify(consumable)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async buyItemFromTrader(item: GenericItem, price: number): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER + CharacterAction.BUY_ITEM_FROM_TRADER, {
      method: RequestMethod.POST,
      body: JSON.stringify({item, price})
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async sellItemToTrader(item: GenericItem, price: number): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER + CharacterAction.SELL_ITEM_TO_TRADER, {
      method: RequestMethod.POST,
      body: JSON.stringify({item, price})
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async addItemToInventory(item: GenericItem): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER_INVENTORY + InventoryAction.ADD, {
      method: RequestMethod.POST,
      body: JSON.stringify(item)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async removeItemFromInventory(item: GenericItem): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER_INVENTORY + InventoryAction.REMOVE, {
      method: RequestMethod.POST,
      body: JSON.stringify(item)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async getInventory(): Promise<Inventory> {
    return await fetch(ApiEndpoint.CHARACTER_INVENTORY, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: Inventory) => {
      return response;
    })
  }
}
