package de.szut.msp_backend.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.item.Lootable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.ResourceUtils.getFile;

public class NodeLootParser
{
  public static final Logger LOGGER = LoggerFactory.getLogger(NodeLootParser.class);

  public static Map<GenericItem, Lootable>  getFindableItems(final String lootTableName)
  {
    Map<GenericItem, Lootable> items = new HashMap<GenericItem, Lootable>();

    try
    {
      items = getItemsFromJson(getFile(String.format("classpath:%s.json", lootTableName)));
    }
    catch (FileNotFoundException e)
    {
      LOGGER.info(e.getMessage());
    }

    return items;
  }

  public static Map<GenericItem, Lootable> getItemsFromJson(final File file) throws FileNotFoundException
  {
    final Map<GenericItem, Lootable> items = new HashMap<>();

    try
    {
      FileReader fileReader = new FileReader(file);
      JsonReader reader = new Gson().newJsonReader(fileReader);

      reader.beginArray();

      while (reader.peek() != JsonToken.END_ARRAY)
      {
        reader.beginObject();

          reader.nextName();
          GenericItem item = ItemParser.getGenericItemById(reader.nextInt());
          reader.nextName();
          Lootable lootable = new Lootable(reader.nextInt(), 0);

        items.put(item, lootable);
        reader.endObject();
      }
    }
    catch (IOException | ItemNotFoundException e)
    {
      LOGGER.info(e.getMessage());
    }

    return items;
  }
}
