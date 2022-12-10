import bankATM.lib.*;
import java.util.*;;

public class App {
    public static void main(String[] args) throws Exception {
        float numAccount = 0;
        String nameATM = "ATM ComputerThanyaburi Bank";

        // รับค่าจำนวนบัญชี และตรวจสอบค่าจากที่รับ
        do {
            numAccount = inputFieldNumber("Step 1. Enter amount of all account = ");
            if (numAccount >= 5)
                break;
            else
                reportReturn("Please enter account then 5");
        } while (true);

        // รับข้อมูลบัญชี
        System.out.println("Step 2. Enter Detail of each account.");
        ArrayList<userATM> accountList = new ArrayList<userATM>();
        for (int x = 0; x < numAccount; x++) {
            System.out.println("No." + String.valueOf(x + 1));

            boolean checkID;
            String idInput;
            String nameInput;
            String password;
            float money;
            do {
                checkID = false;
                idInput = inputFieldString("Account ID=");
                if (idInput.length() != 13) {
                    checkID = true;
                    reportReturn("Account ID equal 13 charector");
                    continue;
                }

                // check id ซ้ำซ้อน
                for (int y = 0; y < accountList.size(); y++) {
                    if (accountList.get(y).getID().equals(idInput)) {
                        checkID = true;
                        reportReturn("Account ID already");
                        break;
                    }
                }
            } while (checkID);

            do {
                nameInput = inputFieldString("Account Name=");
                if (nameInput.length() <= 50)
                    break;
                else
                    reportReturn("Account Name less than or equal to 50 charector");
            } while (true);

            do {
                password = inputFieldString("Password=");
                if (password.length() == 4)
                    break;
                else
                    reportReturn("Password equal 4 charector");
            } while (true);

            do {
                try {
                    money = inputFieldNumber("Balance=");
                    if (money <= 1000000) {
                        break;
                    } else {
                        reportReturn("Please enter money no more than 1,000,000");
                    }
                } catch (Exception e) {
                    reportReturn("Please enter number");
                }
            } while (true);
            accountList.add((new userATM(idInput, nameInput, password, money)));
            System.out.println("");
        }

        // ส่วน Service Account
        do {
            System.out.println("-------------------------------------");
            System.out.println(nameATM);
            String idScanner = inputFieldString("Account ID : ");
            int posisionAccount = 0;
            String statusLogin = "";
            for (int x = 0; x < accountList.size(); x++) {
                statusLogin = "";
                if (accountList.get(x).getID().equals(idScanner)) {
                    // Max input password 3 round
                    for (int loopEnd = 3; loopEnd > 0; loopEnd--) {
                        // นับจำนวนครั้งที่กรอก password ได้
                        System.out.print("(" + String.valueOf(loopEnd) + ")");
                        String passScanner = inputFieldString("Account Password : ");
                        if (accountList.get(x).getPassword().equals(passScanner)) {
                            statusLogin = "pass login";
                            posisionAccount = x;
                            break;
                        }
                    }
                    if (statusLogin.equals("pass login"))
                        break;
                } else {
                    statusLogin = "Invaid account";
                }
            }

            if (statusLogin.equals("pass login")) {
                do {
                    System.out.print("Menu Service\n1. Account Balance\n2. Withdrawal\n3. Exit\n");

                    try {
                        Float choose = inputFieldNumber("Choose : ");
                        if (choose == 1) {
                            System.out.println("-------------------------------------");
                            System.out.print("Account Balance : "
                                    + String.valueOf(accountList.get(posisionAccount).getBalance())
                                    + " Bath\n");
                        } else if (choose == 2) {
                            Float withdrawal;
                            do {
                                try {
                                    System.out.println("-------------------------------------");
                                    withdrawal = inputFieldNumber("You Whitdrawal = ");
                                    break;
                                } catch (Exception e) {
                                    reportReturn("Please enter number");
                                }
                            } while (true);
                            if (accountList.get(posisionAccount).getBalance() >= withdrawal) {
                                accountList.get(posisionAccount).setBalance(withdrawal);
                                System.out.print("Success withdrawal : " + String.valueOf(withdrawal) + " Bath\n");
                            } else
                                reportReturn("Balance less withdrawal");

                        } else if (choose == 3) {
                            break;
                        } else {
                            reportReturn("Please enter choose 1 - 3");
                        }
                    } catch (Exception e) {
                    }
                    System.out.println("-------------------------------------");
                } while (true);
            } else if (statusLogin.equals("Invaid account")) {
                reportReturn(statusLogin);
            } else {
                reportReturn("Account no correct");
            }
        } while (true);
    }

    public static String inputFieldString(String textField) {
        System.out.print(textField);
        return new Scanner(System.in).nextLine();
    }

    public static float inputFieldNumber(String textField) {
        System.out.print(textField);
        return new Scanner(System.in).nextFloat();
    }

    public static void reportReturn(String wordReport) {
        System.out.print("!!! -----");
        for (int x = 0; x < wordReport.length(); x++)
            System.out.print("-");
        System.out.println("----- !!!");

        System.out.println("!!! ---- " + wordReport + " ---- !!!");

        System.out.print("!!! -----");
        for (int x = 0; x < wordReport.length(); x++)
            System.out.print("-");
        System.out.println("----- !!!");
    }

}
