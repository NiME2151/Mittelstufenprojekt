import {MapNode} from "../models/MapNode";
import {useState} from "react";

export const useCurrentNode = (currentNode: MapNode): [MapNode, (newCurrentNode: MapNode) => void] => {
  return useState(currentNode);
};