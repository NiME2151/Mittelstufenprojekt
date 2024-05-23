import {createSlice, type PayloadAction} from "@reduxjs/toolkit";
import {useSelector} from "react-redux";
import {RootState} from "../configureReduxStore";
import {MapNode} from "../../models/MapNode";
import {Direction} from "../../enums/Direction";


interface currentNodeState {
  value: MapNode
}

const initialState: currentNodeState = {
  value: new MapNode(
      "id",
      "name",
      "description",
      [], 
      new Map<Direction, string>(),
      "loot",
      "enemyLoot"
  )
};

export const currentNodeSlice = createSlice({
  name: "currentNode",
  initialState,
  reducers: {
    setCurrentNode: (state, action: PayloadAction<MapNode>) => {
      state.value = action.payload;
    }
  }
});


export const {setCurrentNode} = currentNodeSlice.actions;

export default currentNodeSlice.reducer;

export const useCurrentNode = (): MapNode => useSelector((state: RootState) => state.currentNode.value);
