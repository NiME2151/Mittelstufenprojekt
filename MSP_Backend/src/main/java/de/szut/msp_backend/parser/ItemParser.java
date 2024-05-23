package de.szut.msp_backend.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.item.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;

import static org.springframework.util.ResourceUtils.getFile;

public class ItemParser
{
    public static final Logger LOGGER = LoggerFactory.getLogger(ItemParser.class);

    public static final List<GenericItem> ITEMS = getItemList();

    public static List<GenericItem> getItemList()
    {
        List<GenericItem> allItems = new ArrayList<>();
        try
        {
            allItems.addAll(getItemsFromJson(getFile("classpath:items.json"), new TypeToken<List<Item>>()
            {
            }.getType()));
            allItems.addAll(getItemsFromJson(getFile("classpath:consumables.json"), new TypeToken<List<Consumable>>()
            {
            }.getType()));
            allItems.addAll(getItemsFromJson(getFile("classpath:weapons.json"), new TypeToken<List<Weapon>>()
            {
            }.getType()));
        }
        catch (FileNotFoundException e)
        {
            LOGGER.info(e.getMessage());
        }
        return allItems;
    }

    private static <T extends GenericItem> List<T> getItemsFromJson(File itemsListFile, Type type)
    {
        FileReader fileReader;
        try
        {
            fileReader = new FileReader(itemsListFile);
        }
        catch (FileNotFoundException e)
        {
            LOGGER.error("File with the name: '" + itemsListFile + "' could not be found");
            return List.of();
        }
        return new Gson().fromJson(fileReader, type);
    }

    public static GenericItem getGenericItemById(int itemId) throws ItemNotFoundException
    {
        for (GenericItem item : ITEMS)
        {
            if (item.getItemID() == itemId)
            {
                return item;
            }
        }
        throw new ItemNotFoundException("The consumable with the given item id: ‘" + itemId + "' could not be found");
    }
}
