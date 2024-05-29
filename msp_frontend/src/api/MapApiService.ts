import {RequestMethod} from "../enums/RequestMethod";
import {ApiEndpoint} from "../enums/ApiEndpoint";
import {MapAction} from "../enums/apiActions/MapAction";
import {MapNode} from "../models/MapNode";
import {Direction} from "../enums/Direction";

export abstract class MapApiService {

  /**
   * @description Calls the backend endpoint to get the MapNode where the player is currently located at.
   * @returns {Promise<MapNode>} the MapNode representing the current node where the player is located at.
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

  /**
   * @description Calls the backend endpoint to get the content of node via nodeId.
   * @param nodeId the Id of the requested node.
   * @returns {Promise<MapNode>} the requested node.
   */
  public static async getNode(nodeId: string): Promise<MapNode> {
    return await fetch(ApiEndpoint.MAP + MapAction.NODE + "/" + nodeId, {
      method: RequestMethod.GET,
    }).then(async (response: Response) => {
      return response.json();
    }).then((response: MapNode) => {
      return response;
    })
  }

  /**
   * @description Calls the backend endpoint to set the MapNode where the player is currently located at.
   * @param nodeId the Id of the new current node.
   * @returns {Promise<number>} the returned status code.
   */
  public static async setCurrentNode(nodeId: string): Promise<number> {
    return await fetch(ApiEndpoint.MAP + MapAction.CURRENT_NODE, {
      method: RequestMethod.POST,
      body: nodeId
    }).then(async (response: Response) => {
      return response.status;
    })
  }

  /**
   * @description Calls the backend endpoint to get the neighbours of a nodes via nodeId.
   * @param nodeId the Id of the node whos neighbours are requested.
   * @returns {Promise<Array<MapNode>>} an array containing the neighbouring nodes.
   */
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
  
  /**
   * @description Calls the backend endpoint to get one nodes neighbour in one direction via nodeId.
   * @param nodeId the Id of the node whos neighbour is requested.
   * @param direction the direction of the neighbour that is requested.
   * @returns {Promise<MapNode>} the neighbour node.
   */
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
