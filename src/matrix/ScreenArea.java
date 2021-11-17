package matrix;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public final class ScreenArea {
	public static final Rectangle RECTANGLE;
	public static final int 
		LEFT, RIGHT, 
		TOP, BOTTOM, 
		MIN_WIDTH, MAX_WIDTH, 
		MIN_HEIGHT, MAX_HEIGHT, 
		TOTAL_WIDTH, TOTAL_HEIGHT;
	
	static {
		// Initialise local vars
		int left, right, top, bottom, 
			minWidth, maxWidth, minHeight, maxHeight;
		left = top = minWidth = minHeight = Integer.MAX_VALUE;
		right = bottom = maxWidth = maxHeight = Integer.MIN_VALUE;
		// In a single loop process all bounds
		Rectangle bounds;
		for (GraphicsDevice device : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
			bounds = device.getDefaultConfiguration().getBounds();
			if (left > bounds.x)
				left = bounds.x;
			if (right < bounds.x + bounds.width)
				right = bounds.x + bounds.width;
			if (top > bounds.y)
				top = bounds.y;
			if (bottom < bounds.y + bounds.height)
				bottom = bounds.y + bounds.height;
			if (minWidth > bounds.width)
				minWidth = bounds.width;
			if (maxWidth < bounds.width)
				maxWidth = bounds.width;
			if (minHeight > bounds.height)
				minHeight = bounds.height;
			if (maxHeight < bounds.height)
				maxHeight = bounds.height;
		}
		TOTAL_WIDTH = right - left;
		TOTAL_HEIGHT = bottom - top;
		RECTANGLE = new Rectangle(TOTAL_WIDTH, TOTAL_HEIGHT);
		// Transfer local to immutable global vars
		LEFT = left; 
		RIGHT = right; 
		TOP = top; 
		BOTTOM = bottom;
		MIN_WIDTH = minWidth; 
		MAX_WIDTH = maxWidth;
		MIN_HEIGHT = minHeight; 
		MAX_HEIGHT = maxHeight;
	}
}