/**
 * @description Enum holding the common inventory sub-endpoints.
 */
export enum InventoryAction {
  GET_ITEMS = "/items",
  ADD_ITEM = "/add",
  REMOVE_ITEM = "/remove",
  INVENTORY_SIZE = "/size",
  IS_NOT_FULL = "/is_not_full",
  IS_ITEM_PRESENT = "/is_item_present",
  GET_ITEMS_OF_TYPE = "/get_items_of_type",
  GET_EMPTY_SLOTS = "/get_empty_slots"
}
