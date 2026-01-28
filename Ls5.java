import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TrichXuatLogRegex {
    
    public static class LogEntry {
        String ngayThang;
        String nguoiDung;
        String hanhDong;
        String maSach;
        
        public LogEntry(String ngayThang, String nguoiDung, String hanhDong, String maSach) {
            this.ngayThang = ngayThang;
            this.nguoiDung = nguoiDung;
            this.hanhDong = hanhDong;
            this.maSach = maSach;
        }
        
        public void hienThi() {
            System.out.println("  Ngày: " + ngayThang);
            System.out.println("  Người dùng: " + nguoiDung);
            System.out.println("  Hành động: " + hanhDong);
            System.out.println("  Mã sách: " + maSach);
        }
    }
    
    public static LogEntry phanTichLogRegex(String log) {
        String regex = "(\\d{4}-\\d{2}-\\d{2})\\s*\\|\\s*User:\\s*([^|]+)\\s*\\|\\s*Action:\\s*([^|]+)\\s*\\|\\s*BookID:\\s*(.+)";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(log);
        
        if (matcher.find()) {
            String ngayThang = matcher.group(1).trim();
            String nguoiDung = matcher.group(2).trim();
            String hanhDong = matcher.group(3).trim();
            String maSach = matcher.group(4).trim();
            
            return new LogEntry(ngayThang, nguoiDung, hanhDong, maSach);
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========== HỆ THỐNG PHÂN TÍCH LOG THƯ VIỆN (REGEX) ==========\n");
        
        System.out.print("Nhập số lượng dòng log: ");
        int soLuong = Integer.parseInt(scanner.nextLine());
        
        LogEntry[] danhSachLog = new LogEntry[soLuong];
        int demBorrow = 0;
        int demReturn = 0;
        
        System.out.println("\nNhập các dòng log:");
        System.out.println("(Định dạng: YYYY-MM-DD | User: Tên | Action: BORROW/RETURN | BookID: Mã)\n");
        
        for (int i = 0; i < soLuong; i++) {
            System.out.print("Log #" + (i + 1) + ": ");
            String log = scanner.nextLine();
            
            LogEntry entry = phanTichLogRegex(log);
            
            if (entry != null) {
                danhSachLog[i] = entry;
                
                if (entry.hanhDong.equals("BORROW")) {
                    demBorrow++;
                } else if (entry.hanhDong.equals("RETURN")) {
                    demReturn++;
                }
            } else {
                System.out.println("  ✗ Log không hợp lệ!");
                i--;
            }
        }
        
        System.out.println("\n========== KẾT QUẢ PHÂN TÍCH ==========\n");
        
        for (int i = 0; i < soLuong; i++) {
            System.out.println("Log #" + (i + 1) + ":");
            danhSachLog[i].hienThi();
            System.out.println();
        }
        
        System.out.println("========== THỐNG KÊ ==========");
        System.out.println("Tổng số log: " + soLuong);
        System.out.println("Số lần BORROW (Mượn): " + demBorrow);
        System.out.println("Số lần RETURN (Trả): " + demReturn);
        
        double tyLeBorrow = (demBorrow * 100.0) / soLuong;
        double tyLeReturn = (demReturn * 100.0) / soLuong;
        
        System.out.println("\nTỷ lệ:");
        System.out.println("BORROW: " + String.format("%.1f", tyLeBorrow) + "%");
        System.out.println("RETURN: " + String.format("%.1f", tyLeReturn) + "%");
        
        scanner.close();
    }
}
