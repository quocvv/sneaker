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

public class HoaDonChiTiet {

    Integer id;
    Integer idHoaDon;
    Integer idSPCT;
    Integer idGiamGia = null;
    Integer idHDCT = null;
    Integer soLuong;
}
