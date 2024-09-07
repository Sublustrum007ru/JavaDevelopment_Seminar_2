package view;

import controller.ClientController;
import controller.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;


    private JTextArea log;
    private JTextField fieldIP, fieldPort, fieldLogin, fieldMessage;
    private JPasswordField fieldPassword;
    private JButton btnLogin, btnDisconect, btnSend;
    private JPanel headerPanel;

    private ClientController clientController;

    public ClientGUI() {
        setting();
        createPanel();

        setVisible(true);
    }

    private void setting() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private void createPanel() {
        add(createHederPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    public void setClient(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void showMessage(String message) {
        log.append(message + "\n");
    }

    @Override
    public void disconnectedFromServer() {
        headerPanel.setVisible(true);
    }


    public void login() {
        if(clientController.connectToServer(fieldLogin.getText())){
            headerPanel.setVisible(false);
        }

    }

    public void disconnetFromServer() {
        headerPanel.setVisible(true);
    }

    private void message() {
        clientController.message(fieldMessage.getText());
        fieldMessage.setText(null);
    }

    private Component createHederPanel() {
        headerPanel = new JPanel(new GridLayout(2, 3));
        fieldIP = new JTextField("127.0.0.1");
        fieldPort = new JTextField("3984");
        fieldLogin = new JTextField("Sublustrum007");
        fieldPassword = new JPasswordField("12345678");
        btnLogin = new JButton("Connect");
        btnDisconect = new JButton("Disconnect");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnDisconect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnectedFromServer();
                clientController.disconnectFromServer();
            }
        });
        headerPanel.add(fieldIP);
        headerPanel.add(fieldPort);
        headerPanel.add(new JPanel());
        headerPanel.add(fieldLogin);
        headerPanel.add(fieldPassword);
        headerPanel.add(btnLogin);

        return headerPanel;
    }

    private Component createLog() {
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        fieldMessage = new JTextField();
        fieldMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    message();
                }
            }
        });
        btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(fieldMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.disconnectedFromServer();
        }
    }
}
