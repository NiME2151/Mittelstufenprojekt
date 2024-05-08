import {ApiEndpoint} from "../enums/ApiEndpoint";
import {RequestMethod} from "../enums/RequestMethod";
import {Trader} from "../models/Trader";

export abstract class TraderApiService {

  public static async getTrader(): Promise<Trader> {
    return await fetch(ApiEndpoint.TRADER, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: Trader) => {
      return response;
    })
  }
}
