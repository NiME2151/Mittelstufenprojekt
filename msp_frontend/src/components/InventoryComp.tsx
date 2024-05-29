import * as React from 'react';
import {Box, Dialog, DialogContent, DialogTitle, IconButton} from "@mui/material";
import {Close} from "@mui/icons-material";
import {useEffect, useState} from "react";
import {CharacterApiService} from "../api/CharacterApiService";
import {Inventory} from "../models/Inventory";
import {setInventory, useGlobals} from "../redux/slices/globals";
import {useDispatch} from "react-redux";
import {ActionMenu} from "./ActionMenu";

/**
 * @description The props of the component which the component needs to correctly render.
 */
interface InventoryProps {
  isOpen: boolean,
  setIsOpen: (isOpen: boolean) => void
}

/**
 * @description This component renders the player's inventory.
 */
export const InventoryComp: React.FC<InventoryProps> = ({isOpen, setIsOpen}) => {

  /**
   * @description Destructured globals state retrieving the player's inventory.
   */
  const [isActionMenuOpen, setIsActionMenuOpen] = useState<boolean>(false);

  const {inventory} = useGlobals();

  /**
   * @description Const for dispatching slice functions.
   */
  const dispatch = useDispatch();

  /**
   * @description Function to fetch the player's inventory.
   */
  const getPlayerInventory = (): void => {
    void CharacterApiService.getInventory().then((inventory: Inventory) => {
      dispatch(setInventory(inventory));
    })
  }

  /**
   * @description Calls the getPlayerInventory function on first render.
   */
  useEffect(() => {
    getPlayerInventory();
  }, []);

  const handleRightClick = (event: React.MouseEvent<HTMLDivElement>): void => {
    console.log("here");
    setIsActionMenuOpen(true);
    event.preventDefault();
  }

  return (
    <Dialog
      className="dialog"
      open={isOpen}
      onClose={() => {setIsOpen(false)}}
      scroll="paper"
    >
      <DialogTitle>
        Inventory
        <IconButton
          onClick={() => {
            setIsOpen(false);
          }}
          className="dialog-close-btn"
        >
          <Close/>
        </IconButton>
      </DialogTitle>
      <DialogContent>
        <Box className="inventory-container">
          {inventory !== undefined && Object.entries(inventory.items).concat(new Array(inventory.maxSize-Object.entries(inventory.items).length).fill(["null", null], 0, inventory.maxSize - Object.entries(inventory.items).length)).map(([item, amount], index: number) => {
            const parsedItem = JSON.parse(item)
            if (parsedItem !== null) {
              return (
                <Box onContextMenu={(e) => {handleRightClick(e)}} className="inventory-item" key={index}>
                  <Box className="inventory-item-amount">{amount}</Box>
                  <img className="item-image-64" src={`/resources/items/${parsedItem.displayName}.png`} />
                  <ActionMenu isOpen={isActionMenuOpen} setIsOpen={setIsActionMenuOpen} item={parsedItem} />
                </Box>
              );
            }
            else {
              return (
                <Box className="inventory-item" key={index} />
              );
            }
          })}
        </Box>
      </DialogContent>
    </Dialog>
  );
}

