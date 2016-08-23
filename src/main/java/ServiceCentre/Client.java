package ServiceCentre;

/**
 * User: Litovka Serg
 * Date: 19.06.2016
 * Time: 14:19
 */
public interface Client {

    public boolean giveProductForRepair(ServiceCentre serviceCentre, Product product);

    public boolean takeProduct(ServiceCentre serviceCentre, ClientTicket clientTicket);

    public boolean buyProduct(Product product);


}
