package services;

import entity.KhuyenMai;
import java.util.List;
import ultis.DBContext;
import java.sql.*;
import java.util.ArrayList;

public class QuanLysKhuyenMaiService extends DBContext implements MethodService<KhuyenMai> {

    @Override
    public List<KhuyenMai> getAll() {
        List<KhuyenMai> list = new ArrayList<>();
        try {
            String sql = "select * from GiamGia";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setId(rs.getInt("IDGiamGia"));
                km.setTen(rs.getString("Ten"));
                km.setNgayBatDau(rs.getString("ThoiGianBD"));
                km.setNgayKetThuc(rs.getString("ThoiGiaKT"));
                km.setTrangThai(rs.getString("TrangThai"));
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    public List<KhuyenMai> getAllById(int id) {
        List<KhuyenMai> list = new ArrayList<>();
        try {
            String sql = "select * from GiamGia where IDGiamGia = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setId(rs.getInt("IDGiamGia"));
                km.setTen(rs.getString("Ten"));
                km.setNgayBatDau(rs.getString("ThoiGianBD"));
                km.setNgayKetThuc(rs.getString("ThoiGiaKT"));
                km.setTrangThai(rs.getString("TrangThai"));
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return list;
    }

    @Override
    public boolean createObject(KhuyenMai t) {
        try {
            String sql = "insert into GiamGia(Ten, ThoiGianBD, ThoiGiaKT, TrangThai) values (?,?,?,?)";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getTen());
            st.setString(2, t.getNgayBatDau());
            st.setString(3, t.getNgayKetThuc());
            st.setString(4, t.getTrangThai());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return false;

    }

    @Override
    public boolean updateObject(KhuyenMai t, int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public KhuyenMai getObject(int id) {
        KhuyenMai km = new KhuyenMai();
        try {
            String sql = "select * from GiamGia where IDGIAMGIA = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                km.setId(rs.getInt("IDGiamGia"));
                km.setTen(rs.getString("Ten"));
                km.setNgayBatDau(rs.getString("ThoiGianBD"));
                km.setNgayKetThuc(rs.getString("ThoiGiaKT"));
                km.setTrangThai(rs.getString("TrangThai"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return km;

    }

    public void updateTrangThai(String trangThai, int id) {
        try {
            String sql = "update GiamGia set trangthai = ? where idgiamgia = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, trangThai);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

}
