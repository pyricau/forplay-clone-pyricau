/*
 * Copyright 2010 Google Inc.
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
package forplay.flash;

import flash.display.Sprite;
import forplay.flash.FlashCanvasLayer.CanvasElement;

import forplay.core.GroupLayer;
import forplay.core.Transform;

import forplay.core.Surface;
import forplay.core.SurfaceLayer;

/**
 *
 */
public class FlashSurfaceLayer extends FlashLayer implements SurfaceLayer {

  private FlashSurface surface;

  /**
   * @param width
   * @param height
   */
  public FlashSurfaceLayer(int width, int height) {
    super((Sprite) CanvasElement.create(width, height).cast());  
    surface = new FlashSurface(width, height, ((CanvasElement) display().cast()).getContext());
  }

  /* (non-Javadoc)
   * @see forplay.core.SurfaceLayer#surface()
   */
  @Override
  public Surface surface() {
    // TODO Auto-generated method stub
    return surface;
  }

 

}
