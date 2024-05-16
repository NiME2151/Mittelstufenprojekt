import * as React from 'react';
import {Box, Dialog, DialogContent, DialogTitle, IconButton} from "@mui/material";
import {Close} from "@mui/icons-material";
import {useEffect, useState} from "react";
import {CharacterApiService} from "../api/CharacterApiService";
import {Inventory} from "../models/Inventory";
import {GenericItem} from "../models/items/GenericItem";
import {Consumable} from "../models/items/Consumable";
import * as assert from "assert";

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

  return (
    <Dialog
      className="dialog"
      open={isOpen}
      onClose={() => {setIsOpen(false)}}
      scroll="paper"
    >
      <DialogTitle>
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
        {inventory !== undefined && Object.entries(inventory.items).fill(inventory.maxSize-inventory.items.size).map(([item, amount], index: number) => {
          const parsedItem = JSON.parse(item)
          console.log(parsedItem);
          return (
            <Box className="inventory-item" key={index}>
              <Box className="inventory-item-amount">{amount}</Box>
              <img className="item-image-64" src={`/resources/items/${parsedItem.displayName}.png`} />
            </Box>
          );
        })}
      </DialogContent>
    </Dialog>
  );
}

