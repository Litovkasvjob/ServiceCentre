import java.util.ArrayList;

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
    private ArrayList<Ticket> tickets;
    private Director director;
    private Adress adress;
    private String name; // name of company

    // create Singletone
    private ServiceCentre() {
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
}
