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

public class SanPhamChiTiet {

    Integer IDSPCT;
    String maSPCT;
    Integer idSanPham;
    Integer idMauSac;
    Integer idKichThuoc;
    Integer soLuong;
    Integer gia;
    String maQR;
    String anh;
}
