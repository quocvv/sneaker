package services;

import ultis.DBContext;
import java.sql.*;

public class ThongKeService extends DBContext {

    public int tongDonHang() {
        try {
            String sql = "SELECT count(*) From HoaDon";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return 0;
    }

    public int tongDTTG(Date startDate, Date endDate) {
        try {
            String sql = """
                     SELECT SUM(TongTien) AS DoanhThu
                     FROM HoaDon
                     WHERE NgayMua BETWEEN ? AND ?
                     GROUP BY YEAR(NgayMua), MONTH(NgayMua)
                     """;
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, startDate);
            st.setDate(2, endDate);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("DoanhThu");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return 0;
    }

}
