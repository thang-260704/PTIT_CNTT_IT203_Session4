import java.util.Scanner;

public class Ls4 {

    public static String normalizeSpaces(String str) {
        str = str.trim();

        StringBuilder result = new StringBuilder();
        boolean lastWasSpace = false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == ' ') {
                if (!lastWasSpace) {
                    result.append(c);
                    lastWasSpace = true;
                }
            } else {
                result.append(c);
                lastWasSpace = false;
            }
        }

        return result.toString();
    }

    public static String capitalizeWords(String str) {
        String[] words = str.split(" ");
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tên sách: ");
        String tenSach = scanner.nextLine();

        System.out.print("Nhập tên tác giả: ");
        String tacGia = scanner.nextLine();

        System.out.print("Nhập thể loại: ");
        String theLoai = scanner.nextLine();

        tenSach = normalizeSpaces(tenSach);
        tacGia = normalizeSpaces(tacGia);
        theLoai = normalizeSpaces(theLoai);

        tenSach = tenSach.toUpperCase();

        tacGia = capitalizeWords(tacGia);

        String ketQua = "[" + tenSach + "] - Tác giả: [" + tacGia + "]";
        System.out.println("\nKết quả sau khi chuẩn hóa:");
        System.out.println(ketQua);

        scanner.close();
    }
}