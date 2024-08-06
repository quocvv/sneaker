package dto;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhanVienDTO {

    String maNhanVien;
    String hoTen;
    String sdt;
    String tenTk;
    String email;
    String diaChi;
    Boolean gioiTinh;
    Boolean vaiTro;
    String ngaySinh;
    Boolean trangThai;
}
