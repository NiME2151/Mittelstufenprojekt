import {RequestMethod} from "../enums/RequestMethod";
import {ApiEndpoint} from "../enums/ApiEndpoint";
import {MapAction} from "../enums/apiActions/MapAction";
import {MapNode} from "../models/MapNode";

export abstract class MapApiService {

  public static async getCurrentNode(): Promise<MapNode> {
    return await fetch(ApiEndpoint.MAP + MapAction.CURRENT_NODE, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: MapNode) => {
      return response;
    })
  }
}
