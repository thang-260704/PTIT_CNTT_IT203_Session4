import java.util.Scanner;

public class Ls2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập đoạn mô tả sách:");
        String moTa = scanner.nextLine();

        String tuKhoa = "Kệ:";

        if (moTa.contains(tuKhoa)) {
            System.out.println("Tìm thấy từ khóa 'Kệ:' trong mô tả");

            int viTriBatDau = moTa.indexOf(tuKhoa);
            int viTriMa = viTriBatDau + tuKhoa.length();

            int viTriKetThuc = moTa.indexOf(",", viTriMa);
            if (viTriKetThuc == -1) {
                viTriKetThuc = moTa.length();
            }

            String maViTri = moTa.substring(viTriMa, viTriKetThuc).trim();
            System.out.println("Mã vị trí: " + maViTri);

            String moTaMoi = moTa.replace(tuKhoa, "Vị trí lưu trữ:");
            System.out.println("\nMô tả sau khi thay thế:");
            System.out.println(moTaMoi);

        } else {
            System.out.println("Không tìm thấy từ khóa 'Kệ:' trong mô tả");
        }

        scanner.close();
    }
}