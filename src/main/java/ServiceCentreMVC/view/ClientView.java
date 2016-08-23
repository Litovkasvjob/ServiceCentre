package ServiceCentreMVC.view;

import ServiceCentreMVC.controller.ClientWithProductController;
import ServiceCentreMVC.model.ClientWithProduct;

import java.util.Scanner;

/**
 * Created by SergLion on 03.08.2016.
 */
public class ClientView {

    private ClientWithProductController clientWithProductController = new ClientWithProductController()
    private Scanner scanner = new Scanner(System.in);

    public void showClientMenu(ClientWithProduct clientWithProduct) { // id to identify ServiceCentre.Client
        while (true) {
            System.out.println("1. Give Product to repair");
            System.out.println("2. Buy Product");
            System.out.println("3. Take Product from Service");
            System.out.println("4. Set money");
            System.out.println("5. Show Products");
            System.out.println("6. Show Tickets");
            System.out.println("7. Back");

            System.out.println("Input number of menu");
            int numType = scanner.nextInt();
            if (numType == 7) break;
            clientWithProductController.logicClient(numType, clientWithProduct);
        }

    }

}
