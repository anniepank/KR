import java.io.IOException;
import java.io.PrintWriter;

public abstract class Series {

    public abstract double getElement(int number);

    public double sum(int numberOfElements) {
        double sum = 0;
        for (int i = 0; i < numberOfElements; i++) {
            sum += getElement(i);
        }
        return sum;
    }

    public String toString(int numberOfElements) {
        StringBuilder sb = new StringBuilder();
        if(this instanceof Linear) {
            sb.append("Linear progression: ");
        } else {
            sb.append("Exponential progression: ");
        }
        for (int i = 0; i < numberOfElements; i++) {
            sb.append(getElement(i));
            sb.append(" ");
        }
        return sb.toString();
    }

    public void saveToFile(String fileName, int numberOfElements) throws IOException {
        PrintWriter pw = new PrintWriter(fileName, "UTF-8");

        for (int i = 0; i < numberOfElements; i++) {
            pw.print(getElement(i));
            pw.print(" ");
        }

        pw.close();
    }
}
