package ServiceCentreMVC;


import ServiceCentreMVC.controller.ClientWithProductController;
import ServiceCentreMVC.model.Address;
import ServiceCentreMVC.model.ClientWithProduct;
import ServiceCentreMVC.model.Director;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * User: Litovka Serg
 * Date: 27.06.2016
 * Time: 9:40
 */
public class MenuMVC {

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<ClientWithProduct> clientWithProducts = new ArrayList<>();
    private ServiceCentre serviceCentre = ServiceCentre.getServiceCentre();

    ClientWithProductController clientWithProductController;

    public MenuMVC() {
    }

    public MenuMVC(ServiceCentre serviceCentre) {
        this.serviceCentre = serviceCentre;
    }

    public ArrayList<ClientWithProduct> getClientWithProducts() {
        return clientWithProducts;
    }

    public void setClientWithProducts(ArrayList<ClientWithProduct> clientWithProducts) {
        this.clientWithProducts = clientWithProducts;
    }

    public void start() {
        while (true) {
            showMainMenu();
            System.out.println("Do choice (number to work or different symbol to stop)");
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                doChoice(num);
            } else {
                System.out.println("The program has been stopped");
                System.exit(0);
            }

        }
    }

    private void doChoice(int i) {
        if (i == 1) {
            create();
        } else if (i == 2) {
            signIn();
        } else {
            System.out.println("You entered uncorrect number");
        }
    }

    public void create() {
        System.out.println("Input number of Type:");
        System.out.println("1 - director");
        System.out.println("2 - client");
        //System.out.println("3 - admin");
        //System.out.println("4 - specialist");

        int type = scanner.nextInt();

        System.out.println("Input name");
        String name = scanner.next();

        switch (type) {

            case 1: {
                Director director = new Director(name);
                serviceCentre.setDirector(director);
                System.out.println("ServiceCentre.Director was created.");
                break;
            }
            case 2: {
                ClientWithProduct clientWithProduct = new ClientWithProduct(name);
                clientWithProducts.add(clientWithProduct);
                System.out.println("You id = " + clientWithProduct.getId() + ". Remember it!");
                System.out.println("ServiceCentre.Client was created.");
            }
            /*case 3: {
                serviceCentre.createAdministrator(name);
                System.out.println("Administrator was created.");
                break;
            }
            case 4: {
                serviceCentre.addSpecialist(serviceCentre.createSpecialist(name));
                System.out.println("ServiceCentre.Specialist was created.");
            }*/
        }
    }

    public void signIn() {

        System.out.println("Input number of Type:");
        System.out.println("1 - director");
        System.out.println("2 - admin");
        System.out.println("3 - specialist");
        System.out.println("4 - client");
        int type = scanner.nextInt();

        switch (type) {

            case 1: {
                if (serviceCentre.getDirector() != null) {
                    showDirectorMenu();
                } else {
                    System.out.println("ServiceCentre.Director is not exist, create him");
                }
                break;
            }
            case 2: {
                if (!serviceCentre.getAdministrators().isEmpty()) {
                    System.out.println("Enter you id");
                    int id = scanner.nextInt();

                    boolean flag = false;
                    for (ServiceCentre.AdminService administrator : serviceCentre.getAdministrators()) {
                        if (administrator.getId() == id) {
                            showAdminMenu(administrator);
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        System.out.println("There is not Administrator with id = " + id);
                    }
                } else {
                    System.out.println("Administrator is not exist, director can hire him");
                }
                break;
            }
            case 3: {

                if (!serviceCentre.getSpecialists().isEmpty()) {
                    System.out.println("Enter your id");
                    int id = scanner.nextInt();

                    boolean flag = false;
                    for (ServiceCentre.Specialist specialist : serviceCentre.getSpecialists()) {
                        if (specialist.getId() == id) {
                            showSpecialistMenu(specialist);
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        System.out.println("There is not ServiceCentre.Specialist with id = " + id);
                    }
                } else {
                    System.out.println("ServiceCentre.Specialist is not exist, director can to hire him");
                }
                break;
            }
            case 4: {
                // check id
                if (!getClientWithProducts().isEmpty()) {
                    System.out.println("Enter you id");
                    int id = scanner.nextInt();

                    boolean flag = false;
                    for (ClientWithProduct clientWithProduct : clientWithProducts) {
                        if (clientWithProduct.getId() == id) {
                            clientView.showClientMenu(clientWithProduct);
                            clientWithProductController = new ClientWithProductController(clientWithProduct, c)
                            showClientMenu(clientWithProduct);
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        System.out.println("There is not ServiceCentre.Client with id = " + id);
                    }
                } else {
                    System.out.println("ServiceCentre.Client is not exist, create him");
                }
                break;
            }
            default: {
                System.out.println("You enter uncorrect Type");
                break;
            }
        }
    }

    public void showMainMenu() {
        System.out.println("1. Create");
        System.out.println("2. Sign in");
    }

    public void showDirectorMenu() {

        while (true) {
            System.out.println("1. Change ServiceCentre.Address of Service Centre");
            System.out.println("2. Hire of Employee");
            System.out.println("3. Remove of Employee");
            System.out.println("4. Show all Employees");
            System.out.println("5. Pay Tax");
            System.out.println("6. Look at Money");
            System.out.println("7. Pay the Salary");
            System.out.println("8. Create ServiceCentre.Product");
            System.out.println("9. Back");

            System.out.println("Input number of menu");
            int num = scanner.nextInt();
            if (num == 9) break;
            logicDirector(num);
        }

    }

    public void showAdminMenu(ServiceCentre.AdminService administrator) {

        while (true) {
            System.out.println("1. Show all Clients");
            System.out.println("2. Show report about ServiceCentre.Product");
            System.out.println("3. Take ServiceCentre.Product to repair");
            System.out.println("4. Give ServiceCentre.Product to ServiceCentre.Client");
            System.out.println("5. Take ServiceCentre.Product from ServiceCentre.Specialist");
            System.out.println("6. Give ServiceCentre.Product to ServiceCentre.Specialist");
            System.out.println("7. Show ServiceCentre.Product in Service");
            System.out.println("8. Show repaired ServiceCentre.Product");
            System.out.println("9. Back");

            System.out.println("Input number of menu");
            int num = scanner.nextInt();
            if (num == 9) break;
            logicAdmin(num, administrator);
        }
    }

    public void showSpecialistMenu(ServiceCentre.Specialist specialist) {
        while (true) {
            System.out.println("1. Repair ServiceCentre.Product");
            System.out.println("2. Return ServiceCentre.Product to ServiceCentre.Admin");
            System.out.println("3. Back");

            System.out.println("Input number of menu");
            int numType = scanner.nextInt();
            if (numType == 3) break;
            logicSpecialist(numType, specialist);
        }
    }

    public void showClientMenu(ClientWithProduct clientWithProduct) { // id to identify ServiceCentre.Client
        while (true) {
            System.out.println("1. Give ServiceCentre.Product to repair");
            System.out.println("2. Buy ServiceCentre.Product");
            System.out.println("3. Take ServiceCentre.Product from Service");
            System.out.println("4. Set money");
            System.out.println("5. Show Products");
            System.out.println("6. Show Tickets");
            System.out.println("7. Back");

            System.out.println("Input number of menu");
            int numType = scanner.nextInt();
            if (numType == 7) break;
            clientWithProductController.logicClient(numType, clientWithProduct);
        }

    }

    public Address createAddress() {
        Address address = new Address();
        System.out.println("Enter city");
        String newCity = scanner.next();
        address.setCity(newCity);
        System.out.println("Enter street");
        String newStreet = scanner.next();
        address.setStreet(newStreet);
        System.out.println("Enter house");
        String newHouse = scanner.next();
        address.setHouse(newHouse);
        System.out.println("Enter flat");
        int newFlat = scanner.nextInt();
        address.setFlat(newFlat);
        return address;
    }

    public void logicDirector(int num) {
        switch (num) {
            case 1:  //Change ServiceCentre.Address of Service Centre
                ServiceCentre.Address address = createAddress();
                serviceCentre.getDirector().newAddress(address);
                break;
            case 2:  //Hire of Employee
                System.out.println("Enter Employee to hire:\n1 - Administrator\n2 - ServiceCentre.Specialist");
                int number = scanner.nextInt();
                if (number == 1) {
                    System.out.println("Enter name");
                    String newName = scanner.next();
                    ServiceCentre.AdminService adminService = serviceCentre.createAdministrator(newName);
                    serviceCentre.getDirector().hireAdministrator(adminService);
                    System.out.println("Administrator is hired");
                } else if (number == 2) {
                    System.out.println("Enter name");
                    String newName = scanner.next();
                    ServiceCentre.Specialist specialist = serviceCentre.createSpecialist(newName);
                    serviceCentre.getDirector().hireSpecialist(specialist);
                    System.out.println("ServiceCentre.Specialist is hired");
                } else {
                    System.out.println("You enter uncorrect symbol");
                }
                break;
            case 3:    //Remove of Employee
                System.out.println("Enter Employee to remove:\n1 - Administrator\n2 - ServiceCentre.Specialist");
                int number2 = scanner.nextInt();
                if (number2 == 1) {
                    System.out.println("Enter id remove");
                    int newId = scanner.nextInt();
                    if (serviceCentre.getAdministrators().isEmpty()) {
                        System.out.println("There is not admin at all");
                        break;
                    }
                    for (Iterator it = serviceCentre.getAdministrators().listIterator(); it.hasNext(); ) {
                        ServiceCentre.AdminService administrator = (ServiceCentre.AdminService) it.next();

                        if (newId == administrator.getId()) {
                            serviceCentre.getAdministrators().remove(administrator);
                            System.out.println("Administrator is removed");
                            break;
                        }
                        if (!it.hasNext()) {
                            System.out.println("There is not admin with this id");
                        }
                    }
                    break;

                } else if (number2 == 2) {
                    System.out.println("Enter id remove");
                    int newId = scanner.nextInt();
                    if (serviceCentre.getSpecialists().isEmpty()) {
                        System.out.println("There is not specialist at all");
                        break;
                    }
                    for (Iterator it = serviceCentre.getSpecialists().listIterator(); it.hasNext(); ) {
                        ServiceCentre.Specialist specialist = (ServiceCentre.Specialist) it.next();
                        if (newId == specialist.getId()) {
                            serviceCentre.getSpecialists().remove(specialist);
                            System.out.println("ServiceCentre.Specialist is removed");
                            break;
                        }
                        if (!it.hasNext()) {
                            System.out.println("There is not specialist with this id");
                        }

                    }
                } else {
                    System.out.println("You enter uncorrect symbol");
                }
                break;
            case 4:  // Show all Employees
                serviceCentre.getDirector().showAllEmployees();
                break;
            case 5:  // Pay Tax
                serviceCentre.getDirector().payTax();
                break;
            case 6:  // Look at Money
                System.out.println("There are money in our company: " + serviceCentre.getMoney());
                break;
            case 7:  //  Pay the Salary
                System.out.println("Enter salary for Administrators and for Specialists");
                System.out.print("for Administrator: ");
                double salaryAdmin = scanner.nextDouble();
                System.out.print("\nfor ServiceCentre.Specialist: ");
                double salarySpecial = scanner.nextDouble();
                double moneyBeforePayingSalary = serviceCentre.getMoney();
                serviceCentre.getDirector().paySalary(salaryAdmin, salarySpecial);
                double moneyAfterPayingSalary = serviceCentre.getMoney();
                double valueOfSalary = moneyBeforePayingSalary - moneyAfterPayingSalary;
                System.out.println();
                System.out.println("You pay " + valueOfSalary);
                break;
            case 8:
                ServiceCentre.Product product = serviceCentre.getDirector().createProduct();
                if (serviceCentre.getProducts().add(product)) {
                    System.out.println("ServiceCentre.Product is add");
                } else {
                    System.out.println("ServiceCentre.Product is not add");
                }
                //serviceCentre.getProducts().add(product);
                break;
            default:
                System.out.println("You enter uncorrect symbol");
                break;

        }
    }

    public void logicAdmin(int num, ServiceCentre.AdminService administrator) {
        switch (num) {
            case 1:  // Show all Clients
                administrator.showAllClients();
                //serviceCentre.getClientWithProducts().stream().forEach(e -> e.toString());
                break;
            case 2:  //Show report about ServiceCentre.Product  (quantity of repaired equipment per day, per week, per month)
                administrator.showReportAboutProduct();
                break;
            case 3:  // Take ServiceCentre.Product to repair:
                System.out.println("Enter id of ServiceCentre.Client");
                if (!getClientWithProducts().isEmpty()) {
                    getClientWithProducts().stream().forEach(e -> System.out.println(e.toString()));
                    int id = scanner.nextInt();
                    for (ClientWithProduct clientWithProduct : clientWithProducts) {
                        if (clientWithProduct.getId() == id) {
                            System.out.println("Enter id of ServiceCentre.Product to repair");
                            if (!clientWithProduct.getProducts().isEmpty()) {
                                clientWithProduct.getProducts().stream().forEach(e -> System.out.println(e.toString()));
                                int idp = scanner.nextInt();
                                for (ServiceCentre.Product product : clientWithProduct.getProducts()) {
                                    if (product.getId() == idp) {
                                        ServiceCentre.ClientTicket clientTicket = administrator.receiveProduct(product, clientWithProduct);
                                        if (clientWithProduct.getClientTickets().add(clientTicket)) {
                                            clientWithProduct.getProducts().remove(product);
                                        }
                                    } else {
                                        System.out.println("You enter uncorrect id");
                                    }
                                    break;
                                }
                            } else {
                                System.out.println("There is not ServiceCentre.Product in this ServiceCentre.Client ");
                            }

                        } else {
                            System.out.println("There is not ServiceCentre.Client with this id");
                        }
                    }

                } else {
                    System.out.println("There is not ServiceCentre.Client yet");
                }
                break;
            case 4: //  Give ServiceCentre.Product to ServiceCentre.Client
                System.out.println("Enter id of ServiceCentre.Product to give to ServiceCentre.Client");
                serviceCentre.getTickets().stream().forEach(e -> System.out.println(e.getProduct().toString()));
                int idP = scanner.nextInt();
                for (Iterator iterProduct = serviceCentre.getTickets().iterator(); iterProduct.hasNext(); ) {
                    ServiceCentre.Ticket ticket = (ServiceCentre.Ticket) iterProduct.next();
                    if (ticket.getProduct().getId() == idP) {
                        ClientWithProduct clientWithProduct = ticket.getClient();
                        for (ServiceCentre.ClientTicket clientTicket : clientWithProduct.getClientTickets()) {
                            if (clientTicket.getTicket().getProduct().getId() == idP) {
                                ServiceCentre.Product product = administrator.giveProductToClient(clientTicket);
                                clientWithProduct.addProduct(product);
                                clientWithProduct.getClientTickets().remove(clientTicket);

                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            case 5: // Take ServiceCentre.Product from ServiceCentre.Specialist
                System.out.println("Enter id of ServiceCentre.Product to take from ServiceCentre.Specialist");

                // TODO: проверка если пусто нужна здесь
                serviceCentre.getSpecialists().stream().flatMap(e -> e.getItems().stream()).forEach(e ->
                        System.out.println(e.getProduct().toString()));
                int idS = scanner.nextInt();
                boolean flag = false;
                for (Iterator iterSpecialist = serviceCentre.getSpecialists().iterator(); iterSpecialist.hasNext(); ) {
                    ServiceCentre.Specialist specialist = (ServiceCentre.Specialist) iterSpecialist.next();
                    for (Iterator iterProduct = specialist.getItems().iterator(); iterProduct.hasNext(); ) {
                        ServiceCentre.Ticket ticket = (ServiceCentre.Ticket) iterProduct.next();
                        if (ticket.getProduct().getId() == idS) {
                            specialist.repairProduct(ticket);
                            specialist.removeTicket(ticket);
                            serviceCentre.setRepairedTicket(ticket);
                            System.out.println(ticket.getProduct().toString() + "\n is repaired");
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) {
                    System.out.println("There is no ServiceCentre.Product in Specialists");
                }


                break;
            case 6: // Give ServiceCentre.Product to ServiceCentre.Specialist
                System.out.println("Enter id of ServiceCentre.Specialist");
                if (!serviceCentre.getSpecialists().isEmpty()) {
                    serviceCentre.getSpecialists().stream().forEach(e -> System.out.println(e.toString()));
                    int id = scanner.nextInt();
                    for (ServiceCentre.Specialist specialist : serviceCentre.getSpecialists()) {
                        if (specialist.getId() == id) {
                            System.out.println("Enter id of ServiceCentre.Product to repair");
                            if (!serviceCentre.getTickets().isEmpty()) {
                                serviceCentre.getTickets().stream().map(Ticket::getProduct)
                                        .forEach(e -> System.out.println(e.toString()));
                                int idp = scanner.nextInt();
                                for (ServiceCentre.Ticket ticket : serviceCentre.getTickets()) {
                                    if (ticket.getProduct().getId() == idp) {
                                        specialist.addTicket(ticket);
                                        System.out.println("ServiceCentre.Specialist takes ServiceCentre.Product to repair");
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("There are not Products to repair");
                            }
                        }
                    }

                } else {
                    System.out.println("There is not ServiceCentre.Specialist yet");
                }
                break;
            case 7:
                administrator.showProductInService();
                break;
            case 8:
                administrator.showRepairedProduct();
                break;
            default:
                System.out.println("You enter uncorrect symbol");
                break;
        }


    }

    public void logicClient(int numType, ClientWithProduct clientWithProduct) {
        switch (numType) {
            case 1:  //Give ServiceCentre.Product to repair
                if (!clientWithProduct.getProducts().isEmpty()) {
                    System.out.println("ServiceCentre.Product to repair");
                    clientWithProduct.showAllClientProduct();
                    System.out.println("Enter ServiceCentre.Product id to repair");
                    int id = scanner.nextInt();
                    boolean flag = false;
                    for (ServiceCentre.Product product : clientWithProduct.getProducts()) {
                        if (product.getId() == id) {
                            if (clientWithProduct.giveProductForRepair(serviceCentre, product)) {
                                System.out.println("You give ServiceCentre.Product to repair");
                                flag = true;
                            }
                            break;
                        }
                    }
                    if (!flag) {
                        System.out.println("There is not ServiceCentre.Product with id = " + id);
                    }
                } else {
                    System.out.println("ServiceCentre.Product is not exist, create him");
                }
                break;
            case 2: // Buy ServiceCentre.Product
                serviceCentre.showProducts();
                System.out.println("Enter ServiceCentreMVC.view.model of ServiceCentre.Product to buy");
                String model = scanner.next();
                for (ServiceCentre.Product product : serviceCentre.getProducts()) {
                    if (model.equals(product.getModel())) {
                        if (clientWithProduct.buyProduct(product)) {
                            System.out.println("You buy: " + product.toString());
                            serviceCentre.getProducts().remove(product);
                        }
                        break;
                    }
                }
                break;
            case 3:  // Take ServiceCentre.Product from Service
                if (!clientWithProduct.getClientTickets().isEmpty()) {
                    System.out.println("My tickets");
                    clientWithProduct.showTickets();
                    System.out.println("Enter ServiceCentre.Ticket id to take ServiceCentre.Product from repair");
                    int id = scanner.nextInt();
                    boolean flag = false;
                    for (ServiceCentre.ClientTicket clientTicket : clientWithProduct.getClientTickets()) {
                        if (clientTicket.getTicket().getNumber() == id) {
                            ServiceCentre.Product product = serviceCentre.getAdministrator().giveProductToClient(clientTicket);
                            if (product == null) {
                                flag = true;
                                break;
                            } else {
                                clientWithProduct.addProduct(product);
                                clientWithProduct.getClientTickets().remove(clientTicket);
                                flag = true;
                                break;
                            }

                        }
                    }
                    if (!flag) {
                        System.out.println("There is not ServiceCentre.Product with id = " + id);
                    }
                } else {
                    System.out.println("ServiceCentre.Product is not exist, create him");
                }
                break;
            case 4:
                System.out.println("Make cash");
                double cash = scanner.nextDouble();
                clientWithProduct.setCash(cash);
                break;
            case 5:
                if (!clientWithProduct.getProducts().isEmpty()) {
                    clientWithProduct.getProducts().stream().forEach(e -> System.out.println(e.toString()));
                    break;
                } else {
                    System.out.println("There is no ServiceCentre.Product");
                }
                break;
            case 6:
                if (!clientWithProduct.getClientTickets().isEmpty()) {
                    clientWithProduct.getClientTickets().stream().forEach(t -> System.out.println(t.toString()));
                    break;
                } else {
                    System.out.println("There is no tickets");
                }
                break;
            default:
                System.out.println("You enter uncorrect symbol");
                break;


        }
    }


    public void logicSpecialist(int numType, ServiceCentre.Specialist specialist) {

        switch (numType) {
            case 1:        // Repair ServiceCentre.Product   нет правильно
                System.out.println("Enter id of ServiceCentre.Product to repair");
                specialist.getItems().stream().forEach(e -> System.out.println(e.getProduct().toString()));
                if (!specialist.getItems().isEmpty()) {
                    int idP = scanner.nextInt();
                    for (Iterator iterProduct = specialist.getItems().iterator(); iterProduct.hasNext(); ) {
                        ServiceCentre.Ticket ticket = (ServiceCentre.Ticket) iterProduct.next();
                        if (ticket.getProduct().getId() == idP) {
                            specialist.repairProduct(ticket);
                            System.out.println("ServiceCentre.Product is repaired");
                            break;

                        }
                    }
                } else {
                    System.out.println("There is not ServiceCentre.Product in ServiceCentre.Specialist");
                }

                break;
            case 2:      //Return ServiceCentre.Product to ServiceCentre.Admin
                System.out.println("Enter id of ServiceCentre.Product to return to ServiceCentre.Admin");
                specialist.getItems().stream().forEach(e ->
                        System.out.println(e.getProduct().toString()));
                int idS = scanner.nextInt();
                for (Iterator iterProduct = specialist.getItems().iterator(); iterProduct.hasNext(); ) {
                    ServiceCentre.Ticket ticket = (ServiceCentre.Ticket) iterProduct.next();
                    if (ticket.getProduct().getId() == idS) {
                        if (ticket.getProduct().isFixed() == true) {
                            serviceCentre.setRepairedTicket(ticket);
                        }
                        specialist.removeTicket(ticket);
                        System.out.println("ServiceCentre.Product is returned");
                        break;
                    }
                }
                break;
            default:
                System.out.println("You enter uncorrect symbol");
                break;
        }
    }


}
