package cs.pacificu.edu.sharksquared.gameobjects;

import android.content.Context;
import android.view.Display;

import java.util.Random;

import cs.pacificu.edu.sharksquared.R;

/**
 * A garbage fish that randomly spawn on edges of the screen
 */

public class GarbageFish extends Fish
{
  private final static int drawable = R.drawable.newuserfish;
  private Random randSpawn;
  private int test = 0; //Delete this.

  /**
   * Constructor which randomly assigns spawn points to garbage fish
   * @param context the current application resources
   * @param display to be displayed to
   */
  public GarbageFish (Context context, Display display)
  {
    super (context, display, drawable, 0, 0, 2, 1);
    randSpawn = new Random ();
    int topCoord, leftCoord;

    topCoord = randSpawn.nextInt (getDisplayHeight ());
    setTopCoord (topCoord);

    leftCoord = randSpawn.nextInt (2);
    if (leftCoord == 0)
    {
      leftCoord = -25;
      setDirection (Direction.direction.EAST);
    }
    else
    {
      leftCoord = getDisplayWidth () + 25;
      setDirection (Direction.direction.WEST);
      setSpriteImage (flipBitmap (getSpriteImage ()));
    }
    setLeftCoord (leftCoord);

  }

}
