import React, {JSX, useEffect, useState} from "react";
import {Box, Button, ButtonGroup, Dialog, DialogContent, DialogTitle, Grid, IconButton} from "@mui/material";
import {Close} from "@mui/icons-material";
import {TradeItem} from "../../models/TradeItem";
import {TraderListItem} from "./TraderListItem";
import {TraderApiService} from "../../api/TraderApiService";
import {Trader} from "../../models/Trader";
import {TraderTab} from "../../types/TraderTab";
import {CharacterApiService} from "../../api/CharacterApiService";
import {UiTexts} from "../../enums/UiTexts";

/**
 * @description The props of the component which the component needs to correctly render.
 */
interface TraderProps {
  isOpen: boolean,
  setIsOpen: (isOpen: boolean) => void
  type: "tavern" | "market",
  traderId: number
}

/**
 * @description This component renders a trader menu.
 */
export const GenericTrader: React.FC<TraderProps> = ({isOpen, setIsOpen, type, traderId}): JSX.Element => {

  /**
   * @description Local state determining if the buy or sell item list is rendered.
   */
  const [traderTab, setTraderTab] = useState<TraderTab>("buy")
  /**
   * @description Local state holding the trade items of the trader.
   */
  const [tradeItems, setTradeItems] = useState<TradeItem[]>([])
  /**
   * @description Variable holding the trader object.
   */
  const [trader, setTrader] = useState<Trader>()

  /**
   * @description Fetches trader object and their trade items on first render.
   */
  useEffect(() => {
    getTrader();
    getTradeItemsOfTrader();
  }, []);

  /**
   * @description Fetches necessary trader data when trader tab is changed.
   */
  useEffect(() => {
    if (traderTab === "buy") {
      getTrader();
      getTradeItemsOfTrader();
    }
    else if (traderTab === "sell") {
      getTrader();
      getTradeItemsOfPlayer();
    }
    else {
      throw new Error("traderTab contains unexpected value: " + traderTab)
    }
  }, [traderTab]);

  /**
   * @description Function to fetch trader object and set the local state.
   */
  const getTrader = (): void => {
    void TraderApiService.getTrader(traderId).then((trader: Trader) => {
      setTrader(trader);
    })
  }

  /**
   * @description Function to fetch trader items of the player and set the local state.
   */
  const getTradeItemsOfPlayer = (): void => {
    void CharacterApiService.getTradeInventory().then((items: TradeItem[]) => {
      setTradeItems(items);
    })
  }

  /**
   * @description Function to fetch trader items of the trader and set the local state.
   */
  const getTradeItemsOfTrader = (): void => {
    void TraderApiService.getTradeItems(traderId).then((items: TradeItem[]) => {
      setTradeItems(items);
    })
  }

  return (
    <Dialog
      className="dialog"
      open={isOpen}
      onClose={() => {setIsOpen(false)}}
      scroll="paper"
    >
      <DialogTitle>
        <IconButton
          onClick={() => {
            setIsOpen(false);
          }}
          className="dialog-close-btn"
        >
          <Close/>
        </IconButton>
      </DialogTitle>
      <DialogContent>
        <Grid container className="trader-list-container">
          <Grid item>
            <Box className="trader-info-container text-center">{trader?.name}</Box>
            <img className="trader-image" src={`/resources/ui/trader_${type}.png`}></img>
            <Box className="trader-info-container trader-gold-container text-left">
                <Box>{trader?.money}</Box>
                <img className="item-image-32" src={`/resources/ui/money.png`}></img>
            </Box>
          </Grid>
          <Grid item>
            <ButtonGroup variant="outlined" className="trader-tab-container">
              <Button className="trader-tab-btn" onClick={() => setTraderTab("buy")}>{UiTexts.TRADER_TAB_BUY}</Button>
              <Button className="trader-tab-btn" onClick={() => setTraderTab("sell")}>{UiTexts.TRADER_TAB_SELL}</Button>
            </ButtonGroup>
            <Grid item className="trader-item-list">
              {tradeItems && tradeItems.length > 0 ? tradeItems.map((item, index) => {
                return <TraderListItem
                  item={item}
                  traderTab={traderTab}
                  getTrader={getTrader}
                  traderId={traderId}
                  getTradeItemsOfTrader={getTradeItemsOfTrader}
                  getTradeItemsOfPlayer={getTradeItemsOfPlayer}
                  key={`${item.itemID}-${index}`}
                ></TraderListItem>
              }) : <Box className="trader-item-list-empty" >{traderTab === "buy" ? UiTexts.NOTHING_TO_BUY : UiTexts.NOTHING_TO_SELL}</Box>}
            </Grid>
          </Grid>
        </Grid>
      </DialogContent>
    </Dialog>
  )
}
