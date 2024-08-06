package ultis;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataExcel {

    public static void readData(File file, String sql) {
        try {
            DBContext dbContext = new DBContext();
            Connection connection = dbContext.connection;

            // Tạo 1 worbook
            Workbook workbook = WorkbookFactory.create(file);

            // Lấy sheet 1
            Sheet sheet = workbook.getSheetAt(0);
            
            // Lấy số dòng và số cột
            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Đọc dữ liệu từ Excel và lưu vào cơ sở dữ liệu
            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);

                for (int j = 0; j < cols; j++) {
                    Cell cell = row.getCell(j);

                    // Điều chỉnh kiểu dữ liệu tùy thuộc vào cột trong cơ sở dữ liệu
                    if (cell.getCellType() == CellType.NUMERIC) {
                        preparedStatement.setDouble(j + 1, cell.getNumericCellValue());
                    } else if (cell.getCellType() == CellType.STRING) {
                        preparedStatement.setString(j + 1, cell.getStringCellValue());
                    }
                }
                // Thực hiện truy vấn
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
            connection.close();
            workbook.close();
        } catch (IOException | SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
