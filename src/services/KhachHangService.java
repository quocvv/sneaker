package services;

import entity.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ultis.JdbcHelper;

public class KhachHangService implements MethodService1<KhachHang> {

    String GET_ALL = "SELECT * FROM KhachHang";
    String GET_BY_ID = "SELECT * FROM KhachHang WHERE IDKhachHang = ?";
    String GET_BY_SDT = "SELECT * FROM KhachHang WHERE SDT = ?";
    String INSERT = "Insert KhachHang(Ten, SDT, email, NgaySinh, TichDiem) VALUES (?,?,?,?,?)";
    String UPDATE = "Update KhachHang Set Ten = ?, SDT = ?, email = ?, NgaySinh = ?, TichDiem = ? Where IDKhachHang = ?";

    @Override
    public List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                KhachHang khachHang = KhachHang.builder()
                        .maKH(rs.getInt(1))
                        .hoTen(rs.getString(2))
                        .sdt(rs.getString(3))
                        .email(rs.getString(4))
                        .ngaySinh(rs.getString(5))
                        .tichDiem(rs.getInt(6))
                        .build();
                list.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    @Override
    public List<KhachHang> getAll() {
        return selectBySQL(GET_ALL);
    }

    @Override
    public KhachHang getByID(int id) {
        List<KhachHang> list = this.selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public KhachHang getBySDT(String sdt) {
        List<KhachHang> list = this.selectBySQL(GET_BY_SDT, sdt);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void add(KhachHang o) {
        JdbcHelper.executeUpdate(INSERT, o.getHoTen(), o.getSdt(), o.getEmail(), o.getNgaySinh(), o.getTichDiem());
    }

    @Override
    public void update(KhachHang o, int id) {
        JdbcHelper.executeUpdate(UPDATE, o.getHoTen(), o.getSdt(), o.getEmail(), o.getNgaySinh(), o.getTichDiem(), id);
    }

}
