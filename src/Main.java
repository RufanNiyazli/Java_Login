import java.util.Scanner;

public class Main {
    static int attempt = 3;
    static boolean activity = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (activity) {
            while (attempt > 0) {
                System.out.println("İstifadəçi adını daxil edin:");
                String username = scanner.nextLine();
                System.out.println("Şifrəni daxil edin:");
                String password = scanner.nextLine();

                boolean result = login(username, password);
                if (result) {
                    System.out.println("Siz uğurla daxil oldunuz!");
                    break;
                } else {
                    attempt--;
                    if (attempt > 0) {
                        System.out.println("Yanlış məlumat! Qalan cəhdlər: " + attempt);
                    } else {
                        System.out.println("Cəhdlər bitdi. Hesab bloklandı!");
                        activity = false;
                    }
                }
            }
        } else {
            System.out.println("Siz aktiv deyilsiniz. İdarəçilə əlaqə saxlayın.");
        }


    }

    public static boolean login(String username, String password) {
        if (username.equals("rufan") && password.equals("123")) {
            return true;
        } else {
            return false;
        }
    }
}