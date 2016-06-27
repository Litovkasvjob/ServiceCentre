import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * User: Litovka Serg
 * Date: 26.06.2016
 * Time: 11:31
 */

public class Director extends Human {

    private ServiceCentre serviceCentre = ServiceCentre.getServiceCentre();

    public Director(String name, int age, double salary) {     // TODO: Director as a Singleton ?
        super(name, age, salary);
    }

    public Director(String name) {
        super(name);
    }

    public ServiceCentre getServiceCentre() {
        return serviceCentre;
    }

    public void newAddress(Address newAddress) {
        serviceCentre.setAddress(newAddress);
    }

    public boolean hireAdministrator(AdminService adminService) {
        return serviceCentre.addAdministrator(adminService);
    }

    public boolean hireSpecialist(Specialist specialist) {
        return serviceCentre.addSpecialist(specialist);
    }

    public boolean removeAdministrator(AdminService adminService) {
        return serviceCentre.removeAdministrator(adminService);
    }

    public boolean removeSpecialist(Specialist specialist) {
        return serviceCentre.removeSpecialist(specialist);
    }

    public void showAllEmployees() {
        if (serviceCentre.getAdministrators().isEmpty() & serviceCentre.getSpecialists().isEmpty()) {
            System.out.println("Nobody to show");
        } else {
            serviceCentre.getAdministrators().stream().forEach(a -> System.out.println(a.toString()));
            serviceCentre.getSpecialists().stream().forEach(s -> System.out.println(s.toString()));
        }
    }

    public void payTax() {
        double amount = 0;
        long range = 1000 * 60 * 60 * 24 * 30;
        for (Ticket ticket : serviceCentre.getRepairedTickets()) {
            if (ticket.getPutTime().getTime() >= (new Date().getTime() - range)) {
                amount = amount + ticket.getProduct().getPrice() * 0.1 * 0.25;
            }
        }
        System.out.println("Tax is payed: " + amount);
        serviceCentre.setMoney(serviceCentre.getMoney() - amount);
    }

    public void payAdministratorSalary(double salary) {
        for (Iterator iter = serviceCentre.getAdministrators().iterator(); iter.hasNext() ;) {
            AdminService administrator = (AdminService) iter.next();
            if ((serviceCentre.getMoney() - salary) < 0) {
                System.out.println("In company there is a few money to pay salary");
                break;
            }
            serviceCentre.setMoney(serviceCentre.getMoney() - salary);
            administrator.setCash(administrator.getCash() + salary);
        }
    }

    public void paySpecialistSalary(double salary) {
        for (Specialist specialist : serviceCentre.getSpecialists()) {
            if ((serviceCentre.getMoney() - salary) < 0) {
                System.out.println("In company there is a few money to pay salary");
                break;
            }
            serviceCentre.setMoney(serviceCentre.getMoney() - salary);
            specialist.setCash(specialist.getCash() + salary);
        }
    }

    public void paySalary(double salaryAdmin, double salarySpecialist) {
        payAdministratorSalary(salaryAdmin);
        paySpecialistSalary(salarySpecialist);
    }

    public void showReportAboutMoney() {
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
                        range = 1000 * 60 * 60 * 24 * 30; // one month
                        flag = false;
                        break;
                    default:
                        System.out.println("You didn't enter correct number");
                }
            } else {
                System.out.println("You didn't enter correct number, repeat");
            }
        }

        double amount = 0;

        for (Iterator iterator = serviceCentre.getRepairedTickets().iterator(); iterator.hasNext();) {
            Ticket ticket = (Ticket) iterator.next();
            if (ticket.getPutTime().getTime() >= (new Date().getTime() - range)) {
                amount = amount + ticket.getProduct().getPrice() * 0.1;
            }

        }
        System.out.println("Money are " + amount);
    }
}
