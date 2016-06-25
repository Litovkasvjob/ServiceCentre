import java.util.Date;

/**
 * User: Litovka Serg
 * Date: 19.06.2016
 * Time: 10:51
 */


public class Product {

    private String model;
    private double price;
    private boolean fixed;


    public Product(String model, double price) {
        this.model = model;
        this.price = price;
        this.fixed = false;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    @Override
    public String toString() {
        return "Product{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", fixed=" + fixed +
                '}';
    }
}
