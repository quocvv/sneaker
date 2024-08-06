package main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Component;
import dto.SessionTaiKhoan;
import dto.TaiKhoanDTOResponse;
import form.Form_1;
import form.Form_BanHang;
import form.Form_DoiMatKhau;
import form.Form_DoiTra;
import form.Form_HoaDon;
import form.QuanLyKhuyenMai;
import form.View_Products;
import form.View_Addtribiutes;
import form.View_Addtribiutes1;
import form.View_DSNhanVien;
import form.View_LoginForm;
import form.View_QLKhachHang;
import form.View_ThongTinTaiKhoan;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.SwingUtilities;
import raven.toast.Notifications;
import ultis.RunKhuyenMai;

public final class Main extends javax.swing.JFrame {

    private static Main main;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Main(boolean success) {
        initComponents();
        TaiKhoanDTOResponse taiKhoan = SessionTaiKhoan.getTaiKhoanDTOResponse();
        jLabel1.setText(taiKhoan.getHoten());
        jLabel2.setText(taiKhoan.getVaiTro() ? "Nhân Viên" : "Quản Lý");
        Notifications.getInstance().setJFrame(this);
        RunKhuyenMai runKhuyenMai = new RunKhuyenMai();
        runKhuyenMai.start();
        if (success) {
            executorService.submit(() -> {
                main.dispose();
                main = this;
                titleBar.initJFram(this);
                showForm(new View_DSNhanVien());
                menu.setSelectedIndex(0, 0);
                setVisible(true);
            });
            executorService.shutdown();
        } else {
            init();
        }
    }

    public void init() {
        main = this;
        titleBar.initJFram(this);
        menu.addEvent((index, indexSubMenu) -> {
            System.out.println(index + " " + indexSubMenu);
            switch (index) {
                case 0 -> {
                    if (indexSubMenu == 0) {
                        showForm(new Form_1());
                    }
                }
                case 1 -> {
                    switch (indexSubMenu) {
                        case 1 ->
                            showForm(new View_Products());
                        case 2 ->
                            showForm(new View_Addtribiutes());
                        case 3 ->
                            showForm(new View_Addtribiutes1());
                    }
                }
                case 2 -> {
                    if (indexSubMenu == 0) {
                        showForm(new View_DSNhanVien());
                    }
                }
                case 3 -> {
                    if (indexSubMenu == 0) {
                        showForm(new View_QLKhachHang());
                    }
                }
                case 4 -> {
                    if (indexSubMenu == 0) {
                        showForm(new Form_HoaDon());
                    }
                }
                case 5 -> {
                    if (indexSubMenu == 0) {
                        showForm(new Form_BanHang());
                    }
                }
                case 6 -> {
                    if (indexSubMenu == 0) {
                        showForm(new QuanLyKhuyenMai());
                    }
                }
                case 7 -> {
                    if (indexSubMenu == 0) {
                        showForm(new Form_DoiTra());
                    }
                }
                case 8 -> {
                    if (indexSubMenu == 1) {
                        showForm(new View_ThongTinTaiKhoan());
                    }
                    if (indexSubMenu == 2) {
                        Form_DoiMatKhau form_DoiMatKhau = new Form_DoiMatKhau();
                        form_DoiMatKhau.setVisible(true);
                        form_DoiMatKhau.setLocationRelativeTo(null);
                    }
                }
                 case 9 -> {
                    if (indexSubMenu == 0) {
                        SessionTaiKhoan.setTaiKhoanDTOResponse(null);
                        this.setVisible(false);
                        View_LoginForm view_LoginForm = new View_LoginForm();
                        view_LoginForm.setVisible(true);
                        view_LoginForm.setLocationRelativeTo(this);
                    }
                }
            }
        });

        menu.setSelectedIndex(0, 0);
    }

    public void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.revalidate();
        body.repaint();
    }

    public static Main getMain() {
        return main;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        menu = new menu.Menu();
        roundPanel11 = new swing.RoundPanel1();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        titleBar = new titlebar.TitleBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trang chủ");
        setUndecorated(true);

        background.setBackground(new java.awt.Color(245, 245, 245));

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(249, 249, 249));
        menu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Tên Nhân Viên");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Chức vự");

        javax.swing.GroupLayout roundPanel11Layout = new javax.swing.GroupLayout(roundPanel11);
        roundPanel11.setLayout(roundPanel11Layout);
        roundPanel11Layout.setHorizontalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
        );
        roundPanel11Layout.setVerticalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(roundPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addComponent(roundPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        titleBar.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, 1033, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        FlatIntelliJLaf.setup();
//        SwingUtilities.invokeLater(() -> {
//            new Main(false).setVisible(true);
//        });

        SwingUtilities.invokeLater(() -> {
            new View_LoginForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JPanel body;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private menu.Menu menu;
    private javax.swing.JPanel panelMenu;
    private swing.RoundPanel1 roundPanel11;
    private titlebar.TitleBar titleBar;
    // End of variables declaration//GEN-END:variables
}
