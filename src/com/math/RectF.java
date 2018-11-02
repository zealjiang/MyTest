package com.math;

import org.w3c.dom.css.Rect;

public class RectF {

	public float left;
    public float top;
    public float right;
    public float bottom;
    
    /**
     * Create a new empty RectF. All coordinates are initialized to 0.
     */
    public RectF() {}

    /**
     * Create a new rectangle with the specified coordinates. Note: no range
     * checking is performed, so the caller must ensure that left <= right and
     * top <= bottom.
     *
     * @param left   The X coordinate of the left side of the rectangle
     * @param top    The Y coordinate of the top of the rectangle
     * @param right  The X coordinate of the right side of the rectangle
     * @param bottom The Y coordinate of the bottom of the rectangle
     */
    public RectF(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    /**
     * Create a new rectangle, initialized with the values in the specified
     * rectangle (which is left unmodified).
     *
     * @param r The rectangle whose coordinates are copied into the new
     *          rectangle.
     */
    public RectF(RectF r) {
        if (r == null) {
            left = top = right = bottom = 0.0f;
        } else {
            left = r.left;
            top = r.top;
            right = r.right;
            bottom = r.bottom;
        }
    }
    
    public void set(float left,float top,float right,float bottom) {
    	this.left = left;
    	this.top = top;
    	this.right = right;
    	this.bottom = bottom;
    }
}
