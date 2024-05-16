package de.szut.msp_backend.parser;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.tradesystem.Trader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class TraderParser
{
  /**
   * This method shall parse the Traders in the traders.json file
   *
   * @return a list of the parsed traders
   */
  public static List<Trader> parseTraders()
  {
    // initialize the list of parsed traders
    final List<Trader> traders = new ArrayList<>();
    // due to possibly occuring errors surround the code with a try/catch
    try
    {
      // The JSONObject fileJSON as the traders.json file
      final JSONObject fileJSON = new JSONObject(JSONLoader.getInstance().getResourceURL("traders").toString());
      // Get the surrounding Array of the jsonfile
      // should only have 1 name, the outer array in general. There is no key present
      final JSONArray outerArray = fileJSON.names();
      // go into a for loop due to multiple inner objects being present
      for (int i = 0; i < outerArray.length(); i++)
      {
        // get the object containing the trader
        final JSONObject traderObject = outerArray.getJSONObject(i);
        // parse the trader from the traderobject and add it to the list of traders
        traders.add(parseJSONArrayToTrader(traderObject));
      }
    }
    // if an error occurs somewhere
    catch (final JSONException e)
    {
      // throw a runtimeexception to keep the application operable
      throw new RuntimeException(e);
    }
    // return the traders list
    return traders;
  }

  /**
   * This method shall parse the trader from a given jsonobject
   *
   * @param traderAsObject the jsonobject containing the trader
   * @return the parsed trader
   * @throws JSONException the exception that occurs if something goes wrong
   */
  private static Trader parseJSONArrayToTrader(final JSONObject traderAsObject) throws JSONException
  {
    // name, money, items -> String, int, Array (id, amount -> int, int)

    // The name of the trader from inside the jsonobject
    final String name = traderAsObject.getString("name");
    // The money of the trader from inside the jsonobject
    final int money = traderAsObject.getInt("money");
    // initialize the map for the items that the trader holds
    final Map<GenericItem, Integer> items = new HashMap<>();
    // The items of the trader from inside the jsonobject
    final JSONArray itemsArray = traderAsObject.getJSONArray("items");
    // for each gotten item
    for (int i = 0; i < itemsArray.length(); i++)
    {
      // get the object containing the item
      final JSONObject itemObject = itemsArray.getJSONObject(i);
      // surrounding try catch as necessary precaution if the itemid is faulty
      try
      {
        // put the item with its desired amount into the map of items
        items.put(ItemParser.getGenericItemFromID(itemObject.getInt("id")), itemObject.getInt("amount"));
      }
      // if the item was not found
      catch (final ItemNotFoundException e)
      {
        //Ignore this as it should never ever happen at all
      }
    }
    // initialize the trader with its name and its money
    final Trader trader = new Trader(name, money);
    // fill the inventory of the trader with the initial stock items
    trader.populateInventory(items);

    // return the trader
    return trader;
  }
}
