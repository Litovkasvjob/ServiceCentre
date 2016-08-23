package ServiceCentreMVC.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * User: Litovka Serg
 * Date: 19.06.2016
 * Time: 14:56
 */
public class Specialist extends Human {

    private ArrayList<Ticket> items;
    private static int idStatic = 1;
    private int id = 0;


    public Specialist(String name, int age, double salary) {
        super(name, age, salary);
        this.id = idStatic++;
        this.items = new ArrayList<>();
    }

    public Specialist(String name) {
        super(name);
        this.id = idStatic++;
        this.items = new ArrayList<>();
    }

    public ArrayList<Ticket> getItems() {
        return items;
    }

    /*public void setItems(ArrayList<ServiceCentre.Ticket> items) {
        this.items = items;
    }*/

    public boolean addTicket(Ticket ticket) {
        return  items.add(ticket);
    }

    public boolean removeTicket(Ticket ticket) {
        return items.remove(ticket);
    }

    public boolean repairProduct(Ticket ticket) {
        if (items.contains(ticket)) {
            ticket.setPutTime(new Date());
            ticket.getProduct().setFixed(true);
            return true;
        } else {
            System.out.println("I don't have such product");
        }
        return false;
    }

    public boolean returnTicket(Ticket ticket) {
        if (repairProduct(ticket))
            return items.remove(ticket);

        return false;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Specialist {" + getId() + '}' + super.toString();
    }
}
