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
import forplay.core.ForPlay;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ImageLayer;

public class Hudson {

    public static String IMAGE = "images/hudson.png";
    private ImageLayer layer;
    private float angle;

    private final long createdAt = System.currentTimeMillis();

    private double disappearingAt;

    private double killedAt;

    private State state = State.CREATED;
    private final int timeToLive;
    private final int timeToDisappear;

    private final HelloGame game;

    private Box box;

    enum State {
        CREATED, DISAPPEARING, KILLED;
    }

    public Hudson(final HelloGame game, final float x, final float y, int timeToLive) {

        this.game = game;
        this.timeToLive = (int) (0.6 * timeToLive);
        this.timeToDisappear = (int) (0.4 * timeToLive);

        Image image = assetManager().getImage(IMAGE);
        layer = graphics().createImageLayer(image);

        box = new Box(x - image.width() / 2f, y - image.height() / 2f, image.width(), image.height());

        layer.setOrigin(image.width() / 2f, image.height() / 2f);
        layer.setTranslation(x, y);
        game.hudsonLayer.add(layer);
    }

    public boolean killed(float x, final float y) {
        if (state != State.KILLED && box.contains(x, y)) {
            state = State.KILLED;
            killedAt = ForPlay.currentTime();
            return true;
        }
        return false;
    }

    public void removeFromLayer(GroupLayer hudsonLayer) {
        hudsonLayer.remove(layer);
    }

    public void update(float delta) {

        switch (state) {
        case CREATED: {
            if (timeElapsed(createdAt) > timeToLive) {
                state = State.DISAPPEARING;
                disappearingAt = ForPlay.currentTime();
            } else {
                break;
            }
        }
        case DISAPPEARING: {
            if (timeElapsed(disappearingAt) > timeToDisappear) {
                game.hudsonMissed(this);
            } else {
                angle += delta;
                layer.setRotation(angle);
            }
            break;
        }
        case KILLED: {
            double timeElapsed = timeElapsed(killedAt);
            if (timeElapsed > 500) {
                game.hudsonRemove(this);
            } else {
                layer.setScale((float) (1. - timeElapsed / 500.));
            }
            break;
        }
        }
    }

    private double timeElapsed(double since) {
        return ForPlay.currentTime() - since;
    }
}
