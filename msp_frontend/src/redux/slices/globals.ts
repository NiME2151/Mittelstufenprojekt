import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { useSelector } from "react-redux";
import { RootState } from "../configureReduxStore";
import { Inventory } from "../../models/Inventory";

/**
 * Defines the global state type, representing the player's attributes and inventory.
 * @property {number} money - The amount of money the player possesses.
 * @property {number} healthPoints - The player's current health points.
 * @property {number} maxHealthPoints - The player's maximum health points.
 * @property {Inventory} inventory - The player's inventory, containing items.
 */
export type globalsType = {
  money: number;
  healthPoints: number;
  maxHealthPoints: number;
  inventory: Inventory;
};

/**
 * Defines the structure of the globals state slice.
 *
 * @property {globalsType} value - The current value of the global state.
 */
interface globalsState {
  value: globalsType;
}

/**
 * Sets the initial state for the globals slice, with default values for money, health, and an empty inventory.
 */
const initialState: globalsState = {
  value: {
    money: 0,
    healthPoints: 0,
    maxHealthPoints: 0,
    inventory: new Inventory(new Map(), 0),
  },
};

/**
 * A Redux Toolkit slice for managing the global state of the game, including money, health, and inventory.
 */
export const globalsSlice = createSlice({
  name: "globals",
  initialState,
  reducers: {
    /**
     * Updates the entire global state with the provided new values.
     *
     * @param {globalsState} state - The current state of the globals slice.
     * @param {PayloadAction<globalsType>} action - An action containing the new global state values.
     */
    setGlobals: (state, action: PayloadAction<globalsType>) => {
      state.value = action.payload;
    },
    /**
     * Updates the player's money with the specified amount.
     *
     * @param {globalsState} state - The current state of the globals slice.
     * @param {PayloadAction<number>} action - An action containing the new money value.
     */
    setMoney: (state, action: PayloadAction<number>) => {
      state.value.money = action.payload;
    },
    /**
     * Updates the player's inventory with the provided Inventory object.
     *
     * @param {globalsState} state - The current state of the globals slice.
     * @param {PayloadAction<Inventory>} action - An action containing the new inventory.
     */
    setInventory: (state, action: PayloadAction<Inventory>) => {
      state.value.inventory = action.payload;
    },
  },
});

export const { setGlobals, setMoney, setInventory } = globalsSlice.actions;

export default globalsSlice.reducer;

/**
 * A custom hook that retrieves the current global state from the Redux store.
 *
 * @returns {globalsType} - The current global state.
 */
export const useGlobals = (): globalsType => useSelector((state: RootState) => state.globals.value);
