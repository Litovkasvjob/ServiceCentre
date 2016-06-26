import java.util.ArrayList;
import java.util.Date;

/**
 * User: Litovka Sergii
 * Date: 25.06.2016
 * Time: 11:58
 */
public class ServiceCentre {

    private static volatile ServiceCentre serviceCentre;

    private double money = 0;
    private ArrayList<AdminService> administrators;
    private ArrayList<Specialist> specialists;
    private ArrayList<ClientWithProduct> clientWithProducts;
    private ArrayList<Ticket> tickets;
    private Director director;
    private Address address;
    private String name; // name of company

    // create Singletone
    private ServiceCentre() {
        this.administrators = new ArrayList<>();
        this.specialists = new ArrayList<>();
        this.clientWithProducts = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public static ServiceCentre getServiceCentre() {
        if (serviceCentre == null) {
            synchronized (ServiceCentre.class) {
                if (serviceCentre == null)
                    serviceCentre = new ServiceCentre();

            }
        }
        return serviceCentre;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ArrayList<AdminService> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(ArrayList<AdminService> administrators) {
        this.administrators = administrators;
    }

    public ArrayList<Specialist> getSpecialists() {
        return specialists;
    }

    public void setSpecialists(ArrayList<Specialist> specialists) {
        this.specialists = specialists;
    }

    public ArrayList<ClientWithProduct> getClientWithProducts() {
        return clientWithProducts;
    }

    public void setClientWithProducts(ArrayList<ClientWithProduct> clientWithProducts) {
        this.clientWithProducts = clientWithProducts;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Create Administrator
     * @param name
     * @return administrator
     */
    public AdminService addAdministrator(String name) {
        AdminService adminService = new AdminService(name);
        administrators.add(adminService);
        //int index = administrators.indexOf(adminService);
        //return administrators.get(index);
        return adminService;
    }

    /**
     * Remove Administrator
     * @param administrator
     * @return boolean true if removed
     */
    public boolean removeAdministrator(AdminService administrator) {
        return administrators.remove(administrator);
    }

    public boolean addSpecialist(Specialist specialist) {
        return specialists.add(specialist);
    }

    public Specialist createSpecialist(String name) {
        Specialist specialist = new Specialist(name);
        return specialist;
    }

    public boolean removeSpecialist(Specialist specialist) {
        return specialists.remove(specialist);
    }

    public boolean addClientWithProduct(ClientWithProduct clientWithProduct) {
        return clientWithProducts.add(clientWithProduct);
    }

    public ClientWithProduct createClientWithProduct(String name) {
        ClientWithProduct clientWithProduct = new ClientWithProduct(name);
        return clientWithProduct;
    }

    public boolean removeClientWithProduct(ClientWithProduct clientWithProduct) {
        return clientWithProducts.remove(clientWithProduct);
    }

    public boolean addTicket(Ticket ticket) {
        return tickets.add(ticket);
    }

    public Ticket createTicket(int number, Product product, Date putTime, Date getTime, ClientWithProduct client) {
        Ticket ticket = new Ticket(number, product, putTime, getTime, client);
        return ticket;
    }

    public boolean removeTicket(Ticket ticket) {
        return tickets.remove(ticket);
    }




}
