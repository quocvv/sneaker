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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongTinCaNhan {

    Integer id;
    String maNhanVien;
    String hoTen;
    String ngaySinh;
    Boolean gioiTinh;
    String sdt;
    String email;
    String diaChi;

}
