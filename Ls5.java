import java.util.Scanner;

public class Ls5 {

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
            System.out.println("Ngày: " + ngayThang);
            System.out.println("Người dùng: " + nguoiDung);
            System.out.println("Hành động: " + hanhDong);
            System.out.println("Mã sách: " + maSach);
            System.out.println("---");
        }
    }

    public static LogEntry phanTichLog(String log) {
        String[] parts = log.split("\\|");

        String ngayThang = parts[0].trim();

        String nguoiDung = parts[1].trim();
        nguoiDung = nguoiDung.substring(nguoiDung.indexOf(":") + 1).trim();

        String hanhDong = parts[2].trim();
        hanhDong = hanhDong.substring(hanhDong.indexOf(":") + 1).trim();

        String maSach = parts[3].trim();
        maSach = maSach.substring(maSach.indexOf(":") + 1).trim();

        return new LogEntry(ngayThang, nguoiDung, hanhDong, maSach);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] logs = {
                "2024-05-20 | User: NguyenVanA | Action: BORROW | BookID: BK12345",
                "2024-05-21 | User: TranThiB | Action: RETURN | BookID: BK67890",
                "2024-05-22 | User: LeVanC | Action: BORROW | BookID: BK11111",
                "2024-05-23 | User: PhamThiD | Action: BORROW | BookID: BK22222",
                "2024-05-24 | User: NguyenVanA | Action: RETURN | BookID: BK12345"
        };

        System.out.println("========== PHÂN TÍCH FILE LOG THƯ VIỆN ==========\n");

        LogEntry[] danhSachLog = new LogEntry[logs.length];
        int demBorrow = 0;
        int demReturn = 0;

        for (int i = 0; i < logs.length; i++) {
            System.out.println("Log #" + (i + 1) + ": " + logs[i]);

            LogEntry entry = phanTichLog(logs[i]);
            danhSachLog[i] = entry;

            System.out.println("Thông tin đã trích xuất:");
            entry.hienThi();

            if (entry.hanhDong.equals("BORROW")) {
                demBorrow++;
            } else if (entry.hanhDong.equals("RETURN")) {
                demReturn++;
            }

            System.out.println();
        }

        System.out.println("========== THỐNG KÊ ==========");
        System.out.println("Tổng số log: " + logs.length);
        System.out.println("Số lần BORROW (Mượn): " + demBorrow);
        System.out.println("Số lần RETURN (Trả): " + demReturn);

        scanner.close();
    }
}