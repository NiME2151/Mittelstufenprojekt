package de.szut.msp_backend;

import de.szut.msp_backend.models.item.GenericItem;
import de.szut.msp_backend.models.tradesystem.Trader;
import de.szut.msp_backend.parser.TraderParser;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TraderParserTest
{
  @Test
  public void TestTraderParser()
  {
    List<Trader> traders = TraderParser.getTraders();
    System.out.println(traders.size());
    for (Trader trader : traders)
    {
      System.out.println(trader);
      System.out.println();
    }
  }
}
