package controller;

/**
 * Класс содержащий логику работы клиента
 *
 * @clientView - абстракция графического интерфейса
 * @server - объект для связи с сервером
 */
public class ClientController {
    private boolean connected;
    private String userName;
    private ClientView clientView;
    private ServerController serverController;

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;

    }

    /**
     * Метод попытки подключения к серверую. Вызывается из GUI
     *
     * @param userName - имя пользователя, котоум будем подписываться исходящее сообщение
     * @return - ответ от ссервера. "true" - если прошли авторизацию.
     */
    public boolean connectToServer(String userName) {
        this.userName = userName;
        if (serverController.connectUser(this)) {
            showOnWindow("Вы успешно подключены!\n");
            connected = true;
            String log = serverController.getHistory();
            if (log != null) {
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Подключение не удалось");
            return false;
        }
    }

    /**
     * Метод отключения от сервера инициализированное клиентом
     */
    public void disconnetedFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectedFromServer();
            showOnWindow("Вы были отключены от сервера!");
            clientView.disconnectedFromServer();
        }
    }

    /**
     * Метод отключения от сервера инициализированное клиентом(например закрытие окна)
     */
    public void disconnectFromServer() {
        serverController.disconnetUser(this);
    }

    /**
     * Метод, с помощью которого сервер передает клиенту сообщения
     *
     * @param text - текст переданный от сервера
     */
    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    /**
     * Метод для передачи сообщения на сервер
     *
     * @param text - текст передаваемого сообщения
     */
    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                serverController.message(userName + ": " + text);
            }
        } else {
            showOnWindow("Нет подключения к серверу");
        }
    }

    /**
     * Метод вывода сообщения на GUI
     *
     * @param text - текс, который требуется вывести на экран
     */
    private void showOnWindow(String text) {
        clientView.showMessage(text);
    }
}
