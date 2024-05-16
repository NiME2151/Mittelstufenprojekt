import {configureStore} from "@reduxjs/toolkit";
import {globalsSlice} from "./slices/globals";
import {currentNodeSlice} from "./slices/currentNode";

export const configureReduxStore = configureStore({
  reducer: {
    globals: globalsSlice.reducer,
    currentNode: currentNodeSlice.reducer,
  }
});

export type RootState = ReturnType<typeof configureReduxStore.getState>;
export type AppDispatch = typeof configureReduxStore.dispatch;
