import {ApiEndpoint} from "../enums/ApiEndpoint";
import {InventoryAction} from "../enums/InventoryAction";
import {RequestMethod} from "../enums/RequestMethod";
import {GenericItem} from "../models/GenericItem";

class InventoryApiService {

  public static async getAllItems(): Promise<GenericItem[]> {
    return await fetch(ApiEndpoint.ITEMS, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: GenericItem[]) => {
      return response;
    })
  }

  public static async addItem(item: GenericItem, amount: number): Promise<number> {
    return await fetch(ApiEndpoint.INVENTORY + InventoryAction.ADD, {
      method: RequestMethod.POST,
      body: JSON.stringify({item, amount}),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async removeItem(item: GenericItem, amount: number): Promise<number> {
    return await fetch(ApiEndpoint.INVENTORY + InventoryAction.REMOVE, {
      method: RequestMethod.DELETE,
      body: JSON.stringify({item, amount}),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async getSize(): Promise<number> {
    return await fetch(ApiEndpoint.INVENTORY + InventoryAction.INVENTORY_SIZE, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: number) => {
      return response;
    })
  }

  public static async setSize(size: number): Promise<number> {
    return await fetch(ApiEndpoint.INVENTORY + InventoryAction.INVENTORY_SIZE, {
      method: RequestMethod.POST,
      body: JSON.stringify(size),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async isNotFull(): Promise<boolean> {
    return await fetch(ApiEndpoint.INVENTORY + InventoryAction.IS_NOT_FULL, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: boolean) => {
      return response;
    })
  }

  public static async getItemsOfType(): Promise<GenericItem[]> {
    return await fetch(ApiEndpoint.INVENTORY + InventoryAction.GET_ITEMS_OF_TYPE, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: GenericItem[]) => {
      return response;
    })
  }

  public static async getEmptySlots(): Promise<number> {
    return await fetch(ApiEndpoint.INVENTORY + InventoryAction.GET_EMPTY_SLOTS, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: number) => {
      return response;
    })
  }

  public static async removeRandomItem(): Promise<number> {
    return await fetch(ApiEndpoint.INVENTORY + InventoryAction.GET_ITEMS_OF_TYPE, {
      method: RequestMethod.DELETE,
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async isItemPresent(item: GenericItem): Promise<boolean> {
    return await fetch(ApiEndpoint.INVENTORY + InventoryAction.IS_ITEM_PRESENT, {
      method: RequestMethod.GET,
      body: JSON.stringify(item)
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: boolean) => {
      return response;
    })
  }

}
