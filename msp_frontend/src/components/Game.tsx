import { Box } from "@mui/material";
import React, {JSX, useEffect, useState} from "react";
import {NavigationMenu} from "./NavigationMenu";
import {InventoryComp} from "./InventoryComp";
import {GenericTrader} from "./trader/GenericTrader";
import {CharacterApiService} from "../api/CharacterApiService";
import {Character} from "../models/Character";
import {setGlobals} from "../redux/slices/globals";
import {useDispatch} from "react-redux";

export default function Game(): JSX.Element {

  const [isInventoryOpen, setIsInventoryOpen] = useState<boolean>(false);
  const [isTraderMenuOpen, setIsTraderMenuOpen] = useState<boolean>(false);

  const dispatch = useDispatch();

  useEffect(() => {
    CharacterApiService.getCharacter().then((character: Character) => {
      dispatch(setGlobals(
        {
          money: character.money,
          healthPoints: character.healthPoints,
          maxHealthPoints: character.maxHealthPoints
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
