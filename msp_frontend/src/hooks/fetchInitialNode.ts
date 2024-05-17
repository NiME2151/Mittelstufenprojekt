import {MapNode} from "../models/MapNode";
import {MapApiService} from "../api/MapApiService";

export const fetchInitialNode = async (): Promise<MapNode> => {
  return await MapApiService.getCurrentNode().then();
}