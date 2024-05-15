import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {MapNode} from "../../models/MapNode";
import {useSelector} from "react-redux";
import {RootState} from "../configureReduxStore";
import {globalsSlice, globalsType} from "./globals";

export const currentNodeSlice = createSlice({
  name: "currentNode", 
  initialState: undefined,
  reducers: {
    setGlobals: (state, action: PayloadAction<MapNode>) => {
      state.value = action.payload;
    }
  }
})

export const {setCurrentNode} = currentNodeSlice.actions;

export default currentNodeSlice.reducer;

export const useCurrentNode = (): globalsType => useSelector((state: RootState) => state.currentNode.value);
