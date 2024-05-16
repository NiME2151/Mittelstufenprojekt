import { Box } from "@mui/material";
import React, {JSX, useEffect} from "react";
import NavigationMenu from "./NavigationMenu";
import Node from "./Node";

export default function Game(): JSX.Element {
  
  return (
    <>
      <NavigationMenu />
      <Node/>
    </>
  )
}
