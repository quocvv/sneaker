package form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import dto.NhanVienDTO;
import entity.TaiKhoan;
import entity.ThongTinCaNhan;
import services.EmailService;
import services.TaiKhoanService;
import services.ThongTinCaNhanService;
import ultis.PasswordEncoder;
import javax.swing.JOptionPane;

public class View_QLNhanVien extends javax.swing.JDialog {

    private final ThongTinCaNhanService caNhanService = new ThongTinCaNhanService();
    private final TaiKhoanService taiKhoanService = new TaiKhoanService();

    public View_QLNhanVien(NhanVienDTO nhanVienDTO, String text) throws ParseException {

        initComponents();
        setLocationRelativeTo(null);
        if (nhanVienDTO != null) {
            mapObject(nhanVienDTO);
            jButton1.setText(text);
            jTexfieldPH_FielTex1.setEditable(false);
        } else {
            jButton1.setText("Thêm");
        }

    }

    private ThongTinCaNhan readObject() {
        boolean gioiTinh = jRadioButton1.isSelected();
        String maNhanVien = jTexfieldPH_FielTex1.getText();
        String tenNhanVien = jTexfieldPH_FielTex2.getText();
        String sdt = jTexfieldPH_FielTex3.getText();
        String email = jTexfieldPH_FielTex5.getText();
        String diaChi = jTexfieldPH_FielTex6.getText();
        Date selectedDate = jDateChooser1.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinh = dateFormat.format(selectedDate);
        ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan(null, maNhanVien, tenNhanVien, ngaySinh, gioiTinh, sdt, email, diaChi);
        return thongTinCaNhan;
    }

    public boolean validateInput() {
        boolean gioiTinh = jRadioButton1.isSelected();
        if (!gioiTinh && !jRadioButton2.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String maNhanVien = jTexfieldPH_FielTex1.getText();
        if (maNhanVien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String tenNhanVien = jTexfieldPH_FielTex2.getText();
        if (tenNhanVien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String sdt = jTexfieldPH_FielTex3.getText();
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String email = jTexfieldPH_FielTex5.getText();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ email", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String diaChi = jTexfieldPH_FielTex6.getText();
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Date selectedDate = jDateChooser1.getDate();
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void mapObject(NhanVienDTO nhanVienDTO) throws ParseException {
        jTexfieldPH_FielTex1.setText(nhanVienDTO.getMaNhanVien());
        jTexfieldPH_FielTex2.setText(nhanVienDTO.getHoTen());
        jTexfieldPH_FielTex3.setText(nhanVienDTO.getSdt());

        jTexfieldPH_FielTex4.setText(nhanVienDTO.getTenTk());
        jTexfieldPH_FielTex5.setText(nhanVienDTO.getEmail());

        jTexfieldPH_FielTex6.setText(nhanVienDTO.getDiaChi());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaySinh = dateFormat.parse(nhanVienDTO.getNgaySinh());
        jDateChooser1.setDate(ngaySinh);
        if (nhanVienDTO.getGioiTinh()) {
            jRadioButton1.setSelected(true);
        }
        if (!nhanVienDTO.getGioiTinh()) {
            jRadioButton2.setSelected(true);
        }
        if (nhanVienDTO.getVaiTro()) {
            jRadioButton4.setSelected(true);
        }

        if (!nhanVienDTO.getVaiTro()) {
            jRadioButton3.setSelected(true);
        }
        if (!nhanVienDTO.getTrangThai()) {
            jRadioButton5.setSelected(true);
        }

        if (nhanVienDTO.getTrangThai()) {
            jRadioButton6.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jTexfieldPH_FielTex1 = new LIB.JTexfieldPH_FielTex();
        jTexfieldPH_FielTex2 = new LIB.JTexfieldPH_FielTex();
        jTexfieldPH_FielTex3 = new LIB.JTexfieldPH_FielTex();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTexfieldPH_FielTex4 = new LIB.JTexfieldPH_FielTex();
        jTexfieldPH_FielTex5 = new LIB.JTexfieldPH_FielTex();
        jTexfieldPH_FielTex6 = new LIB.JTexfieldPH_FielTex();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã Nhân Viên");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên Nhân Viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Số Điện Thoại");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tài Khoản");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Địa chỉ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Thêm Nhân Viên");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Giới Tính");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Nam");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton2.setText("Nữ");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Vai trò");

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton3.setText("Quản Lý");

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton4.setSelected(true);
        jRadioButton4.setText("Nhân Viên");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Ngày Sinh");

        jButton1.setBackground(new java.awt.Color(0, 102, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Làm Mới");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton5.setSelected(true);
        jRadioButton5.setText("Hoạt Động");

        buttonGroup3.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton6.setText("Nghỉ Việc");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Trạng Thái");

        jTexfieldPH_FielTex1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTexfieldPH_FielTex1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTexfieldPH_FielTex1ActionPerformed(evt);
            }
        });

        jTexfieldPH_FielTex2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTexfieldPH_FielTex2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTexfieldPH_FielTex2ActionPerformed(evt);
            }
        });

        jTexfieldPH_FielTex3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTexfieldPH_FielTex4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTexfieldPH_FielTex5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTexfieldPH_FielTex6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTexfieldPH_FielTex3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTexfieldPH_FielTex2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTexfieldPH_FielTex1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(96, 96, 96)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTexfieldPH_FielTex5, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(jTexfieldPH_FielTex4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTexfieldPH_FielTex6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTexfieldPH_FielTex1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTexfieldPH_FielTex2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTexfieldPH_FielTex3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTexfieldPH_FielTex4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTexfieldPH_FielTex5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTexfieldPH_FielTex6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton3)
                        .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTexfieldPH_FielTex1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTexfieldPH_FielTex1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTexfieldPH_FielTex1ActionPerformed

    private void jTexfieldPH_FielTex2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTexfieldPH_FielTex2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTexfieldPH_FielTex2ActionPerformed

    private void jTexfieldPH_FielTex3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTexfieldPH_FielTex3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTexfieldPH_FielTex3ActionPerformed

    private void jTexfieldPH_FielTex4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTexfieldPH_FielTex4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTexfieldPH_FielTex4ActionPerformed

    private void jTexfieldPH_FielTex5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTexfieldPH_FielTex5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTexfieldPH_FielTex5ActionPerformed

    private void jTexfieldPH_FielTex6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTexfieldPH_FielTex6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTexfieldPH_FielTex6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTexfieldPH_FielTex1.setText("");
        jTexfieldPH_FielTex2.setText("");
        jTexfieldPH_FielTex3.setText("");
        jTexfieldPH_FielTex4.setText("");
        jTexfieldPH_FielTex5.setText("");
        jTexfieldPH_FielTex6.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!validateInput()) {
            return;
        }
        boolean vaiTro = jRadioButton3.isSelected() ? false : true;
        boolean trangThai = jRadioButton5.isSelected() ? false : true;
        String taiKhoanNv = jTexfieldPH_FielTex4.getText();
        String email = jTexfieldPH_FielTex5.getText();
        String password = generateRandomPassword(6);
        String hashedPassword = PasswordEncoder.encode(password);
//        Boolean checkP = PasswordEncoder.checkPassword(email, "787287");
//        System.out.println(checkP);
        if (jButton1.getText().equalsIgnoreCase("Thêm")) {
            caNhanService.createObject(readObject());
            try {
                EmailService.sendEmail(email, password);
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }

            TaiKhoan taiKhoan = TaiKhoan.builder()
                    .password(hashedPassword)
                    .ten(taiKhoanNv)
                    .trangThai(trangThai)
                    .vaiTro(vaiTro)
                    .thongTinCaNhan(caNhanService.getThongTinCaNhanByMa(jTexfieldPH_FielTex1.getText()))
                    .build();
            taiKhoanService.createObject(taiKhoan);
        } else {
            ThongTinCaNhan thongTinCaNhan = caNhanService.getThongTinCaNhanByMa(jTexfieldPH_FielTex1.getText());
            caNhanService.updateObject(readObject(), thongTinCaNhan.getId());
            TaiKhoan taiKhoan = TaiKhoan.builder()
                    .ten(taiKhoanNv)
                    .trangThai(trangThai)
                    .vaiTro(vaiTro)
                    .thongTinCaNhan(caNhanService.getThongTinCaNhanByMa(jTexfieldPH_FielTex1.getText()))
                    .build();
            taiKhoanService.updateObjectByIDTTCN(taiKhoan, String.valueOf(thongTinCaNhan.getId()));
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public String generateRandomPassword(int length) {
        Random random = new Random();
        StringBuilder passwordBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomNumber = random.nextInt(10); // Số ngẫu nhiên từ 0 đến 9
            passwordBuilder.append(randomNumber);
        }
        return passwordBuilder.toString();
    }

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
    }//GEN-LAST:event_jRadioButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private LIB.JTexfieldPH_FielTex jTexfieldPH_FielTex1;
    private LIB.JTexfieldPH_FielTex jTexfieldPH_FielTex2;
    private LIB.JTexfieldPH_FielTex jTexfieldPH_FielTex3;
    private LIB.JTexfieldPH_FielTex jTexfieldPH_FielTex4;
    private LIB.JTexfieldPH_FielTex jTexfieldPH_FielTex5;
    private LIB.JTexfieldPH_FielTex jTexfieldPH_FielTex6;
    // End of variables declaration//GEN-END:variables
}
