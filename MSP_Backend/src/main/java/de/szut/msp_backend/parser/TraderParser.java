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
  public static List<Trader> parseTraders()
  {
    final List<Trader> traders = new ArrayList<>();
    try
    {
      final JSONObject object = new JSONObject(JSONLoader.getInstance().getResourceURL("traders").toString());
      final JSONArray outerArray = object.names(); // should only have 1 name, the outer array in general. There is no key present
      for (int i = 0; i < outerArray.length(); i++)
      {
        JSONObject traderObject = outerArray.getJSONObject(i);
        traders.add(parseJSONArrayToTrader(traderObject));
      }
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
    return traders;
  }

  private static Trader parseJSONArrayToTrader(final JSONObject traderAsObject) throws JSONException
  {
    // name, money, items -> String, int, Array (id, amount -> int, int)
    final String name = traderAsObject.optString("name");
    final int money = traderAsObject.optInt("money");
    final Map<GenericItem, Integer> items = new HashMap<>();
    final JSONArray itemsArray = traderAsObject.optJSONArray("items");
    for (int i = 0; i < itemsArray.length(); i++)
    {
      final JSONObject itemObject = itemsArray.getJSONObject(i);
      try
      {
        items.put(ItemParser.getGenericItemFromID(itemObject.optInt("id")), itemObject.getInt("amount"));
      }
      catch (ItemNotFoundException e)
      {
        //Ignore this as it should never ever happen at all
      }
    }

    final Trader trader = new Trader(name, money);
    trader.populateInventory(items);

    return trader;
  }
}
