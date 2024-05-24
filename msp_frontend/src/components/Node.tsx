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
  setCurrentNode: (value: (((prevState: MapNode) => MapNode) | MapNode)) => void
}
export const Node: React.FC<NodeProps> = ({currentNode, setCurrentNode}) => {
  
  // super annoying, but to get a Map out of JSON is HARD!
  const neighboursObjectKeys = Object.keys(currentNode.neighbourMap)
  const neighboursMap = new Map<Direction,string>();
  
  neighboursObjectKeys.forEach((key) => {
    const k = key as Direction
    //@ts-ignore
    const v = currentNode.neighbourMap[k] as string
    neighboursMap.set(k, v)
  })
  
  
  const upNeighbor = neighboursMap.get(Direction.UP);
  const downNeighbor = neighboursMap.get(Direction.DOWN);
  const northNeighbor = neighboursMap.get(Direction.NORTH);
  const eastNeighbor = neighboursMap.get(Direction.EAST);
  const southNeighbor = neighboursMap.get(Direction.SOUTH);
  const westNeighbor = neighboursMap.get(Direction.WEST);

  console.log("southNeighbor");
  console.log(southNeighbor);

  const fetchNode = (nodeId: string, currentNode: MapNode): MapNode => {
    let newNode = currentNode;
    MapApiService.getNode(nodeId).then((response: MapNode) => {
      if (response)
      newNode = response
    })
    return newNode
  }

  const HandleNodeChange = (newCurrentNode: string) => {
    postNewNode(newCurrentNode);
    const newNode = fetchNode(newCurrentNode, currentNode);
    setCurrentNode(newNode)
  }
  

  function postNewNode(newCurrentNode: string) {
    MapApiService.setCurrentNode(newCurrentNode).then((status:number) => {
      if (status === 200){
        return
      }
      alert("Ups, you seem to have lost the way!")
    }
    )
  }
  
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
          {northNeighbor !== undefined ?
          (<button onClick={() => HandleNodeChange(northNeighbor)} className={"goNorthButton"}>
            <ArrowDropUpIcon/>
          </button>) : null}
        </Box>
        <Box>{westNeighbor !== undefined ?
            ( <button onClick={() => HandleNodeChange(westNeighbor)} className={"goWestButton"}>
            <ArrowLeftIcon/>
          </button>) : null}
        </Box>
        <Box>{eastNeighbor !== undefined ?
          (<button onClick={() => HandleNodeChange(eastNeighbor)} className={"goEastButton"}>
            <ArrowRightIcon/>
          </button>) : null}
        </Box>
        <Box> {southNeighbor !== undefined ?
          (<button onClick={() => HandleNodeChange(southNeighbor)} className={"goSouthButton"}>
            <ArrowDropDownIcon/>
          </button>) : null }
        </Box>
        <Box>{downNeighbor !== undefined ?
          (<button onClick={() => HandleNodeChange(downNeighbor)} className={"goDownButton"}>
            <KeyboardDoubleArrowDownIcon/>
          </button>) : null}
        </Box>
      </Box>
  )
}