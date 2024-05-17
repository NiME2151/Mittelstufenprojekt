import React, {useEffect, useState} from "react";
import {Box} from "@mui/material";
import {Direction} from "../enums/Direction";
import KeyboardDoubleArrowUpIcon from '@mui/icons-material/KeyboardDoubleArrowUp';
import KeyboardDoubleArrowDownIcon from '@mui/icons-material/KeyboardDoubleArrowDown';
import ArrowLeftIcon from '@mui/icons-material/ArrowLeft';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowDropUpIcon from '@mui/icons-material/ArrowDropUp';
import {MapApiService} from "../api/MapApiService";
import {MapNode} from "../models/MapNode";

interface NodeProps {
  currentNode: MapNode,
  setCurrentNode: (node: MapNode) => void
}
export const Node: React.FC<NodeProps> = ({currentNode, setCurrentNode}) => {
  
  // @ts-ignore
  const neighbours = currentNode.neighbourMap;
  
  const upNeighbor = neighbours.get(Direction.UP);
  const downNeighbor = neighbours.get(Direction.DOWN);
  const northNeighbor = neighbours.get(Direction.NORTH);
  const eastNeighbor = neighbours.get(Direction.EAST);
  const southNeighbor = neighbours.get(Direction.SOUTH);
  const westNeighbor = neighbours.get(Direction.WEST);
  

  const handleNodeChange = (newCurrentNode: MapNode | undefined) => {
    if (newCurrentNode == undefined){
      return
    }
    postNewNode(newCurrentNode);
    setCurrentNode(newCurrentNode);
  }
  

  function postNewNode(newCurrentNode: MapNode) {
    MapApiService.setCurrentNode(newCurrentNode.nodeId).then((status:number) => {
      if (status === 200){
        return
      }
      alert("Ups, you seem to have lost the way!")
    }
    )
  }
  
  return (
      <Box className={`node-${currentNode.displayName}`}>
        <Box> 
          {upNeighbor !== undefined ? (
              <button onClick={() => handleNodeChange(upNeighbor)} className={"goUpButton"}>
                <KeyboardDoubleArrowUpIcon />
              </button>
          ) : null}
        </Box>
        <Box> 
          {northNeighbor != undefined ?
          (<button onClick={() => handleNodeChange(northNeighbor)} className={"goNorthButton"}>
            <ArrowDropUpIcon/>
          </button>) : null}
        </Box>
        <Box>{westNeighbor != undefined ?
            ( <button onClick={() => handleNodeChange(westNeighbor)} className={"goWestButton"}>
            <ArrowLeftIcon/>
          </button>) : null}
        </Box>
        <Box>{eastNeighbor != undefined ?
          (<button onClick={() => handleNodeChange(eastNeighbor)} className={"goEastButton"}>
            <ArrowRightIcon/>
          </button>) : null}
        </Box>
        <Box> {southNeighbor != undefined ?
          (<button onClick={() => handleNodeChange(southNeighbor)} className={"goSouthButton"}>
            <ArrowDropDownIcon/>
          </button>) : null }
        </Box>
        <Box>{downNeighbor != undefined ?
          (<button onClick={() => handleNodeChange(downNeighbor)} className={"goDownButton"}>
            <KeyboardDoubleArrowDownIcon/>
          </button>) : null}
        </Box>
      </Box>
  )
}