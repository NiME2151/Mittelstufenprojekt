package de.szut.msp_backend.models.item;

public class Lootable
{
  private boolean isAvailable;
  private final int clicksNeededPerRespawn;
  private int clicksTillRespawn;
  private int oldClicks;

  public Lootable(final int clicksNeededPerRespawn, final int oldClicks)
  {
    this.clicksNeededPerRespawn = clicksNeededPerRespawn;
    this.clicksTillRespawn = 0;
    this.isAvailable = true;
    this.oldClicks = oldClicks;
  }

  public boolean isAvailable()
  {
    return isAvailable;
  }

  public void update(final int clicks)
  {
    if (isAvailable)
    {
      return;
    }
    clicksTillRespawn -= clicks - oldClicks;
    if (clicksTillRespawn <= 0)
    {
      clicksTillRespawn = 0;
    }
    oldClicks = clicks;
    isAvailable = true;
  }

  public void lootItem()
  {
    isAvailable = false;
    clicksTillRespawn = clicksNeededPerRespawn;
  }

  @Override
  public String toString()
  {
    return String.format("isAvailable:\t\t\t\t%s\nclicksNeededPerRespawn:\t%d\nclicksTillRespawn:\t\t%d\noldClicks:\t\t\t%d\n", isAvailable, clicksNeededPerRespawn, clicksTillRespawn, oldClicks);
  }
}
