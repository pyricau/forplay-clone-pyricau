/*
 * Copyright 2011 Google Inc.
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
package flash.display;

import com.google.gwt.core.client.JavaScriptObject;

import flash.gwt.FlashImport;

/**
 */
@FlashImport("flash.text.TextField")
public class TextField extends JavaScriptObject {

  protected TextField() {
  }

  public static native TextField create() /*-{
    return new flash.text.TextField();
  }-*/;

  final public native String getText() /*-{
        return this.text;
    }-*/;

  final public native void setText(String text) /*-{
        this.text = text;
    }-*/;

  final public native int getTextColor() /*-{
        return this.textColor;
    }-*/;

  final public native void setTextColor(int textColor) /*-{
        this.textColor = textColor;
    }-*/;
}
