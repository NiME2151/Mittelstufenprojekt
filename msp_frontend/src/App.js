import './App.css';
import React from "react";
import {MainMenu} from "./components/MainMenu";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Game from "./components/Game";
import {CssBaseline, ThemeProvider} from "@mui/material";
import theme from "./theme";
import {Provider} from "react-redux";
import {configureReduxStore as store} from "./redux/configureReduxStore";
import {SnackbarProvider} from "notistack";

function App() {
  return (
    <ThemeProvider theme={theme}>
      <SnackbarProvider
        variant="error"
        autoHideDuration={3000}
        anchorOrigin={{horizontal: "right", vertical: "top"}}
        maxSnack={10}
      >
        <Provider store={store}>
          <CssBaseline />
          <BrowserRouter>
            <Routes>
              <Route path={"/"} element={<MainMenu />}></Route>
              <Route path={"/game"} element={<Game />}></Route>
            </Routes>
          </BrowserRouter>
        </Provider>
      </SnackbarProvider>
    </ThemeProvider>
  );
}

export default App;
