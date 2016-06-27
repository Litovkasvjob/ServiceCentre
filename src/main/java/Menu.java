import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * User: Litovka Serg
 * Date: 27.06.2016
 * Time: 9:40
 */
public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<ClientWithProduct> clientWithProducts = new ArrayList<>();
    private ServiceCentre serviceCentre = ServiceCentre.getServiceCentre();

    public Menu() {
    }

    public Menu(ServiceCentre serviceCentre) {
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
                System.out.println("Director was created.");
                break;
            }
            case 2: {
                ClientWithProduct clientWithProduct = new ClientWithProduct(name);
                clientWithProducts.add(clientWithProduct);
                System.out.println("You id = " + clientWithProduct.getId() + ". Remember it!");
                System.out.println("Client was created.");
            }
            /*case 3: {
                serviceCentre.createAdministrator(name);
                System.out.println("Administrator was created.");
                break;
            }
            case 4: {
                serviceCentre.addSpecialist(serviceCentre.createSpecialist(name));
                System.out.println("Specialist was created.");
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
                    System.out.println("Director is not exist, create him");
                }
                break;
            }
            case 2: {
                if (!serviceCentre.getAdministrators().isEmpty()) {
                    showAdminMenu();
                } else {
                    System.out.println("Administrator is not exist, director can hire him");
                }
                break;
            }
            case 3: {
                if (!serviceCentre.getSpecialists().isEmpty()) {
                    showSpecialistMenu();
                } else {
                    System.out.println("Specialist is not exist, director can to hire him");
                }
                break;
            }
            case 4: {
                // check id
                if (!getClientWithProducts().isEmpty()) {
                    System.out.println("Enter you id");
                    int id = scanner.nextInt();

                    boolean flag = false;     //TODO: определить создаг ли клиент с таким id
                    for (ClientWithProduct clientWithProduct : clientWithProducts) {
                        if (clientWithProduct.getId() == id) {
                            showClientMenu(id);
                            break;
                        }
                    }
                    for (Iterator iter = clientWithProducts.iterator(); iter.hasNext(); ) {
                        ClientWithProduct cl = (ClientWithProduct) iter.next();


                    }



                } else {
                    System.out.println("Client is not exist, create him");
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
            System.out.println("1. Change Address of Service Centre");
            System.out.println("2. Hire of Employee");
            System.out.println("3. Remove of Employee");
            System.out.println("4. Show all Employees");
            System.out.println("5. Pay Tax");
            System.out.println("6. Look at Money");
            System.out.println("7. Pay the Salary");
            System.out.println("8. Back");

            System.out.println("Input number of menu");
            int num = scanner.nextInt();
            if (num == 8) break;
            logicDirector(num);
        }

    }

    public void showAdminMenu() {

        while (true) {
            System.out.println("1. Show all Clients");
            System.out.println("2. Show report about Product");
            System.out.println("3. Take Product to repair");
            System.out.println("4. Give Product to Client");
            System.out.println("5. Take Product from Specialist");
            System.out.println("6. Give Product to Specialist");
            System.out.println("7. Back");

            System.out.println("Input number of menu");
            int num = scanner.nextInt();
            if (num == 7) break;
            logicAdmin(num);
        }
    }

    public void showSpecialistMenu() {
        System.out.println("1. Repair Product");
        System.out.println("2. Return Product to Admin");
    }

    public void showClientMenu(int id) { // id to identify Client
        while (true) {
            System.out.println("1. Give Product to repair");
            System.out.println("2. Buy Product");
            System.out.println("3. Take Product from Service");
            System.out.println("4. Back");

            System.out.println("Input number of menu");
            int num = scanner.nextInt();
            if (num == 4) break;
            logicClient(num);
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
            case 1:  //Change Address of Service Centre
                Address address = createAddress();
                serviceCentre.getDirector().newAddress(address);
                break;
            case 2:  //Hire of Employee
                System.out.println("Enter Employee to hire:\n1 - Administrator\n2 - Specialist");
                int number = scanner.nextInt();
                if (number == 1) {
                    System.out.println("Enter name");
                    String newName = scanner.next();
                    AdminService adminService = serviceCentre.createAdministrator(newName);
                    serviceCentre.getDirector().hireAdministrator(adminService);
                    System.out.println("Administrator is hired");
                } else if (number == 2) {
                    System.out.println("Enter name");
                    String newName = scanner.next();
                    Specialist specialist = serviceCentre.createSpecialist(newName);
                    serviceCentre.getDirector().hireSpecialist(specialist);
                    System.out.println("Specialist is hired");
                } else {
                    System.out.println("You enter uncorrect symbol");
                }
                break;
            case 3:    //Remove of Employee
                System.out.println("Enter Employee to remove:\n1 - Administrator\n2 - Specialist");
                int number2 = scanner.nextInt();
                if (number2 == 1) {
                    System.out.println("Enter id remove");
                    int newId = scanner.nextInt();
                    if (serviceCentre.getAdministrators().isEmpty()) {
                        System.out.println("There is not admin at all");
                        break;
                    }
                    for (Iterator it = serviceCentre.getAdministrators().listIterator(); it.hasNext(); ) {
                        AdminService administrator = (AdminService) it.next();

                        if (newId == administrator.getId()) {
                            serviceCentre.getAdministrators().remove(administrator);
                            System.out.println("Administrator is removed");
                            break;
                        }
                        if (!it.hasNext()) {
                            System.out.println("There is not admin with this id");
                        }
                    }

                } else if (number2 == 2) {
                    System.out.println("Enter id remove");
                    int newId = scanner.nextInt();
                    if (serviceCentre.getSpecialists().isEmpty()) {
                        System.out.println("There is not specialist at all");
                        break;
                    }
                    for (Iterator it = serviceCentre.getSpecialists().listIterator(); it.hasNext(); ) {
                        Specialist specialist = (Specialist) it.next();
                        if (newId == specialist.getId()) {
                            serviceCentre.getSpecialists().remove(specialist);
                            System.out.println("Specialist is removed");
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
                System.out.print("\nfor Specialist: ");
                double salarySpecial = scanner.nextDouble();
                double moneyBeforePayingSalary = serviceCentre.getMoney();
                serviceCentre.getDirector().paySalary(salaryAdmin, salarySpecial);
                double moneyAfterPayingSalary = serviceCentre.getMoney();
                double valueOfSalary = moneyBeforePayingSalary - moneyAfterPayingSalary;
                System.out.println();
                System.out.println("You pay " + valueOfSalary);
                break;
            default:
                System.out.println("You enter uncorrect symbol");
                break;


        }
    }

    public void logicAdmin(int num) {
        switch (num) {
            case 1:  // Show all Clients
                //serviceCentre.getAdministrators().

        }

    }

    public void logicClient(int num) {
        switch (num) {
            case 1:  //Give Product to repair


        }
    }


}
