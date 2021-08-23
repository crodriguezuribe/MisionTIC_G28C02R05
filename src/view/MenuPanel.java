package view;

import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class MenuPanel extends JPanel implements ActionListener{

    private View indexView;
    private JMenuBar menuBar;
    private JMenu menuA;
    private JMenu menuB;
    private JMenu menuC;
    private JSeparator item01_menuA;
    private JSeparator item01_menuC;
    private JMenuItem item02_menuA;
    private JMenuItem item01_menuB;
    private JMenuItem item02_menuB; 
    private JMenuItem item03_menuB;    
    private JMenuItem item02_menuC;

    public MenuPanel(View introIndexView) {
        super();
        setLayout(new GridLayout());
        indexView = introIndexView;

        menuBar = new JMenuBar();

        add(menuBar);

        menuA = new JMenu("File");
        menuB = new JMenu("Requirements");
        menuC = new JMenu("Help");

        menuBar.add(menuA);
        menuBar.add(menuB);
        menuBar.add(menuC);

        item01_menuA = new JSeparator();
        item02_menuA = new JMenuItem("Quit");
        item01_menuB = new JMenuItem("Requierment 1");
        item02_menuB = new JMenuItem("Requierment 2");
        item03_menuB = new JMenuItem("Requierment 3");
        item01_menuC = new JSeparator();
        item02_menuC = new JMenuItem("About");


        item02_menuA.addActionListener(this);
        item01_menuB.addActionListener(this);
        item02_menuB.addActionListener(this);
        item03_menuB.addActionListener(this);
        item02_menuC.addActionListener(this);
        

        menuA.add(item01_menuA);
        menuA.add(item02_menuA);
        menuB.add(item01_menuB);
        menuB.add(item02_menuB);
        menuB.add(item03_menuB);
        menuC.add(item01_menuC);
        menuC.add(item02_menuC);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(item02_menuA.getActionCommand())) {
            System.exit(0);
        }
        if (e.getActionCommand().equals(item01_menuB.getActionCommand())) {
            indexView.updateList1();
        }
        if (e.getActionCommand().equals(item02_menuB.getActionCommand())) {
            indexView.updateList2();
        }
        if (e.getActionCommand().equals(item03_menuB.getActionCommand())) {
            indexView.updateList3();
        }
        if (e.getActionCommand().equals(item02_menuC.getActionCommand())) {
            JOptionPane.showMessageDialog(null, ""
                + "MisionTIC 2022 - Grupo 28 - Ciclo 2 - Reto 5\n"
                + "Version: 1.0.1\n"
                + "Programa hecho por: Carlos Rodríguez\n" 
                + "Fecha de realización: 20-Agosto-2021\n"
                );
        }

        
    }
}
