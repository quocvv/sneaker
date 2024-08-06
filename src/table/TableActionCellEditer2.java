package table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditer2 extends DefaultCellEditor {

    private final TableActionEvent event;

    public TableActionCellEditer2(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelAction panelAction = new PanelAction();
        panelAction.setBackground(Color.WHITE);
        panelAction.initEvent(event, row);
        return panelAction;
    }

}
