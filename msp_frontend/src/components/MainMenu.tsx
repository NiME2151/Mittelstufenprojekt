import {Box, Button} from "@mui/material";
import {useNavigate} from "react-router";
import React, {JSX, useCallback, useEffect, useState} from "react";
import {setCurrentNode} from "../redux/slices/currentNode";
import {useAppDispatch} from "../hooks/reduxHooks";
import {MapNode} from "../models/MapNode";
import {MapApiService} from "../api/MapApiService";


export const MainMenu = (): JSX.Element => {
  
  const dispatch = useAppDispatch();
  
  const navigate = useNavigate()

  const fetchInitialNode = useCallback( async (): Promise<MapNode> => {
    return await MapApiService.getCurrentNode().then()}, []);
  
  useEffect(() => {
    fetchInitialNode().then(mapNode => {
      // @ts-ignore
      dispatch(setCurrentNode(mapNode))
    })
  },[dispatch, fetchInitialNode])
   
  return (
    <Box className="game-menu">
      <Box className="game-menu-title">Xilisab</Box>
      <Button onClick={() => {navigate("/game")}}>Start Game</Button>
    </Box>
  )
}
