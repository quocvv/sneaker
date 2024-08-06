package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.SanPhamChiTiet;
import ultis.JdbcHelper;

public class SanPhamChiTietService implements MethodService1<SanPhamChiTiet> {

    String GET_ALL = "Select * From SanPhamChiTiet";
    String GET_ALL_BTT = """
                         Select IDSPCT, MaSPCT, IDSanPham, IDMauSac, IDKichThuoc, SoLuong, Gia, MaQR, Anh
                         From SanPhamChiTiet
                         JOIN dbo.SanPham SP on SP.ID = SanPhamChiTiet.IDSanPham
                         Where SP.TinhTrang = 1 AND SoLuong > 0 """;
    String GET_BY_ID = "Select * From SanPhamChiTiet Where IDSPCT = ?";
    String GET_BY_IDSP = "Select * From SanPhamChiTiet Where IDSanPham = ?";
    String GET_BY_QR = "Select * From SanPhamChiTiet Where MaQR = ?";
    String GET_BY_IDMASPCT = "Select * From SanPhamChiTiet Where MaSPCT = ?";
    String GET_BY_LikeMASPCT = "Select * From SanPhamChiTiet Where MaSPCT Like ? AND IDSanPham = ?";
    String GET_BY_SlMASPCT = "Select * From SanPhamChiTiet Where MaSPCT Like ?";
    String INSERT = "Insert SanPhamChiTiet(MaSPCT, IDSanPham, IDMauSac, IDKichThuoc, SoLuong, Gia, MaQR, Anh) VALUES (?,?,?,?,?,?,?,?)";
    String UPDATE = "Update SanPhamChiTiet Set MaSPCT = ?, IDSanPham = ?, IDMauSac = ?, IDKichThuoc = ?, SoLuong = ?, Gia = ?, MaQR = ?, Anh = ? Where IDSPCT = ?";
    String GET_PAGE = "SELECT * FROM SanPhamChiTiet Where IDSanPham = ? ORDER BY IDSPCT DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    String GET_PAGEV2 = "SELECT * FROM SanPhamChiTiet ORDER BY IDSPCT DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    @Override
    public List<SanPhamChiTiet> selectBySQL(String sql, Object... args) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                SanPhamChiTiet spct = SanPhamChiTiet.builder()
                        .IDSPCT(rs.getInt("IDSPCT"))
                        .maSPCT(rs.getString("MaSPCT"))
                        .idSanPham(rs.getInt("IDSanPham"))
                        .idMauSac(rs.getInt("IDMauSac"))
                        .idKichThuoc(rs.getInt("IDKichThuoc"))
                        .soLuong(rs.getInt("SoLuong"))
                        .gia(rs.getInt("Gia"))
                        .maQR(rs.getString("MaQR"))
                        .anh(rs.getString("Anh"))
                        .build();
                list.add(spct);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    @Override
    public List<SanPhamChiTiet> getAll() {
        return selectBySQL(GET_ALL);
    }

    public List<SanPhamChiTiet> getAllBTT() {
        return selectBySQL(GET_ALL_BTT);
    }

    public List<SanPhamChiTiet> getPage(int idSP, int pageNumber, int pageSize) {
        return selectBySQL(GET_PAGE, idSP, pageNumber, pageSize);
    }

    public List<SanPhamChiTiet> getAllIDSP(int value) {
        return selectBySQL(GET_BY_IDSP, value);
    }

    public List<SanPhamChiTiet> getByQR(String value) {
        return selectBySQL(GET_BY_QR, value);
    }

    public SanPhamChiTiet getByMaSPCT(String maSPCT) {
        List<SanPhamChiTiet> list = this.selectBySQL(GET_BY_IDMASPCT, maSPCT);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<SanPhamChiTiet> search(String value, int idSP) {
        return selectBySQL(GET_BY_LikeMASPCT, "%" + value + "%", idSP);
    }

    public List<SanPhamChiTiet> searchAll(String value) {
        return selectBySQL(GET_BY_SlMASPCT, "%" + value + "%");
    }

    @Override
    public SanPhamChiTiet getByID(int id) {
        List<SanPhamChiTiet> list = this.selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void add(SanPhamChiTiet o) {
        JdbcHelper.executeUpdate(INSERT, o.getMaSPCT(), o.getIdSanPham(),
                o.getIdMauSac(), o.getIdKichThuoc(), o.getSoLuong(),
                o.getGia(), o.getMaQR(), o.getAnh());
    }

    @Override
    public void update(SanPhamChiTiet o, int id) {
        JdbcHelper.executeUpdate(UPDATE, o.getMaSPCT(), o.getIdSanPham(),
                o.getIdMauSac(), o.getIdKichThuoc(), o.getSoLuong(),
                o.getGia(), o.getMaQR(), o.getAnh(), id);
    }

    public List<SanPhamChiTiet> getPage(int pageNumber, int pageSize) {
        return selectBySQL(GET_PAGEV2, pageNumber, pageSize);
    }
}
