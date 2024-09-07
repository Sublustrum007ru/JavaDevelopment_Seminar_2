package controller;

import view.ClientGUI;
import view.ServerGUI;

import java.util.ArrayList;
import java.util.List;

public class ServerController {
    List<ClientController> clientList;
    private ClientController clientController;
    private ServerGUI serverGUI;
    private boolean serverSatatus;

    public ServerController() {
        clientList = new ArrayList<>();
    }

    public void setServerGUI(ServerGUI serverGUI) {
        this.serverGUI = serverGUI;
    }

    public boolean connectUser(ClientController clientController) {
        if (!getServerStatus()) {
            return false;
        }
        clientList.add(clientController);
        return true;
    }

    public String getHistory() {
        String result = "";
        return result;
    }

    public void disconnetUser(ClientController clientController) {
        serverGUI.showMessage("Disconnect " + clientController);

    }

    public void message(String message) {
        serverGUI.showMessage(message);
    }

    public void setServerStatus(boolean newStatus) {
        if (!getServerStatus() && newStatus) {
            serverSatatus = newStatus;
            serverGUI.showMessage("The server has started");
        } else if (getServerStatus() && newStatus) {
            serverGUI.showMessage("The server is already running");
        } else if (getServerStatus() && !newStatus) {
            serverGUI.showMessage("The server has stopped");
            serverSatatus = newStatus;
            disconnetUser(clientController);
        } else {
            serverGUI.showMessage("The server has already been stopped");
        }
    }

    private boolean getServerStatus() {
        return serverSatatus;
    }
}
