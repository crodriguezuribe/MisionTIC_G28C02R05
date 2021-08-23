package view;

import javax.swing.JFrame;
import controller.Controller;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame {

    private MenuPanel menuPanel;
    private DisplayPanel displayPanel;
    private ButtonPanel buttonPanel;
    private Controller control;

    public View(String url) {
        super();
        setLayout(new BorderLayout());
        setSize(600, 700);
        setMinimumSize(new Dimension(600, 700));
        setLocationRelativeTo(null);

        setTitle("MisionTIC 2022 - Grupo 28 - Ciclo 2 - Reto 5");
        
        menuPanel = new MenuPanel(this);
        displayPanel = new DisplayPanel(this);
        buttonPanel = new ButtonPanel(this);

        control = new Controller(url);

        add(menuPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateList1(){
        control.req15();
        req1(control.getReq1s5());
    }

    public void req1(ArrayList<String> requierementList1) {
        String[] requieredList = new String[requierementList1
        .size()];
        for (int i = 0; i < requieredList.length; i++) {
            requieredList[i] = requierementList1
            .get(i);
        }
        displayPanel.updateList(requieredList);
    }
    

    public void updateList2(){
        control.req25();
        req2(control.getReq2s5());
    }

    public void req2(ArrayList<String> requierementList2) {
        String[] requieredList = new String[requierementList2.size()];
        for (int i = 0; i < requieredList.length; i++) {
            requieredList[i] = requierementList2.get(i);
        }
        displayPanel.updateList(requieredList);
    }

    public void updateList3(){
        control.req35();
        req3(control.getReq3s5());
    }

    public void req3(ArrayList<String> requierementList3) {
        String[] requieredList = new String[requierementList3.size()];
        for (int i = 0; i < requieredList.length; i++) {
            requieredList[i] = requierementList3.get(i);
        }
        displayPanel.updateList(requieredList);
    }
}
