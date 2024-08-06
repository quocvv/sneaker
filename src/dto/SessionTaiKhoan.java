package dto;

public class SessionTaiKhoan {

    private static TaiKhoanDTOResponse taiKhoanDTOResponse;

    public static TaiKhoanDTOResponse getTaiKhoanDTOResponse() {
        return taiKhoanDTOResponse;
    }

    public static void setTaiKhoanDTOResponse(TaiKhoanDTOResponse taiKhoanDTOResponse) {
        SessionTaiKhoan.taiKhoanDTOResponse = taiKhoanDTOResponse;
    }

    public boolean isLoggedIn() {
        return taiKhoanDTOResponse != null;
    }

    public void logout() {
        taiKhoanDTOResponse = null;
    }
}
