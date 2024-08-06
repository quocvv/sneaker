package table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditer_1 extends DefaultCellEditor {

    private final TableActionEvent event;

    public TableActionCellEditer_1(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelActionUpdate update = new PanelActionUpdate();
        update.setBackground(Color.WHITE);
        update.initEvent(event, row);
        return update;
    }

}
