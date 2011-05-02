/**
 * Copyright 2010 The ForPlay Authors
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
package forplay.core;

/**
 * Utility methods for working with packed-integer colors.
 */
public class Color {

  /**
   * Creates a packed integer color from four ARGB values in the range [0, 255].
   */
  public static int argb(int a, int r, int g, int b) {
    return (a << 24) | (r << 16) | (g << 8) | b;
  }

  /**
   * Creates a packed integer color from three RGB values in the range [0, 255].
   */
  public static int rgb(int r, int g, int b) {
    return argb(0xff, r, g, b);
  }
}
