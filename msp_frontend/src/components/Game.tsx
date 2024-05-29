import { Box } from "@mui/material";
import React, {JSX, useEffect, useState} from "react";
import {NavigationMenu} from "./NavigationMenu";
import {InventoryComp} from "./InventoryComp";
import {GenericTrader} from "./trader/GenericTrader";
import {CharacterApiService} from "../api/CharacterApiService";
import {Character} from "../models/Character";
import {setGlobals} from "../redux/slices/globals";
import {useDispatch} from "react-redux";

/**
 * @description Main component which renders multiple child components to create a playable game.
 */
export default function Game(): JSX.Element {

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

  return (
    <>
      <NavigationMenu setIsInventoryOpen={setIsInventoryOpen} setIsTraderMenuOpen={setIsTraderMenuOpen} />
      <InventoryComp isOpen={isInventoryOpen} setIsOpen={setIsInventoryOpen} />
      {/* TODO remove before final ver*/}
      <GenericTrader isOpen={isTraderMenuOpen} setIsOpen={setIsTraderMenuOpen} type={"market"} traderId={0} />
      <Box>Das Spiel</Box>
    </>
  )
}
