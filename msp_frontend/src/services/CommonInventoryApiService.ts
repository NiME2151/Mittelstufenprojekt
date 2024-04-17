import {InventoryAction} from "../enums/InventoryAction";
import {RequestMethod} from "../enums/RequestMethod";
import {GenericItem} from "../models/GenericItem";
import {EntityInventory} from "../types/EntityInventory";

export class CommonInventoryApiService {
  private apiEndpoint: EntityInventory
  
  constructor(apiEndpoint: EntityInventory) {
    this.apiEndpoint = apiEndpoint;
  }

  public async getAllItems(): Promise<GenericItem[]> {
    return await fetch(this.apiEndpoint + InventoryAction.ITEMS, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: GenericItem[]) => {
      return response;
    })
  }

  public async addItem(item: GenericItem, amount: number): Promise<number> {
    return await fetch(this.apiEndpoint + InventoryAction.ADD, {
      method: RequestMethod.POST,
      body: JSON.stringify({item, amount}),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public async removeItem(item: GenericItem, amount: number): Promise<number> {
    return await fetch(this.apiEndpoint + InventoryAction.REMOVE, {
      method: RequestMethod.DELETE,
      body: JSON.stringify({item, amount}),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public async getSize(): Promise<number> {
    return await fetch(this.apiEndpoint + InventoryAction.INVENTORY_SIZE, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: number) => {
      return response;
    })
  }

  public async setSize(size: number): Promise<number> {
    return await fetch(this.apiEndpoint + InventoryAction.INVENTORY_SIZE, {
      method: RequestMethod.POST,
      body: JSON.stringify(size),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public async isNotFull(): Promise<boolean> {
    return await fetch(this.apiEndpoint + InventoryAction.IS_NOT_FULL, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: boolean) => {
      return response;
    })
  }

  public async getItemsOfType(): Promise<GenericItem[]> {
    return await fetch(this.apiEndpoint + InventoryAction.GET_ITEMS_OF_TYPE, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: GenericItem[]) => {
      return response;
    })
  }

  public async getEmptySlots(): Promise<number> {
    return await fetch(this.apiEndpoint + InventoryAction.GET_EMPTY_SLOTS, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: number) => {
      return response;
    })
  }

  // Might be unnecessary in FE
  public async removeRandomItem(): Promise<number> {
    return await fetch(this.apiEndpoint + InventoryAction.REMOVE_RANDOM_ITEM, {
      method: RequestMethod.DELETE,
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public async isItemPresent(item: GenericItem): Promise<boolean> {
    return await fetch(this.apiEndpoint + InventoryAction.IS_ITEM_PRESENT, {
      method: RequestMethod.GET,
      body: JSON.stringify(item)
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: boolean) => {
      return response;
    })
  }

}
