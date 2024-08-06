package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.HoaDon;
import ultis.DBContext;
import ultis.JdbcHelper;

public class HoaDonService extends DBContext implements MethodService1<HoaDon> {

    String GET_ALL = "Select * From HoaDon";
    String GET_BY_ID = "Select * From HoaDon Where IDHoaDon = ?";
    String INSERT = "Insert HoaDon(IDTaiKhoan, IDKhachHang, IDPhuongThuc, "
            + "TrangThai, NgayMua, KhachTienMat, KhachChuyenKhoan, TongTien, TienGiam, TienPhaiTra) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";
    String UPDATE = "Update HoaDon Set IDTaiKhoan = ?, IDKhachHang = ?, "
            + "IDPhuongThuc = ?, TrangThai = ?, NgayMua = ?, KhachTienMat = ?, KhachChuyenKhoan = ?, TongTien = ?, "
            + "TienGiam = ?, TienPhaiTra = ? Where IDHoaDon = ?";

    @Override
    public List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDon hoaDon = HoaDon.builder()
                        .idHoaDon(rs.getInt(1))
                        .idTaiKhoan(rs.getInt(2))
                        .idKhachHang(rs.getInt(3))
                        .idPhuongThuc(rs.getInt(4))
                        .trangThai(rs.getString(5))
                        .ngayMua(rs.getString(6))
                        .khachTienMat(rs.getInt(7))
                        .khachChuyenKhoan(rs.getInt(8))
                        .tienSP(rs.getInt(9))
                        .tienGiam(rs.getInt(10))
                        .tongTien(rs.getInt(11))
                        .build();
                list.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    @Override
    public List<HoaDon> getAll() {
        return selectBySQL(GET_ALL);
    }

    @Override
    public HoaDon getByID(int id) {
        List<HoaDon> list = this.selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void add(HoaDon o) {
        JdbcHelper.executeUpdate(INSERT, o.getIdTaiKhoan(),
                o.getIdKhachHang(), o.getIdPhuongThuc(),
                o.getTrangThai(), o.getNgayMua(), o.getKhachTienMat(), o.getKhachChuyenKhoan(),
                o.getTienSP(),
                o.getTienGiam(), o.getTongTien());
    }

    @Override
    public void update(HoaDon o, int id) {
        JdbcHelper.executeUpdate(UPDATE, o.getIdTaiKhoan(), 
                o.getIdKhachHang(), o.getIdPhuongThuc(),
                o.getTrangThai(), o.getNgayMua(), o.getKhachTienMat(), o.getKhachChuyenKhoan(),
                o.getTienSP(),
                o.getTienGiam(), o.getTongTien(), id);  
    }

    public List<HoaDon> getAllPhanTrang(int pageNumber, int size) {
        List<HoaDon> hoaDons = getAll();
        int tongSoHoaDon = hoaDons.size();
        int soTrang = tongSoHoaDon / size;
        if (tongSoHoaDon % size != 0) {
            soTrang++;
        }
        int indexDau = (pageNumber - 1) * size;
        int indexCuoi = indexDau + size;
        if (indexCuoi > tongSoHoaDon) {
            indexCuoi = tongSoHoaDon;
        }
        return hoaDons.subList(indexDau, indexCuoi);
    }

    public int getSoTrang(int soLuongHoaDonTrenTrang) {
        int tongSoHoaDon = getAll().size();
        int soTrang = tongSoHoaDon / soLuongHoaDonTrenTrang;
        if (tongSoHoaDon % soLuongHoaDonTrenTrang != 0) {
            soTrang++;
        }
        return soTrang;
    }
}
