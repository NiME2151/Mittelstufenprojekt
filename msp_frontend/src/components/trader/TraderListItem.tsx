import React, {JSX, useEffect, useState} from "react";
import {Box, Button, Grid} from "@mui/material";
import {TradeItem} from "../../models/TradeItem";
import {TraderTab} from "../../types/TraderTab";
import {UiTexts} from "../../enums/UiTexts";
import {TradeApiService} from "../../api/TradeApiService";
import {HttpStatus} from "../../enums/HttpStatus";

interface TraderListItemProps {
  item: TradeItem,
  getTrader: () => void,
  getTradeItemsOfTrader: () => void,
  getTradeItemsOfPlayer: () => void,
  traderTab: TraderTab
}

export const TraderListItem: React.FC<TraderListItemProps> = ({item, getTrader, getTradeItemsOfTrader, getTradeItemsOfPlayer, traderTab}): JSX.Element => {

  const [actionStatus, setActionStatus] = useState<number>(HttpStatus.OK);

  useEffect(() => {
    if (actionStatus === HttpStatus.METHOD_NOT_ALLOWED) {
      alert("You have no space in your inventory, to buy this item")
    }
    else if (actionStatus === HttpStatus.NOT_ACCEPTABLE) {
      alert("You don't have enough money, to buy this item")
    }
  }, [actionStatus]);

  const buyItem = (): void => {
    void TradeApiService.buyItem(item).then((status: number) => {
      setActionStatus(status);
    })
  }

  const sellItem = (): void => {
    void TradeApiService.sellItem(item).then((status: number) => {
      setActionStatus(status);
    })
  }

  const handleTradeButtonAction = (): void => {
    if (traderTab === "buy") {
      buyItem();
      if (actionStatus === HttpStatus.OK) {
        getTrader();
        getTradeItemsOfTrader();
      }
    }
    else {
      sellItem();
      if (actionStatus === HttpStatus.OK) {
        getTrader();
        getTradeItemsOfPlayer();
      }
    }
  }

  return (
    <Grid container className="trader-list-item-container">
      <Grid item>
        <img className="trader-list-item-image" src={`/resources/items/${item.itemID}.png`} />
      </Grid>
      <Grid item className="trader-list-item-name">{item.displayName}</Grid>
      <Button variant="contained" className="trader-list-item-buy-btn" onClick={handleTradeButtonAction}>
        {traderTab === "buy" ? UiTexts.TRADER_BUY_BTN : UiTexts.TRADER_SELL_BTN} &nbsp;
        <Box className="trader-list-item-price">{item.marketBuyValue}</Box>
        <img className="trader-list-item-gold" src={`/resources/ui/gold.png`} />
      </Button>
    </Grid>
  );
}
