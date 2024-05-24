import React, {JSX, useCallback, useEffect, useState} from "react";
import {NavigationMenu} from "./NavigationMenu";
import {InventoryComp} from "./InventoryComp";
import {Node} from "./Node";
import {MapNode} from "../models/MapNode";
import {Direction} from "../enums/Direction";
import {MapApiService} from "../api/MapApiService";


export default function Game(): JSX.Element {
  
  const initialNode: MapNode = new MapNode(
      "id",
      "lake",
      "description",
      [],
      new Map<Direction, string>([]),
      "loot",
      "enemyLoot"
  )
  const [isInventoryOpen, setIsInventoryOpen] = useState<boolean>(false);
  const [currentNode, setCurrentNode] = useState<MapNode>(initialNode);

  const fetchInitialNode = useCallback( async (): Promise<MapNode> => {
    return await MapApiService.getCurrentNode().then()}, []);

  useEffect(() => {
    fetchInitialNode().then(mapNode => {
      setCurrentNode(mapNode)
    })
  },[fetchInitialNode])
  
  return (
    <>
      <NavigationMenu setIsInventoryOpen={setIsInventoryOpen} />
      <InventoryComp isOpen={isInventoryOpen} setIsOpen={setIsInventoryOpen} />
      <Node currentNode={currentNode} setCurrentNode={setCurrentNode}/>
    </>
  )
}
