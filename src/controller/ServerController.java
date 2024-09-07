package controller;

import view.ClientGUI;
import view.ServerGUI;

import java.util.ArrayList;
import java.util.List;

public class ServerController implements ServerView{
    private ServerGUI serverGUI;
    private ClientController clientController = new ClientController();
    private boolean serverStatus = false;
    List<ClientGUI> clientList;

    public void setServerGUI(ServerGUI serverGUI) {
        this.serverGUI = serverGUI;
    }

    public boolean getServerStatus(){
        return serverStatus;
    }

    public void setServerStatus(boolean newStatus){
        if(!serverStatus && newStatus){
            serverStatus = newStatus;
            serverGUI.showMessage("Server started");
        }else if(serverStatus && newStatus){
            serverGUI.showMessage("Server is alraedy start");
        }else if(getServerStatus() && !newStatus){
            serverStatus = newStatus;
            serverGUI.showMessage("Server stopped");
        }else{
            serverGUI.showMessage("Server is already stopped");
        }
    }

    public String getHistory(){
        return "";
    }

    public void message(String message){
        showMessage(message);
    }

    @Override
    public void disconnectUser(ClientController clientController){
        serverGUI.showMessage("Disconnect" + clientController.getClientGUI());
    }

    @Override
    public boolean connectUser(ClientController clientController) {
        clientList = new ArrayList<>();
        if(!getServerStatus()){
            return false;
        }
        clientList.add(clientController.getClientGUI());
        return true;
    }

    @Override
    public void showMessage(String message) {
        serverGUI.showMessage(message);
        clientController.answerFromServer(message);
    }


}
