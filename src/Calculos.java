public class Calculos extends Thread {


    private Coso coso;
    private Circle[] circles;

    private int start, num;


    public Calculos(Circle[] circles, Coso coso, int start, int num) {
        this.circles = circles;
        this.coso = coso;
        this.start = start;
        this.num = num;
    }

    public void run() {
        boolean fin = false;
        while (!isInterrupted() && !fin) {
            try {
                coso.calcDato(circles, start, num);
            } catch (InterruptedException e) {
                fin = true;
            }
        }
    }

}
