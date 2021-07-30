package unsw.loopmania.Goals;

import unsw.loopmania.Character;

public abstract class Observer {
  protected Character character;
  protected Goal goal;

  /**
   * Update the status of the goal depending on its quantity and type
   */
  public abstract void update();
}
