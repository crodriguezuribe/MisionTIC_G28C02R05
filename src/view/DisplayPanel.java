package view;

import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.*;

public class DisplayPanel extends JPanel{

    private JList<String> requirementList;
    // private View vistaIndex;

    public DisplayPanel(View introIndexView) {
        super();
        setLayout(new GridLayout());
        // vistaIndex = introIndexView;

        requirementList = new JList<>();

        add(requirementList);
    }

    public void updateList(String[] introList) {
        requirementList.setListData(introList);
    }

}
