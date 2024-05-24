import {createSlice, type PayloadAction, createAction, Action, Reducer} from "@reduxjs/toolkit";
import {useSelector} from "react-redux";
import {RootState} from "../configureReduxStore";
import {MapNode} from "../../models/MapNode";


interface CurrentNodeState {
  value: MapNode
}

const initialState: CurrentNodeState = {
  value: new MapNode(
      "id",
      "tavern",
      "description",
      [], 
      new Array([["UP", "2"]]),
      "loot",
      "enemyLoot"
  )
};

// Reducer
export const SET_ACTION = "currentNode/SET"
export const RESET_ACTION = "currentNode/RESET"

export interface SetCurrentNodeAction extends Action {
  type: typeof SET_ACTION,
  payload: MapNode
}
export interface ResetCurrentNodeAction extends Action {
  type: typeof RESET_ACTION,
}
export type CurrentNodeAction = SetCurrentNodeAction | ResetCurrentNodeAction

export const currentNode: Reducer<CurrentNodeState, CurrentNodeAction, CurrentNodeState> = (state: CurrentNodeState = initialState, action: CurrentNodeAction) => {
  switch (action.type) {
    case "currentNode/SET":
      return { value: action.payload }
    case "currentNode/RESET":
      return initialState;
    default:
      return state;
  }
}

export const setCurrentNode = (mapNode: MapNode): SetCurrentNodeAction => ({
  type: SET_ACTION,
  payload: mapNode
})

// selctor
export const useCurrentNode = (): MapNode => useSelector((state: RootState) => state.currentNode.value);
