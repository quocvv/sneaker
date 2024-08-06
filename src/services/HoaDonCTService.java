package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.HoaDonChiTiet;
import ultis.JdbcHelper;

public class HoaDonCTService implements MethodService1<HoaDonChiTiet> {

    String GET_ALL = "Select * From HoaDonChiTiet";
    String GET_BY_ID = "Select * From HoaDonChiTiet Where ID = ?";
    String GET_BY_IDHD = "Select * From HoaDonChiTiet Where IDHoaDon = ?";
    String GET_BY_IDSPCT = "Select * From HoaDonChiTiet Where IDHoaDon = ? AND IDSPCT = ?";
    String INSERT = "Insert HoaDonChiTiet(IDHoaDon, IDSPCT, IDGiamGia, IDHDCT, SoLuong)VALUES (?,?,?,?,?)";
    String UPDATE = "Update HoaDonChiTiet Set IDHoaDon = ?, IDSPCT = ?, IDGiamGia = ?, IDHDCT = ?, SoLuong  = ? Where ID = ?";
    String DELETE = "Delete HoaDonChiTiet Where ID = ?";
    String HUY = "Delete HoaDonChiTiet Where IDHoaDon = ?";

    @Override
    public List<HoaDonChiTiet> selectBySQL(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDonChiTiet hdct = HoaDonChiTiet.builder()
                        .id(rs.getInt(1))
                        .idHoaDon(rs.getInt(2))
                        .idSPCT(rs.getInt(3))
                        .idGiamGia(rs.getInt(4))
                        .idHDCT(rs.getInt(5))
                        .soLuong(rs.getInt(6))
                        .build();
                list.add(hdct);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    @Override
    public List<HoaDonChiTiet> getAll() {
        return selectBySQL(GET_ALL);
    }

    public List<HoaDonChiTiet> getByIDHD(int value) {
        return selectBySQL(GET_BY_IDHD, value);
    }

    @Override
    public HoaDonChiTiet getByID(int id) {
        List<HoaDonChiTiet> list = this.selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public HoaDonChiTiet getByIDSPCT(int idhd, int idspct) {
        List<HoaDonChiTiet> list = this.selectBySQL(GET_BY_IDSPCT, idhd, idspct);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void add(HoaDonChiTiet o) {
        JdbcHelper.executeUpdate(INSERT, o.getIdHoaDon(),
                o.getIdSPCT(), o.getIdGiamGia(),
                o.getIdHDCT(), o.getSoLuong());
    }

    @Override
    public void update(HoaDonChiTiet o, int id) {
        JdbcHelper.executeUpdate(UPDATE, o.getIdHoaDon(),
                o.getIdSPCT(), o.getIdGiamGia(),
                o.getIdHDCT(), o.getSoLuong(), id);
    }

    public void remove(int id) {
        JdbcHelper.executeUpdate(DELETE, id);
    }

    public void huy(int id) {
        JdbcHelper.executeUpdate(HUY, id);
    }

}
