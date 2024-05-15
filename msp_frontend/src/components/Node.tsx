import React from "react";
import {Box} from "@mui/material";
import {useCurrentNode} from "../redux/slices/currentNode";
import {Direction} from "../enums/Direction";
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowUp';
import ArrowLeftIcon from '@mui/icons-material/ArrowLeft';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import {MapApiService} from "../api/MapApiService";

export default function Node() {
  
  const [currentNode, setCurrentNode] = useCurrentNode();

  const neighbours = currentNode.neighbourMap;
  
  const upNeighbor = neighbours.get(Direction.UP);
  const downNeighbor = neighbours.get(Direction.DOWN);
  const northNeighbor = neighbours.get(Direction.NORTH);
  const eastNeighbor = neighbours.get(Direction.EAST);
  const southNeighbor = neighbours.get(Direction.SOUTH);
  const westNeighbor = neighbours.get(Direction.WEST);

  function postNewNode(newCurrentNode) {
    MapApiService.setCurrentNode(newCurrentNode.nodeId).then((status:number) => {
      if (status === 200){
        return
      }
      alert("Ups, you seem to have lost the way!")
    }
    )
  }

  const handleNodeChange = (newCurrentNode) => {
    postNewNode(newCurrentNode);
    setCurrentNode(newCurrentNode)
  }
  
  
  return (
      <Box>
        <Box> {upNeighbor != undefined} &&
          <button onClick={() => handleNodeChange(upNeighbor)} className={"goUpButton"}>
            <KeyboardArrowUpIcon/>
          </button>
        </Box>
        <Box> {northNeighbor != undefined} &&
          <button onClick={() => handleNodeChange(northNeighbor)} className={"goNorthButton"}>
            <KeyboardArrowUpIcon/>
          </button>
        </Box>
        <Box>{westNeighbor != undefined} &&
          <button onClick={() => handleNodeChange(westNeighbor)} className={"goWestButton"}>
            <ArrowLeftIcon/>
          </button>
        </Box>
        <Box>{eastNeighbor != undefined} && 
          <button onClick={() => handleNodeChange(eastNeighbor)} className={"goEastButton"}>
            <ArrowRightIcon/>
          </button>
        </Box>
        <Box> {southNeighbor != undefined} &&
          <button onClick={() => handleNodeChange(southNeighbor)} className={"goSouthButton"}>
            <ArrowDropDownIcon/>
          </button>
        </Box>
        <Box>{downNeighbor != undefined} &&
          <button onClick={() => handleNodeChange(downNeighbor)} className={"goDownButton"}>
            <KeyboardArrowDownIcon/>
          </button>
        </Box>
      </Box>
  )
}