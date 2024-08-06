package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.ThuocTinh;
import ultis.JdbcHelper;

public class ThuocTinhService {

    String GET_ALL = "{CALL dbo.GetAllAttribute(?)}";
    String GET_BY_ID = "{CALL dbo.GetOneAttribute(?,?)}";
    String GET_BY_NAME = "{CALL dbo.GetIDByTen(?,?)}";
    String INSERT = "{CALL dbo.AddAttribute(?,?,?,?)}";
    String UPDATE = "{CALL dbo.UpdateAttribute(?,?,?,?,?)}";

    public List<ThuocTinh> selectBySQL(String sql, Object... args) {
        List<ThuocTinh> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                ThuocTinh chatLieu = ThuocTinh.builder()
                        .idThuocTinh(rs.getInt("ID"))
                        .maThuocTinh(rs.getString("Ma"))
                        .tenThuocTinh(rs.getString("Ten"))
                        .trangThai(rs.getBoolean("TrangThai"))
                        .build();
                list.add(chatLieu);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    public List<ThuocTinh> getAll(String name) {
        return selectBySQL(GET_ALL, name);
    }

    public ThuocTinh getByID(String name, int id) {
        List<ThuocTinh> list = selectBySQL(GET_BY_ID, name, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void add(String name, ThuocTinh o) {
        JdbcHelper.executeUpdate(INSERT, name, o.getMaThuocTinh(), o.getTenThuocTinh(), o.getTrangThai());
    }

    public void update(String name, Integer id, ThuocTinh o) {
        JdbcHelper.executeUpdate(UPDATE, name, id, o.getMaThuocTinh(), o.getTenThuocTinh(), o.getTrangThai());
    }

    public ThuocTinh getByName(String name, String value) {
        List<ThuocTinh> list = selectBySQL(GET_BY_NAME, name, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
