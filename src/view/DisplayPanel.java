package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
// import javax.swing.JTable;
import javax.swing.JList;
import java.awt.*;

public class DisplayPanel extends JPanel{

    private JList<String> requirementList;
    private JScrollPane diplayPanel;

    public DisplayPanel(View introIndexView) {
        super();
        setLayout(new GridLayout());
        // vistaIndex = introIndexView;

        requirementList = new JList<>();

        diplayPanel = new JScrollPane(requirementList);
        
        add(diplayPanel);
    }

    public void updateList(String[] introList) {
        requirementList.setListData(introList);
    }

}
