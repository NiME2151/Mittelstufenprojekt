import { Box } from "@mui/material";
import React, {JSX, useState} from "react";
import {NavigationMenu} from "./NavigationMenu";
import {InventoryComp} from "./InventoryComp";

export default function Game(): JSX.Element {

  const [isInventoryOpen, setIsInventoryOpen] = useState<boolean>(false);

  return (
    <>
      <NavigationMenu setIsInventoryOpen={setIsInventoryOpen} />
      <InventoryComp isOpen={isInventoryOpen} setIsOpen={setIsInventoryOpen} />
      <Box>Das Spiel</Box>
    </>
  )
}
