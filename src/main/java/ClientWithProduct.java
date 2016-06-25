import java.util.ArrayList;

/**
 * User: Litovka Serg
 * Date: 19.06.2016
 * Time: 11:12
 */
public class ClientWithProduct extends Human implements Client {

    private ArrayList<Product> products;
    private ArrayList<ClientTicket> clientTickets;


    public ClientWithProduct(String name, int age, double cash) {
        super(name, age, cash);
        products = new ArrayList<Product>();
    }

    public ClientWithProduct(String name) {
        super(name);
        products = new ArrayList<Product>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addAllProducts(ArrayList<Product> products) {
        this.products.addAll(products);
    }


    @Override
    public boolean giveProductForRepair(AdminService adminService, Product product) {
        if (adminService.receiveProduct(product, this).equals(null)) {
            return false;
        }

        if (clientTickets.add(adminService.receiveProduct(product, this))) {
            return products.remove(product);
        }
        return false;
    }

    @Override
    public boolean takeProduct(AdminService adminService, ClientTicket clientTicket) {
        return products.add(adminService.giveProductToClient(clientTicket));
    }

    @Override
    public boolean buyProduct(Product product) {
        if (super.getCash() >= product.getPrice()) {
            super.setCash(super.getCash() - product.getPrice());
            return products.add(product);
        } else {
            System.out.println("You have a little money");
        }
        return false;
    }

    @Override
    public String toString() {
        return "ClientWithProduct" + super.toString();
    }
}
