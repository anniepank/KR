import java.util.Random;
import java.util.Scanner;

public  class Main {
    public static void printMatrix(int [][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter size of array");
        int n = in.nextInt();

        int[][] matrix = new int[n][n];

        Random random = new Random();
        int randInt;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                randInt = random.nextInt(2*n + 1) - n;
                matrix[i][j] = randInt;
            }
        }

        printMatrix(matrix, n);

        int[][] transponed = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transponed[i][j] = matrix[j][i];
            }
        }

        System.out.println();
        printMatrix(transponed, n);
    }
}
