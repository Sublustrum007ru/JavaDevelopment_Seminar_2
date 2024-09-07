package controller;

import controller.impl.FileOperation;
import view.ServerGUI;

import java.util.ArrayList;
import java.util.List;

public class ServerController extends FileOperation implements ServerView {
    private ServerGUI serverGUI;
    private ClientController clientController = new ClientController();
    private boolean serverStatus = false;
    List<ClientController> clientList = new ArrayList<>();
    private FileOperation file = new FileOperation();
    private final String path_log = "chatLog.txt";

    public void setServerGUI(ServerGUI serverGUI) {
        this.serverGUI = serverGUI;
    }

    public boolean getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(boolean newStatus) {
        if (!serverStatus && newStatus) {
            serverStatus = newStatus;
            serverGUI.showMessage("Server started");
        } else if (serverStatus && newStatus) {
            serverGUI.showMessage("Server is alraedy start");
        } else if (getServerStatus() && !newStatus) {
            serverStatus = newStatus;
            serverGUI.showMessage("Server stopped");
            disconnectAllUser();
        } else {
            serverGUI.showMessage("Server is already stopped");

        }
    }

    public String getHistory() {
        return "";
    }

    public void message(String message) {
        showMessage(message);
    }

    private void disconnectAllUser() {
        for(ClientController client : clientList){
            disconnectUser(client);
        }
    }

    @Override
    public void disconnectUser(ClientController clientController) {
        clientController.disconnetedFromServer();
    }

    @Override
    public boolean connectUser(ClientController clientController) {
        if (!getServerStatus()) {
            return false;
        }
        addClient(clientController);
        return true;
    }

    @Override
    public void showMessage(String message) {
        serverGUI.showMessage(message);
        sendMessageAllClient(message);
        writeFile(path_log, message);
    }

    private void sendMessageAllClient(String message){
        for(ClientController client : clientList){
            if(client != null){
                client.answerFromServer(message);
            }
        }
    }

    private void addClient(ClientController clientController) {
        clientList.add(clientController);
    }

    public void removeClient(ClientController clientController) {
        clientList.remove(clientController);
    }

    public List<ClientController> getClientList() {
        return clientList;
    }

}
