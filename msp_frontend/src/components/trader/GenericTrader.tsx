import React, {JSX, useEffect, useState} from "react";
import {Box, Button, ButtonGroup, Dialog, DialogContent, DialogTitle, Grid, IconButton} from "@mui/material";
import {Close} from "@mui/icons-material";
import {TradeApiService} from "../../api/TradeApiService";
import {TradeItem} from "../../models/TradeItem";
import {TraderListItem} from "./TraderListItem";
import {TraderApiService} from "../../api/TraderApiService";
import {Trader} from "../../models/Trader";
import {TraderTab} from "../../types/TraderTab";
import {CharacterApiService} from "../../api/CharacterApiService";
import {Inventory} from "../../models/Inventory";
import {UiTexts} from "../../enums/UiTexts";
interface TraderProps {
  isOpen: boolean,
  setIsOpen: (isOpen: boolean) => void
  type: "tavern" | "market"
}

export const GenericTrader: React.FC<TraderProps> = ({isOpen, setIsOpen, type}): JSX.Element => {

  const [traderTab, setTraderTab] = useState<TraderTab>("buy")

  const [tradeItems, setTradeItems] = useState<TradeItem[]>([])
  const [trader, setTrader] = useState<Trader>()

  useEffect(() => {
    getTrader();
    getTradeItemsOfTrader()
  }, []);

  useEffect(() => {
    if (traderTab === "buy") {
      getTradeItemsOfTrader()
    }
    else if (traderTab === "sell") {
      getTradeItemsOfPlayer()
    }
    else {
      throw new Error("traderTab contains unexpected value: " + traderTab)
    }
  }, [traderTab]);

  const getTrader = () => {
    void TraderApiService.getTrader().then((trader: Trader) => {
      setTrader(trader);
    })
  }

  const getTradeItemsOfPlayer = () => {
    void CharacterApiService.getTradeInventory().then((items: TradeItem[]) => {
      setTradeItems(items);
    })
  }

  const getTradeItemsOfTrader = () => {
    void TradeApiService.getTradeItems().then((items: TradeItem[]) => {
      setTradeItems(items);
    })
  }

  return (
    <Dialog
      open={isOpen}
      onClose={() => {setIsOpen(false)}}
      scroll="paper"
    >
      <DialogTitle>
        {trader?.name}
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
            <img className="trader-image" src={`/resources/ui/trader_${type}.png`}></img>
          </Grid>
          <Grid item>
            <ButtonGroup variant="outlined" className="trader-tab-container">
              <Button className="trader-tab-btn" onClick={() => setTraderTab("buy")}>{UiTexts.TRADER_TAB_BUY}</Button>
              <Button className="trader-tab-btn" onClick={() => setTraderTab("sell")}>{UiTexts.TRADER_TAB_SELL}</Button>
            </ButtonGroup>
            <Grid item>
              {tradeItems.map((item, index) => {
                return <TraderListItem item={item} traderTab={traderTab} key={`${item.itemID}-${index}`}></TraderListItem>
              })}
            </Grid>
          </Grid>
        </Grid>
      </DialogContent>
    </Dialog>
  )
}
