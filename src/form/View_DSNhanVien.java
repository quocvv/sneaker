package form;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.SortOrder;
import java.util.List;
import dto.NhanVienDTO;
import dto.TaiKhoanChiTietDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import services.ThongTinCaNhanService;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import raven.toast.Notifications;
import ultis.ExcelHelper;
import ultis.MsgHelper;

public final class View_DSNhanVien extends javax.swing.JPanel {

    ThongTinCaNhanService caNhanService = new ThongTinCaNhanService();

    public View_DSNhanVien() {
        initComponents();
        init(caNhanService.getAllTaiKhoanDTO());
    }

    public void init(List<TaiKhoanChiTietDTO> list) {
        table.fixTable(jScrollPane1);
        TableCellRenderer buttonRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Component component) {
                    return component;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };

        JButton updateButton = new JButton("Update");
        updateButton.setBackground(new java.awt.Color(0, 204, 255));
        updateButton.setFont(new java.awt.Font("Segoe UI", 1, 14));
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        TableColumn updateColumn = table.getColumnModel().getColumn(9);
        updateColumn.setCellRenderer(buttonRenderer);
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        tm.setRowCount(0);
        Integer number = 0;
        for (TaiKhoanChiTietDTO o : list) {
            number++;
            tm.addRow(new Object[]{
                number, o.getMaNhanVien(), o.getHoTen(), o.getEmail(), o.getSdt(),
                o.getGioiTinh() ? "Nam" : "Nữ", o.getDiaChi(), !o.getVaiTro() ? "Quản lý" : "Nhân Viên",
                !o.getTrangThai() ? "Hoạt Động" : "Nghỉ Việc",
                updateButton
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fadeEffect1 = new LIB.FadeEffect();
        jPanel1 = new javax.swing.JPanel();
        roundPanel2 = new swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new table.Table();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTexfieldPH_FielTex1 = new LIB.JTexfieldPH_FielTex();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel2.setRound(10);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Mã nhân viên", "Tên", "Email", "SDT", "Giới tính ", "Địa chỉ", "Vai trò", "Trạng thái", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setText("Danh Sách Nhân Viên");

        jButton3.setBackground(new java.awt.Color(0, 102, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tìm Kiếm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTexfieldPH_FielTex1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTexfieldPH_FielTex1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTexfieldPH_FielTex1FocusGained(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Xuất Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo Trạng Thái", "Theo Chức Vụ", "Theo Giới Tính", "Theo Mã Nhân Viên" }));
        jComboBox1.setToolTipText("");
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox1FocusGained(evt);
            }
        });
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Thêm Mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addGap(48, 48, 48)
                .addComponent(jTexfieldPH_FielTex1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(268, 268, 268))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTexfieldPH_FielTex1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        View_QLNhanVien modal_TaiKhoan = null;
        try {
            modal_TaiKhoan = new View_QLNhanVien(null, "Thêm");
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
        }
        modal_TaiKhoan.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                init(caNhanService.getAllTaiKhoanDTO());
            }
        });
        modal_TaiKhoan.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        View_QLNhanVien modal_TaiKhoan = null;

        int row = table.getSelectedRow();
        String id = (String) table.getValueAt(row, 1);
        NhanVienDTO nhanVienDTO = caNhanService.getNhanVienByMa(id);
        String text = "Sửa";
        try {
            modal_TaiKhoan = new View_QLNhanVien(nhanVienDTO, text);
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
        }
        modal_TaiKhoan.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                init(caNhanService.getAllTaiKhoanDTO());
            }
        });
        modal_TaiKhoan.setVisible(true);


    }//GEN-LAST:event_tableMouseClicked

    private void jTexfieldPH_FielTex1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTexfieldPH_FielTex1ActionPerformed

    }//GEN-LAST:event_jTexfieldPH_FielTex1ActionPerformed

    private void jTexfieldPH_FielTex1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTexfieldPH_FielTex1FocusGained


    }//GEN-LAST:event_jTexfieldPH_FielTex1FocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String text = jTexfieldPH_FielTex1.getText();
        List<TaiKhoanChiTietDTO> chiTietDTOs = new ArrayList<>();
        for (TaiKhoanChiTietDTO o : caNhanService.getAllTaiKhoanDTO()) {
            if (o.getHoTen().contains(text)) {
                chiTietDTOs.add(o);
            }
        }
        init(chiTietDTOs);
    }//GEN-LAST:event_jButton3ActionPerformed


    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String key = jComboBox1.getSelectedItem().toString();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        if (key.equalsIgnoreCase("Theo Trạng Thái")) {
            sortKeys.add(new RowSorter.SortKey(8, SortOrder.ASCENDING));
        }
        if (key.equalsIgnoreCase("Theo Chức Vụ")) {
            sortKeys.add(new RowSorter.SortKey(7, SortOrder.ASCENDING));
        }
        if (key.equalsIgnoreCase("Theo Giới Tính")) {
            sortKeys.add(new RowSorter.SortKey(5, SortOrder.ASCENDING));
        }
        if (key.equalsIgnoreCase("Theo Mã Nhân Viên")) {
            sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        }
        sorter.setSortKeys(sortKeys);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked

    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusGained


    }//GEN-LAST:event_jComboBox1FocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            ExcelHelper.fillData(table, new File("D:\\result1.xls"), table.getColumnCount() - 1, table.getRowCount());
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Xuất file excel thành công");
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Data saved at "
                    + "'D: \\ result.xls' successfully");
        } catch (HeadlessException ex) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Xuất file excel thất bại");
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LIB.FadeEffect fadeEffect1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private LIB.JTexfieldPH_FielTex jTexfieldPH_FielTex1;
    private swing.RoundPanel roundPanel2;
    private table.Table table;
    // End of variables declaration//GEN-END:variables

}
