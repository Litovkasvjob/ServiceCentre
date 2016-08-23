package ServiceCentre;

import java.util.Date;

/**
 * User: Litovka Serg
 * Date: 19.06.2016
 * Time: 14:03
 */
public class Ticket {

    private int number;
    private Product product;
    private Date putTime;
    private Date getTime;
    private ClientWithProduct client;

    public Ticket(int number, Product product, Date putTime, Date getTime, ClientWithProduct client) {
        this.number = number;
        this.product = product;
        this.putTime = putTime;
        this.getTime = getTime;
        this.client = client;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getPutTime() {
        return putTime;
    }

    public void setPutTime(Date putTime) {
        this.putTime = putTime;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public ClientWithProduct getClient() {
        return client;
    }

    public void setClient(ClientWithProduct client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ServiceCentre.Ticket{" +
                "number=" + number +
                ", product=" + product +
                ", putTime=" + putTime +
                ", getTime=" + getTime +
                ", client=" + client +
                '}';
    }
}
