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
    /**
     * This method shall parse the items from the JSON file "items.json" inside the resource path of this project
     *
     * @return a list of all parsed items
     */
    public static List<GenericItem> parseItems()
    {
        // Create an empty List of all the items that we want to parse
        final List<GenericItem> items = new ArrayList<>();
        // due to exceptions in the following code have a try/catch surrounding the code
        try
        {
            // Get the JSONObject fileJSON as the JSONfile that we want to parse
            final JSONObject fileJSON = new JSONObject(JSONLoader.getInstance().getResourceURL("items").toString());
            // Get The surrounding Array of the file
            // should only have 1 name, the outer array in general. There is no key present
            final JSONArray outerArray = fileJSON.names();
            // go into a for loop due to inner array of the first array
            for (int i = 0; i < outerArray.length(); i++)
            {
                // for each of these inner arrays get the array as a variable
                final JSONArray innerArray = outerArray.getJSONArray(i);
                // parse the items inside the inner arrays to itemList and add them to our total items list
                items.addAll(parseJSONArrayToItems(innerArray));
            }
        }
        // in case an exception is thrown for accessing something that doesnt exist for example
        catch (final JSONException e)
        {
            // throw a new runtimeexception so our program still continnues
            throw new RuntimeException(e);
        }
        // return all our parsed items as a list
        return items;
    }

    /**
     * This method shall parse the jsonArray containing all the items to an itemList
     *
     * @param jsonArray The JSONArray containing all our items that we want to parse
     * @return the list of items that is being returned
     * @throws JSONException the exception to throw when something goes wrong
     */
    private static List<GenericItem> parseJSONArrayToItems(final JSONArray jsonArray) throws JSONException
    {
        // Initialize the list to store the parsed items
        final List<GenericItem> items = new ArrayList<>();

        // for each array item inside the JSONArray
        for (int i = 0; i < jsonArray.length(); i++)
        {
            // get the JSONOBject containing the item inside the JSONarray
            final JSONObject itemAsObject = jsonArray.getJSONObject(i);
            // parse the item inside the jsonobject and add it to our itemlist
            items.add(parseItemJSONObject(itemAsObject));
        }
        // return the itemlist
        return items;
    }

    /**
     * This method shall parse a single item given in the JSONObject
     *
     * @param itemAsObject the object containing the item
     * @return the item that has been parsed
     * @throws JSONException the exception to throw when something goes wrong
     */
    private static GenericItem parseItemJSONObject(final JSONObject itemAsObject) throws JSONException
    {
        // Initialize the item at the start for easy returning later on
        GenericItem item = null;
        // itemId, displayName, description, standardValue, itemType, rarity, (healthGain || damage) -> int, String, String, int, String, String, int

        // get the itemtype of the item from the JSONObject
        final ItemType itemType = ItemType.valueOf(itemAsObject.getString("itemType").toUpperCase(Locale.GERMAN));
        // get the rarity of the item from the JSONObject
        final Rarity rarity = Rarity.valueOf(itemAsObject.getString("rarity").toUpperCase(Locale.GERMAN));
        // get the name of the item from the JSONObject
        final String name = itemAsObject.getString("displayName");
        // get the description of the item from the JSONObject
        final String description = itemAsObject.getString("description");
        // get the itemID of the item from the JSONObject
        final int itemId = itemAsObject.getInt("itemId");
        // get the standardvalue of the item from the JSONObject
        final int standardValue = itemAsObject.getInt("standardValue");
        // if the item is a consumable
        if (itemType.equals(ItemType.CONSUMABLE))
        {
            // get the healthgain of the item from the JSONObject
            final int healthgain = itemAsObject.getInt("healthgain");
            // set item to a new instance of a consumable
            item = new Consumable(itemId, name, description, standardValue, rarity, healthgain);
        }
        //if the item is a weapon
        else if (itemType.equals(ItemType.WEAPON))
        {
            // get the damage of the item from the JSONObject
            final int damage = itemAsObject.getInt("damage");
            // set item to a new instance of a weapon
            item = new Weapon(itemId, name, description, standardValue, rarity, damage);
        }
        // if the item is a fish
        else if (ItemType.equals(ItemType.FISH))
        {
            // set item to a new instance of a fish
            item = new Fish(itemId, name, description, standardValue, rarity);
        }
        // if the item is just a normal item
        else if (itemType.equals(ItemType.ITEM))
        {
            // set item to a new instance of a item
            item = new Item(itemId, name, description, standardValue, rarity);
        }

        // return the parsed item
        return item;
    }

    /**
     * This method shall get an item from its itemID
     *
     * @param itemID the itemId of the item that shall be gotten
     * @return the item that has been found
     * @throws ItemNotFoundException the exception to throw if the itemid does not have an item affliated with it
     */
    public static GenericItem getGenericItemFromID(final int itemID) throws ItemNotFoundException
    {
        // get all the items that are in the game
        final List<GenericItem> items = parseItems();

        // for each of the items in the game
        for (final GenericItem item : items)
        {
            // if the itemid of the given item is the same as the itemid that we are looking for
            if (item.getItemID() == itemID)
            {
                // return the item
                return item;
            }
        }
        // after going through every item in the game we have not found our wanted item, so throw an exception stating we did not find the item
        throw new ItemNotFoundException("The Item with the given itemID could not be found");
    }
}
