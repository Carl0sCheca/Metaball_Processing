import processing.core.PApplet;

public class UIText {

    public static void drawText(PApplet p, String text, int x, int y) {
        p.fill(255, 0, 0);
        p.textSize(32);
        p.textAlign(PApplet.LEFT, PApplet.TOP);
        p.text(text, x, y);
    }

}
