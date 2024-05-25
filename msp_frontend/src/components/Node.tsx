import React from "react";
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
  
  // getting a Map out of JSON is HARD!
  const neighboursObjectKeys = Object.keys(currentNode.neighbourMap)
  const neighboursMap = new Map<Direction,string>();
  
  neighboursObjectKeys.forEach((key) => {
    const k = key as Direction
    //@ts-ignore
    const v = currentNode.neighbourMap[k] as string
    neighboursMap.set(k, v)
  })
  
  //getting all possible Neighbours
  const upNeighbor = neighboursMap.get(Direction.UP);
  const downNeighbor = neighboursMap.get(Direction.DOWN);
  const northNeighbor = neighboursMap.get(Direction.NORTH);
  const eastNeighbor = neighboursMap.get(Direction.EAST);
  const southNeighbor = neighboursMap.get(Direction.SOUTH);
  const westNeighbor = neighboursMap.get(Direction.WEST);
  
  // click on a Direction Button
  const HandleNodeChange = async (newCurrentNode: string) => {
    await postNewNode(newCurrentNode);
    const newNode = await fetchNode(newCurrentNode, currentNode);
    setCurrentNode(newNode)
  }
  
  // send new locationID to Backend
  const postNewNode = async (newCurrentNode: string) => {
    const status = await MapApiService.setCurrentNode(newCurrentNode)
    if (status === 200){
      return
    }
    alert("Ups, you seem to have lost the way!")
  }
  
  // get new locationNode from Backend
  const fetchNode = async (nodeId: string, currentNode: MapNode): Promise<MapNode> => {
    const response = await MapApiService.getNode(nodeId);
      if (response) {
        return response;
    }
    return currentNode;
  }
  
  // Change BackgroundImage
  const body = document.querySelector("body")
  if (body) {
    body.className = ""
    body.classList.add(`node-${currentNode.nodeId}`)
  }
  
  return (
      <Box className={`node-${currentNode.displayName}`}>
        <Box> 
          {upNeighbor && (
              <button onClick={() => (HandleNodeChange(upNeighbor))} className={"goUpButton"}>
                <KeyboardDoubleArrowUpIcon />
              </button>
          )}
        </Box>
        <Box> 
          {northNeighbor &&
          (<button onClick={() => HandleNodeChange(northNeighbor)} className={"goNorthButton"}>
            <ArrowDropUpIcon/>
          </button>)}
        </Box>
        <Box>{westNeighbor &&
            ( <button onClick={() => HandleNodeChange(westNeighbor)} className={"goWestButton"}>
            <ArrowLeftIcon/>
          </button>)}
        </Box>
        <Box>{eastNeighbor &&
          (<button onClick={() => HandleNodeChange(eastNeighbor)} className={"goEastButton"}>
            <ArrowRightIcon/>
          </button>)}
        </Box>
        <Box> {southNeighbor &&
          (<button onClick={() => HandleNodeChange(southNeighbor)} className={"goSouthButton"}>
            <ArrowDropDownIcon/>
          </button>)}
        </Box>
        <Box>{downNeighbor &&
          (<button onClick={() => HandleNodeChange(downNeighbor)} className={"goDownButton"}>
            <KeyboardDoubleArrowDownIcon/>
          </button>)}
        </Box>
      </Box>
  )
}