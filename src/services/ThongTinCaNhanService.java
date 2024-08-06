package services;

import ultis.DBContext;
import java.util.ArrayList;
import java.util.List;
import entity.ThongTinCaNhan;
import java.sql.*;
import dto.NhanVienDTO;
import dto.TaiKhoanChiTietDTO;

public class ThongTinCaNhanService extends DBContext implements MethodService<ThongTinCaNhan> {

    @Override
    public List<ThongTinCaNhan> getAll() {
        List<ThongTinCaNhan> thongTinCaNhanList = new ArrayList<>();
        String sql = "SELECT * FROM ThongTinCN";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ThongTinCaNhan thongTinCaNhan = ThongTinCaNhan.builder()
                        .id(rs.getInt("idttcn"))
                        .maNhanVien(rs.getString("maNv"))
                        .ngaySinh(rs.getString("ngaySinh"))
                        .gioiTinh(rs.getBoolean("gioiTinh"))
                        .sdt(rs.getString("sdt"))
                        .email(rs.getString("email"))
                        .diaChi(rs.getString("diaChi"))
                        .build();
                thongTinCaNhanList.add(thongTinCaNhan);
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return thongTinCaNhanList;
    }

    @Override
    public boolean createObject(ThongTinCaNhan t) {
        try {
            String query = "INSERT INTO ThongTinCn(maNv, ngaySinh, gioiTinh, sdt, email, diaChi,ten) VALUES(?, ?, ?, ?, ?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, t.getMaNhanVien());
            preparedStatement.setString(2, t.getNgaySinh());
            preparedStatement.setBoolean(3, t.getGioiTinh());
            preparedStatement.setString(4, t.getSdt());
            preparedStatement.setString(5, t.getEmail());
            preparedStatement.setString(6, t.getDiaChi());
            preparedStatement.setString(7, t.getHoTen());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return false;
    }

    @Override
    public boolean updateObject(ThongTinCaNhan t, int id) {
        try {
            String query = "UPDATE ThongTinCn SET  ngaySinh = ?, gioiTinh = ?, sdt = ?, email = ?, diaChi = ?, ten = ? WHERE idttcn = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, t.getNgaySinh());
            preparedStatement.setBoolean(2, t.getGioiTinh());
            preparedStatement.setString(3, t.getSdt());
            preparedStatement.setString(4, t.getEmail());
            preparedStatement.setString(5, t.getDiaChi());
            preparedStatement.setString(6, t.getHoTen());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return false;
    }

    @Override
    public ThongTinCaNhan getObject(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ThongTinCaNhan getThongTinCaNhanByMa(String maNv) {
        ThongTinCaNhan thongTinCaNhan = null;
        try {
            String query = "SELECT * FROM ThongTinCN WHERE maNV=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, maNv);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                thongTinCaNhan = new ThongTinCaNhan();
                thongTinCaNhan.setId(resultSet.getInt("idttcn"));
                thongTinCaNhan.setHoTen(resultSet.getString("ten"));
                thongTinCaNhan.setMaNhanVien(resultSet.getString("maNv"));
                thongTinCaNhan.setNgaySinh(resultSet.getString("ngaySinh"));
                thongTinCaNhan.setGioiTinh(resultSet.getBoolean("gioiTinh"));
                thongTinCaNhan.setSdt(resultSet.getString("sdt"));
                thongTinCaNhan.setEmail(resultSet.getString("email"));
                thongTinCaNhan.setDiaChi(resultSet.getString("diaChi"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return thongTinCaNhan;
    }

    public NhanVienDTO getNhanVienByMa(String maNv) {
        NhanVienDTO nhanVienDTO = null;
        try {
            String sql = "SELECT k.MaNV, k.Ten, k.SDT, t.Ten as Tentk, k.email, k.DiaChi, k.NgaySinh, k.GioiTinh, t.VaiTro, t.trangthai "
                    + "FROM taikhoan t JOIN [ThongTinCN] k ON t.IDTTCN = k.IDTTCN "
                    + "WHERE k.MaNV = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, maNv);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                nhanVienDTO = new NhanVienDTO();
                nhanVienDTO.setMaNhanVien(rs.getString("MaNV"));
                nhanVienDTO.setHoTen(rs.getString("Ten"));
                nhanVienDTO.setSdt(rs.getString("SDT"));
                nhanVienDTO.setTenTk(rs.getString("TenTk"));
                nhanVienDTO.setEmail(rs.getString("email"));
                nhanVienDTO.setDiaChi(rs.getString("DiaChi"));
                nhanVienDTO.setGioiTinh(rs.getBoolean("GioiTinh"));
                nhanVienDTO.setVaiTro(rs.getBoolean("VaiTro"));
                nhanVienDTO.setTrangThai(rs.getBoolean("trangthai"));
                nhanVienDTO.setNgaySinh(rs.getString("ngaysinh"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return nhanVienDTO;
    }

    public List<TaiKhoanChiTietDTO> getAllTaiKhoanDTO() {
        List<TaiKhoanChiTietDTO> taiKhoanChiTietDTOs = new ArrayList<>();
        try {
            String sql = "SELECT  k.MaNV, k.Ten, k.email, k.SDT,  k.GioiTinh, k.DiaChi, t.VaiTro, t.TrangThai"
                    + " FROM taikhoan t JOIN [ThongTinCN] k ON t.IDTTCN = k.IDTTCN";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Ánh xạ dữ liệu từ ResultSet sang TaiKhoanChiTietDTO
                TaiKhoanChiTietDTO taiKhoanChiTietDTO = new TaiKhoanChiTietDTO();
                taiKhoanChiTietDTO.setMaNhanVien(resultSet.getString("MaNV"));
                taiKhoanChiTietDTO.setHoTen(resultSet.getString("Ten"));
                taiKhoanChiTietDTO.setEmail(resultSet.getString("email"));
                taiKhoanChiTietDTO.setSdt(resultSet.getString("SDT"));
                taiKhoanChiTietDTO.setDiaChi(resultSet.getString("DiaChi"));
                taiKhoanChiTietDTO.setGioiTinh(resultSet.getBoolean("GioiTinh"));
                taiKhoanChiTietDTO.setVaiTro(resultSet.getBoolean("VaiTro"));
                taiKhoanChiTietDTO.setTrangThai(resultSet.getBoolean("TrangThai"));

                taiKhoanChiTietDTOs.add(taiKhoanChiTietDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return taiKhoanChiTietDTOs;
    }

    public NhanVienDTO getNhanVienByEmail(String email) {
        NhanVienDTO nhanVienDTO = null;
        try {
            String sql = "SELECT k.MaNV, k.Ten, k.SDT, t.Ten as Tentk, k.email, k.DiaChi, k.NgaySinh, k.GioiTinh, t.VaiTro, t.trangthai "
                    + "FROM taikhoan t JOIN [ThongTinCN] k ON t.IDTTCN = k.IDTTCN "
                    + "WHERE k.email = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                nhanVienDTO = new NhanVienDTO();
                nhanVienDTO.setMaNhanVien(rs.getString("MaNV"));
                nhanVienDTO.setHoTen(rs.getString("Ten"));
                nhanVienDTO.setSdt(rs.getString("SDT"));
                nhanVienDTO.setTenTk(rs.getString("TenTk"));
                nhanVienDTO.setEmail(rs.getString("email"));
                nhanVienDTO.setDiaChi(rs.getString("DiaChi"));
                nhanVienDTO.setGioiTinh(rs.getBoolean("GioiTinh"));
                nhanVienDTO.setVaiTro(rs.getBoolean("VaiTro"));
                nhanVienDTO.setTrangThai(rs.getBoolean("trangthai"));
                nhanVienDTO.setNgaySinh(rs.getString("ngaysinh"));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return nhanVienDTO;
    }

}
