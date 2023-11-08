package view;

import javax.swing.*;

/**
 * A panel containing a label and a button.
 */
class LabelButtonPanel extends JPanel {
    LabelButtonPanel(JLabel label, JButton button) {
        this.add(label);
        this.add(button);
    }
}
