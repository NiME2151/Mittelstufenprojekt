import {Box, Button} from "@mui/material";
import React from "react";
import {globalsType, useGlobals} from "../redux/slices/globals";
import {Backpack} from "@mui/icons-material";

interface NavigationMenuProps {
  setIsInventoryOpen: (isOpen: boolean) => void
}

export const NavigationMenu: React.FC<NavigationMenuProps> = ({setIsInventoryOpen}) => {

  const globals: globalsType = useGlobals();
  return (
    <Box className="navigation-menu">
      <Button className="inventory-btn" startIcon={<Backpack />} onClick={() => setIsInventoryOpen(true)}>Inventory</Button>
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
