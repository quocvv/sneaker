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
@Builder
@ToString
public class GiamGiaChiTiet {

    private int idGiamGiaCT;
    private int idGiamGia;
    private int idSp;
    private String donVi;
    private Integer soTien;
}
