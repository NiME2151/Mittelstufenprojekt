import * as React from 'react';
import {Box, Dialog, DialogContent, DialogTitle, IconButton} from "@mui/material";
import {Close} from "@mui/icons-material";
import {useEffect, useState} from "react";
import {CharacterApiService} from "../api/CharacterApiService";
import {Inventory} from "../models/Inventory";

interface InventoryProps {
  isOpen: boolean,
  setIsOpen: (isOpen: boolean) => void
}

export const InventoryComp: React.FC<InventoryProps> = ({isOpen, setIsOpen}) => {
  const [inventory, setInventory] = useState<Inventory>()

  const getPlayerInventory = (): void => {
    void CharacterApiService.getInventory().then((inventory: Inventory) => {
      console.log(inventory);
      setInventory(inventory);
    })
  }

  useEffect(() => {
    getPlayerInventory();
  }, []);

  function parseKey<T>(key: string): T | undefined {
    const match = key.match(/^([A-Z][a-zA-Z]+)\(([^)]+)\)$/);
    if (!match) return undefined;

    const properties = match[2].split(',').map(prop => {
      const [propName, propValue] = prop.split('=');
      return { [propName]: parseInt(propValue) };
    });

    const obj: T = {} as T;
    for (const prop of properties) {
      Object.assign(obj as object, prop);
    }

    return obj;
  }

  // const obj1 = inventory.maxSize-inventory.items.size

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
                <Box className="inventory-item" key={index}>
                  <Box className="inventory-item-amount">{amount}</Box>
                  <img className="item-image-64" src={`/resources/items/${parsedItem.displayName}.png`} />
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

