package de.szut.msp_backend.parser;

import de.szut.msp_backend.models.tradesystem.Trader;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TraderParserTest
{
  @Test
  void testGetTraders()
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
