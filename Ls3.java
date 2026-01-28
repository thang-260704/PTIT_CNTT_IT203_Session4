import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ls3 {

    public static void main(String[] args) {
        String[] maSach = {"B001", "B002", "B003", "B004", "B005"};
        String[] ngayMuon = {"2024-08-01", "2024-08-02", "2024-08-03", "2024-08-04", "2024-08-05"};

        int soLanLap = 10000;

        System.out.println("========== BÁO CÁO NHẬT KÝ MƯỢN SÁCH ==========\n");

        long startStringBuilder = System.nanoTime();
        String baoCaoSB = taoBaoCaoVoiStringBuilder(maSach, ngayMuon, soLanLap);
        long endStringBuilder = System.nanoTime();
        long timeStringBuilder = endStringBuilder - startStringBuilder;

        long startString = System.nanoTime();
        String baoCaoStr = taoBaoCaoVoiString(maSach, ngayMuon, soLanLap);
        long endString = System.nanoTime();
        long timeString = endString - startString;

        System.out.println("--- Sử dụng StringBuilder ---");
        System.out.println(baoCaoSB.substring(0, Math.min(500, baoCaoSB.length())));
        System.out.println("...");
        System.out.println("Tổng số giao dịch: " + (maSach.length * soLanLap));
        System.out.println("Thời gian thực thi: " + timeStringBuilder / 1_000_000.0 + " ms");

        System.out.println("\n--- Sử dụng String (+) ---");
        System.out.println(baoCaoStr.substring(0, Math.min(500, baoCaoStr.length())));
        System.out.println("...");
        System.out.println("Tổng số giao dịch: " + (maSach.length * soLanLap));
        System.out.println("Thời gian thực thi: " + timeString / 1_000_000.0 + " ms");

        System.out.println("\n========== SO SÁNH HIỆU NĂNG ==========");
        System.out.println("StringBuilder: " + timeStringBuilder / 1_000_000.0 + " ms");
        System.out.println("String (+):    " + timeString / 1_000_000.0 + " ms");
        System.out.println("StringBuilder nhanh hơn: " + String.format("%.2f", (double) timeString / timeStringBuilder) + " lần");
    }

    public static String taoBaoCaoVoiStringBuilder(String[] maSach, String[] ngayMuon, int soLanLap) {
        StringBuilder sb = new StringBuilder();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        sb.append("NHẬT KÝ MƯỢN SÁCH\n");
        sb.append("Thời gian tạo báo cáo: ").append(now.format(formatter)).append("\n");
        sb.append("==========================================\n\n");

        for (int i = 0; i < soLanLap; i++) {
            for (int j = 0; j < maSach.length; j++) {
                sb.append("Giao dịch #").append(i * maSach.length + j + 1).append("\n");
                sb.append("Mã sách: ").append(maSach[j]).append("\n");
                sb.append("Ngày mượn: ").append(ngayMuon[j]).append("\n");
                sb.append("------------------------------------------\n");
            }
        }

        return sb.toString();
    }

    public static String taoBaoCaoVoiString(String[] maSach, String[] ngayMuon, int soLanLap) {
        String baoCao = "";

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        baoCao += "NHẬT KÝ MƯỢN SÁCH\n";
        baoCao += "Thời gian tạo báo cáo: " + now.format(formatter) + "\n";
        baoCao += "==========================================\n\n";

        for (int i = 0; i < soLanLap; i++) {
            for (int j = 0; j < maSach.length; j++) {
                baoCao += "Giao dịch #" + (i * maSach.length + j + 1) + "\n";
                baoCao += "Mã sách: " + maSach[j] + "\n";
                baoCao += "Ngày mượn: " + ngayMuon[j] + "\n";
                baoCao += "------------------------------------------\n";
            }
        }

        return baoCao;
    }
}