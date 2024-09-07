package view;

import controller.ClientController;
import controller.ServerController;

public class Application{
    public static void app(){ 
        System.out.println("Hello world!!!\nMy name is Sublustrum007\n");
        run();
    }

    private static void run(){
        ServerGUI serverGUI = new ServerGUI();
        ServerController serverController = new ServerController();
        serverController.setServerGUI(serverGUI);
        serverGUI.setServerController(serverController);

//        создание объектов клиента1 и создание связи между ними
        ClientGUI clientGUI1 = new ClientGUI();
        ClientController clientController1 = new ClientController();
        clientController1.setClientView(clientGUI1);
        clientGUI1.setClient(clientController1);
        clientController1.setServer(serverController);

        //создание объектов клиента2 и создание связи между ними
        ClientGUI clientGUI2 = new ClientGUI();
        ClientController clientController2 = new ClientController();
        clientController2.setClientView(clientGUI2);
        clientGUI2.setClient(clientController2);
        clientController2.setServer(serverController);
    }
} 
