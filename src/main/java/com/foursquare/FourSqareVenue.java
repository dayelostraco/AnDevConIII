package com.foursquare;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import com.arkit.ARSphericalView;

public class FourSqareVenue extends ARSphericalView
{
	public String name;
	public int checkins;
	public FourSqareVenue(Context ctx)
	{
		super(ctx);
		inclination = 0;
	}

	public void draw(Canvas c)
	{

		p.setColor(Color.WHITE);
		if(name != null)
			c.drawText(name, getLeft(), getTop(), p);
			
	}
}
