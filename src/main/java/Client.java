/**
 * User: Litovka Serg
 * Date: 19.06.2016
 * Time: 14:19
 */
public interface Client {

    public boolean giveProductForRepair(AdminService adminService, Product product);

    public boolean takeProduct(AdminService adminService, ClientTicket clientTicket);

    public boolean buyProduct(Product product);


}
