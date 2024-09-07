package controller;

import view.ClientGUI;

public interface ClientView {
    void showMessage(String message);
    void disconnectedFromServer();
    ClientGUI getClientGUI();
}
