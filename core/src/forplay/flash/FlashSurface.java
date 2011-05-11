// Copyright 2011 Google Inc. All Rights Reserved.

package forplay.flash;

import forplay.core.Surface;

import forplay.core.Canvas.Composite;
import forplay.core.Canvas.LineCap;
import forplay.core.Canvas.LineJoin;
import forplay.core.ForPlay;
import forplay.core.Gradient;
import forplay.core.Image;
import forplay.core.Path;
import forplay.core.Pattern;
import forplay.flash.FlashCanvasLayer.Context2d;

/**
 * @author cromwellian@google.com (Your Name Here)
 *
 */
public class FlashSurface implements Surface {
  private final int width, height;
  private boolean dirty = true;
  private final Context2d context2d;

  FlashSurface(int width, int height, Context2d context2d) {
    this.width = width;
    this.height = height;
    this.context2d = context2d;
  }

  @Override
  public void clear() {
    dirty = true;
  }


  @Override
  public void drawImage(Image img, float x, float y) {
    assert img instanceof FlashImage;
    dirty = true;
    context2d.drawImage(((FlashImage) img).bitmapData(), x, y);
  }

  @Override
  public void drawImage(Image img, float x, float y, float w, float h) {
    assert img instanceof FlashImage;
    dirty = true;
    context2d.drawImage(((FlashImage) img).bitmapData(), x, y);
  }

  @Override
  public void drawImage(Image img, float dx, float dy, float dw, float dh,
      float sx, float sy, float sw, float sh) {
    assert img instanceof FlashImage;
    dirty = true;
    context2d.drawImage(((FlashImage) img).bitmapData(), dx, dy, dw, dh, sx, sy, sw, sh);
  }

  @Override
  public void drawImageCentered(Image img, float x, float y) {
    drawImage(img, x - img.width()/2, y - img.height()/2);
    dirty = true;
  }

 
  @Override
  public void fillRect(float x, float y, float w, float h) {
    dirty = true;
  
  }

  @Override
  public final int height() {
    return height;
  }

  @Override
  public void restore() {
    context2d.restore();
  }

  @Override
  public void rotate(float radians) {
    context2d.rotate(radians);
  }

  @Override
  public void save() {
    context2d.save();
  }

  @Override
  public void scale(float x, float y) {
    context2d.scale(x,y);
  }

 

  @Override
  public void setFillColor(int color) {
  }


  @Override
  public void setFillPattern(Pattern pattern) {
  }

 

  @Override
  public void setTransform(float m11, float m12, float m21, float m22, float dx, float dy) {
    context2d.setTransform(m11, m12, m21, m22, dx, dy);
  }

  @Override
  public void transform(float m11, float m12, float m21, float m22, float dx,
      float dy) {
    context2d.transform(m11, m12, m21, m22, dx, dy);
  }

  @Override
  public void translate(float x, float y) {
    context2d.translate(x,y);
  }

  @Override
  public final int width() {
    return width;
  }


  void clearDirty() {
    dirty = false;
  }

  boolean dirty() {
    return dirty;
  }

  /* (non-Javadoc)
   * @see forplay.core.Surface#drawLine(float, float, float, float, float)
   */
  @Override
  public void drawLine(float x0, float y0, float x1, float y1, float width) {
    // TODO Auto-generated method stub
    
  }

 
}
