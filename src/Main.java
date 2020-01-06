import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main(Main.class, args);
    }

    private Player player;

    private static PApplet p;

    public void setup() {
        surface.setTitle("Pruebas");
        init();
    }

    public void settings() {
        p = this;
        size(1280, 720, P2D);

        // para asegurar que nunca sea null sin tener que comprobarlo
        Keyboard.initialize();

        player = new Player(p, 0, 0, 20);
    }

    @Override
    public void keyPressed() {
        Keyboard.keyPressed(keyCode);
    }

    @Override
    public void keyReleased() {
        Keyboard.keyReleased(keyCode);
    }


    public final static int NUM_CIRCLES = 10;
    public final static int PX_SIZE = 2;
    public final static int SUM_THRESHOLD = 5;

    public static Circle[] circles;
    public static Calculos[] calculos = new Calculos[4];
    public static Coso coso;

    private void init() {
        circles = new Circle[NUM_CIRCLES];

        for (int i = 0; i < NUM_CIRCLES; i++) {
            circles[i] = new Circle(p, random(0, width), random(0, height), random(80, 160), random(-20, 20), random(-20, 20));
        }

    }

    public boolean started = false;

    public void draw() {
        background(0);


        for (Circle circle : circles) {
            circle.update();
//            circle.draw();
        }

// http://jamie-wong.com/2014/08/19/metaballs-and-marching-squares/
//        for (int x = 0; x < p.width; x += PX_SIZE) {
//            for (int y = 0; y < p.height; y += PX_SIZE) {
//
//                float sum = 0;
//                float closestD2 = Float.POSITIVE_INFINITY;
//                int closestColor = -1;
//
//                for (int i = 0; i < circles.length; i++) {
//                    Circle c = circles[i];
//                    float dx = x - c.x;
//                    float dy = y - c.y;
//                    float d2 = dx * dx + dy * dy;
//                    sum += c.r * c.r / d2;
//
//                    if (d2 < closestD2) {
//                        closestD2 = d2;
//                        closestColor = c.color;
//                    }
//                }
//
//                if (sum > SUM_THRESHOLD) {
//                    if (closestColor != -1) {
//                        fill(closestColor);
//                    }
//                    noStroke();
//                    rect(x, y, PX_SIZE, PX_SIZE);
//                }
//
//
//            }
//        }


        // "multithreading" en teoria

        if (!started) {
            started = true;
            coso = new Coso(p);
            for (int i = 0; i < calculos.length; i++) {
                calculos[i] = new Calculos(circles, coso, i * (width/calculos.length), calculos.length);
            }

            for (Calculos calculo : calculos) {
                calculo.start();
            }
        }

        if (Coso.getQueue().isEmpty()) {
            for (int x = 0; x < p.width; x+=PX_SIZE) {
                for (int y = 0; y < p.height; y+=PX_SIZE) {

                    float sum = 0;
                    float closestD2 = Float.POSITIVE_INFINITY;
                    int closestColor = -1;

                    for (int i = 0; i < circles.length; i++) {
                        Circle c = circles[i];
                        float dx = x - c.x;
                        float dy = y - c.y;
                        float d2 = dx * dx + dy * dy;
                        sum += c.r * c.r / d2;

                        if (d2 < closestD2) {
                            closestD2 = d2;
                            closestColor = c.color;
                        }
                    }

                    if (sum > SUM_THRESHOLD) {
                        if (closestColor != -1) {
                            fill(closestColor);
                        }
                        noStroke();
                        rect(x, y, PX_SIZE, PX_SIZE);
                    }
                }
            }

        } else {
            while (!Coso.getQueue().isEmpty()) {
                RectStruct struct = Coso.getQueue().poll();
//              System.out.println(struct);
                fill(struct.color);
                noStroke();
                rect(struct.x, struct.y, Main.PX_SIZE, Main.PX_SIZE);
            }
            coso.resumeThreads();
        }


        UIText.drawText(p, String.valueOf((int) frameRate), 0, 0);
    }

}
