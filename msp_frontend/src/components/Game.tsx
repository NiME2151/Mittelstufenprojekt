import React, {JSX, useEffect, useState} from "react";
import {NavigationMenu} from "./NavigationMenu";
import {InventoryComp} from "./InventoryComp";
import {Node} from "./Node";
import {MapNode} from "../models/MapNode";
import {Direction} from "../enums/Direction";
import {fetchInitialNode} from "../hooks/fetchInitialNode";

export default function Game(): JSX.Element {
 
  const [isInventoryOpen, setIsInventoryOpen] = useState<boolean>(false);
  const [currentNode, setCurrentNode] = useState<MapNode>(new MapNode("id", "lake","lake", [], new Map<Direction, MapNode>(), "jk", "hjk"));
  
  useEffect(() => {
    fetchInitialNode().then( node => {
      setCurrentNode(node)
    })
  },[])
  
  return (
    <>
      <NavigationMenu setIsInventoryOpen={setIsInventoryOpen} />
      <InventoryComp isOpen={isInventoryOpen} setIsOpen={setIsInventoryOpen} />
      <Node currentNode={currentNode} setCurrentNode={setCurrentNode}/>
    </>
  )
}
