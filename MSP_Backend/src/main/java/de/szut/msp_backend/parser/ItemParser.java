package de.szut.msp_backend.parser;

import de.szut.msp_backend.item.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ItemParser
{
    public static List<GenericItem> parseItems()
    {
        List<GenericItem> items = new ArrayList<>();

        FileReader fileReader = JSONLoader.getInstance().getResourceFileFromName("items");

        //get the 3 arrays containg item, consumable and weapon
        List<String> itemList = getItemList(fileReader);

        for (String itemsList : itemList)
        {
            //gets all items from each itemList
            List<GenericItem> newList = getItemsFromItemArrayString(itemsList);
            items.addAll(newList);
        }

        return items;
    }

    public static List<String> getItemList(FileReader fileReader)
    {
        BufferedReader reader = new BufferedReader(fileReader);
        List<String> itemArraysList = new ArrayList<>();
        String temporaryHelperString = "";
        boolean getLines = false;
        int linecount = 0;
        for (String line : (String[])reader.lines().toArray())
        {
            linecount++;
            if (linecount == 1)
            {
                continue;
            }
            if (line.contains("["))
            {
                getLines = true;
            }
            else if (line.contains("]") && linecount != reader.lines().count() -1)
            {
                getLines = false;
                itemArraysList.add(temporaryHelperString);
                temporaryHelperString = "";
            }
            if (getLines)
            {
                temporaryHelperString += line;
            }
        }

        return itemArraysList;
    }

    public static List<GenericItem> getItemsFromItemArrayString(String items)
    {
        List<GenericItem> itemsList = new ArrayList<>();
        String[] singleItemsArray = items.split(",");
        for (String singleItem : singleItemsArray)
        {
            int itemId = 0;
            String displayName = "";
            String description = "";
            int standardValue = 0;
            ItemType itemType = null;
            Rarity rarity = null;
            int healthGain = 0;
            int damage = 0;
            for (String line : singleItem.split("\n"))
            {
                if (line.contains(":"))
                {
                    String[] fragments = line.split(":");
                    switch (fragments[0].toLowerCase().strip())
                    {
                        case "itemid":
                            itemId = Integer.parseInt(fragments[1].strip());
                            break;
                        case "displayname":
                            displayName = fragments[1].strip();
                            break;
                        case "description":
                            description = fragments[1].strip();
                            break;
                        case "standardvalue":
                            standardValue = Integer.parseInt(fragments[1].strip());
                            break;
                        case "itemtype":
                            itemType = ItemType.valueOf(fragments[1].toUpperCase().strip());
                            break;
                        case "rarity":
                            rarity = Rarity.valueOf(fragments[1].toUpperCase().strip());
                            break;
                        case "healthgain":
                            healthGain = Integer.parseInt(fragments[1].strip());
                            break;
                        case "damage":
                            damage = Integer.parseInt(fragments[1].strip());
                            break;
                        default:
                            break;
                    }
                }
            }
            switch (itemType)
            {
                case Item -> itemsList.add(new Item(itemId, displayName, description, standardValue, rarity));
                case Consumable -> itemsList.add(new Consumable(itemId, displayName, description, standardValue, rarity, healthGain));
                case Weapon -> itemsList.add(new Weapon(itemId, displayName, description, standardValue, rarity, damage));
                default -> System.out.println("I ran into a problem with the corresponding itemtype while parsing json file");
            }
        }
        return itemsList;
    }
}
