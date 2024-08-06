package services;

import dto.TaiKhoanDTOResponse;

import ultis.DBContext;
import java.sql.*;

public class AuthService extends DBContext {

    public TaiKhoanDTOResponse login(String username) {

        TaiKhoanDTOResponse dTOResponse = null;
        try {
            String sql = """
                            select t.IDTaiKhoan,  tt.MaNV, tt.Ten, t.VaiTro, 
                                    t.TrangThai, t.ten from TaiKhoan t join ThongTinCN tt
                                on t.IDTTCN = tt.IDTTCN
                                where t.Ten = ?  and t.TrangThai = 0""";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                dTOResponse = TaiKhoanDTOResponse.builder()
                        .id(rs.getInt(1))
                        .maNv(rs.getString(2))
                        .hoten(rs.getString(3))
                        .vaiTro(rs.getBoolean(4))
                        .trangThai(rs.getBoolean(5))
                        .tenTk(rs.getString(6))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return dTOResponse;
    }

    public String getPasswordFromDatabase(String username) {

        String password = null;
        try {
            String sql = "select matkhau from taikhoan where ten = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                password = rs.getString("matkhau");
            }
            System.out.println(password);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return password;
    }

}
