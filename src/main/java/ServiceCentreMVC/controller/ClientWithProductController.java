package ServiceCentreMVC.controller;

import ServiceCentreMVC.model.ClientTicket;
import ServiceCentreMVC.model.ClientWithProduct;
import ServiceCentreMVC.model.Product;
import ServiceCentreMVC.model.ServiceCentre;
import ServiceCentreMVC.view.ClientView;

import java.util.Scanner;

/**
 * User: Litovka Serg
 * Date: 19.06.2016
 * Time: 11:12
 */
public class ClientWithProductController {

    private ClientWithProduct clientWithProduct;
    private ClientView clientView;
    private ServiceCentre serviceCentre;

    public ClientWithProductController(ClientWithProduct clientWithProduct, ClientView clientView,
                                       ServiceCentre serviceCentre) {
        this.clientWithProduct = clientWithProduct;
        this.clientView = clientView;
        this.serviceCentre = serviceCentre;
    }

    private Scanner scanner = new Scanner(System.in);


    public void logicClient(int numType, ClientWithProduct clientWithProduct) {
        switch (numType) {
            case 1:  //Give ServiceCentre.Product to repair
                if (!clientWithProduct.getProducts().isEmpty()) {
                    System.out.println("ServiceCentre.Product to repair");
                    clientWithProduct.showAllClientProduct();
                    System.out.println("Enter ServiceCentre.Product id to repair");
                    int id = scanner.nextInt();
                    boolean flag = false;
                    for (Product product : clientWithProduct.getProducts()) {
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
                for (Product product : serviceCentre.getProducts()) {
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
                    for (ClientTicket clientTicket : clientWithProduct.getClientTickets()) {
                        if (clientTicket.getTicket().getNumber() == id) {
                            Product product = serviceCentre.getAdministrator().giveProductToClient(clientTicket);
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
}