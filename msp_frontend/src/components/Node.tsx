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

/**
 * @description The props of the component which the component needs to render correct.
 */
interface NodeProps {
  currentNode: MapNode,
  setCurrentNode: (node: MapNode) => void
}

/**
* @description This component renders the current node aka location.
*/
export const Node: React.FC<NodeProps> = ({currentNode, setCurrentNode}) => {
  
  // getting a Map out of JSON is HARD!
  const neighboursObjectKeys = Object.keys(currentNode.neighbourMap)
  const neighboursMap = new Map<Direction,string>();

  /**
   * @description sets for each key and value of the Object neighboursObjectKeys a new key value pair in neighborsMap
   */
  neighboursObjectKeys.forEach((key) => {
    const k = key as Direction
    //@ts-ignore
    const v = currentNode.neighbourMap[k] as string
    neighboursMap.set(k, v)
  })

  /**
   * @description getting all possible Neighbours or set const to null
   */
  const upNeighbor = neighboursMap.get(Direction.UP);
  const downNeighbor = neighboursMap.get(Direction.DOWN);
  const northNeighbor = neighboursMap.get(Direction.NORTH);
  const eastNeighbor = neighboursMap.get(Direction.EAST);
  const southNeighbor = neighboursMap.get(Direction.SOUTH);
  const westNeighbor = neighboursMap.get(Direction.WEST);

  /**
   * @description sets new current node with postNewNode, 
   * fetches Value for new currentNode from backend 
   * and sets the state of currentNode to the new node.
   * @param newCurrentNode id of the potential new currentNode
   * 
   */
  const HandleNodeChange = async (newCurrentNode: string) => {
    await postNewNode(newCurrentNode);
    const newNode = await fetchNode(newCurrentNode, currentNode);
    setCurrentNode(newNode)
  }

  /**
   * @description calls on the api to set the mapNode in the backend to where the player is currently located at.
   * @param newCurrentNode id of the new currentNode.
   * @alert if process is not working, an alert is displayed.
   */
  const postNewNode = async (newCurrentNode: string) => {
    const status = await MapApiService.setCurrentNode(newCurrentNode)
    if (status === 200){
      return
    }
    alert("Ups, you seem to have lost the way!")
  }

  /**
   * @description gets content of new node from backend and returns it or else currentNode.
   * @param nodeId id of the node that's content is asked of the backend.
   * @param currentNode the currentnode used as fallback return value.
   */
  const fetchNode = async (nodeId: string, currentNode: MapNode): Promise<MapNode> => {
    const response = await MapApiService.getNode(nodeId);
      if (response) {
        return response;
    }
    return currentNode;
  }

  /**
   * @description sets the background image to that of the current node on html body
   */
  const body = document.querySelector("body")
  if (body) {
    body.className = ""
    body.classList.add(`node-${currentNode.nodeId}`)
  }
  
  
  
  return (
      <Box className="node--button-container" >

        <div className="goUpButton"><Box>
          {upNeighbor && (
              <button onClick={() => (HandleNodeChange(upNeighbor))} className={"goUpButton"}>
                <KeyboardDoubleArrowUpIcon/>
              </button>
          )}
        </Box></div>
        <div className="goNorthButton"><Box>
          {northNeighbor &&
              (<button onClick={() => HandleNodeChange(northNeighbor)} className={"goNorthButton"}>
                <ArrowDropUpIcon/>
              </button>)}
        </Box></div>

        <div className="goWestButton"><Box>{westNeighbor &&
            (<button onClick={() => HandleNodeChange(westNeighbor)} className={"goWestButton"}>
              <ArrowLeftIcon/>
            </button>)}
        </Box></div>
        <div className="goEastButton"><Box>{eastNeighbor &&
            (<button onClick={() => HandleNodeChange(eastNeighbor)} className={"goEastButton"}>
              <ArrowRightIcon/>
            </button>)}
        </Box></div>
        <div className="goSouthButton"><Box> {southNeighbor &&
            (<button onClick={() => HandleNodeChange(southNeighbor)} className={"goSouthButton"}>
              <ArrowDropDownIcon/>
            </button>)}
        </Box></div>
        <div className="goDownButton"><Box>{downNeighbor &&
            (<button onClick={() => HandleNodeChange(downNeighbor)} className={"goDownButton"}>
              <KeyboardDoubleArrowDownIcon/>
            </button>)}
        </Box></div>
      </Box>
  )
}