public class Linear extends Series {
    public double d;
    public double a1;

    public Linear(double d, double a1) {
        this.d = d;
        this.a1 = a1;
    }

    @Override
    public double getElement(int number) {
        if (number == 0) {
            return a1;
        }
        return a1 + (number) * d;
    }
}
