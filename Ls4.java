import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Ls4 {
    
    public static String kiemTraMaTheChiTiet(String maThe) {
        if (maThe.length() != 11) {
            return "✗ Độ dài không hợp lệ! Mã thẻ phải có đúng 11 ký tự (2 chữ cái + 4 chữ số + 5 chữ số)";
        }
        
        String regex2ChuCai = "^[A-Z]{2}.*";
        if (!Pattern.matches(regex2ChuCai, maThe)) {
            return "✗ Thiếu tiền tố! Mã thẻ phải bắt đầu bằng 2 chữ cái viết HOA (VD: TV, LB, DH)";
        }
        
        String regex4So = "^[A-Z]{2}\\d{4}.*";
        if (!Pattern.matches(regex4So, maThe)) {
            return "✗ Năm không hợp lệ! Sau tiền tố phải là 4 chữ số đại diện cho năm (VD: 2023, 2024)";
        }
        
        String regex5SoCuoi = "^[A-Z]{2}\\d{4}\\d{5}$";
        if (!Pattern.matches(regex5SoCuoi, maThe)) {
            return "✗ Mã số không hợp lệ! 5 ký tự cuối phải là số (VD: 12345, 00001)";
        }
        
        return "✓ Mã thẻ hợp lệ!";
    }
    
    public static boolean kiemTraMaTheDayDu(String maThe) {
        String regex = "^[A-Z]{2}\\d{4}\\d{5}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(maThe);
        return matcher.matches();
    }
    
    public static void hienThiThongTinMaThe(String maThe) {
        if (maThe.length() == 11) {
            String tienTo = maThe.substring(0, 2);
            String nam = maThe.substring(2, 6);
            String maSo = maThe.substring(6, 11);
            
            System.out.println("\n--- Phân tích mã thẻ ---");
            System.out.println("Tiền tố: " + tienTo);
            System.out.println("Năm nhập học: " + nam);
            System.out.println("Mã số sinh viên: " + maSo);
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========== HỆ THỐNG KIỂM TRA MÃ THẺ THƯ VIỆN ==========\n");
        
        System.out.println("Quy tắc mã thẻ:");
        System.out.println("1. 2 chữ cái viết HOA (VD: TV, LB, DH)");
        System.out.println("2. 4 chữ số năm nhập học (VD: 2023, 2024)");
        System.out.println("3. 5 chữ số mã sinh viên (VD: 12345, 00001)");
        System.out.println("Ví dụ: TV202312345\n");
        
        System.out.print("Nhập số lượng mã thẻ cần kiểm tra: ");
        int soLuong = Integer.parseInt(scanner.nextLine());
        System.out.println();
        
        int dem = 0;
        int demHopLe = 0;
        int demKhongHopLe = 0;
        
        while (dem < soLuong) {
            System.out.print("Nhập mã thẻ #" + (dem + 1) + ": ");
            String maThe = scanner.nextLine();
            
            String ketQua = kiemTraMaTheChiTiet(maThe);
            System.out.println(ketQua);
            
            if (kiemTraMaTheDayDu(maThe)) {
                hienThiThongTinMaThe(maThe);
                demHopLe++;
            } else {
                demKhongHopLe++;
            }
            
            System.out.println();
            dem++;
        }
        
        System.out.println("========== THỐNG KÊ ==========");
        System.out.println("Tổng số mã thẻ đã kiểm tra: " + soLuong);
        System.out.println("Số mã hợp lệ: " + demHopLe);
        System.out.println("Số mã không hợp lệ: " + demKhongHopLe);
        
        double tyLeHopLe = (demHopLe * 100.0) / soLuong;
        System.out.println("Tỷ lệ hợp lệ: " + String.format("%.1f", tyLeHopLe) + "%");
        
        scanner.close();
    }
}
