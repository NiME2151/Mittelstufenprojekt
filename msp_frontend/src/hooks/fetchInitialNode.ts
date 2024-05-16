import {MapNode} from "../models/MapNode";
import {MapApiService} from "../api/MapApiService";

export const fetchInitialNode = async (): Promise<MapNode> => {
  const node: MapNode =  await MapApiService.getCurrentNode().then();
  return node;
}