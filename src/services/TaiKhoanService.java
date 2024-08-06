package services;

import ultis.DBContext;
import java.util.List;
import entity.TaiKhoan;
import entity.ThongTinCaNhan;
import java.sql.*;
import java.util.ArrayList;

public class TaiKhoanService extends DBContext implements MethodService<TaiKhoan> {

    @Override
    public List<TaiKhoan> getAll() {
        List<TaiKhoan> taiKhoanList = new ArrayList<>();
        try {
            String query = "SELECT * FROM TaiKhoan";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                TaiKhoan taiKhoan = new TaiKhoan(); // Khởi tạo một đối tượng tài khoản mới.
                taiKhoan.setIdTaiKhoan(resultSet.getInt("idTaiKhoan"));
                taiKhoan.setTen(resultSet.getString("ten"));
                taiKhoan.setPassword(resultSet.getString("password"));
                taiKhoan.setVaiTro(resultSet.getBoolean("vaiTro"));
                taiKhoan.setTrangThai(resultSet.getBoolean("trangThai"));
                // Lấy thông tin cá nhân của tài khoản từ cơ sở dữ liệu.
                int idThongTinCaNhan = resultSet.getInt("idThongTinCaNhan");
                ThongTinCaNhan thongTinCaNhan = getThongTinCaNhanById(idThongTinCaNhan);
                taiKhoan.setThongTinCaNhan(thongTinCaNhan);
                taiKhoanList.add(taiKhoan); // Thêm tài khoản vào danh sách.
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return taiKhoanList;
    }

    @Override
    public boolean createObject(TaiKhoan taiKhoan) {
        try {
            String query = "INSERT TaiKhoan (ten, matkhau, vaiTro, trangThai, IDTTCN) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, taiKhoan.getTen());
            preparedStatement.setString(2, taiKhoan.getPassword());
            preparedStatement.setBoolean(3, taiKhoan.getVaiTro());
            preparedStatement.setBoolean(4, taiKhoan.getTrangThai());
            preparedStatement.setInt(5, taiKhoan.getThongTinCaNhan().getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return false;
    }

    @Override
    public boolean updateObject(TaiKhoan t, int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TaiKhoan getObject(int id) {
        TaiKhoan taiKhoan = null;
        try {
            String query = "SELECT * FROM TaiKhoan WHERE idTaiKhoan = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                taiKhoan = new TaiKhoan();
                taiKhoan.setIdTaiKhoan(resultSet.getInt("idTaiKhoan"));
                taiKhoan.setTen(resultSet.getString("ten"));
                taiKhoan.setVaiTro(resultSet.getBoolean("vaiTro"));
                taiKhoan.setTrangThai(resultSet.getBoolean("trangThai"));
                int idThongTinCaNhan = resultSet.getInt("idttcn");
                ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhanService().getThongTinCaNhanByMa(String.valueOf(id));
                System.out.println(thongTinCaNhan);
                taiKhoan.setThongTinCaNhan(thongTinCaNhan);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return taiKhoan;
    }

    private ThongTinCaNhan getThongTinCaNhanById(int idThongTinCaNhan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean updateObjectByIDTTCN(TaiKhoan t, String IDTTCN) {
        try {
            String query = "UPDATE TaiKhoan SET ten = ?, vaiTro = ?, trangThai = ? WHERE IDTTCN = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, t.getTen());
            preparedStatement.setBoolean(2, t.getVaiTro());
            preparedStatement.setBoolean(3, t.getTrangThai());
            preparedStatement.setString(4, IDTTCN);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return false;
    }

    public void updateMatKhauByUsername(String username, String newPassword) {
        try {
            String sql = "update  taikhoan set matkhau = ? where ten = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPassword);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

}
