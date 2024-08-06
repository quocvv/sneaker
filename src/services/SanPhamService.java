package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.SanPham;
import ultis.JdbcHelper;

public class SanPhamService implements MethodService1<SanPham> {

    String GET_ALL = "Select * From SanPham";
    String GET_BY_ID = "Select * From SanPham Where ID = ?";
    String GET_BY_MASP = "Select * From SanPham Where MaSP = ?";
    String GET_BY_LIKE_MASP = "Select * From SanPham Where MaSP LIKE ?";
    String INSERT = "Insert SanPham(MaSP, TenSP, TinhTrang, IDThuongHieu, IDChatLieu, IDGioiTinh, IDDeGiay, IDLot) VALUES (?,?,?,?,?,?,?,?)";
    String UPDATE = "Update SanPham Set MaSP = ?, TenSP = ?, TinhTrang = ?, IDThuongHieu = ?, IDChatLieu = ?, IDGioiTinh = ?, IDDeGiay = ?, IDLot = ? Where ID = ?";
    String GET_PAGE = "SELECT * FROM SanPham ORDER BY ID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    @Override
    public List<SanPham> selectBySQL(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                SanPham sp = SanPham.builder()
                        .idSP(rs.getInt("ID"))
                        .maSp(rs.getString("MaSP"))
                        .tenSP(rs.getString("TenSP"))
                        .trangThai(rs.getBoolean("TinhTrang"))
                        .idThuongHieu(rs.getInt("IDThuongHieu"))
                        .idChatLieu(rs.getInt("IDChatLieu"))
                        .idGioiTinh(rs.getInt("IDGioiTinh"))
                        .idDeGiay(rs.getInt("IDDeGiay"))
                        .idLot(rs.getInt("IDLot"))
                        .build();
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    @Override
    public List<SanPham> getAll() {
        return selectBySQL(GET_ALL);
    }

    public List<SanPham> getPage(int pageNumber, int pageSize) {
        return selectBySQL(GET_PAGE, pageNumber, pageSize);
    }

    @Override
    public SanPham getByID(int id) {
        List<SanPham> list = this.selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public SanPham getByMaSP(String ma) {
        List<SanPham> list = this.selectBySQL(GET_BY_MASP, ma);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<SanPham> getByLikeMaSP(String ma) {
        return selectBySQL(GET_BY_LIKE_MASP, '%' + ma + '%');
    }

    @Override
    public void add(SanPham o) {
        JdbcHelper.executeUpdate(INSERT, o.getMaSp(), o.getTenSP(),
                o.getTrangThai(), o.getIdThuongHieu(),
                o.getIdChatLieu(), o.getIdGioiTinh(), o.getIdDeGiay(),
                o.getIdLot());
    }

    @Override
    public void update(SanPham o, int id) {
        JdbcHelper.executeUpdate(UPDATE, o.getMaSp(), o.getTenSP(),
                o.getTrangThai(), o.getIdThuongHieu(),
                o.getIdChatLieu(), o.getIdGioiTinh(), o.getIdDeGiay(),
                o.getIdLot(), id);
    }

}
