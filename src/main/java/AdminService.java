import java.util.*;

/**
 * User: Litovka Serg
 * Date: 19.06.2016
 * Time: 14:48
 */
public class AdminService extends Human {

    private ServiceCentre serviceCentre = ServiceCentre.getServiceCentre();

    private static int key = 0;
    private static int idStatic = 1;
    private int id = 0;

    public AdminService(String name, int age, double salary) {
        super(name, age, salary);
        this.id = idStatic++;
    }

    public AdminService(String name) {
        super(name);
        this.id = idStatic++;
    }

    /**
     * Take Product for repairing (cost of repairs = 10% of price of Product)
     * @param product
     * @param clientWithProduct
     * @return clientTicket for clientWithProduct instead of Product
     */

    public ClientTicket receiveProduct(Product product, ClientWithProduct clientWithProduct) {

        if (clientWithProduct.getCash() < (product.getPrice() * 0.1)) {
            System.out.println("You have a little money to repair the " + product.getModel());
            return null;  // TODO:  лучше здесь кинуть исключение
        }
        key += 1;

        serviceCentre.addClientWithProduct(clientWithProduct);

        clientWithProduct.setCash(clientWithProduct.getCash() - (product.getPrice() * 0.1));
        double repairedMoney = serviceCentre.getMoney();   // earned money at all
        serviceCentre.setMoney(repairedMoney + (product.getPrice() * 0.1));

        Ticket ticket = serviceCentre.createTicket(key, product, new Date(), null, clientWithProduct);

        ClientTicket clientTicket = new ClientTicket(ticket);
        serviceCentre.addTicket(ticket);
        return clientTicket;
    }

    /**
     * Give Product to ClientWithProduct instead of clientTicket
     * @param clientTicket
     * @return Product
     */

    public Product giveProductToClient(ClientTicket clientTicket) {

        if (serviceCentre.removeTicket(clientTicket.getTicket())) {
            serviceCentre.addRepairedTicket(clientTicket.getTicket());
        } else {
            System.out.println("There is not such product in service");
        }
        return clientTicket.getProduct();
    }

    /**
     * Admin gives Product to specialist
     * @param ticket
     * @param specialist
     * @return true if specialist add ticket (Product) to yourself
     */

    public boolean giveProductToSpecialist(Ticket ticket, Specialist specialist) {
        Ticket ticket1 = serviceCentre.getTicket(ticket);
        return specialist.addTicket(ticket1);     //TODO:
    }

    /**
     * Take Product on the ticket
     * @param ticket
     * @return true if Admin receive Product (specialist gave Product to Admin)
     */
    public boolean takeProductFromSpecialist(Ticket ticket) {
        for (Iterator iter = serviceCentre.getSpecialists().iterator(); iter.hasNext(); ) {
            Specialist specialist = (Specialist) iter.next();
            for (Iterator it = specialist.getItems().iterator(); it.hasNext(); ) {
                Ticket t = (Ticket) it.next();
                if (t.getNumber() == ticket.getNumber()) {
                    return specialist.removeTicket(ticket);
                }
            }
        }
        return false;
    }

    /**
     * Take Product on the ticket (used Stream)
     * @param ticket
     * @return true if Admin receive Product (specialist gave Product to Admin)
     */

    public boolean takeProductFromSpecialistStream(Ticket ticket) {
        final boolean[] f = {false};

        serviceCentre.getSpecialists().stream().flatMap(t ->
                t.getItems().stream().map(d -> {

                            if (d.getNumber() == ticket.getNumber()) {
                                f[0] = t.removeTicket(ticket);
                            }
                            return f[0];

                        }
                )
        );
        return f[0];
    }

    /**
     * Show all clients with ticket
     */

    public void showAllClients() {
        Set<ClientWithProduct> clientWithProductSet = new HashSet<>();
        for (Iterator it = serviceCentre.getClientWithProducts().iterator(); it.hasNext(); ) {
            ClientWithProduct nextClient = (ClientWithProduct) it.next();
            System.out.println( nextClient.toString());
        }
    }

    /**
     * Show report (quantity of repaired equipment per day, per week, per month)
     */

    public void showReportAboutProduct() {
        Scanner scanner = new Scanner(System.in);
        long range = 0;
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the range:\n1 - 1 day\n2 - 1 weeak\n3 - 1 month");
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                switch (i) {
                    case 1:
                        range = 1000 * 60 * 60 * 24; // one day
                        flag = false;
                        break;
                    case 2:
                        range = 1000 * 60 * 60 * 24 * 7; // one weak
                        flag = false;
                        break;
                    case 3:
                        range = (long) (1000 * 60 * 60 * 24 * 30); // one month
                        flag = false;
                        break;
                    default:
                        System.out.println("You didn't enter correct number");
                }
            } else {
                System.out.println("You didn't enter correct number, repeat");
            }
        }

        int amount = 0;

        for (Iterator iterator = serviceCentre.getRepairedTickets().iterator(); iterator.hasNext();) {
            Ticket ticket = (Ticket) iterator.next();
            if (ticket.getPutTime().getTime() >= (new Date().getTime() - range)) {
                amount++;
            }

        }
        System.out.println("Amount of repaired products is " + amount);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Administrator {" + getId() + '}' + super.toString();
    }
}



