package forplay.sample.hello.core;

public class Box {

    private final float left;
    private final float top;
    private final float right;
    private final float down;

    public Box(float x, float y, float width, float height) {
        this.left = x;
        this.top = y;
        this.right = x + width;
        this.down = y + height;
    }

    public boolean contains(float x, float y) {
        return (x >= left && x <= right && y >= top && y <= down);
    }

}
