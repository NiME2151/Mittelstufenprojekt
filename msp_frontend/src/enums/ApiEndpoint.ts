/**
 * @description Enum holding the top level backend endpoints.
 */
export enum ApiEndpoint {
  API = "http://localhost:8080/api",
  CHARACTER = API + "/character",
  CHARACTER_INVENTORY = CHARACTER + "/inventory",
  CHARACTER_TRADE_INVENTORY = CHARACTER + "/trade_inventory",
  TRADER = API + "/trader",
  TRADER_INVENTORY = TRADER + "/inventory",
  MAP = API + "/map"
}
