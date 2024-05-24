
import React, {JSX, useCallback, useEffect, useState} from "react";
import {NavigationMenu} from "./NavigationMenu";
import {InventoryComp} from "./InventoryComp";
import {GenericTrader} from "./trader/GenericTrader";
import {CharacterApiService} from "../api/CharacterApiService";
import {Character} from "../models/Character";
import {setGlobals} from "../redux/slices/globals";
import {useDispatch} from "react-redux";
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
  const [isTraderMenuOpen, setIsTraderMenuOpen] = useState<boolean>(false);

  const dispatch = useDispatch();

  useEffect(() => {
    CharacterApiService.getCharacter().then((character: Character) => {
      dispatch(setGlobals(
        {
          money: character.money,
          healthPoints: character.healthPoints,
          maxHealthPoints: character.maxHealthPoints,
          inventory: character.inventory
        }))
    })
  }, []);

  const fetchInitialNode = useCallback( async (): Promise<MapNode> => {
    return await MapApiService.getCurrentNode().then()}, []);

  useEffect(() => {
    fetchInitialNode().then(mapNode => {
      setCurrentNode(mapNode)
    })
  },[fetchInitialNode])
  
  return (
    <>
      <NavigationMenu setIsInventoryOpen={setIsInventoryOpen} setIsTraderMenuOpen={setIsTraderMenuOpen} />
      <InventoryComp isOpen={isInventoryOpen} setIsOpen={setIsInventoryOpen} />
      {/* TODO remove before final ver*/}
      <GenericTrader isOpen={isTraderMenuOpen} setIsOpen={setIsTraderMenuOpen} type={"market"} traderId={0} />
      <Node currentNode={currentNode} setCurrentNode={setCurrentNode}/>
    </>
  )
}
