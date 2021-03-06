package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class ButtonPanel extends JPanel implements ActionListener{
    private JButton btnReq1;
    private JButton btnReq2;
    private JButton btnReq3;
    private JLabel space12; 
    private JLabel space23;

    private View indexView;

    public ButtonPanel(View introIndexView) {
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        indexView = introIndexView;

        space12 = new JLabel("        ");
        space23 = new JLabel("        ");
        btnReq1 = new JButton("Requirement 1");
        btnReq2 = new JButton("Requirement 2");
        btnReq3 = new JButton("Requirement 3");

        btnReq1.addActionListener(this);
        btnReq2.addActionListener(this);
        btnReq3.addActionListener(this);

        add(btnReq1);
        add(space12);
        add(btnReq2);
        add(space23);
        add(btnReq3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(btnReq1.getActionCommand())) {
            indexView.updateList1();
        }
        if (e.getActionCommand().equals(btnReq2.getActionCommand())) {
            indexView.updateList2();
        }
        if (e.getActionCommand().equals(btnReq3.getActionCommand())) {
            indexView.updateList3();
        }
    }
    
}
