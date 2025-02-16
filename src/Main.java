import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int attempt = 3;
        boolean activity = true;
        String path = "D:\\Java_lessons\\login\\src\\login.csv";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, Sir.");
        int btn = 0;
        while (true) {
            System.out.println("Login-->1: Register-->2: Forget Password-->3: Close App-->4");
            btn = scanner.nextInt();
            scanner.nextLine();
            if (btn == 1) {
                System.out.print("Enter the Email: ");
                String logEmail = scanner.nextLine();
                while (!logEmail.matches(".*@.*")) {
                    System.out.print("Enter valid email.");
                    logEmail = scanner.nextLine();
                }
                System.out.print("Enter the password:");
                String logPassword = scanner.nextLine();
                while (loginProcess(logEmail, logPassword, path)) {
                    System.out.println("Your email or password wrong.");
                    System.out.print("Enter valid Email.");
                    logEmail = scanner.nextLine();
                    logPassword = scanner.nextLine();
                }
                System.out.println("Yu succesfully login.");


            } else if (btn == 2) {
                System.out.println("Registiration process.");
                System.out.print("Enter the username: ");
                String regUserName = scanner.nextLine();
                System.out.print("Enter Email");
                String regEmail = scanner.nextLine();
                while (!regEmail.matches(".*@.*") || emailExist(path, regEmail)) {
                    if (!regEmail.matches(".*@.*")) {

                        System.out.print("Enter valid email.");
                    }
                    if (!emailExist(path, regEmail)) {
                        System.out.println("This email is exist.");
                    }
                    regEmail = scanner.nextLine();
                }
                System.out.print("Enter password atleast a big letter a small letter a number: ");
                String regPassword = scanner.nextLine();
                while (!regPassword.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,20}$")) {
                    System.out.println("Enter valid password");
                    regPassword = scanner.nextLine();
                }


                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
                    writer.append(regUserName);
                    writer.append(";");
                    writer.append(regEmail);
                    writer.append(";");
                    writer.append(regPassword);
                    writer.newLine();
                    writer.flush();
                    writer.close();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println("you scucesfully registered");


            } else if (btn == 3) {
                System.out.print("Enter your registered email: ");
                String forgotEmail = scanner.nextLine();

                if (!emailExist(path, forgotEmail)) {
                    System.out.println("Email not found in the system.");
                } else {
                    System.out.print("Enter new password (at least one uppercase, one lowercase, and one number): ");
                    String newPassword = scanner.nextLine();
                    while (!newPassword.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,20}$")) {
                        System.out.println("Invalid password format. Try again.");
                        newPassword = scanner.nextLine();
                    }

                    updatePassword(path, forgotEmail, newPassword);
                    System.out.println("Password updated successfully!");
                }


            } else if (btn == 4) {
                System.out.println("Bye-Bye");
                break;

            } else {
                System.out.println("There is not a function");
            }

        }


    }

    public static boolean emailExist(String path, String regEmail) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length > 1 && values[1].trim().equalsIgnoreCase(regEmail)) {
                    return true;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean loginProcess(String email, String password, String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length > 1 && values[1].trim().equalsIgnoreCase(email) && values[2].trim().equalsIgnoreCase(password)) {
                    return false;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static void updatePassword(String path, String email, String newPassword) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length > 1 && values[1].trim().equalsIgnoreCase(email)) {
                    values[2] = newPassword; // Täze paroly çalyş
                    line = String.join(";", values);
                }
                sb.append(line).append("\n");
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(sb.toString());
            bw.flush();
            bw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}