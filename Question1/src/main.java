import java.util.Scanner;

public class main {

        public static void main(String[] args) {

            Scanner input = new Scanner(System.in);

            String[] productNames = {
                    "Cozy Nights Monarch",
                    "Restonic Barbados",
                    "Sealy Phuket"
            };

            int[][] productSales = new int[3][3];

            for (int row = 0; row < productNames.length; row++) {

                for (int column = 0; column < 3; column++) {

                    System.out.print("Enter number of sales for month "
                            + (column + 1) + " for "
                            + productNames[row] + ": ");

                    productSales[row][column] = input.nextInt();
                }
            }

            Product product = new Product(productNames, productSales);

            PrintProduct report = new PrintProduct();

            report.PrintReport(product);

            input.close();
        }
}


