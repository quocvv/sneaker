package table;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        if (value instanceof ImageIcon imageIcon) {
            label.setIcon(imageIcon);
        }
        return label;
    }

}
