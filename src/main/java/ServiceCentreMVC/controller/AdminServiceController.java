package ServiceCentreMVC.controller;

import ServiceCentreMVC.model.AdminService;
import ServiceCentreMVC.model.ClientWithProduct;

import java.util.Iterator;

/**
 * Created by SergLion on 04.08.2016.
 */
public class AdminServiceController {



    public void logicAdmin(int num, AdminService administrator) {
        switch (num) {
            case 1:  // Show all Clients
                administrator.showAllClients();
                //serviceCentre.getClientWithProducts().stream().forEach(e -> e.toString());
                break;
            case 2:  //Show report about ServiceCentre.Product  (quantity of repaired equipment per day, per week, per month)
                administrator.showReportAboutProduct();
                break;
            case 3:  // Take ServiceCentre.Product to repair:
                System.out.println("Enter id of ServiceCentre.Client");
                if (!getClientWithProducts().isEmpty()) {
                    getClientWithProducts().stream().forEach(e -> System.out.println(e.toString()));
                    int id = scanner.nextInt();
                    for (ClientWithProduct clientWithProduct : clientWithProducts) {
                        if (clientWithProduct.getId() == id) {
                            System.out.println("Enter id of ServiceCentre.Product to repair");
                            if (!clientWithProduct.getProducts().isEmpty()) {
                                clientWithProduct.getProducts().stream().forEach(e -> System.out.println(e.toString()));
                                int idp = scanner.nextInt();
                                for (ServiceCentre.Product product : clientWithProduct.getProducts()) {
                                    if (product.getId() == idp) {
                                        ServiceCentre.ClientTicket clientTicket = administrator.receiveProduct(product, clientWithProduct);
                                        if (clientWithProduct.getClientTickets().add(clientTicket)) {
                                            clientWithProduct.getProducts().remove(product);
                                        }
                                    } else {
                                        System.out.println("You enter uncorrect id");
                                    }
                                    break;
                                }
                            } else {
                                System.out.println("There is not ServiceCentre.Product in this ServiceCentre.Client ");
                            }

                        } else {
                            System.out.println("There is not ServiceCentre.Client with this id");
                        }
                    }

                } else {
                    System.out.println("There is not ServiceCentre.Client yet");
                }
                break;
            case 4: //  Give ServiceCentre.Product to ServiceCentre.Client
                System.out.println("Enter id of ServiceCentre.Product to give to ServiceCentre.Client");
                serviceCentre.getTickets().stream().forEach(e -> System.out.println(e.getProduct().toString()));
                int idP = scanner.nextInt();
                for (Iterator iterProduct = serviceCentre.getTickets().iterator(); iterProduct.hasNext(); ) {
                    ServiceCentre.Ticket ticket = (ServiceCentre.Ticket) iterProduct.next();
                    if (ticket.getProduct().getId() == idP) {
                        ClientWithProduct clientWithProduct = ticket.getClient();
                        for (ServiceCentre.ClientTicket clientTicket : clientWithProduct.getClientTickets()) {
                            if (clientTicket.getTicket().getProduct().getId() == idP) {
                                ServiceCentre.Product product = administrator.giveProductToClient(clientTicket);
                                clientWithProduct.addProduct(product);
                                clientWithProduct.getClientTickets().remove(clientTicket);

                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            case 5: // Take ServiceCentre.Product from ServiceCentre.Specialist
                System.out.println("Enter id of ServiceCentre.Product to take from ServiceCentre.Specialist");

                // TODO: проверка если пусто нужна здесь
                serviceCentre.getSpecialists().stream().flatMap(e -> e.getItems().stream()).forEach(e ->
                        System.out.println(e.getProduct().toString()));
                int idS = scanner.nextInt();
                boolean flag = false;
                for (Iterator iterSpecialist = serviceCentre.getSpecialists().iterator(); iterSpecialist.hasNext(); ) {
                    ServiceCentre.Specialist specialist = (ServiceCentre.Specialist) iterSpecialist.next();
                    for (Iterator iterProduct = specialist.getItems().iterator(); iterProduct.hasNext(); ) {
                        ServiceCentre.Ticket ticket = (ServiceCentre.Ticket) iterProduct.next();
                        if (ticket.getProduct().getId() == idS) {
                            specialist.repairProduct(ticket);
                            specialist.removeTicket(ticket);
                            serviceCentre.setRepairedTicket(ticket);
                            System.out.println(ticket.getProduct().toString() + "\n is repaired");
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) {
                    System.out.println("There is no ServiceCentre.Product in Specialists");
                }


                break;
            case 6: // Give ServiceCentre.Product to ServiceCentre.Specialist
                System.out.println("Enter id of ServiceCentre.Specialist");
                if (!serviceCentre.getSpecialists().isEmpty()) {
                    serviceCentre.getSpecialists().stream().forEach(e -> System.out.println(e.toString()));
                    int id = scanner.nextInt();
                    for (ServiceCentre.Specialist specialist : serviceCentre.getSpecialists()) {
                        if (specialist.getId() == id) {
                            System.out.println("Enter id of ServiceCentre.Product to repair");
                            if (!serviceCentre.getTickets().isEmpty()) {
                                serviceCentre.getTickets().stream().map(Ticket::getProduct)
                                        .forEach(e -> System.out.println(e.toString()));
                                int idp = scanner.nextInt();
                                for (ServiceCentre.Ticket ticket : serviceCentre.getTickets()) {
                                    if (ticket.getProduct().getId() == idp) {
                                        specialist.addTicket(ticket);
                                        System.out.println("ServiceCentre.Specialist takes ServiceCentre.Product to repair");
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("There are not Products to repair");
                            }
                        }
                    }

                } else {
                    System.out.println("There is not ServiceCentre.Specialist yet");
                }
                break;
            case 7:
                administrator.showProductInService();
                break;
            case 8:
                administrator.showRepairedProduct();
                break;
            default:
                System.out.println("You enter uncorrect symbol");
                break;
        }


    }
}
