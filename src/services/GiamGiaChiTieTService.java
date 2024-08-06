package services;

import entity.GiamGiaChiTiet;
import entity.SanPhamChiTiet;
import java.util.List;
import ultis.DBContext;
import java.sql.*;
import java.util.ArrayList;

public class GiamGiaChiTieTService extends DBContext implements MethodService<GiamGiaChiTiet> {

    @Override
    public List<GiamGiaChiTiet> getAll() {
        List<GiamGiaChiTiet> list = new ArrayList<>();
        try {
            String sql = "select * from GiamGiaChiTiet ";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                GiamGiaChiTiet giamGiaChiTiet = new GiamGiaChiTiet();
                giamGiaChiTiet.setIdGiamGiaCT(rs.getInt(1));
                giamGiaChiTiet.setIdGiamGia(rs.getInt(2));
                giamGiaChiTiet.setIdSp(rs.getInt(3));
                giamGiaChiTiet.setDonVi(rs.getString(4));
                giamGiaChiTiet.setSoTien(rs.getInt(5));
                list.add(giamGiaChiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    @Override
    public boolean createObject(GiamGiaChiTiet t) {
        try {
            String sql = "INSERT INTO GiamGiaChiTiet (idGiamGia, idspct, donVi, soTien) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, t.getIdGiamGia());
            st.setInt(2, t.getIdSp());
            st.setString(3, t.getDonVi());
            st.setInt(4, t.getSoTien());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return false;
    }

    @Override
    public boolean updateObject(GiamGiaChiTiet t, int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public GiamGiaChiTiet getObject(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateKhuyenmai(Integer giaTri, int idsp) {
        try {
            String sql = "update sanphamchitiet set gia = gia - ? where idspct = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, giaTri);
            st.setInt(2, idsp);
            int i = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public void updateKhuyenmaiEnd(Integer giaTri, int idsp) {
        try {
            String sql = "update sanphamchitiet set gia = gia + ? where idspct = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, giaTri);
            st.setInt(2, idsp);
            int i = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public List<GiamGiaChiTiet> getAllByIdGiamGia(int id) {
        List<GiamGiaChiTiet> list = new ArrayList<>();
        try {
            String sql = "select * from GiamGiaChiTiet where idgiamgia = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                GiamGiaChiTiet giamGiaChiTiet = new GiamGiaChiTiet();
                giamGiaChiTiet.setIdGiamGiaCT(rs.getInt(1));
                giamGiaChiTiet.setIdGiamGia(rs.getInt(2));
                giamGiaChiTiet.setIdSp(rs.getInt(3));
                giamGiaChiTiet.setDonVi(rs.getString(4));
                giamGiaChiTiet.setSoTien(rs.getInt(5));
                list.add(giamGiaChiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    public GiamGiaChiTiet getByIdSPCT(int idspct) {
        try {
            String sql = """
                         Select GGCT.IDGiamGiaChiTiet, GGCT.IDGiamGia, GGCT.IDSPCT, GGCT.DonVi, GGCT.SoTien 
                         FROM GiamGia JOIN dbo.GiamGiaChiTiet GGCT on GiamGia.IDGiamGia = GGCT.IDGiamGia
                         Where TrangThai = N'Đang Diễn Ra' AND GGCT.IDSPCT = ? """;
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idspct);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                GiamGiaChiTiet giamGiaChiTiet = new GiamGiaChiTiet();
                giamGiaChiTiet.setIdGiamGiaCT(rs.getInt(1));
                giamGiaChiTiet.setIdGiamGia(rs.getInt(2));
                giamGiaChiTiet.setIdSp(rs.getInt(3));
                giamGiaChiTiet.setDonVi(rs.getString(4));
                giamGiaChiTiet.setSoTien(rs.getInt(5));
                return giamGiaChiTiet;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    public List<SanPhamChiTiet> getByIdGiamGia(int id) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            String sql = """
                         select SanPhamChiTiet.* from duAn1_v2.dbo.SanPhamChiTiet 
                             join GiamGiaChiTiet GGCT on SanPhamChiTiet.IDSanPham = GGCT.IDSanPham
                             join dbo.GiamGia GG on GG.IDGiamGia = GGCT.IDGiamGia
                             where GG.IDGiamGia = ? """;
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
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

}
