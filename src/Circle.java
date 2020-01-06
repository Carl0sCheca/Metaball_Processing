import processing.core.PApplet;

public class Circle extends GameObject {
    public float x, y, r, vx, vy;

    public int color;
    
    public Circle(PApplet p, float x, float y, float r, float vx, float vy) {
        super(p);

        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
        this.color = p.color(p.random(50, 255), p.random(50, 255), p.random(50, 255));
    }

    @Override
    void draw() {
        p.fill(color);
        p.ellipse(x,y,r,r);
    }

    @Override
    void update() {
        x += vx * (1/p.frameRate) * 10;
        y += vy * (1/p.frameRate) * 10;

        if (x - r < 0) {
            vx = Math.abs(vx);
        }

        if (x + r > p.width) {
            vx = -Math.abs(vy);
        }

        if (y - r < 0) {
            vy = Math.abs(vy);
        }

        if (y + r > p.height) {
            vy = -Math.abs(vy);
        }

    }

}
