import javax.swing.*;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class LibrarySystem {
    ArrayList<Book> booksInLibrary = new ArrayList<Book>();
    ArrayList<AccountData> allAccounts = new ArrayList<AccountData>();

    public void setBook(Book book) {

    }

    public ArrayList<Book> getBook() {
        return booksInLibrary;
    }

    public void start() {

        allAccounts.add(new Student(new AccountData("��", "kevin", "2326", 3)));

        String option1[] = { "�n�J", "���U" };
        int choice = JOptionPane.showOptionDialog(null, "�w��Ө�Ϯ��]���y���٨t��", "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);
        if (choice == 0) {
            login();
        } else if (choice == 1) {
            register();
        }
    }

    public void register() {
        JTextField name = new JTextField();
        JTextField account = new JTextField();
        JPasswordField password = new JPasswordField();
        JPasswordField repassword = new JPasswordField();
        String option1[] = { "�ǥ�", "�Юv", "¾��" };
        String option2[] = { "���U" };
        JComboBox<String> idenfication = new JComboBox<String>(option1);
        Object information[] = { "�W�r:", name, "�b��:", account, "�K�X:", password, "�T�{�K�X:", repassword, "�����O:",
                idenfication };

        int choice = JOptionPane.showOptionDialog(null, information, "�Ϯ��]���y���٨t��", 1, 1, null, option2, option2[0]);
        int x = 0;// x:1 �b���S������
        int y = 0;// y:1 �K�X�P�T�{�K�X�ۦP
        if (choice == 0) {
            for (AccountData a : allAccounts) {
                if (account.getText().equals(a.getID())) {
                    break;
                }
                if (a == allAccounts.get(allAccounts.size() - 1)) {
                    x++;
                    break;
                }
            }
            if (password.getText().equals(repassword.getText())) {
                y++;
            }

            if (x == 1 && y == 1) {
                allAccounts.add(new AccountData(name.getText(), account.getText(), password.getText(),
                        idenfication.getSelectedIndex()));
                JOptionPane.showMessageDialog(null, "���U���\!\n�N����ܵn�J����", "�Ϯ��]���y���٨t��", 0);
                login();
            }
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "���U����!\n�b���w�s�b", "�Ϯ��]���y���٨t��", 0);
                register();
            }
            if (x == 1 && y == 0) {
                JOptionPane.showMessageDialog(null, "���U����!\n�K�X��J���~", "�Ϯ��]���y���٨t��", 0);
                register();
            }
        }

    }

    public void login() {
        JTextField account = new JTextField();
        JPasswordField password = new JPasswordField();
        String option1[] = { "�n�J" };
        Object information[] = { "�b��:", account, "�K�X:", password };

        int choice = JOptionPane.showOptionDialog(null, information, "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);
        if (choice == 0) {
            for (AccountData a : allAccounts) {
                if (a.getID().equals(account.getText())) {
                    if (a.getPassword().equals(password.getText())) {
                        Member m = new Member(a);
                        switch (a.getIdenfication()) {
                            case 0:
                                m = new Student(a);
                                break;
                            case 1:
                                m = new Teacher(a);
                                break;
                            case 2:
                                m = new Staff(a);
                                break;
                        }
                        m.menu();
                    } else {
                        JOptionPane.showMessageDialog(null, "�z��J���K�X���~!", "�Ϯ��]���y���٨t��", 1);
                        login();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "�z��J���b�����~�άd�L���b��!", "�Ϯ��]���y���٨t��", 1);
                    login();
                }
            }
        }
    }

    public void searchBook() { // �O�o��void ��^ Book

    }

    public void showAllBook() {

    }

}
