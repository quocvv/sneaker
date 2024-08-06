package entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class HoaDon {

    Integer idHoaDon;
    Integer idTaiKhoan;
    Integer idKhachHang;
    Integer idPhuongThuc;
    String trangThai;
    String ngayMua;
    Integer khachTienMat = 0;
    Integer khachChuyenKhoan = 0;
    Integer tienSP;
    Integer tienGiam = 0;
    Integer tongTien;

}
