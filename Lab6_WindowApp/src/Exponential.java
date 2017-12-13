public class Exponential extends Series {
    public double q;
    public double b1;

    public Exponential(double q, double b1) {
        this.q = q;
        this.b1 = b1;
    }

    @Override
    public double getElement(int number) {
        if (number == 0) {
            return b1;
        }
        return b1 * Math.pow(q, number);
    }
}
