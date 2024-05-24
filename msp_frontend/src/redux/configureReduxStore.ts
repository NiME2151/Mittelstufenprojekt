import {configureStore} from "@reduxjs/toolkit";
import {globalsSlice} from "./slices/globals";
import {currentNode} from "./slices/currentNode";


export const configureReduxStore = configureStore({
  reducer: {
    globals: globalsSlice.reducer,
    // @ts-ignore
    currentNode,
  }
});

export type RootState = ReturnType<typeof configureReduxStore.getState>;
export type AppDispatch = typeof configureReduxStore.dispatch;
