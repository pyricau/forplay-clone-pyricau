/**
 * Copyright 2011 The ForPlay Authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package forplay.android;

import forplay.core.Image;
import forplay.core.ImageLayer;

class AndroidImageLayer extends AndroidLayer implements ImageLayer {

  private float width, height;
  private boolean widthSet, heightSet;
  private float sx, sy, sw, sh;
  private boolean sourceRectSet;
  private boolean repeatX, repeatY;

  private AndroidImage image;

  AndroidImageLayer() {
  }

  AndroidImageLayer(AndroidImage image) {
    this.image = image;
  }

  @Override
  public void clearHeight() {
    heightSet = false;
  }

  @Override
  public void clearSourceRect() {
    sourceRectSet = false;
  }

  @Override
  public void clearWidth() {
    widthSet = false;
  }

  @Override
  public Image image() {
    return image;
  }

  @Override
  public void setHeight(float height) {
    assert height > 0;

    heightSet = true;
    if (this.height != height) {
      this.height = height;
    }
  }

  @Override
  public void setImage(Image image) {
    assert image instanceof AndroidImage;
    this.image = (AndroidImage) image;
  }

  @Override
  public void setRepeatX(boolean repeat) {
    if (repeat) {
      assert !sourceRectSet;
    }

    if (repeatX != repeat) {
      repeatX = repeat;
    }
  }

  @Override
  public void setRepeatY(boolean repeat) {
    if (repeat) {
      assert !sourceRectSet;
    }

    if (repeatY != repeat) {
      repeatY = repeat;
    }
  }

  @Override
  public void setSize(float width, float height) {
    setWidth(width);
    setHeight(height);
  }

  @Override
  public void setSourceRect(float sx, float sy, float sw, float sh) {
    assert !repeatX && !repeatY;

    sourceRectSet = true;
    this.sx = sx;
    this.sy = sy;
    this.sw = sw;
    this.sh = sh;
  }

  @Override
  public void setWidth(float width) {
    assert width > 0;

    widthSet = true;
    if (this.width != width) {
      this.width = width;
    }
  }

  @Override
  void paint(AndroidCanvas canvas) {
    canvas.save();
    transform(canvas);

    float dw = widthSet ? width : image.width();
    float dh = heightSet ? height : image.height();

    if (repeatX || repeatY) {
      // TODO(jgw): something to make this repeat properly.
      canvas.drawImage(image, 0, 0, dw, dh);
    } else if (sourceRectSet) {
      canvas.drawImage(image, 0, 0, dw, dh, sx, sy, sw, sh);
    } else {
      canvas.drawImage(image, 0, 0, dw, dh);
    }

    canvas.restore();
  }
}
