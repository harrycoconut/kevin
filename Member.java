import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;

public abstract class Member {
    protected AccountData account;
    protected static int fine = 0;
    protected static ArrayList<Book> borrowList = new ArrayList<Book>();
    protected static ArrayList<Record> allRecords = new ArrayList<Record>();
    LibrarySystem L = new LibrarySystem();

    public Member(AccountData account) {
        setAccount(account);
    }

    public AccountData getAccount() {
        return account;
    }

    public void setAccount(AccountData account) {
        this.account = account;
    }

    public void menu() {
        String option1[] = { "�]�w", "�j�M���y", "�ɮ�", "�ٮ�", "���٬���", "�n�X" };
        int choice = JOptionPane.showOptionDialog(null, "", "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);
        switch (choice) {
            case 0:
                data();
                break;
            case 1:
                L.searchBook();
                break;
            case 2:
                L.searchBookforBorrow();
            case 3:
                returnBook();
            case 4:
                history();
            case 5:
                L.logout();
            case -1:
                String option2[] = { "�T�w", "����" };
                int choice1 = JOptionPane.showOptionDialog(null, "�T�w�n�����{����?", "�Ϯ��]���y���٨t��", 1, 1, null, option2,
                        option2[0]);
                if (choice1 == 1) {
                    menu();
                } else {
                    System.exit(0);
                }
                break;
        }
    }

    public void data() {
        String option1[] = { "�s��m�W�b��", "�ק�K�X", "��^�D�e��" };
        String text = "�ӤH���:\n�m�W: " + account.getName() + "\n�b��: " + account.getID() + "\n�����O: "
                + showIdenfication();
        int choice = JOptionPane.showOptionDialog(null, text, "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);
        if (choice == 0) {
            editData();
        }
        if (choice == 1) {
            editPassword();
        }
        if (choice == 2) {
            menu();
        }
        if (choice == -1) {
            menu();
        }
    }

    public void editData() {
        JTextField name = new JTextField();
        JTextField account = new JTextField();
        String option1[] = { "�T�w���", "����" };
        Object information[] = { "�m�W:\t" + this.account.getName() + "\n��J�s�m�W(�p�G�L�����Ъť�):", name,
                "�b��:\t" + this.account.getID() + "\n��J�s�b��(�p�G�L�����Ъť�):", account };

        int choice = JOptionPane.showOptionDialog(null, information, "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);

        int x = 1; // x:1 �b���S����
        for (AccountData a : L.getAllAccounts()) {
            if (account.getText().equals(a.getID())) {
                x--;
            }
        }

        if (choice == 0) {
            if (name.getText().equals("") && account.getText().equals("")) {
                String option2[] = { "�T�w" };
                int choice1 = JOptionPane.showOptionDialog(null, "���~:�W�r�b���үd��", "�Ϯ��]���y���٨t��", 1, 1, null, option2,
                        option2[0]);
                if (choice1 == 0) {
                    data();
                }
            } else if (choice != -1) {
                if (x == 1) {
                    if (account.getText().equals("")) {
                    } else {
                        this.account.setID(account.getText());
                    }
                    if (name.getText().equals("")) {
                    } else {
                        this.account.setName(name.getText());
                    }
                    String option3[] = { "�T�w" };
                    int choice2 = JOptionPane.showOptionDialog(null, "�s�覨�\!", "�Ϯ��]���y���٨t��", 1, 1, null, option3,
                            option3[0]);
                    if (choice2 == 0) {
                        data();
                    }
                } else {
                    String option4[] = { "�T�w" };
                    int choice3 = JOptionPane.showOptionDialog(null, "�b���P��L�H����!", "�Ϯ��]���y���٨t��", 1, 1, null, option4,
                            option4[0]);
                    if (choice3 == 0) {
                        data();
                    }
                }
            }
        }
        if (choice == 1) {
            data();
        }
    }

    public void editPassword() {
        JPasswordField password = new JPasswordField();
        JPasswordField repassword = new JPasswordField();
        String option1[] = { "�T�w���", "����" };
        Object information[] = { "�п�J�s�K�X:", password, "�T�{�K�X:", repassword };
        int choice = JOptionPane.showOptionDialog(null, information, "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);
        if (choice == 0) {
            if (password.getText().equals(repassword.getText())) {
                account.setPassword(password.getText());
                String option2[] = { "�T�w" };
                int choice1 = JOptionPane.showOptionDialog(null, "���K�X���\!", "�Ϯ��]���y���٨t��", 1, 1, null, option2,
                        option2[0]);
                if (choice1 == 0) {
                    data();
                }
            } else {
                String option3[] = { "�T�w" };
                int choice2 = JOptionPane.showOptionDialog(null, "��異��!�K�X��J�����T", "�Ϯ��]���y���٨t��", 1, 1, null, option3,
                        option3[0]);
                if (choice2 == 0) {
                    data();
                }
            }
        }
        if (choice == 1) {
            data();
        }
    }

    public void history() {
        String text = "";
        if (allRecords.size() != 0) {
            text += String.format("%-20s%-15s%-15s%-10s%-10s%-10s\n", "�ѦW:", "�ɾ\���:", "�k�ٴ���:", "�k�٪��A:", "�O�����:",
                    "�@��:");
            for (Record r : allRecords) {
                r.calculusFine();
                r.checkDueStatus();
                text += r.toString() + "\n";
            }
        } else {
            text += "�S������!";
        }

        String Option1[] = { "�T�w" };
        int choice = JOptionPane.showOptionDialog(null, text, "�Ϯ��]���y���٨t��", 1, 1, null, Option1, Option1[0]);
        if (choice == 0) {
            menu();
        }
        if (choice == -1) {
            menu();
        }

    }

    public String showIdenfication() {
        String i = "";
        switch (account.getIdenfication()) {
            case 0:
                i += "�ǥ�";
                break;
            case 1:
                i += "�Ѯv";
                break;
            case 2:
                i += "¾��";
                break;
        }
        return i;
    }

    public abstract void borrowBook(Book b);

    public abstract void returnBook();

}