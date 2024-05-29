
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
import {useCurrentNode} from "../hooks/useCurrentNode";

/**
 * @description Main component which renders multiple child components to create a playable game.
 */
export default function Game(): JSX.Element {
  /**
   * @description Local state determining the player's initial spawn location.
   */
  const initialNode: MapNode = new MapNode(
      "id",
      "lake",
      "description",
      [],
      new Map<Direction, string>([]),
      "loot",
      "enemyLoot"
  )

  /**
   * @description Local state determining where the player is located at.
   */
  const [currentNode, setCurrentNode] = useCurrentNode(initialNode);
  /**
   * @description Local state determining if the player's inventory is open.
   */
  const [isInventoryOpen, setIsInventoryOpen] = useState<boolean>(false);
  /**
   * @description Local state determining if a trader menu is open.
   */
  const [isTraderMenuOpen, setIsTraderMenuOpen] = useState<boolean>(false);

  /**
   * @description Const for dispatching slice functions.
   */
  const dispatch = useDispatch();

  // TODO useEffects bei Gelegenheit zusammen fassen

  /**
   * @description Fetches player object on first render and sets globally needed attributes
   */
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

  /**
   * @description Fetches initial node and awaits response from backend
   */
  const fetchInitialNode = useCallback( async (): Promise<MapNode> => {
    return await MapApiService.getCurrentNode().then()}, []);

  /**
   * @description Calls fetchInitialNode on first Render and sets local state of currentNode with result
   */
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
