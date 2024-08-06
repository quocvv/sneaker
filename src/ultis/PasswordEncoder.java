package ultis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import services.AuthService;

public class PasswordEncoder extends DBContext {

    public static String encode(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(System.err);
            return null;
        }
    }

    public static boolean checkPassword(String username, String password) {
        AuthService authService = new AuthService();
        // Lấy mật khẩu đã lưu trữ từ cơ sở dữ liệu
        String storedPassword = authService.getPasswordFromDatabase(username);

        // Nếu không tìm thấy mật khẩu đã lưu trữ, trả về false
        if (storedPassword == null) {
            return false;
        }

        // Mã hóa mật khẩu đã nhập để so sánh với mật khẩu đã lưu trữ
        String hashedPassword = hashPassword(password);

        // So sánh mật khẩu đã nhập với mật khẩu đã lưu trữ
        return hashedPassword.equals(storedPassword);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(System.err);
            return null;
        }
    }
}
