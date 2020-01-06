import processing.core.PApplet;

public abstract class GameObject {
    protected static PApplet p;

    GameObject(PApplet p) {
        GameObject.p = p;
    }

    abstract void draw();

    abstract void update();
}
