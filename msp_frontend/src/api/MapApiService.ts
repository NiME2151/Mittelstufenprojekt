import {RequestMethod} from "../enums/RequestMethod";
import {ApiEndpoint} from "../enums/ApiEndpoint";
import {MapAction} from "../enums/apiActions/MapAction";
import {MapNode} from "../models/MapNode";
import {Direction} from "../enums/Direction";

export abstract class MapApiService {

  /**
   * @description Calls the backend endpoint to get the MapNode where the player is currently located at.
   * @returns {Promise<MapNode>} the MapNode representing the current node where the player is located at
   */
  public static async getCurrentNode(): Promise<MapNode> {
    return await fetch(ApiEndpoint.MAP + MapAction.CURRENT_NODE, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: MapNode) => {
      return response;
    })
  }

  public static async getNode(nodeId: string): Promise<MapNode> {
    return await fetch(ApiEndpoint.MAP + MapAction.NODE + "/" + nodeId, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: MapNode) => {
      return response;
    })
  }

  public static async setCurrentNode(nodeId: string): Promise<number> {
    return await fetch(ApiEndpoint.MAP + MapAction.CURRENT_NODE, {
      method: RequestMethod.POST,
      body: nodeId
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  public static async getNeighbourNodes(nodeId: string): Promise<Array<MapNode>> {
    return await fetch(ApiEndpoint.MAP + MapAction.NEIGHBOURS, {
      method: RequestMethod.GET,
      body: nodeId
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: Array<MapNode>) => {
      return response;
    })
  }

  public static async getNeighbour(nodeId: string, direction: Direction): Promise<MapNode> {
    return await fetch(ApiEndpoint.MAP + MapAction.NEIGHBOUR, {
      method: RequestMethod.GET,
      body: JSON.stringify({nodeId, direction})
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: MapNode) => {
      return response;
    })
  }
}
