import java.util.Date;

/**
 * User: Litovka Serg
 * Date: 19.06.2016
 * Time: 14:08
 */
public class ClientTicket {

    /**
     * Wrapper
     */

    private Ticket ticket;

    public ClientTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Date getGiveTime() {
        return ticket.getPutTime();
    }

    public int getKeyNumber() {
        return ticket.getNumber();
    }

    public Product getProduct() {
        return ticket.getProduct();
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "ClientTicket{" +
                "ticket=" + ticket +
                '}';
    }
}
