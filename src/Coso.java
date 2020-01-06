import processing.core.PApplet;

import java.util.*;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Coso {

    PApplet p;
    boolean[] bufferEnded = {false, false, false, false};

    public static Queue<RectStruct> queue;

    public Coso(PApplet p) {
        this.p = p;
        queue = new ConcurrentLinkedQueue<>();
    }

    public static synchronized Queue<RectStruct> getQueue() {
        return queue;
    }

    public void calcDato(Circle[] circles, int start, int num) throws InterruptedException {

        int id = 0;
        int end = 319;
        switch (start) {
            case 320:
                id = 1;
                end = 639;
                break;
            case 640:
                id = 2;
                end = 959;
                break;
            case 960:
                id = 3;
                end = 1280;
                break;
        }

        Queue<RectStruct> singleQueue = new ConcurrentLinkedQueue<>();


        for (int x = start; x <= end; x += Main.PX_SIZE) {
            for (int y = 0; y < p.height; y += Main.PX_SIZE) {
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

                if (sum > Main.SUM_THRESHOLD) {
                    singleQueue.add(new RectStruct(x, y, closestColor));
//                    System.out.println("añadido: " + singleQueue.peek());
                }


            }
        }

        writeBuffer(id, singleQueue);

    }

    public synchronized void resumeThreads() {
        Arrays.fill(bufferEnded, false);

        notifyAll();
    }

    public synchronized void writeBuffer(int id, Queue<RectStruct> singleQueue) throws InterruptedException {
        bufferEnded[id] = true;
//        System.out.println(id);
        if (!singleQueue.isEmpty()) {
            queue.addAll(singleQueue);
        }

        while (bufferEnded[id]) {
            wait();
        }

    }

}
