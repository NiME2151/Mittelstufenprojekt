import React, {JSX} from "react";
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
import {setCurrentNode, useCurrentNode} from "../redux/slices/currentNode";
import {useAppDispatch} from "../hooks/reduxHooks";

export const Node = (): JSX.Element => {
  
  const dispatch = useAppDispatch();
  const currentNode: MapNode = useCurrentNode()
  
  const neighbours = JSON.stringify(currentNode.neighbourMap);
  const neighboursMap = new Map<Direction,string>();
  
  const helperArr = neighbours.split(",");
  
  helperArr.forEach((splitling) => {
    const KVPair = splitling.split(":")
    neighboursMap.set(KVPair[0] as Direction, KVPair[1])
  })

  const upNeighbor = neighboursMap.get(Direction.UP);
  const downNeighbor = neighboursMap.get(Direction.DOWN);
  const northNeighbor = neighboursMap.get(Direction.NORTH);
  const eastNeighbor = neighboursMap.get(Direction.EAST);
  const southNeighbor = neighboursMap.get(Direction.SOUTH);
  const westNeighbor = neighboursMap.get(Direction.WEST);

  const fetchNode = (nodeId: string): MapNode => {
    let newNode = currentNode;
    MapApiService.getNode(nodeId).then((response: MapNode) => {
      if (response)
      newNode = response
    })
    return newNode
  }

  const handleNodeChange = (newCurrentNode: string) => {
    postNewNode(newCurrentNode);
    const newNode = fetchNode(newCurrentNode);
    dispatch(setCurrentNode(newNode));
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
          {northNeighbor !== undefined ?
          (<button onClick={() => handleNodeChange(northNeighbor)} className={"goNorthButton"}>
            <ArrowDropUpIcon/>
          </button>) : null}
        </Box>
        <Box>{westNeighbor !== undefined ?
            ( <button onClick={() => handleNodeChange(westNeighbor)} className={"goWestButton"}>
            <ArrowLeftIcon/>
          </button>) : null}
        </Box>
        <Box>{eastNeighbor !== undefined ?
          (<button onClick={() => handleNodeChange(eastNeighbor)} className={"goEastButton"}>
            <ArrowRightIcon/>
          </button>) : null}
        </Box>
        <Box> {southNeighbor !== undefined ?
          (<button onClick={() => handleNodeChange(southNeighbor)} className={"goSouthButton"}>
            <ArrowDropDownIcon/>
          </button>) : null }
        </Box>
        <Box>{downNeighbor !== undefined ?
          (<button onClick={() => handleNodeChange(downNeighbor)} className={"goDownButton"}>
            <KeyboardDoubleArrowDownIcon/>
          </button>) : null}
        </Box>
      </Box>
  )
}