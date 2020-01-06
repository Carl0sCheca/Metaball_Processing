public class RectStruct {
    public float x, y;
    public int color;

    public RectStruct(float x, float y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public String toString() {
        return "RectStruct{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
