import {InventoryAction} from "../enums/apiActions/InventoryAction";
import {RequestMethod} from "../enums/RequestMethod";
import {GenericItem} from "../models/items/GenericItem";
import {EntityInventory} from "../types/EntityInventory";
import {ItemType} from "../enums/ItemType";

export class CommonInventoryApiService {

  /**
   * @description The api endpoint of the entity for which the inventory is to be called.
   */
  private apiEndpoint: EntityInventory

  /**
   * @description The constructor of the CommonInventoryApiService.
   * @constructor
   * @param {EntityInventory} apiEndpoint which entity inventory is to be called
   */
  constructor(apiEndpoint: EntityInventory) {
    this.apiEndpoint = apiEndpoint;
  }

  /**
   * @description Calls the backend endpoint to get the items inside the entity's inventory.
   * @returns {Promise<GenericItem[]>} the items inside the entity's inventory
   */
  public async getAllItems(): Promise<GenericItem[]> {
    return await fetch(this.apiEndpoint + InventoryAction.GET_ITEMS, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: GenericItem[]) => {
      return response;
    })
  }

  /**
   * @description Calls the backend endpoint to add an item to the entity's inventory.
   * @param {GenericItem} item the item which is to be added to the entity's inventory
   * @param {number} amount the amount of the item to add
   * @returns {Promise<number>} http status code
   */
  public async addItem(item: GenericItem, amount: number): Promise<number> {
    return await fetch(this.apiEndpoint + InventoryAction.ADD_ITEM, {
      method: RequestMethod.POST,
      body: JSON.stringify({item, amount}),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  /**
   * @description Calls the backend endpoint to remove an item to the entity's inventory.
   * @param {GenericItem} item the item which is to be removed from the entity's inventory
   * @param {number} amount the amount of the item to remove
   * @returns {Promise<number>} http status code
   */
  public async removeItem(item: GenericItem, amount: number): Promise<number> {
    return await fetch(this.apiEndpoint + InventoryAction.REMOVE_ITEM, {
      method: RequestMethod.DELETE,
      body: JSON.stringify({item, amount}),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  /**
   * @description Calls the backend endpoint to get the size of the entity's inventory.
   * @returns {Promise<number>} size of the entity's inventory
   */
  public async getSize(): Promise<number> {
    return await fetch(this.apiEndpoint + InventoryAction.INVENTORY_SIZE, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: number) => {
      return response;
    })
  }

  /**
   * @description Calls the backend endpoint to set the size of the entity's inventory.
   * @param {number} size the size to which the entity's inventory is to be set
   * @returns {Promise<number>} http status code
   */
  public async setSize(size: number): Promise<number> {
    return await fetch(this.apiEndpoint + InventoryAction.INVENTORY_SIZE, {
      method: RequestMethod.POST,
      body: JSON.stringify(size),
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  /**
   * @description Calls the backend endpoint to check if the entity's inventory is not full.
   * @returns {Promise<boolean>} returns true when the entity's inventory is not full otherwise false is returned
   */
  public async isNotFull(): Promise<boolean> {
    return await fetch(this.apiEndpoint + InventoryAction.IS_NOT_FULL, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: boolean) => {
      return response;
    })
  }

  /**
   * @description Calls the backend endpoint to get all items of a given item type.
   * @param {ItemType} itemType the type of item which all items of are to be fetched
   * @returns {Promise<GenericItem[]>} the items of the given type
   */
  public async getItemsOfType(itemType: ItemType): Promise<GenericItem[]> {
    return await fetch(this.apiEndpoint + InventoryAction.GET_ITEMS_OF_TYPE, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: GenericItem[]) => {
      return response;
    })
  }

  /**
   * @description Calls the backend endpoint to get the amount of empty slots in the entity's inventory.
   * @returns {Promise<number>} the amount of empty slots in the entity's inventory
   */
  public async getEmptySlots(): Promise<number> {
    return await fetch(this.apiEndpoint + InventoryAction.GET_EMPTY_SLOTS, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: number) => {
      return response;
    })
  }

  /**
   * @description Calls the backend endpoint to check whether an item is already in the entity's inventory or not.
   * @param {GenericItem} item the item which is to be checked if already in the entity's inventory
   * @returns {Promise<boolean>} returns true when the provided item is already in the entity's inventory otherwise false is returned
   */
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
