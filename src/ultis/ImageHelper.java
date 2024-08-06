package ultis;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageHelper {

//    public static Image logoApp() {
//        File file = new File("src/image", "fpt.png");
//        Image image = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());
//        return image;
//    }
    public static void saveImg(File file) {
        File dir = new File("/image", file.getName());
        // Tạo thư mục nếu chưa tồn tại 
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            // Copy vào thư mục image (đè nếu đã tồn tại) 
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(dir.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageIcon readImg(String fileName) {
        if (fileName == null) {
            return null;
        }
        File path = new File("/image", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(170, 80, Image.SCALE_DEFAULT));
    }

    public static void saveImageQr(BufferedImage image, String folderPath, String fileName) {
        try {
            // Kiểm tra xem thư mục tồn tại chưa, nếu chưa thì tạo mới
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            // Tạo đường dẫn đầy đủ cho tệp hình ảnh
            String filePath = folderPath + File.separator + fileName;
            // Lưu hình ảnh vào tệp
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }
        return image;
    }

    public static BufferedImage genQR(String code) {
        if (!code.isEmpty()) {
            try {
                BitMatrix matrix = new MultiFormatWriter().encode(code, BarcodeFormat.QR_CODE, 300, 300);
                BufferedImage image = toBufferedImage(matrix);
                return image;
            } catch (WriterException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

}
