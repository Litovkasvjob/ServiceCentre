/**
 * Created by SergLion on 25.06.2016.
 */
public class ServiceCentre {

    private static volatile ServiceCentre serviceCentre;

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

    private double money = 0;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
