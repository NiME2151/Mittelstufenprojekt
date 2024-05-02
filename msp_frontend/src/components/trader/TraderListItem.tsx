import React, {JSX} from "react";
import {Box, Button, Grid} from "@mui/material";
import {TradeItem} from "../../models/TradeItem";
import {TraderTab} from "../../types/TraderTab";
import {UiTexts} from "../../enums/UiTexts";
import {TradeApiService} from "../../api/TradeApiService";

interface TraderListItemProps {
  item: TradeItem,
  traderTab: TraderTab
}

export const TraderListItem: React.FC<TraderListItemProps> = ({item, traderTab}): JSX.Element => {

  const buyItem = (): number => {
    return await TradeApiService.buyItem(item).then((status: number) => {
      return status;
    })
  }

  const handleTradeButtonAction = () => {
    if (traderTab === "buy") {
      const status = buyItem();
    }
    else {

    }
  }

  return (
    <Grid container className="trader-list-item-container">
      <Grid item>
        <img className="trader-list-item-image" src={`/resources/items/${item.itemID}.png`} />
      </Grid>
      <Grid item className="trader-list-item-name">{item.displayName}</Grid>
      <Button variant="contained" className="trader-list-item-buy-btn" onClick={handleTradeButtonAction}>
        {traderTab === "buy" ? UiTexts.TRADER_TAB_BUY : UiTexts.TRADER_TAB_SELL} for&nbsp;
        <Box className="trader-list-item-price">{item.marketBuyValue}</Box>
        <img className="trader-list-item-gold" src={`/resources/ui/gold.png`} />
      </Button>
    </Grid>
  );
}
