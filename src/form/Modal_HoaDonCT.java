package form;

import entity.DiaChi;
import entity.HoaDon;
import entity.HoaDonChiTiet;
import entity.KhachHang;
import entity.SanPham;
import entity.SanPhamChiTiet;
import entity.ThuocTinh;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import services.DiaChiService;
import services.HoaDonService;
import services.KhachHangService;
import services.SanPhamChiTietService;
import services.SanPhamService;
import services.ThuocTinhService;

public class Modal_HoaDonCT extends javax.swing.JDialog {

    private final ThuocTinhService thuocTinhService = new ThuocTinhService();
    Double bHeight = 0.0;
    List<HoaDonChiTiet> hoaDonChiTiets = null;
    int tongTien = 0;

    public Modal_HoaDonCT(List<HoaDonChiTiet> list) {

        initComponents();
        if (!list.isEmpty()) {
            hoaDonChiTiets = new ArrayList<>(list);

            HoaDon hoaDon = new HoaDonService().getByID(list.get(0).getIdHoaDon());
            tongTien = hoaDon.getTienSP();
            KhachHang khachHang = new KhachHangService().getByID(hoaDon.getIdKhachHang());
            if (khachHang != null) {
                jTextField1.setText(khachHang.getHoTen());
                jTextField2.setText(khachHang.getSdt());
                List<DiaChi> diaChi = new DiaChiService().getAllByIDKhachHang(khachHang.getMaKH());
                System.out.println(diaChi.size());
                if (!diaChi.isEmpty()) {
                    jTextField3.setText(diaChi.get(0).getDiaChi());
                }

            }
            jTextField5.setText(tongTien + "");
            loadData(list);
        }
    }

    private void loadData(List<HoaDonChiTiet> hoaDonChiTiets) {
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        tm.setRowCount(0);
        int count = 1;
        for (HoaDonChiTiet o : hoaDonChiTiets) {
            SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTietService().getByID(o.getIdSPCT());
            SanPham sanPham = new SanPhamService().getByID(sanPhamChiTiet.getIdSanPham());
            ThuocTinh mauSac = thuocTinhService.getByID("MauSac", sanPhamChiTiet.getIdMauSac());
            ThuocTinh kichThuoc = thuocTinhService.getByID("KichThuoc", sanPhamChiTiet.getIdKichThuoc());
            ThuocTinh thuongHieu = thuocTinhService.getByID("ThuongHieu", sanPham.getIdThuongHieu());
            ThuocTinh chatLieu = thuocTinhService.getByID("ChatLieu", sanPham.getIdChatLieu());
            ThuocTinh deGiay = thuocTinhService.getByID("Degiay", sanPham.getIdDeGiay());
            ThuocTinh lot = thuocTinhService.getByID("lot", sanPham.getIdLot());

            Integer tongTien = 0;
            tongTien += o.getSoLuong() * sanPhamChiTiet.getGia();
            tm.addRow(new Object[]{
                count++,
                sanPham.getTenSP(),
                mauSac.getTenThuocTinh(), kichThuoc.getTenThuocTinh(),
                thuongHieu.getTenThuocTinh(),
                chatLieu.getTenThuocTinh(),
                deGiay.getTenThuocTinh(),
                lot.getTenThuocTinh(),
                o.getSoLuong(), 0, tongTien
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new swing.RoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new table.Table();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Tên Sản Phẩm", "Màu Sắc", "Kích Thước", "Thương Hiệu", "Chất Liệu", "Đế Giày", "Lót", "Số Lượng", "Tiền Giảm", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(5);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("Chi Tiết Hóa Đơn");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Khách Hàng");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Số Điện Thoại");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Địa Chỉ");

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Thông Tin Sản Phẩm");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tổng Tiền");

        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Xuất Hóa Đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(66, 66, 66)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(29, 29, 29))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(), getPageFormat(pj));
        try {
            pj.print();

        } catch (PrinterException ex) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public PageFormat getPageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = bHeight;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    protected static double cm_to_pp(double cm) {
//        return toPPI(cm * 0.393600787);
        return toPPI(cm * 0.99999999999);

    }

    protected static double toPPI(double inch) {
        return inch * 100d;
    }

    public class BillPrintable implements Printable {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            int r = hoaDonChiTiets.size();
            ImageIcon icon = new ImageIcon(getClass().getResource("/menu/logo-bill.png"));
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {
                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
                try {
                    int y = 50;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    g2d.drawImage(icon.getImage(), 70, 20, 90, 50, null);
                    y += yShift + 30;
                    g2d.drawString("---------------------------------------------------------------------------------------------------------------------------", 12, y);
                    y += yShift;

                    g2d.drawString("              Cửa hàng Shoe Shoes            ", 12, y);
                    y += yShift;
                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    g2d.drawString("Địa Chỉ : " + "Km12 Cầu Diễn, Phường Phúc Diễn, Quận Bắc Từ Liêm, Hà Nội" + "           ", 12, y);
                    y += yShift;
                    g2d.drawString("           SĐT: " + "(024) 8582 0808" + "           ", 12, y);
                    y += yShift;
                    g2d.drawString("---------------------------------------------------------------------------------------------------------------------------", 12, y);
                    y += headerRectHeight;

//                    g2d.drawString("STT |Tên sản phẩm|Màu Sắc|Kích Thước|Chất Liệu|Thương Hiệu| Đế Giày |Lót||SL|Đơn giá|Thành tiền ", 7, y);
//                    y += yShift;
//                    g2d.drawString("---------------------------------------------------------------------------------------------------------------------------", 12, y);
//                    y += headerRectHeight;
                    int count = 1;
                    for (HoaDonChiTiet o : hoaDonChiTiets) {
                        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTietService().getByID(o.getIdSPCT());
                        SanPham sanPham = new SanPhamService().getByID(sanPhamChiTiet.getIdSanPham());
                        ThuocTinh mauSac = thuocTinhService.getByID("MauSac", sanPhamChiTiet.getIdMauSac());
                        ThuocTinh kichThuoc = thuocTinhService.getByID("KichThuoc", sanPhamChiTiet.getIdKichThuoc());
                        ThuocTinh chatLieu = thuocTinhService.getByID("ChatLieu", sanPham.getIdChatLieu());
                        ThuocTinh thuongHieu = thuocTinhService.getByID("ThuongHieu", sanPham.getIdThuongHieu());

                        ThuocTinh deGiay = thuocTinhService.getByID("DeGiay", sanPham.getIdDeGiay());
                        ThuocTinh lot = thuocTinhService.getByID("Lot", sanPham.getIdLot());

                        int thanhTien = o.getSoLuong() * sanPhamChiTiet.getGia();
                        int lineHeight = g2d.getFontMetrics().getHeight();
                        int x = 10;

                        g2d.drawString("STT: " + count, x, y);
                        y += yShift;
                        g2d.drawString("Tên sản phẩm: " + sanPham.getTenSP(), 10, y);
                        y += yShift;
                        g2d.drawString("Màu sắc: " + mauSac.getTenThuocTinh(), 10, y);
                        y += yShift;
                        g2d.drawString("Kích thước: " + kichThuoc.getTenThuocTinh(), 10, y);
                        y += yShift;
                        g2d.drawString("Chất liệu: " + chatLieu.getTenThuocTinh(), 10, y);
                        y += yShift;
                        g2d.drawString("Thương hiệu: " + thuongHieu.getTenThuocTinh(), 10, y);
                        y += yShift;
                        g2d.drawString("Lô hàng: " + lot.getTenThuocTinh(), 10, y);
                        y += yShift;
                        g2d.drawString("Số lượng: " + o.getSoLuong(), 10, y);
                        y += yShift;
                        g2d.drawString("Giá: " + sanPhamChiTiet.getGia() + " VNĐ", 10, y);
                        y += yShift;
                        g2d.drawString("Thành tiền: " + thanhTien + " VNĐ", 10, y);
                        y += yShift + 10;

                        count++;
                    }
//                    y += yShift;
//                    g2d.drawString("Tiền khách đưa: " + String.format("%,d", 0) + " VNĐ", 10, y);
//                    y += yShift;
//                    g2d.drawString("Tiền trả lại: " + String.format("%,d", hoaDon.getTienKhachDua() - hoaDon.getTongTien()) + " VNĐ", 10, y);
                    g2d.drawString("---------------------------------------------------------------------------------------------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("Tổng tiền: " + String.format("%,d", tongTien) + " VNĐ", 10, y);
                    y += yShift;
                    g2d.drawString("Khách Hàng: " + jTextField1.getText(), 10, y);
                    y += yShift;
                    g2d.drawString("Số Điện thoại: " + jTextField2.getText(), 10, y);
                    y += yShift;
                    g2d.drawString("Địa Chỉ: " + jTextField3.getText(), 10, y);
                    y += yShift;
                    g2d.drawString("Cảm ơn quý khách đã đến!", 10, y);
                    y += yShift;
                    g2d.drawString("---------------------------------------------------------------------------------------------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("             Hẹn gặp lại quý khách      ", 10, y);
                    y += yShift;
                    g2d.drawString("---------------------------------------------------------------------------------------------------------------------------", 12, y);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private swing.RoundPanel roundPanel1;
    private table.Table table;
    // End of variables declaration//GEN-END:variables
}
