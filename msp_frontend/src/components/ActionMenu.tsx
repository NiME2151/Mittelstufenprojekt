import {Backdrop, Box, ListItemButton} from "@mui/material";
import React, {JSX} from "react";
import {GenericItem} from "../models/items/GenericItem";
import {Consumable} from "../models/items/Consumable";

interface ActionMenuProps {
  isOpen: boolean,
  setIsOpen: (isOpen: boolean) => void,
  item: GenericItem
}

export const ActionMenu: React.FC<ActionMenuProps> = ({isOpen, setIsOpen, item}): JSX.Element => {

  return (
    <Box className={`action-menu ${isOpen ? "open" : "hidden"}`}>
      <Backdrop
        open={isOpen}
        onClick={() => {setIsOpen(false)}}
      >
      </Backdrop>
      {(item as Consumable).healthGain !== undefined && (
        <ListItemButton className="action-menu-btn" onClick={() => {
          console.log("consumed");
        }}>
          Consume +{(item as Consumable).healthGain}
          <img className="item-image-32" src={`/resources/ui/health.png`} />
        </ListItemButton>
      )}
    </Box>

  )
}
