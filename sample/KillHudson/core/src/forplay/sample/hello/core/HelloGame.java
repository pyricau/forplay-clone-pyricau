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
import forplay.core.ResourceCallback;
import forplay.core.Touch;
import forplay.core.Touch.Listener;
import forplay.core.Touch.TouchEvent;

public class HelloGame implements Game, Pointer.Listener, Listener {

    GroupLayer hudsonLayer;
    List<Hudson> hudsons = new ArrayList<Hudson>(0);

    List<Hudson> hudsonsToRemove = new ArrayList<Hudson>(0);

    private double lastSpawnTime;

    private Image bgImage;
    private float maxHudsonX;
    private float maxHudsonY;
    private float hudsonXTranslation;
    private float hudsonYTranslation;

    private int hudsonKilled;

    boolean loadingDone = false;

    @Override
    public void init() {
        // create and add background bgImage layer
        bgImage = assetManager().getImage("images/bg.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);

        graphics().rootLayer().add(bgLayer);

        // create a group layer to hold the hudsons
        hudsonLayer = graphics().createGroupLayer();
        graphics().rootLayer().add(hudsonLayer);

        // preload the pea bgImage into the asset manager cache
        final Image hudsonImage = assetManager().getImage(Hudson.IMAGE);

        hudsonImage.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image resource) {
                maxHudsonX = bgImage.width() - hudsonImage.width();
                maxHudsonY = bgImage.height() - hudsonImage.height();
                hudsonXTranslation = 0; // hudsonImage.width() / 2f;
                hudsonYTranslation = 0; // hudsonImage.height() / 2f;
                loadingDone = true;
            }

            @Override
            public void error(Throwable err) {
                log().error("Error loading image!", err);
            }

        });

        // add a listener for pointer (mouse, touch) input
        Pointer pointer = pointer();

        if (pointer != null)
            pointer.setListener(this);

        Touch touch = touch();
        if (touch != null) {
            touch.setListener(this);
        }
    }

    @Override
    public void onPointerDrag(float x, float y) {
    }

    @Override
    public void onPointerEnd(float x, float y) {
        for (Hudson hudson : hudsons) {
            if (hudson.killed(x, y)) {
                hudsonKilled++;
            }
        }
    }

    @Override
    public void onPointerStart(float x, float y) {
    }

    @Override
    public void update(float delta) {

        if (!loadingDone) {
            return;
        }

        double currentTime = currentTime();

        int maxTime = 500 - 10 * hudsonKilled;

        maxTime = Math.max(maxTime, 50);

        if (currentTime - lastSpawnTime > maxTime) {
            int timeToLive = 2000 - 20 * hudsonKilled;

            timeToLive = Math.max(timeToLive, 200);

            lastSpawnTime = currentTime;

            Hudson hudson = new Hudson(this, (random() * maxHudsonX) + hudsonXTranslation, (random() * maxHudsonY) + hudsonYTranslation, timeToLive);
            hudsons.add(hudson);
        }

        for (Hudson hudson : hudsons) {
            hudson.update(delta);
        }

        for (Hudson hudson : hudsonsToRemove) {
            hudsons.remove(hudson);
            hudson.removeFromLayer(hudsonLayer);
        }

        hudsonsToRemove.clear();
    }

    @Override
    public int updateRate() {
        return 25;
    }

    @Override
    public void paint(float alpha) {
    }

    public void hudsonMissed(Hudson hudson) {
        if (hudsonKilled > 0)
            hudsonKilled--;
        hudsonRemove(hudson);
    }

    public void hudsonRemove(Hudson hudson) {
        hudsonsToRemove.add(hudson);
    }

    @Override
    public void onTouchStart(TouchEvent[] touches) {

    }

    @Override
    public void onTouchMove(TouchEvent[] touches) {

    }

    @Override
    public void onTouchEnd(TouchEvent[] touches) {
        for (TouchEvent touch : touches) {
            for (Hudson hudson : hudsons) {
                if (hudson.killed(touch.x(), touch.y())) {
                    hudsonKilled++;
                }
            }
        }
    }
}
