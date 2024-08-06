package ultis;

import entity.GiamGiaChiTiet;
import entity.KhuyenMai;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import services.GiamGiaChiTieTService;
import services.QuanLysKhuyenMaiService;


public class RunKhuyenMai extends Thread {

    private volatile boolean running = true;
    private boolean checkStart = true;
    private boolean checkStop = true;

    @Override
    public void run() {
        while (running) {
            QuanLysKhuyenMaiService quanLyKhuyenMaiService = new QuanLysKhuyenMaiService();
            List<KhuyenMai> list = quanLyKhuyenMaiService.getAll();
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (KhuyenMai khuyenMai : list) {
                LocalDate ngayBatDau = LocalDate.parse(khuyenMai.getNgayBatDau(), formatter);
                LocalDate ngayKetThuc = LocalDate.parse(khuyenMai.getNgayKetThuc(), formatter);

                if (ngayBatDau.isEqual(currentDate) && checkStart && !khuyenMai.getTrangThai().equalsIgnoreCase("Đã Kết Thúc")) {
                    checkStart = false;
                    startKhuyenMai(khuyenMai);
                }
                if (ngayKetThuc.isEqual(currentDate) && checkStop && !khuyenMai.getTrangThai().equalsIgnoreCase("Đã Kết Thúc")) {
                    checkStop = false;
                    stopKhuyenMai(khuyenMai);
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    public void stopRunning() {
        running = false;
    }

    private void startKhuyenMai(KhuyenMai khuyenMai) {
        System.out.println("Runn KhuyenMai: " + khuyenMai.getId());

        List<GiamGiaChiTiet> giaChiTiets = new GiamGiaChiTieTService().getAllByIdGiamGia(khuyenMai.getId());
        for (GiamGiaChiTiet o : giaChiTiets) {
            new GiamGiaChiTieTService()
                    .updateKhuyenmai(o.getSoTien(), o.getIdSp());
        }
        new QuanLysKhuyenMaiService().updateTrangThai("Đang Diễn Ra", khuyenMai.getId());

    }

    private void stopKhuyenMai(KhuyenMai khuyenMai) {
        System.out.println("Stop KhuyenMai: " + khuyenMai.getId());

        List<GiamGiaChiTiet> giaChiTiets = new GiamGiaChiTieTService().getAllByIdGiamGia(khuyenMai.getId());
        for (GiamGiaChiTiet o : giaChiTiets) {
            new GiamGiaChiTieTService()
                    .updateKhuyenmaiEnd(o.getSoTien(), o.getIdSp());
        }
        new QuanLysKhuyenMaiService().updateTrangThai("Đã Kết Thúc", khuyenMai.getId());

    }
}
