package services;

import entity.DiaChi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ultis.JdbcHelper;

public class DiaChiService implements MethodService1<DiaChi> {

    String GET_ALL = "SELECT * FROM DiaChi";
    String GET_BY_ID = "SELECT * FROM DiaChi WHERE IDDiaChi = ?";
    String INSERT = "INSERT INTO DiaChi(IDKhachHang,Ten) Values(?,?)";
    String UPDATE = "Update DiaChi Set IDKhachHang = ?, Ten = ? Where IDDiaChi = ?";
    String GET_BY_IDKH = "SELECT * FROM DiaChi WHERE IDKhachHang = ?";

    @Override
    public List<DiaChi> selectBySQL(String sql, Object... args) {
        List<DiaChi> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                DiaChi khachHang = DiaChi.builder()
                        .idDiaChi(rs.getInt(1))
                        .idKhachHang(rs.getInt(2))
                        .diaChi(rs.getString(3))
                        .build();
                list.add(khachHang);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public List<DiaChi> getAll() {
        return selectBySQL(GET_ALL);
    }

    @Override
    public DiaChi getByID(int id) {
        List<DiaChi> list = this.selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void add(DiaChi o) {
        JdbcHelper.executeUpdate(INSERT, o.getIdKhachHang(), o.getDiaChi());
    }

    @Override
    public void update(DiaChi o, int id) {
        JdbcHelper.executeUpdate(UPDATE, o.getIdKhachHang(), o.getDiaChi(), id);
    }

    public List<DiaChi> getAllByIDKhachHang(int value) {
        return selectBySQL(GET_BY_IDKH, value);
    }

}
