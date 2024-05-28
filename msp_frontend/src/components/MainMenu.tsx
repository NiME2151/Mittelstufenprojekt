import {Box, Button} from "@mui/material";
import {useNavigate} from "react-router";
import React, {JSX} from "react";

export const MainMenu = (): JSX.Element => {
  
  const navigate = useNavigate()
   
  return (
    <Box className="game-menu">
      <Box className="game-menu-title">Xilisab</Box>
      <Button onClick={() => {navigate("/game")}}>Start Game</Button>
    </Box>
  )
}
