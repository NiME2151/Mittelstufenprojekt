import {Box, Button} from "@mui/material";
import {useNavigate} from "react-router";
import React, {JSX, useEffect} from "react";
import {fetchInitialNode} from "../hooks/fetchInitialNode";
import {setCurrentNode} from "../redux/slices/currentNode";
import {useAppDispatch} from "../hooks/reduxHooks";

export const MainMenu = (): JSX.Element => {
  
  const dispatch = useAppDispatch();
  
  const navigate = useNavigate()
  
  useEffect(() => {
    fetchInitialNode().then(node => {
      dispatch(setCurrentNode(node))
    })
  },[dispatch])
   
  return (
    <Box className="game-menu">
      <Box className="game-menu-title">Xilisab</Box>
      <Button onClick={() => {navigate("/game")}}>Start Game</Button>
    </Box>
  )
}
