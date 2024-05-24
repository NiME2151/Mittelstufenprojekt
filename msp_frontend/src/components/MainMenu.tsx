import {Box, Button} from "@mui/material";
import {useNavigate} from "react-router";
import React, {JSX} from "react";

/**
 * @description This component renders the start menu of the game.
 */
export const MainMenu = (): JSX.Element => {

  /**
   * @description Function to navigate to another route.
   */
  const navigate = useNavigate()

  return (
    <Box className="game-menu">
      <Box className="game-menu-title">Xilisab</Box>
      <Button onClick={() => {navigate("/game")}}>Start Game</Button>
    </Box>
  )
}
