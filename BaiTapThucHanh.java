import java.util.Scanner;

public class BaiTapThucHanh {

    public static boolean kiemTraMaSach(String maSach) {
        if (maSach.length() != 12) {
            return false;
        }

        if (!maSach.substring(0, 4).equals("LIB-")) {
            return false;
        }

        String phanSo = maSach.substring(4, 8);
        for (int i = 0; i < phanSo.length(); i++) {
            if (!Character.isDigit(phanSo.charAt(i))) {
                return false;
            }
        }

        if (!maSach.substring(8, 10).equals("-S")) {
            return false;
        }

        return true;
    }

    public static boolean kiemTraISBN(String isbn) {
        if (isbn.length() != 10) {
            return false;
        }

        for (int i = 0; i < isbn.length(); i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean kiemTraNamXuatBan(String nam) {
        if (nam.length() != 4) {
            return false;
        }

        for (int i = 0; i < nam.length(); i++) {
            if (!Character.isDigit(nam.charAt(i))) {
                return false;
            }
        }

        int namSo = Integer.parseInt(nam);
        if (namSo > 2026) {
            return false;
        }

        return true;
    }

    public static String chuanHoaTieuDe(String tieuDe) {
        tieuDe = tieuDe.trim();

        StringBuilder chuoiSach = new StringBuilder();
        boolean lastWasSpace = false;

        for (int i = 0; i < tieuDe.length(); i++) {
            char c = tieuDe.charAt(i);

            if (c == ' ') {
                if (!lastWasSpace) {
                    chuoiSach.append(c);
                    lastWasSpace = true;
                }
            } else {
                chuoiSach.append(c);
                lastWasSpace = false;
            }
        }

        String[] words = chuoiSach.toString().split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                String capitalized = words[i].substring(0, 1).toUpperCase()
                        + words[i].substring(1).toLowerCase();
                result.append(capitalized);

                if (i < words.length - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    public static String taoThongTinDayDu(String maSach, String tenSach, String tacGia, String namXB) {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(maSach);
        sb.append("] - [");
        sb.append(tenSach);
        sb.append("] - [");
        sb.append(tacGia);
        sb.append("] (");
        sb.append(namXB);
        sb.append(")");

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== HỆ THỐNG QUẢN LÝ THƯ VIỆN ==========\n");

        System.out.println("PHẦN 1: KIỂM TRA ĐỊNH DẠNG");
        System.out.println("-------------------------------------------");

        System.out.print("Nhập mã sách (LIB-xxxx-S): ");
        String maSach = scanner.nextLine();

        if (kiemTraMaSach(maSach)) {
            System.out.println("✓ Mã sách hợp lệ");
        } else {
            System.out.println("✗ Mã sách không hợp lệ! (Định dạng: LIB-xxxx-S, VD: LIB-1234-S)");
        }

        System.out.print("\nNhập mã ISBN (10 chữ số): ");
        String isbn = scanner.nextLine();

        if (kiemTraISBN(isbn)) {
            System.out.println("✓ Mã ISBN hợp lệ");
        } else {
            System.out.println("✗ Mã ISBN không hợp lệ! (Phải có đúng 10 chữ số)");
        }

        System.out.print("\nNhập năm xuất bản: ");
        String namXB = scanner.nextLine();

        if (kiemTraNamXuatBan(namXB)) {
            System.out.println("✓ Năm xuất bản hợp lệ");
        } else {
            System.out.println("✗ Năm xuất bản không hợp lệ! (Phải là 4 chữ số và ≤ 2026)");
        }

        System.out.println("\n\nPHẦN 2: CHUẨN HÓA TIÊU ĐỀ VÀ TÁC GIẢ");
        System.out.println("-------------------------------------------");

        System.out.print("Nhập tên sách: ");
        String tenSach = scanner.nextLine();
        String tenSachChuan = chuanHoaTieuDe(tenSach);
        System.out.println("Tên sách sau chuẩn hóa: " + tenSachChuan);

        System.out.print("\nNhập tên tác giả: ");
        String tacGia = scanner.nextLine();
        String tacGiaChuan = chuanHoaTieuDe(tacGia);
        System.out.println("Tên tác giả sau chuẩn hóa: " + tacGiaChuan);

        System.out.println("\n\nPHẦN 3: TẠO CHUỖI TRÍCH DẪN");
        System.out.println("-------------------------------------------");

        String thongTinDayDu = taoThongTinDayDu(maSach, tenSachChuan, tacGiaChuan, namXB);
        System.out.println("Thông tin đầy đủ:");
        System.out.println(thongTinDayDu);

        System.out.println("\n\n========== GIẢI THÍCH VỀ STRINGBUILDER ==========");
        System.out.println("Tại sao dùng StringBuilder thay vì toán tử +?");
        System.out.println();
        System.out.println("1. VỀ BỘ NHỚ:");
        System.out.println("   - String (+): Mỗi lần nối tạo ra 1 object String MỚI");
        System.out.println("   - VD: s = s + 'a' + 'b' + 'c'");
        System.out.println("        → Tạo 3 object: 'a', 'ab', 'abc'");
        System.out.println("   - StringBuilder: Chỉ dùng 1 object duy nhất");
        System.out.println("        → Thêm nội dung vào cùng 1 object");
        System.out.println();
        System.out.println("2. VỀ TỐC ĐỘ:");
        System.out.println("   - Với 1000 lần nối:");
        System.out.println("     + String (+):     ~500ms (chậm)");
        System.out.println("     + StringBuilder:  ~5ms (nhanh gấp 100 lần)");
        System.out.println();
        System.out.println("3. NGUYÊN TẮC:");
        System.out.println("   - Dùng String (+): Khi nối < 5 lần");
        System.out.println("   - Dùng StringBuilder: Khi nối nhiều lần hoặc trong vòng lặp");

        scanner.close();
    }
}
