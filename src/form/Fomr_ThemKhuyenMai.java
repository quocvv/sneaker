package form;

import entity.GiamGiaChiTiet;
import entity.KhuyenMai;
import entity.SanPham;
import entity.SanPhamChiTiet;
import entity.ThuocTinh;
import java.awt.Component;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import services.GiamGiaChiTieTService;
import services.QuanLysKhuyenMaiService;
import services.SanPhamChiTietService;
import services.SanPhamService;
import services.ThuocTinhService;
import ultis.ImageHelper;

import ultis.MsgHelper;

public class Fomr_ThemKhuyenMai extends javax.swing.JDialog {

    private final SanPhamChiTietService phamChiTietService = new SanPhamChiTietService();
    private final JCheckBox checkBox = new JCheckBox();
    int page = 0;
    String trangThai = "";
    boolean checkSp = false;

    public Fomr_ThemKhuyenMai() {

        initComponents();

        loadData(phamChiTietService.getPage(page, 10));
        jLabel8.setText(page + "");

    }

    private void loadData(List<SanPhamChiTiet> list) {
        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        tm.setRowCount(0);

        for (SanPhamChiTiet o : list) {
            ThuocTinh mauSac = new ThuocTinhService().getByID("MauSac", o.getIdMauSac());

            ThuocTinh kichThuoc = new ThuocTinhService().getByID("KichThuoc", o.getIdKichThuoc());

            ImageIcon imageIcon = (o.getAnh() != null && !o.getAnh().isEmpty()) ? ImageHelper.readImg(o.getAnh()) : null;

            checkBox.setSelected(false);
            SanPham sanPham = new SanPhamService().getByID(o.getIdSanPham());
            tm.addRow(new Object[]{
                o.getIDSPCT(), sanPham.getTenSP(),
                (imageIcon != null) ? scaleImageIcon(imageIcon) : null,
                mauSac.getTenThuocTinh(), kichThuoc.getTenThuocTinh(),
                o.getSoLuong(), o.getGia(),
                false
            });

        }
        TableColumnModel columnModel = jTable1.getColumnModel();
        TableColumn checkBoxColumn = columnModel.getColumn(7);
        checkBoxColumn.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                checkBox.setSelected((Boolean) value);

                return checkBox;
            }
        });

        checkBoxColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));

    }

    private ImageIcon scaleImageIcon(ImageIcon icon) {
        Image originalImage = icon.getImage();
        Image scaledImage = originalImage.getScaledInstance(110, 45, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private boolean validateData() {
        LocalDate today = LocalDate.now();
        LocalDate selectedDate = jDateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate selectedDateKt = jDateChooser2.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (selectedDate == null || selectedDateKt == null || jTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
            return false;
        }

        if (selectedDate.isBefore(today)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại");
            return false;
        }

        if (selectedDateKt.isBefore(selectedDate)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu");
            return false;
        }

        if (Integer.valueOf(jTextField2.getText()) < 0 || Integer.valueOf(jTextField2.getText()) > 50) {
            MsgHelper.alert(this, "Giá trị sai");
            return false;
        }

        QuanLysKhuyenMaiService quanLyKhuyenMaiService = new QuanLysKhuyenMaiService();
        List<KhuyenMai> list = quanLyKhuyenMaiService.getAll();

        for (KhuyenMai khuyenMai : list) {
            LocalDate khuyenMaiStartDate = LocalDate.parse(khuyenMai.getNgayBatDau());
            LocalDate khuyenMaiEndDate = LocalDate.parse(khuyenMai.getNgayKetThuc());
            if (selectedDate.isEqual(khuyenMaiStartDate) || selectedDateKt.isEqual(khuyenMaiEndDate)) {
                JOptionPane.showMessageDialog(this, "Chỉ được phép có một khuyến mãi trong khoảng thời gian đã chọn");
                return false;
            }
            if (selectedDate.isAfter(khuyenMaiStartDate) && selectedDate.isBefore(khuyenMaiEndDate)) {
                JOptionPane.showMessageDialog(this, "Khuyến mãi khác nằm trong khoảng thời gian đã chọn");
                return false;
            }
            if (selectedDate.isBefore(khuyenMaiEndDate)) {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu nhỏ hơn ngày kết thúc của một khuyến mãi đã tồn tại");
                return false;
            }
        }
        return true;
    }

    private KhuyenMai readData() {

        Date today = new Date();
        Date selectedDate = jDateChooser1.getDate();
        Date selectedDateKt = jDateChooser2.getDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngayBatDau = dateFormat.format(selectedDate);
        String ngayKetThuc = dateFormat.format(selectedDateKt);

        if (selectedDate != null) {
            if (selectedDate.equals(today)) {
                trangThai = "Đang bắt đầu";
            } else if (selectedDate.before(today)) {
                if (selectedDateKt != null && selectedDateKt.after(today)) {
                    trangThai = "Đang diễn ra";

                } else {
                    trangThai = "Đã kết thúc";
                }
            } else {
                trangThai = "Sắp diễn ra";
            }
        }

        return KhuyenMai.builder()
                .ten(jTextField1.getText())
                .ngayBatDau(ngayBatDau)
                .ngayKetThuc(ngayKetThuc)
                .trangThai(trangThai)
                .build();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm Mới");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên Khuyến Mãi");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Thời Gian Bắt Đầu");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Thời Gian Kết Thúc");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Giá Trị");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên Sản Phẩm", "Ảnh", "Màu Sắc", "Kích Thước", "Số Luọng", "Giá", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCheckBox1.setText("Chọn Tất Cả");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("jLabel8");

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("|<");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText(">|");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Tìm Kiếm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tìm kiếm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGap(34, 34, 34)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addGap(31, 31, 31)
                            .addComponent(jLabel8)
                            .addGap(28, 28, 28)
                            .addComponent(jButton3)
                            .addGap(176, 176, 176))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(333, 333, 333)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        List<Integer> selectedMaSpList = new ArrayList<>();

        for (int row = 0; row < jTable1.getRowCount(); row++) {

            boolean isSelected = (boolean) jTable1.getValueAt(row, 7);

            if (isSelected) {
                int maSp = (int) jTable1.getValueAt(row, 0);
                selectedMaSpList.add(maSp);
            }

        }

        if (validateData()) {
            QuanLysKhuyenMaiService quanLysKhuyenMaiService = new QuanLysKhuyenMaiService();
            quanLysKhuyenMaiService.createObject(readData());
            if (checkSp) {
                for (SanPhamChiTiet o : new SanPhamChiTietService().getAll()) {
                    String donVi = "Theo Tiền";

                    if (donVi.equalsIgnoreCase("Theo Tiền")) {
                        GiamGiaChiTiet giamGiaChiTiet = GiamGiaChiTiet.builder()
                                .idGiamGia(quanLysKhuyenMaiService.getAll().get(quanLysKhuyenMaiService.getAll().size() - 1).getId())
                                .soTien(Integer.parseInt(jTextField2.getText()))
                                .idSp(o.getIDSPCT())
                                .donVi(donVi)
                                .build();
                        boolean f = new GiamGiaChiTieTService().createObject(giamGiaChiTiet);

                    }
                }
            }
            for (Integer o : selectedMaSpList) {

                String donVi = "Theo Tiền";

                SanPhamChiTiet sanPham = new SanPhamChiTietService().getByID(o);
                if (donVi.equalsIgnoreCase("Theo Tiền")) {
                    GiamGiaChiTiet giamGiaChiTiet = GiamGiaChiTiet.builder()
                            .idGiamGia(quanLysKhuyenMaiService.getAll().get(quanLysKhuyenMaiService.getAll().size() - 1).getId())
                            .soTien(Integer.parseInt(jTextField2.getText()))
                            .idSp(sanPham.getIDSPCT())
                            .donVi(donVi)
                            .build();
                    boolean f = new GiamGiaChiTieTService().createObject(giamGiaChiTiet);

                }
            }
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private int clickCount = 0;
    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        clickCount++;

        if (clickCount % 2 == 1) {

            for (int row = 0; row < jTable1.getRowCount(); row++) {
                jTable1.setValueAt(true, row, 7);
                checkSp = true;
            }
        } else {
            for (int row = 0; row < jTable1.getRowCount(); row++) {
                jTable1.setValueAt(false, row, 7);
                checkSp = false;
            }
        }
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        page--;
        if (page < 1) {
            page = 1;
        }
        loadData(phamChiTietService.getPage(page, 10));
        if (checkSp) {
            for (int row = 0; row < jTable1.getRowCount(); row++) {
                jTable1.setValueAt(true, row, 7);

            }
        }
        jLabel8.setText(page + "");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int totalItems = phamChiTietService.getAll().size();
        int itemsPerPage = 10;
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        System.out.println(totalPages);
        if (page < totalPages) {
            page++;
            loadData(phamChiTietService.getPage(page, 10));
            if (checkSp) {
                for (int row = 0; row < jTable1.getRowCount(); row++) {
                    jTable1.setValueAt(true, row, 6);

                }
            }
            jLabel8.setText(page + "");
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        List<SanPhamChiTiet> list = new ArrayList<>();
        String searchText = jTextField3.getText().toLowerCase();

        for (SanPhamChiTiet o : phamChiTietService.getAll()) {
            SanPham sanPham = new SanPhamService().getByID(o.getIdSanPham());

            if (sanPham.getTenSP().toLowerCase().contains(searchText)) {
                list.add(o);
            }
        }

        if (!list.isEmpty()) {
            loadData(list);
        }
        if (jTextField3.getText().trim().isEmpty()) {
            page = 1;
            loadData(phamChiTietService.getPage(page, 10));
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
