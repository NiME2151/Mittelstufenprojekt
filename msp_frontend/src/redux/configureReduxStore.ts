import {configureStore} from "@reduxjs/toolkit";
import {globalsSlice} from "./slices/globals";

export const configureReduxStore = configureStore({
  reducer: {
    globals: globalsSlice.reducer,
  }
});

export type RootState = ReturnType<typeof configureReduxStore.getState>;
export type AppDispatch = typeof configureReduxStore.dispatch;
