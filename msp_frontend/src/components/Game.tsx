import { Box } from "@mui/material";
import React, {JSX} from "react";
import NavigationMenu from "./NavigationMenu";

export default function Game(): JSX.Element {

  return (
    <>
      <NavigationMenu />
      <Box>Das Spiel</Box>
    </>
  )
}
