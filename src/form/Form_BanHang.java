package form;

import dto.SessionTaiKhoan;
import dto.TaiKhoanDTOResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import entity.HoaDon;
import services.HoaDonService;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import raven.toast.Notifications;

public class Form_BanHang extends javax.swing.JPanel {

    ImageIcon icon = new ImageIcon("src/javaswingdev/icon/icons8_user_20px_1.png");
    private boolean firstButtonClick = false;
    View_HoaDon hoaDon;
    private final HoaDonService hds = new HoaDonService();
    public static int isChose;
    public static int[] temp = new int[5];
    TaiKhoanDTOResponse taiKhoan = SessionTaiKhoan.getTaiKhoanDTOResponse();

    public Form_BanHang() {
        initComponents();
        hoaDon = new View_HoaDon();
        hoaDon.setEnabled(firstButtonClick);
        jTabbedPane1.addTab("Hóa đơn", icon, hoaDon);
        jTabbedPane1.addChangeListener((ChangeEvent e) -> {
            isChose = jTabbedPane1.getSelectedIndex();
        });
    }

    HoaDon getModel() {
        return HoaDon.builder()
                .ngayMua((LocalDate.now() + " " + LocalTime.now()).substring(0, 22))
                .idTaiKhoan(taiKhoan.getId())
                .trangThai("Chờ xác nhận")
                .build();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jButton6 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setBackground(new java.awt.Color(0, 102, 204));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Tạo hóa đơn");
        jButton6.setToolTipText("");
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(864, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int isCheck = jTabbedPane1.getComponentCount();
        if (isCheck < 5) {
            if (!firstButtonClick) {
                isCheck = 0;
                firstButtonClick = true;
            } else {
                hoaDon = new View_HoaDon();
            }
            jTabbedPane1.addTab("Hóa đơn " + (isCheck + 1), icon, hoaDon);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Hóa đơn " + (isCheck + 1));
            hoaDon.setEnabled(true);
            hds.add(getModel());
            int sl = hds.getAll().size() - 1;
            temp[isCheck] = hds.getAll().get(sl).getIdHoaDon();
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tạo tối đa 5 hóa đơn");
        }
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton6;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
