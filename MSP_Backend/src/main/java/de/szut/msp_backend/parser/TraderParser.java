package de.szut.msp_backend.parser;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import de.szut.msp_backend.exceptions.ItemNotFoundException;
import de.szut.msp_backend.models.tradesystem.Trader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

import static org.springframework.util.ResourceUtils.getFile;

public class TraderParser
{
  public static final Logger LOGGER = LoggerFactory.getLogger(TraderParser.class);

  public static final List<Trader> TRADERS = getTraderList();

  private static List<Trader> getTraderList()
  {
    List<Trader> allTraders = new ArrayList<>();
    try
    {
      allTraders.addAll(getTradersFromJson(getFile("classpath:traders.json"), new TypeToken<List<Trader>>()
      {
      }.getType()));
    }
    catch(FileNotFoundException e)
    {
      LOGGER.info(e.getMessage());
    }
    return allTraders;
  }

  public static List<Trader> getTraders()
  {
    return TRADERS;
  }


  private static List<Trader> getTradersFromJson(File tradersListFile, Type type)
  {
    FileReader fileReader;
    try
    {
      fileReader = new FileReader(tradersListFile);
    }
    catch (FileNotFoundException e)
    {
      throw new RuntimeException(e);
    }
    List<Trader> traders = new GsonBuilder().registerTypeAdapter(Trader.class, new InstanceCreator<Trader>()
    {
      @Override
      public Trader createInstance(Type type)
      {
        return new Trader(0, "", 0);
      }
    }).create().fromJson(fileReader, type);
    try
    {
      fileReader = new FileReader(tradersListFile);
      JsonReader reader = new Gson().newJsonReader(fileReader);
      reader.beginArray();
      for (Trader trader : traders)
      {
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
          reader.beginObject();
          reader.nextName();
          int itemID = reader.nextInt();
          reader.nextName();
          int amount = reader.nextInt();

          trader.getInventory().addItem(ItemParser.getGenericItemById(itemID), amount);
          reader.endObject();
        }
        reader.endArray();
        reader.endObject();
      }
    }
    catch (IOException | ItemNotFoundException e)
    {
      LOGGER.info(e.getMessage());
    }
    return traders;
  }
}
