package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class KhuyenMaiCT {

    private Integer id;
    private Integer idGiamGia;
    private Integer idSanPham;
    private String donVi;
    private Integer soTien;
}
