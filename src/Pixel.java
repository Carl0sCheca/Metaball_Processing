import processing.core.*;

public class Pixel extends GameObject {

    private int x, y;

    private boolean loaded = false;

    public Pixel(PApplet p, int x, int y) {
        super(p);

        this.x = x;
        this.y = y;
    }


    @Override
    public void draw() {
        p.point(x, y);
        loaded = true;
    }

    @Override
    public void update() {

    }
}
