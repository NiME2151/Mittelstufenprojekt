package de.szut.msp_backend.parser;

import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.item.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ItemParser
{
    public static List<GenericItem> parseItems()
    {
        final List<GenericItem> items = new ArrayList<>();
        try
        {
            final JSONObject object = new JSONObject(JSONLoader.getInstance().getResourceURL("items").toString());
            final JSONArray outerArray = object.names(); // should only have 1 name, the outer array in general. There is no key present
            for (int i = 0; i < outerArray.length(); i++)
            {
                JSONArray innerArray = outerArray.getJSONArray(i);
                items.addAll(parseJSONArrayToItems(innerArray));
            }
        }
        catch (JSONException e)
        {
            throw new RuntimeException(e);
        }
        return items;
    }

    private static List<GenericItem> parseJSONArrayToItems(final JSONArray jsonArray) throws JSONException
    {
        final List<GenericItem> items = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++)
        {
            final JSONObject itemAsObject = jsonArray.getJSONObject(i);
            items.add(parseItemJSONObject(itemAsObject));
        }

        return items;
    }

    private static GenericItem parseItemJSONObject(final JSONObject itemAsObject) throws JSONException
    {
        GenericItem item = null;
        // itemId, displayName, description, standardValue, itemType, rarity, (healthGain || damage) -> int, String, String, int, String, String, int
        final String itemTypeString = itemAsObject.getString("itemType");
        final ItemType itemType = ItemType.valueOf(itemTypeString.toUpperCase(Locale.GERMAN));
        final Rarity rarity = Rarity.valueOf(itemAsObject.getString("rarity").toUpperCase(Locale.GERMAN));
        final String name = itemAsObject.getString("displayName");
        final String description = itemAsObject.getString("description");
        final int itemId = itemAsObject.getInt("itemId");
        final int standardValue = itemAsObject.getInt("standardValue");
        if (itemType.equals(ItemType.CONSUMABLE))
        {
            final int healthgain = itemAsObject.getInt("healthgain");
            item = new Consumable(itemId, name, description, standardValue, rarity, healthgain);
        }
        else if (itemType.equals(ItemType.WEAPON))
        {
            final int damage = itemAsObject.getInt("damage");
            item = new Weapon(itemId, name, description, standardValue, rarity, damage);
        }
        else if (ItemType.equals(ItemType.FISH))
        {
            item = new Fish(itemId, name, description, standardValue, rarity);
        }
        else if (itemType.equals(ItemType.ITEM))
        {
            item = new Item(itemId, name, description, standardValue, rarity);
        }


        return item;
    }

    public static GenericItem getGenericItemFromID(final int itemID) throws ItemNotFoundException
    {
        final List<GenericItem> items = parseItems();

        for (GenericItem item : items)
        {
            if (item.getItemID() == itemID)
            {
                return item;
            }
        }

        throw new ItemNotFoundException("The Item with the given itemID could not be found");
    }
}
