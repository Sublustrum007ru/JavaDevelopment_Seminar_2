package controller;

public interface ServerView {
    void showMessage(String message);
    void disconnectUser(ClientController clientController);
    boolean connectUser(ClientController clientController);
}
