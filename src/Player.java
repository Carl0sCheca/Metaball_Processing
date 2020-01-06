import processing.core.PApplet;

public class Player extends GameObject {

    int x, y;
    int size;

    public Player(PApplet p, int x, int y, int size) {
        super(p);

        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    void draw() {
        p.noStroke();
        p.fill(0, 0, 255);
        p.rect(x, y, size,  size);
    }

    @Override
    void update() {
        if (Keyboard.keys.get("UP")) {
            y--;
        } else if (Keyboard.keys.get("RIGHT")) {
            y++;
        }

        if (Keyboard.keys.get("DOWN")) {
            x++;
        } else if (Keyboard.keys.get("LEFT")) {
            x--;
        }


        if (Keyboard.keys.get("ACTION")) {

        }
    }
}
