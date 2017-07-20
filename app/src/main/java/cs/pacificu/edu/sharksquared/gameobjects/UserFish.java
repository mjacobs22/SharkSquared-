package cs.pacificu.edu.sharksquared.gameobjects;//package cs.pacificu.edu.sharksquared.gameobjects;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.Display;
import android.view.View;

import cs.pacificu.edu.sharksquared.R;
import cs.pacificu.edu.sharksquared.gameobjects.Fish;

import static cs.pacificu.edu.sharksquared.gameobjects.Direction.direction.NORTHEAST;
import static cs.pacificu.edu.sharksquared.gameobjects.Direction.direction.SOUTH;

import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * @author Cormac Gleeson on 1/21/2017.
 */

public class UserFish extends Fish
{

  private static int USER_DRAWABLE = R.drawable.rainbowfish;
  private final static float START_SIZE = 0.1f;
  private int mStartWidth = getWidth() / 2;
  private int mStartHeight = getHeight();
  private final static float INCR_SIZE = 0.08f;

  public UserFish (Context context, Display display)
  {
    super (context, display, USER_DRAWABLE, 1300, 1200, 10, 8);

    setScale (START_SIZE);
    setSpriteWidth (getSpriteWidth () * getScale ());
    setSpriteHeight (getSpriteHeight () * getScale ());
  }

  @Override
  public void doDraw (Canvas canvas)
  {
    float width, height;
    canvas.save ();
    canvas.scale (getScale (), getScale (), getLeftCoordinate (),
        getTopCoordinate ());
    canvas
        .drawBitmap (mSpriteImage, (this.mLeftCoordinate), this.mTopCoordinate,
            null);
    canvas.restore ();

    width = getSpriteWidth ();
    height = getSpriteHeight ();

    if (getNeedSet ())
    {
      Log.d ("This is the old height", +height + "");
      if (getNeedSet ())
      {
        setSpriteWidth (width + (width * getScale ()));
        setSpriteHeight (height + (height * getScale ()));
        setNeedSet (false);
        Log.d ("This is the new width", getSpriteWidth () + "");
        Log.d ("This is the new height", getSpriteHeight () + "");
        Log.d ("bNeedSet", getNeedSet () + "");
      }
      Log.d ("Scale is", getScale () + "");
    }
  }

  public void eat (Fish dinner)
  {
    if (getSize () > dinner.getSize ())
    {
      setSize (getSize () + dinner.getSize ());

      setNeedSet (true);

      dinner.die ();
    }
    else if (getSize () < dinner.getSize ())
    {
      die ();
    }
  }

  public void move ()
  {
    if (!offScreen ())
    {
      super.move ();
    }

    if (offScreen ())
    {
      bounce ();
      super.move ();
      //setDirection(Direction.direction.NONE);
    }
  }

  public void bounce ()
  {
    if (getDirection () == Direction.direction.NORTH)
    {
      setDirection (Direction.direction.SOUTH);
    }
    else if (getDirection () == Direction.direction.SOUTH)
    {
      setDirection (Direction.direction.NORTH);
    }
    else if (getDirection () == Direction.direction.EAST)
    {
      setDirection (Direction.direction.WEST);
    }
    else if (getDirection () == Direction.direction.WEST)
    {
      setDirection (Direction.direction.EAST);
    }
    else if (getDirection () == Direction.direction.NORTHEAST)
    {
      if (getLeftCoordinate () + getSpriteWidth () >= getDisplayWidth ())
      {
        setDirection (Direction.direction.NORTHWEST);
      }
      else if (getTopCoordinate () <= 0)
      {
        setDirection (Direction.direction.SOUTHEAST);
      }
    }
    else if (getDirection () == Direction.direction.SOUTHEAST)
    {
      if (getLeftCoordinate () + getSpriteWidth () >= getDisplayWidth ())
      {
        setDirection (Direction.direction.SOUTHWEST);
      }
      else if (getTopCoordinate () + getSpriteHeight () >= getDisplayHeight ())
      {
        setDirection (Direction.direction.NORTHEAST);
      }
    }
    else if (getDirection () == Direction.direction.SOUTHWEST)
    {
      if (getLeftCoordinate () <= 0)
      {
        setDirection (Direction.direction.SOUTHEAST);
      }
      else if (getTopCoordinate () + getSpriteHeight () >= getDisplayHeight ())
      {
        setDirection (Direction.direction.NORTHWEST);
      }
    }
    else if (getDirection () == Direction.direction.NORTHWEST)
    {
      if (getLeftCoordinate () <= 0)
      {
        setDirection (Direction.direction.NORTHEAST);
      }
      else if (getTopCoordinate () <= 0)
      {
        setDirection (Direction.direction.SOUTHWEST);
      }
    }
  }

  public boolean checkDie (SharkFish shark)
  {
    return (collide (shark));
  }

}