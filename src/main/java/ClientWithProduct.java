import java.util.ArrayList;

/**
 * User: Litovka Serg
 * Date: 19.06.2016
 * Time: 11:12
 */
public class ClientWithProduct extends Human implements Client {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<ClientTicket> clientTickets = new ArrayList<>();
    private static int idStatic = 1;
    private int id = 0;



    public ClientWithProduct(String name, int age, double cash) {
        super(name, age, cash);
        this.id = idStatic++;
    }

    public ClientWithProduct(String name) {
        super(name);
        this.id = idStatic++;
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
    public boolean giveProductForRepair(AdminService administrator, Product product) {
        if (administrator.receiveProduct(product, this).equals(null)) {
            return false;
        }

        if (clientTickets.add(administrator.receiveProduct(product, this))) {
            return products.remove(product);
        }
        return false;
    }

    @Override
    public boolean takeProduct(AdminService administrator, ClientTicket clientTicket) {
        return products.add(administrator.giveProductToClient(clientTicket));
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ClientWithProduct: id = " + getId() + super.toString();
    }
}
