import {configureStore} from "@reduxjs/toolkit";
import {globalsSlice} from "./slices/globals";

/**
 * Configures a Redux store using the `configureStore` function.
 *
 * @param {Object} options - The configuration options for the store.
 * @param {Object} options.reducer - The root reducer of the application.
 *   - The reducer takes the current state and an action object as arguments,
 *     and returns the new state of the application.
 * @returns {Object} The created Redux store object.
 */
export const configureReduxStore = configureStore({
  reducer: {
    globals: globalsSlice.reducer,
  },
});
/**
 * Defines the type of the root state of the application.
 * This type is inferred from the return type of the `configureReduxStore.getState` function.
 */
export type RootState = ReturnType<typeof configureReduxStore.getState>;

/**
 * Defines the type of the dispatch function of the Redux store.
 * This type is inferred from the return type of the `configureReduxStore.dispatch` function.
 */
export type AppDispatch = typeof configureReduxStore.dispatch;
