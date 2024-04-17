import {ApiEndpoint} from "../enums/ApiEndpoint";
import {RequestMethod} from "../enums/RequestMethod";
import {CharacterAction} from "../enums/CharacterAction";
import {Consumable} from "../models/Consumable";
import {GenericItem} from "../models/GenericItem";
import {Inventory} from "../models/Inventory";
import {InventoryAction} from "../enums/InventoryAction";
import {Character} from "../models/Character";

class CharacterApiService {

  public static async getCharacter(): Promise<Character> {
    return await fetch(ApiEndpoint.PLAYER, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: Character) => {
      return response;
    })
  }

  public static async addMoney(amount: number): Promise<number> {
    return await fetch(ApiEndpoint.PLAYER + CharacterAction.ADD_MONEY, {
      method: RequestMethod.POST,
      body: JSON.stringify(amount)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async removeMoney(amount: number): Promise<number> {
    return await fetch(ApiEndpoint.PLAYER + CharacterAction.REMOVE_MONEY, {
      method: RequestMethod.POST,
      body: JSON.stringify(amount)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async eat(consumable: Consumable): Promise<number> {
    return await fetch(ApiEndpoint.PLAYER + CharacterAction.EAT, {
      method: RequestMethod.POST,
      body: JSON.stringify(consumable)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async buyItemFromTrader(item: GenericItem, price: number): Promise<number> {
    return await fetch(ApiEndpoint.PLAYER + CharacterAction.BUY_ITEM_FROM_TRADER, {
      method: RequestMethod.POST,
      body: JSON.stringify({item, price})
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async sellItemToTrader(item: GenericItem, price: number): Promise<number> {
    return await fetch(ApiEndpoint.PLAYER + CharacterAction.SELL_ITEM_FROM_TRADER, {
      method: RequestMethod.POST,
      body: JSON.stringify({item, price})
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async addItemToInventory(item: GenericItem): Promise<number> {
    return await fetch(ApiEndpoint.INVENTORY + InventoryAction.ADD, {
      method: RequestMethod.POST,
      body: JSON.stringify(item)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async removeItemFromInventory(item: GenericItem): Promise<number> {
    return await fetch(ApiEndpoint.INVENTORY + InventoryAction.REMOVE, {
      method: RequestMethod.POST,
      body: JSON.stringify(item)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async getInventory(): Promise<Inventory> {
    return await fetch(ApiEndpoint.INVENTORY, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: Inventory) => {
      return response;
    })
  }
}
