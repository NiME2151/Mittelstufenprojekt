import {createSlice, type PayloadAction} from "@reduxjs/toolkit";
import {useSelector} from "react-redux";
import {RootState} from "../configureReduxStore";

export type globalsType = {
  money: number,
  healthPoints: number,
  maxHealthPoints: number
}

interface globalsState {
  value: globalsType
}

const initialState: globalsState = {
  value: {
    money: 0,
    healthPoints: 0,
    maxHealthPoints: 0
  }
};

export const globalsSlice = createSlice({
  name: "globals",
  initialState,
  reducers: {
    setGlobals: (state, action: PayloadAction<globalsType>) => {
      state.value = action.payload;
    },
    setMoney: (state, action: PayloadAction<number>) => {
      state.value.money = action.payload;
    },
  }
});

export const {setGlobals, setMoney} = globalsSlice.actions;

export default globalsSlice.reducer;

export const useGlobals = (): globalsType => useSelector((state: RootState) => state.globals.value);
