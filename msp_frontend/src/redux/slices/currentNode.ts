import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {MapNode} from "../../models/MapNode";
import {useSelector} from "react-redux";
import {RootState} from "../configureReduxStore";
import {globalsSlice, globalsType} from "./globals";

const initialState = new MapNode("", "", [], new Map(), "", "");

export const currentNodeSlice = createSlice({
  name: "currentNode",
  initialState,
  reducers: {
      setCurrentNode: (state, action: PayloadAction<MapNode>) => {
        return action.payload;
      },
  }
});

export const { setCurrentNode } = currentNodeSlice.actions;

export default currentNodeSlice.reducer;

export const useCurrentNode = (): MapNode => useSelector((state: RootState) => state.currentNode);