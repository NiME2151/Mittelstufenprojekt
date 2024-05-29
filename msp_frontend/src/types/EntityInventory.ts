import {ApiEndpoint} from "../enums/ApiEndpoint";

/**
 * @description which inventory endpoint is to be called.
 */
export type EntityInventory = ApiEndpoint.CHARACTER_INVENTORY | ApiEndpoint.TRADER_INVENTORY
