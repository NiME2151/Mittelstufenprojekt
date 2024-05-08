import {Inventory} from "../models/Inventory";
import {ApiEndpoint} from "../enums/ApiEndpoint";
import {RequestMethod} from "../enums/RequestMethod";
import {GenericItem} from "../models/items/GenericItem";
import {TradeAction} from "../enums/apiActions/TradeAction";
import {TradeItem} from "../models/TradeItem";

export abstract class TradeApiService {

  public static async getTradeItems(): Promise<TradeItem[]> {
    return await fetch(ApiEndpoint.TRADER + TradeAction.GET_MARKET_ITEMS, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: TradeItem[]) => {
      return response;
    })
  }

  public static async buyItem(item: GenericItem): Promise<number> {
    return await fetch(ApiEndpoint.TRADER + TradeAction.BUY, {
      method: RequestMethod.POST,
      body: JSON.stringify(item)
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async sellItem(item: GenericItem): Promise<number> {
    return await fetch(ApiEndpoint.TRADER + TradeAction.SELL, {
      method: RequestMethod.POST,
      body: JSON.stringify(item)
    }).then(async (response: Response) => {
      return response.status;
    })
  }
}
