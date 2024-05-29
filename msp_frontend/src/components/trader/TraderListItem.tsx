import React, {JSX} from "react";
import {Box, Button, Grid} from "@mui/material";
import {TradeItem} from "../../models/TradeItem";
import {TraderTab} from "../../types/TraderTab";
import {UiTexts} from "../../enums/UiTexts";
import {HttpStatus} from "../../enums/HttpStatus";
import {CharacterApiService} from "../../api/CharacterApiService";
import {Character} from "../../models/Character";
import {setInventory, setMoney} from "../../redux/slices/globals";
import {useDispatch} from "react-redux";
import {useSnackbar} from "notistack";
import {Inventory} from "../../models/Inventory";

/**
 * @description The props of the component which the component needs to correctly render.
 */
interface TraderListItemProps {
  item: TradeItem,
  getTrader: () => void,
  getTradeItemsOfTrader: () => void,
  getTradeItemsOfPlayer: () => void,
  traderTab: TraderTab,
  traderId: number,
}

/**
 * @description This component renders an item entry in the trader's buy/sell item list.
 */
export const TraderListItem: React.FC<TraderListItemProps> = ({item, getTrader, getTradeItemsOfTrader, getTradeItemsOfPlayer, traderTab, traderId}): JSX.Element => {


  /**
   * @description Dispatches slice functions.
   */
  const dispatch = useDispatch();

  /**
   * @description creates a snackbar to show errors.
   */
  const {enqueueSnackbar} = useSnackbar();

  /**
   * @description Function to fetch the player's inventory and set the global state.
   */
  const getPlayerInventory = (): void => {
    void CharacterApiService.getInventory().then((inventory: Inventory) => {
      dispatch(setInventory(inventory));
    })
  }

  /**
   * @description Function to buy an item from the trader and handle its after effects.
   */
  const buyItem = (): void => {
    void CharacterApiService.buyItemFromTrader(item.itemID, item.buyValue, traderId).then((status: number) => {
      handleItemActionFeedback(status);
      getPlayerInventory();
    })
  }

  /**
   * @description Function to sell an item to the trader and handle its after effects.
   */
  const sellItem = (): void => {
    void CharacterApiService.sellItemToTrader(item.itemID, item.sellValue, traderId).then((status: number) => {
      handleItemActionFeedback(status);
      getPlayerInventory();
    })
  }

  /**
   * @description Function to handle the after effects of the buy/sell action.
   * @param {number} status The http status code from the sell item to trader endpoint response
   */
  const handleItemActionFeedback = (status: number): void => {
    if (status === HttpStatus.OK) {
      getTrader();
      traderTab === "buy" ? getTradeItemsOfTrader() : getTradeItemsOfPlayer()
      CharacterApiService.getCharacter().then((character: Character) => {
        dispatch(setMoney(character.money));
      })
    }
    else if (status === HttpStatus.METHOD_NOT_ALLOWED) {
      enqueueSnackbar("You have no space in your inventory, to buy this item");
    }
    else if (status === HttpStatus.PAYMENT_REQUIRED) {
      enqueueSnackbar("You don't have enough money, to buy this item")
    }
    else if (status === HttpStatus.NOT_ACCEPTABLE) {
      enqueueSnackbar("You can't sell this item to the trader because he doesn't have enough money")
    }
    else if (status !== HttpStatus.OK) {
      throw new Error("Unknown actionStatus: " + status)
    }
  }

  /**
   * @description Function to handle the button action according to the trader tab.
   */
  const handleTradeButtonAction = (): void => {
    if (traderTab === "buy") {
      buyItem();
    }
    else {
      sellItem();
    }
  }

  return (
    <Grid container className="trader-list-item-container">
      <Grid item>
        <img className="trader-list-item-image" src={`/resources/items/${item.displayName}.png`} />
      </Grid>
      <Grid item className="trader-list-item-name">{item.displayName}</Grid>
      <Button variant="contained" className="trader-list-item-buy-btn" onClick={handleTradeButtonAction}>
        {traderTab === "buy" ? UiTexts.TRADER_BUY_BTN : UiTexts.TRADER_SELL_BTN} &nbsp;
        <Box className="trader-list-item-price">{traderTab === "buy" ? item.buyValue : item.sellValue}</Box>
        <img className="trader-list-item-gold" src={`/resources/ui/gold.png`} />
      </Button>
    </Grid>
  );
}
