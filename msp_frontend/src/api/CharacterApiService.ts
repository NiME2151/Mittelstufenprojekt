import {ApiEndpoint} from "../enums/ApiEndpoint";
import {RequestMethod} from "../enums/RequestMethod";
import {CharacterAction} from "../enums/apiActions/CharacterAction";
import {Consumable} from "../models/items/Consumable";
import {GenericItem} from "../models/items/GenericItem";
import {Inventory} from "../models/Inventory";
import {InventoryAction} from "../enums/apiActions/InventoryAction";
import {Character} from "../models/Character";
import {TradeItem} from "../models/TradeItem";

export abstract class CharacterApiService {

  /**
   * @description Fetches the player character object from the backend.
   * @return Character
   */
  public static async getCharacter(): Promise<Character> {
    return await fetch(ApiEndpoint.CHARACTER, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: Character) => {
      return response;
    })
  }

  /**
   * @description Calls the backend endpoint to add money to the player's purse.
   * @param {number} amount money to be added to player's purse
   * @returns {Promise<number>} http status code
   */
  public static async addMoney(amount: number): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER + CharacterAction.ADD_MONEY, {
      method: RequestMethod.POST,
      body: JSON.stringify(amount)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  /**
   * @description Calls the backend endpoint to remove money from the player's purse.
   * @param {number} amount money to be removed from the player's purse
   * @returns {Promise<number>} http status code
   */
  public static async removeMoney(amount: number): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER + CharacterAction.REMOVE_MONEY, {
      method: RequestMethod.POST,
      body: JSON.stringify(amount)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  /**
   * @description Calls the backend endpoint to eat a consumable item.
   * @param {Consumable} consumable the consumable item which is to be eaten
   * @returns {Promise<number>} http status code
   */
  public static async eat(consumable: Consumable): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER + CharacterAction.EAT, {
      method: RequestMethod.POST,
      body: JSON.stringify(consumable)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  /**
   * @description Calls the backend endpoint to buy an item from a trader.
   * @param {number} itemId the item's id which is to be bought
   * @param {number} price the item's price
   * @param {number} traderId the id of the trader with whom the player trades with
   * @returns {Promise<number>} http status code
   */
  public static async buyItemFromTrader(itemId: number, price: number, traderId: number): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER + CharacterAction.BUY_ITEM_FROM_TRADER, {
      method: RequestMethod.POST,
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({itemID: itemId, price, traderID: traderId})
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  /**
   * @description Calls the backend endpoint to sell an item to the trader.
   * @param {number} itemId the item's id which is to be sold
   * @param {number} price the item's price
   * @param {number} traderId the id of the trader with whom the player trades with
   * @returns {Promise<number>} http status code
   */
  public static async sellItemToTrader(itemId: number, price: number, traderId: number): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER + CharacterAction.SELL_ITEM_TO_TRADER, {
      method: RequestMethod.POST,
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({itemID: itemId, price, traderID: traderId})
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  /**
   * @description Calls the backend endpoint to add an item to the player's inventory.
   * @param {GenericItem} item the item which is to be added to the player's inventory
   * @returns {Promise<number>} http status code
   */
  public static async addItemToInventory(item: GenericItem): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER_INVENTORY + InventoryAction.ADD_ITEM, {
      method: RequestMethod.POST,
      body: JSON.stringify(item)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  /**
   * @description Calls the backend endpoint to remove an item from the player's inventory.
   * @param {GenericItem} item the item which is to be removed from the player's inventory
   * @returns {Promise<number>} http status code
   */
  public static async removeItemFromInventory(item: GenericItem): Promise<number> {
    return await fetch(ApiEndpoint.CHARACTER_INVENTORY + InventoryAction.REMOVE_ITEM, {
      method: RequestMethod.POST,
      body: JSON.stringify(item)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  /**
   * @description Calls the backend endpoint to get the player's inventory.
   * @returns {Promise<Inventory>} the player's inventory
   */
  public static async getInventory(): Promise<Inventory> {
    return await fetch(ApiEndpoint.CHARACTER_INVENTORY, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: Inventory) => {
      return response;
    })
  }

  /**
   * @description Calls the backend endpoint to get the player's inventory with items as TradeItems.
   * @returns {Promise<TradeItem[]>} the player's trading inventory
   */
  public static async getTradeInventory(): Promise<TradeItem[]> {
    return await fetch(ApiEndpoint.CHARACTER_TRADE_INVENTORY, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: TradeItem[]) => {
      return response;
    })
  }
}
