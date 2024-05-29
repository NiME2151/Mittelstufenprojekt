import {Box, Button} from "@mui/material";
import React from "react";
import {globalsType, useGlobals} from "../redux/slices/globals";
import {Backpack} from "@mui/icons-material";

/**
 * @description The props of the component which the component needs to correctly render.
 */
interface NavigationMenuProps {
  setIsInventoryOpen: (isOpen: boolean) => void
  setIsTraderMenuOpen: (isOpen: boolean) => void
}

/**
 * @description This component renders the top menu bar containing player stats and action buttons
 */
export const NavigationMenu: React.FC<NavigationMenuProps> = ({setIsInventoryOpen, setIsTraderMenuOpen}) => {

  /**
   * @description Variable holding the global state variables.
   */
  const globals: globalsType = useGlobals();

  return (
    <Box className="navigation-menu">
      <Button className="inventory-btn" startIcon={<Backpack />} onClick={() => setIsInventoryOpen(true)}>Inventory</Button>
      {/* TODO remove this button before final ver*/}
      <Button className="inventory-btn" startIcon={<Backpack />} onClick={() => setIsTraderMenuOpen(true)}>Trader TEST</Button>
      <Box />
      <Box className="navigation-stat-container">
        {globals.money}
        <img className="item-image-32" src="/resources/ui/gold.png" />
      </Box>
      <Box className="navigation-stat-container">
        <Box>
          {globals.healthPoints}/{globals.maxHealthPoints}
        </Box>
        <img className="item-image-32" src="/resources/ui/health.png" />
      </Box>
    </Box>
  )
}
