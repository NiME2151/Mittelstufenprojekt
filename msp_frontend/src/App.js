import './App.css';
import React from "react";
import {MainMenu} from "./components/MainMenu";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Trader from "./components/Trader";
import Game from "./components/Game";
import {CssBaseline, ThemeProvider} from "@mui/material";
import theme from "./theme";
import {Provider} from "react-redux";
import {configureReduxStore as store} from "./redux/configureReduxStore";

function App() {
  return (
    <ThemeProvider theme={theme}>
      <Provider store={store}>
        <CssBaseline />
        <BrowserRouter>
          <Routes>
            <Route path={"/"} element={<MainMenu />}></Route>
            <Route path={"/trade"} element={<Trader />}></Route>
            <Route path={"/game"} element={<Game />}></Route>
          </Routes>
        </BrowserRouter>
      </Provider>
    </ThemeProvider>
  );
}

export default App;
