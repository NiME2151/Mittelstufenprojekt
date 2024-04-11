import {Endpoint} from "../util/Endpoint";
import {InventoryAction} from "../util/InventoryAction";
import {RequestMethod} from "../util/RequestMethod";

class InventoryApiService {

  public static async getAllItems(): Promise<GenericItem> {
    return await fetch(Endpoint.ITEMS, {
      method: RequestMethod.GET,
    }).then(async (response) => {
      return response.json();
    }).then((response) => {
      return response;
    })
  }

  public static async addItem(item: GenericItem, amount: number): Promise<number> {
    return await fetch(Endpoint.INVENTORY + InventoryAction.ADD, {
      method: RequestMethod.POST,
      body: JSON.stringify({item, amount}),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async removeItem(item: GenericItem, amount: number): Promise<number> {
    return await fetch(Endpoint.INVENTORY + InventoryAction.REMOVE, {
      method: RequestMethod.DELETE,
      body: JSON.stringify({item, amount}),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async getSize(): Promise<number> {
    return await fetch(Endpoint.INVENTORY + InventoryAction.INV_SIZE, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: number) => {
      return response;
    })
  }

  public static async setSize(size: number): Promise<number> {
    return await fetch(Endpoint.INVENTORY + InventoryAction.INV_SIZE, {
      method: RequestMethod.POST,
      body: JSON.stringify(size),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async isNotFull(): Promise<boolean> {
    return await fetch(Endpoint.INVENTORY + InventoryAction.IS_NOT_FULL, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: boolean) => {
      return response;
    })
  }

  public static async isItemPresent(item: GenericItem): Promise<boolean> {
    return await fetch(Endpoint.INVENTORY + InventoryAction.IS_ITEM_PRESENT, {
      method: RequestMethod.GET,
      body: JSON.stringify(item)
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: boolean) => {
      return response;
    })
  }

}
