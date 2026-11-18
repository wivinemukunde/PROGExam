public class Product implements IProduct {

    String[] productNames;
    int[][] productSales;

    public Product(String[] productNames, int[][] productSales) {

        this.productNames = productNames;
        this.productSales = productSales;
    }

    @Override
    public int GetTotalProductsSold() {

        int total = 0;

        for (int row = 0; row < productSales.length; row++) {

            for (int column = 0; column < productSales[row].length; column++) {

                total = total + productSales[row][column];
            }
        }

        return total;
    }

    @Override
    public double GetAverageProductsSold() {

        int total = GetTotalProductsSold();

        int numberOfSales = productSales.length * productSales[0].length;

        return (double) total / numberOfSales;
    }

    @Override
    public String GetMostPopularProduct() {

        int highestSales = 0;
        int popularIndex = 0;

        for (int row = 0; row < productSales.length; row++) {

            int productTotal = 0;

            for (int column = 0; column < productSales[row].length; column++) {

                productTotal = productTotal + productSales[row][column];
            }

            if (productTotal > highestSales) {

                highestSales = productTotal;
                popularIndex = row;
            }
        }

        return productNames[popularIndex] + " (" + highestSales + ")";
    }
}