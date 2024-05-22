package de.szut.msp_backend.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.item.GenericItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ResourceUtils.getFile;

public class NodeLootParser
{
  public static final Logger LOGGER = LoggerFactory.getLogger(NodeLootParser.class);

  public static List<GenericItem> getFindableItems(final String lootTableName)
  {
    final List<GenericItem> items = new ArrayList<>();

    try
    {
      items.addAll(getItemsFromJson(getFile(String.format("classpath:%s.json", lootTableName))));
    }
    catch (FileNotFoundException e)
    {
      LOGGER.info(e.getMessage());
    }

    return items;
  }

  public static List<GenericItem> getItemsFromJson(final File file) throws FileNotFoundException
  {
    final List<GenericItem> items = new ArrayList<>();

    try
    {
      FileReader fileReader = new FileReader(file);
      JsonReader reader = new Gson().newJsonReader(fileReader);

      reader.beginArray();

      reader.beginObject();
      reader.nextName();
      reader.nextInt();
      reader.nextName();
      reader.nextString();
      reader.nextName();
      reader.nextInt();
      reader.nextName();
      reader.beginArray();
      while (!reader.peek().equals(JsonToken.END_ARRAY))
      {
      }
      reader.endArray();
      reader.endObject();

    }
    catch (IOException | ItemNotFoundException e)
    {
      LOGGER.info(e.getMessage());
    }

    return items;
  }
}
