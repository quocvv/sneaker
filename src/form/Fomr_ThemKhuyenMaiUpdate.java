package form;

import entity.GiamGiaChiTiet;
import entity.KhuyenMai;
import entity.SanPham;
import entity.SanPhamChiTiet;
import entity.ThuocTinh;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.GiamGiaChiTieTService;
import services.QuanLysKhuyenMaiService;
import services.SanPhamChiTietService;
import services.SanPhamService;
import services.ThuocTinhService;
import ultis.ImageHelper;

public class Fomr_ThemKhuyenMaiUpdate extends javax.swing.JDialog {

    private final JCheckBox checkBox = new JCheckBox();
    private final GiamGiaChiTieTService chiTieTService = new GiamGiaChiTieTService();
    int page = 1;
    private final int pageSize = 10;
    String trangThai = "";
    boolean checkSp = false;
    public List<SanPhamChiTiet> phams = null;

    public Fomr_ThemKhuyenMaiUpdate() throws ParseException {

        initComponents();
        phams = new ArrayList<>();
        String giaTri = "";
        for (GiamGiaChiTiet o : chiTieTService.getAllByIdGiamGia(QuanLyKhuyenMai.idGiamGiaUpdate)) {
            SanPhamChiTiet sanPham = new SanPhamChiTietService().getByID(o.getIdSp());
            phams.add(sanPham);
            giaTri = o.getSoTien() + "";
        }
        KhuyenMai khuyenMai = new QuanLysKhuyenMaiService().getObject(QuanLyKhuyenMai.idGiamGiaUpdate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayBatDau = dateFormat.parse(khuyenMai.getNgayBatDau());
        Date ngayKetThuc = dateFormat.parse(khuyenMai.getNgayKetThuc());

        jTextField1.setText(khuyenMai.getTen());
        jDateChooser1.setDate(ngayBatDau);
        jDateChooser2.setDate(ngayKetThuc);
        jTextField2.setText(giaTri);

        loadData(phams);
        jLabel8.setText(page + "");

    }

    private void loadData(List<SanPhamChiTiet> list) {
        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        tm.setRowCount(0);

        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, list.size());

        for (int i = startIndex; i < endIndex; i++) {
            SanPhamChiTiet o = list.get(i);
            ThuocTinh mauSac = new ThuocTinhService().getByID("MauSac", o.getIdMauSac());
            ThuocTinh kichThuoc = new ThuocTinhService().getByID("KichThuoc", o.getIdKichThuoc());
            ImageIcon imageIcon = (o.getAnh() != null && !o.getAnh().isEmpty()) ? ImageHelper.readImg(o.getAnh()) : null;

            checkBox.setSelected(false);
            SanPham sanPham = new SanPhamService().getByID(o.getIdSanPham());
            tm.addRow(new Object[]{
                o.getIDSPCT(), sanPham.getTenSP(),
                (imageIcon != null) ? scaleImageIcon(imageIcon) : null,
                mauSac.getTenThuocTinh(), kichThuoc.getTenThuocTinh(),
                o.getSoLuong(), o.getGia(),});
        }
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

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên Khuyến Mãi");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Thời Gian Bắt Đầu");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Thời Gian Kết Thúc");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Giá Trị");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Chi Tiết Khuyến Mãi");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Sản Phẩm Được Khuyến Mãi");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên Sản Phẩm", "Ảnh", "Màu Sắc", "Kích Thước", "Số Luọng", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jButton2)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)))
                        .addGap(52, 52, 52))))
            .addGroup(layout.createSequentialGroup()
                .addGap(346, 346, 346)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 243, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jButton2)
                            .addComponent(jButton3))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        page--;
        if (page < 1) {
            page = 1;
        }
        loadData(phams);
        if (checkSp) {
            for (int row = 0; row < jTable1.getRowCount(); row++) {
                jTable1.setValueAt(true, row, 7);
            }
        }
        jLabel8.setText(page + "");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int totalItems = phams.size();
        int itemsPerPage = 10;
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        System.out.println(totalPages);
        if (page < totalPages) {
            page++;
            loadData(phams);
            if (checkSp) {
                for (int row = 0; row < jTable1.getRowCount(); row++) {
                    jTable1.setValueAt(true, row, 6);
                }
            }
            jLabel8.setText(page + "");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        List<SanPhamChiTiet> list = new ArrayList<>();
        String searchText = jTextField3.getText().toLowerCase();

        for (SanPhamChiTiet o : phams) {
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
            loadData(phams);
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
