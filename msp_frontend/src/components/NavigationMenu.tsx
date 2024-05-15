import {Box} from "@mui/material";
import React from "react";
import {globalsType, useGlobals} from "../redux/slices/globals";

export default function NavigationMenu() {

  const globals: globalsType = useGlobals();

  return (
    <Box className="navigation-menu">
      <Box />
      <Box className="navigation-stat-container">
        {globals.money}
        <img className="item-image" src="/resources/ui/gold.png" />
      </Box>
      <Box className="navigation-stat-container">
        <Box>
          {globals.healthPoints}/{globals.maxHealthPoints}
        </Box>
        <img className="item-image" src="/resources/ui/health.png" />
      </Box>
    </Box>
  )
}
