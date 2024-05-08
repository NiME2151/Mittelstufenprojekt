import './App.css';
import React from "react";
import {GameMenu} from "./components/GameMenu";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Trader from "./components/Trader";
import Game from "./components/Game";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={"/"} element={<GameMenu />}></Route>
        <Route path={"/trade"} element={<Trader />}></Route>
        <Route path={"/game"} element={<Game />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
