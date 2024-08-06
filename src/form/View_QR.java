package form;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import services.HoaDonCTService;

public class View_QR extends javax.swing.JDialog {

    private WebcamPanel webcamPanel;
    private Webcam webcam;
    private ScheduledExecutorService executor;
    public static String maQR;
    private View_HoaDon parentPanel;
    private final HoaDonCTService hdcts = new HoaDonCTService();

    private static final int PANEL_WIDTH = 380;
    private static final int PANEL_HEIGHT = 270;
    private static final int CAPTURE_INTERVAL = 400;
    public static boolean shouldScan = true;

    public View_QR() {
        initComponents();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        initializeComboBox();
        initializeWebcam();
    }

    public void setParentPanel(View_HoaDon parentPanel) {
        this.parentPanel = parentPanel;
    }

    private void initializeComboBox() {
        jComboBox1.addItem("Camera máy tính");
        jComboBox1.addItem("Camera iVcame");
        jComboBox1.setSelectedIndex(1);
    }

    private void initializeWebcam() {
        Dimension size = WebcamResolution.VGA.getSize();
        webcam = Webcam.getWebcams().get(jComboBox1.getSelectedIndex());
        webcam.setViewSize(size);
        initializeWebcamPanel(size);
        startCaptureWorker();
    }

    private void initializeWebcamPanel(Dimension size) {
        if (webcamPanel != null) {
            jPanel2.remove(webcamPanel);
        }

        if (webcam != null && webcam.isOpen()) {
            webcam.close();
        }

        webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setPreferredSize(size);
        webcamPanel.setFPSDisplayed(true);
        jPanel2.add(webcamPanel, new AbsoluteConstraints(0, 0, PANEL_WIDTH, PANEL_HEIGHT));

        // Đảm bảo rằng panel được cập nhật lại hiển thị
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    private void startCaptureWorker() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::captureAndDecode, 0, CAPTURE_INTERVAL, TimeUnit.MILLISECONDS);
    }

    private void captureAndDecode() {
        try {
            if (webcam == null || !webcam.isOpen() || !shouldScan) {
                return;
            }

            BufferedImage img = webcam.getImage();
            if (img != null) {
                processImage(img);
            }
        } catch (WebcamException e) {
            e.printStackTrace(System.err);
        }
    }

    private void processImage(BufferedImage img) {
        LuminanceSource source = new BufferedImageLuminanceSource(img);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            maQR = result.getText();
            if (!maQR.isBlank()) {
//                dispose();
                View_SubSPCT subSPCT = new View_SubSPCT();
                subSPCT.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        shouldScan = true;
                        parentPanel.init(hdcts.getByIDHD(Form_BanHang.temp[Form_BanHang.isChose]));
                    }

                    @Override
                    public void windowOpened(WindowEvent e) {
                        shouldScan = false;
                    }

                });
                subSPCT.setVisible(true);
            }
        } catch (NotFoundException ignored) {
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (webcam != null) {
            webcam.close();
        }
        executor.shutdown();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Đổi camera");
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
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (executor != null && !executor.isTerminated()) {
            executor.shutdown();
        }
        if (webcam != null && webcam.isOpen()) {
            webcam.close();
        }
        initializeWebcam();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
