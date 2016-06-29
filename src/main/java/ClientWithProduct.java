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

    public ArrayList<ClientTicket> getClientTickets() {
        return clientTickets;
    }

    public void setClientTickets(ArrayList<ClientTicket> clientTickets) {
        this.clientTickets = clientTickets;
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
    public boolean giveProductForRepair(ServiceCentre serviceCentre, Product product) {
        AdminService administrator;
        if (!serviceCentre.getAdministrators().isEmpty()) {
            administrator = serviceCentre.getAdministrator();

            if (administrator.receiveProduct(product, this).equals(null)) {
                return false;
            }
            if (clientTickets.add(administrator.receiveProduct(product, this))) {
                return products.remove(product);
            }
        } else {
            System.out.println("You can't give product because there is no one admin yet");
        }

        return false;
    }

    @Override
    public boolean takeProduct(ServiceCentre serviceCentre, ClientTicket clientTicket) {
        AdminService administrator;
        if (!serviceCentre.getAdministrators().isEmpty()) {
            administrator = serviceCentre.getAdministrator();

            if (administrator.giveProductToClient(clientTicket).equals(null)) {
                return false;
            }

            Product product = administrator.giveProductToClient(clientTicket);
            return products.add(product);
            }
         else {
            System.out.println("You can't give product because there is no one admin yet");
        }

        return false;
    }

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

    public void showAllClientProduct() {
        products.stream().forEach(e -> System.out.println(e.toString()));
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ClientWithProduct: id = " + getId() + super.toString();
    }
}
