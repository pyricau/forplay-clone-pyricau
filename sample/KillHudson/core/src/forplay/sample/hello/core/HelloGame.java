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
package forplay.sample.hello.core;

import static forplay.core.ForPlay.*;

import java.util.ArrayList;
import java.util.List;

import forplay.core.Game;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ImageLayer;
import forplay.core.Pointer;

public class HelloGame implements Game, Pointer.Listener {

    private GroupLayer hudsonLayer;
    private List<Hudson> hudsons = new ArrayList<Hudson>(0);

    @Override
    public void init() {
        // create and add background image layer
        Image bgImage = assetManager().getImage("images/bg.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

        // create a group layer to hold the hudsons
        hudsonLayer = graphics().createGroupLayer();
        graphics().rootLayer().add(hudsonLayer);

        // preload the pea image into the asset manager cache
        assetManager().getImage(Hudson.IMAGE);

        // add a listener for pointer (mouse, touch) input
        pointer().setListener(this);
    }

    @Override
    public void onPointerDrag(float x, float y) {
    }

    @Override
    public void onPointerEnd(float x, float y) {
        Hudson hudson = new Hudson(hudsonLayer, x, y);
        hudsons.add(hudson);
    }

    @Override
    public void onPointerStart(float x, float y) {
    }

    @Override
    public void update(float delta) {
        for (Hudson hudson : hudsons) {
            hudson.update(delta);
        }
    }

    @Override
    public int updateRate() {
        return 25;
    }

    @Override
    public void paint(float alpha) {
    }
}
