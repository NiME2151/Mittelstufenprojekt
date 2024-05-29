import {ApiEndpoint} from "../enums/ApiEndpoint";
import {RequestMethod} from "../enums/RequestMethod";
import {Trader} from "../models/Trader";
import {TradeAction} from "../enums/apiActions/TradeAction";
import {TradeItem} from "../models/TradeItem";

export abstract class TraderApiService {

  /**
   * @description Calls the backend endpoint to get the trader with the provided id.
   * @param {number} traderId the id of the trader with whom the player trades with
   * @returns {Promise<Trader>} the trader object with the given trader id
   */
  public static async getTrader(traderId: number): Promise<Trader> {
    return await fetch(ApiEndpoint.TRADER + "?traderID=" + traderId, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: Trader) => {
      return response;
    })
  }

  /**
   * @description Calls the backend endpoint to the trader's trade items.
   * @param {number} traderId the id of the trader with whom the player trades with
   * @returns {Promise<TradeItem[]>} the trade items of the given trader
   */
  public static async getTradeItems(traderId: number): Promise<TradeItem[]> {
    return await fetch(ApiEndpoint.TRADER + TradeAction.GET_MARKET_ITEMS + "?traderID=" + traderId, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: TradeItem[]) => {
      return response;
    })
  }
}
