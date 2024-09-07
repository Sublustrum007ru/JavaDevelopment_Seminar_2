package view;

import controller.ServerController;
import controller.ServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ServerView {
    private ServerController serverController;

    private final int WIDHT = 800;
    private final int HEIGTH = 300;
    private final String TITLE = "Server";
    private final String btnStartTitle = "Strat";
    private final String btnStopTitle = "Stop";

    private JTextArea log;
    private JPanel footerPanel;
    private JButton btnStart, btnStop;

    public void setServerController(ServerController serverController){
        this.serverController = serverController;
    }

    public ServerGUI(){
        settings();
        createPanels();
        setVisible(true);
    }

    private void settings(){
        setTitle(TITLE);
        setSize(WIDHT, HEIGTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void createPanels(){
        add(createLog());
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createFooterPanel(){
        footerPanel = new JPanel(new GridLayout(1,2));
        btnStart = new JButton(btnStartTitle);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.setServerStatus(true);
            }
        });
        btnStop = new JButton(btnStopTitle);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.setServerStatus(false);
            }
        });
        footerPanel.add(btnStart);
        footerPanel.add(btnStop);
        return footerPanel;
    }

    @Override
    public void showMessage(String message) {
        log.append(message + "\n");
    }

    @Override
    public void connectUser() {

    }

    @Override
    public void disconnect() {

    }
}
