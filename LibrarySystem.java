import javax.swing.*;

import java.util.ArrayList;

public class LibrarySystem {
    private static ArrayList<Book> booksInLibrary = new ArrayList<Book>();
    private static ArrayList<AccountData> allAccounts = new ArrayList<AccountData>();
    private static AccountData nowAccount = new AccountData(null, null, null, 0);

    public LibrarySystem() {

    }

    public void initialization() {
        booksInLibrary.add(new Book("1", "JK", "1", 1, true));
        booksInLibrary.add(new Book("2", "JK", "2", 2, true));
        booksInLibrary.add(new Book("3", "JK", "3", 3, true));
        allAccounts.add(new AccountData("���͵�", "kxwai", "2326", 0));
        allAccounts.add(new AccountData("��", "kevin", "2326", 3));
    }

    public void setBook(Book book) {

    }

    public ArrayList<Book> getBook() {
        return booksInLibrary;
    }

    public void setNowAccount(AccountData a) {
        nowAccount = a;
    }

    public AccountData getNowAccount() {
        return nowAccount;
    }

    public ArrayList<AccountData> getAllAccounts() {
        return allAccounts;
    }

    // �{���}�l
    public void start() {

        String option1[] = { "�n�J", "���U" };
        int choice = JOptionPane.showOptionDialog(null, "�w��Ө�Ϯ��]���y���٨t��", "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);
        if (choice == 0) {
            login();
        }
        if (choice == 1) {
            register();
        }
        if (choice == -1) {
            String option2[] = { "�T�w", "����" };
            int choice1 = JOptionPane.showOptionDialog(null, "�T�w�n�����{����?", "�Ϯ��]���y���٨t��", 1, 1, null, option2,
                    option2[0]);
            if (choice1 == 1) {
                start();
            } else {
                System.exit(0);
            }
        }
    }

    // ���U
    public void register() {
        JTextField name = new JTextField();
        JTextField account = new JTextField();
        JPasswordField password = new JPasswordField();
        JPasswordField repassword = new JPasswordField();
        String option1[] = { "�ǥ�", "�Юv", "¾��" };
        String option2[] = { "���U", "��^" };
        JComboBox<String> idenfication = new JComboBox<String>(option1);
        Object information[] = { "�W�r:", name, "�b��:", account, "�K�X:", password, "�T�{�K�X:", repassword, "�����O:",
                idenfication };

        int choice = JOptionPane.showOptionDialog(null, information, "�Ϯ��]���y���٨t��", 1, 1, null, option2, option2[0]);
        int x = 0; // x:1 �b���S������
        int y = 0; // y:1 �K�X�P�T�{�K�X�ۦP
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
                JOptionPane.showMessageDialog(null, "���U���\!\n�N����ܵn�J����", "�Ϯ��]���y���٨t��", 1);
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
        if (choice == 1) {
            start();
        }

    }

    // �n�J
    public void login() {

        JTextField account = new JTextField();
        JPasswordField password = new JPasswordField();
        String option1[] = { "�n�J", "��^" };
        Object information[] = { "�b��:", account, "�K�X:", password };

        int choice = JOptionPane.showOptionDialog(null, information, "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);

        int x = 0; // x:1 �b�����T
        int y = 0; // y;1 �K�X���T

        if (choice == 0) {
            for (AccountData a : allAccounts) {
                if (a.getID().equals(account.getText())) {
                    setNowAccount(a);
                    x++;
                    if (nowAccount.getPassword().equals(password.getText())) {
                        y++;
                    }
                    break;
                }
            }

            if (x == 1 && y == 1) {
                switch (nowAccount.getIdenfication()) {
                    case 0:
                        Member m = new Student(nowAccount);
                        m.menu();
                        break;
                    case 1:
                        m = new Teacher(nowAccount);
                        m.menu();
                        break;
                    case 2:
                        m = new Staff(nowAccount);
                        m.menu();
                        break;
                    case 3:
                        Admin a = new Admin(nowAccount);
                        a.menu();
                        break;
                }
            }

            if (x == 1 && y == 0) {
                JOptionPane.showMessageDialog(null, "�z��J���K�X���~!", "�Ϯ��]���y���٨t��", 1);
                login();
            }
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "�z��J���b�����~�άd�L���b��!", "�Ϯ��]���y���٨t��", 1);
                login();
            }
        }
        if (choice == 1) {
            start();
        }
    }

    // �n�X
    public void logout() {
        start();
        nowAccount = null;
    }

    // ���
    public void searchBook() {
        String option1[] = { "�ѦW", "�@��", "�X����", "ISBN", "��^�D�e��" };
        int choice = JOptionPane.showOptionDialog(null, "�H����j�M?", "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);
        if (choice == 4) {
            backToMenu();
        } else if (choice != -1) {
            JTextField key = new JTextField();
            String option2[] = { "�T�w", "��^�D�e��" };
            Object information[] = { "��J�ѦW:", key };
            int choose = JOptionPane.showOptionDialog(null, information, "�Ϯ��]���y���٨t��", 1, 1, null, option2, option2[0]);
            if (choose == 1) {
                backToMenu();
            }

            switch (choice) {
                case 0:
                    searchBookName(key.getText());
                    break;
                case 1:
                    searchBookAuthor(key.getText());
                    break;
                case 2:
                    searchBookPublisher(key.getText());
                    break;
                case 3:
                    searchID(Integer.parseInt(key.getText()));
                    break;
            }
        } else {
            backToMenu();
        }
    }

    public void searchBookforBorrow() {
        JTextField key = new JTextField();
        String option[] = { "�T�w", "��^�D�e��" };
        Object information[] = { "��J�ѦW:", key };
        int choose = JOptionPane.showOptionDialog(null, information, "�Ϯ��]���y���٨t��", 1, 1, null, option, option[0]);
        if (choose == 1) {
            backToMenu();
        }
        if (choose == -1) {
            backToMenu();
        }

        String text = "�j�M���G: \n";
        int x = 0; // x:0 �����
        Book temp = new Book(null, null, null, 0, false);
        for (Book b : booksInLibrary) {
            if (b.getName().equals(key.getText())) {
                x++;
                text += b.toString() + "\n";
                temp = b;
            }
        }

        Member m = memberType(nowAccount);
        String option1[] = { "�ɾ\", "��^�D���" };
        if (x != 0) {
            int choice = JOptionPane.showOptionDialog(null, text, "�Ϯ��]���y���٨t��", 1, 1, null,
                    option1, option1[0]);
            if (choice == 0) {
                if (temp.getStatus() == true) {
                    m.borrowBook(temp);
                } else {
                    String option2[] = { "�~��ɾ\", "�^�D�e��" };
                    int choice1 = JOptionPane.showOptionDialog(null, "���Ѥw�Q�ɨ�!", "�Ϯ��]���y���٨t��", 1, 1, null, option2,
                            option2[0]);
                    if (choice1 == 0) {
                        searchBookforBorrow();
                    }
                    if (choice1 == 1) {
                        backToMenu();
                    }
                }
                if (choice == 2) {
                    backToMenu();
                }
            }
            if (choice == 1) {
                backToMenu();
            }
        }
        if (x == 0) {
            JOptionPane.showMessageDialog(null, "�d�L�����y", "�Ϯ��]���y���٨t��", 1);
            searchBookforBorrow();
        }
    }

    public Book searchBookforAdmin() {
        JTextField target = new JTextField();
        String option1[] = { "�T�w", "��^�D�e��" };
        Object information[] = { "��J�ѦW:", target };

        int choose = JOptionPane.showOptionDialog(null, information, "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);
        Book temp = new Book(null, null, "test", 0, false);
        if (choose == 0) {
            String text = "�j�M���G: \n";
            int x = 0; // x:0 �����
            for (Book b : booksInLibrary) {
                if (b.getName().equals(target.getText())) {
                    x++;
                    text += b.toString() + "\n";
                    temp = b;
                }
            }
        }
        if (choose == 1) {
            backToMenu();
        }
        return temp;
    }

    public void searchBookName(String key) {
        String text = "�j�M���G: \n";
        int x = 0; // x:0 �����
        Book temp = new Book(null, null, null, 0, false);
        for (Book b : booksInLibrary) {
            if (b.getName().equals(key)) {
                x++;
                text += b.toString() + "\n";
                temp = b;
            }
        }

        Member m = memberType(nowAccount);
        String option1[] = { "�~��j�M", "�ɾ\", "��^�D���" };
        if (x != 0) {
            int choice = JOptionPane.showOptionDialog(null, text, "�Ϯ��]���y���٨t��", 1, 1, null,
                    option1, option1[0]);
            if (choice == 0) {
                searchBook();
            }
            if (choice == 1) {
                if (temp.getStatus() == true) {
                    if (nowAccount.getIdenfication() != 3) {
                        m.borrowBook(temp);
                    } else {
                        String o[] = { "��^�D���" };
                        int c = JOptionPane.showOptionDialog(null, "�޲z���L�k�ɾ\!", "�Ϯ��]���y���٨t��", 1, 1, null, o, o[0]);
                        if (c == 0) {
                            backToMenu();
                        }
                    }
                } else {
                    String option2[] = { "�~��j�M", "�^�D�e��" };
                    int choice1 = JOptionPane.showOptionDialog(null, "���Ѥw�Q�ɨ�!", "�Ϯ��]���y���٨t��", 1, 1, null, option2,
                            option2[0]);
                    if (choice1 == 0) {
                        searchBook();
                    }
                    if (choice1 == 1) {
                        backToMenu();
                    }
                }
                if (choice == 2) {
                    backToMenu();
                }
            }
            if (choice == 2) {
                backToMenu();
            }
        }
        if (x == 0) {
            JOptionPane.showMessageDialog(null, "�d�L�����y", "�Ϯ��]���y���٨t��", 1);
            searchBook();
        }
    }

    public void searchBookAuthor(String key) {
        String text = "�j�M���G: \n";
        int x = 0; // x:0 �����
        Book temp = new Book(null, null, null, 0, false);
        for (Book b : booksInLibrary) {
            if (b.getAuthor().equals(key)) {
                x++;
                text += b.toString() + "\n";
            }
        }

        Member m = memberType(nowAccount);
        String option1[] = { "�~��j�M", "��^�D���" };
        if (x != 0) {

            int choice = JOptionPane.showOptionDialog(null, text, "�Ϯ��]���y���٨t��", 1, 1, null,
                    option1, option1[0]);
            if (choice == 0) {
                searchBook();
            }
            if (choice == 1) {
                backToMenu();
            }
        }
        if (x == 0) {
            JOptionPane.showMessageDialog(null, "�d�L���@�̪����y", "�Ϯ��]���y���٨t��", 1);
            searchBook();
        }
    }

    public void searchBookPublisher(String key) {
        String text = "�j�M���G: \n";
        int x = 0; // x:0 �����
        Book temp = new Book(null, null, null, 0, false);
        for (Book b : booksInLibrary) {
            if (b.getPublisher().equals(key)) {
                x++;
                text += b.toString() + "\n";
            }
        }

        Member m = memberType(nowAccount);
        String option1[] = { "�~��j�M", "��^�D���" };
        if (x != 0) {

            int choice = JOptionPane.showOptionDialog(null, text, "�Ϯ��]���y���٨t��", 1, 1, null,
                    option1, option1[0]);
            if (choice == 0) {
                searchBook();
            }
            if (choice == 1) {
                backToMenu();
            }
        }
        if (x == 0) {
            JOptionPane.showMessageDialog(null, "�d�L���X���Ӫ����y", "�Ϯ��]���y���٨t��", 1);
            searchBook();
        }
    }

    public void searchID(int key) {
        String text = "�j�M���G: \n";
        int x = 0; // x:0 �����
        Book temp = new Book(null, null, null, 0, false);
        for (Book b : booksInLibrary) {
            if (b.getID() == key) {
                x++;
                text += b.toString() + "\n";
            }
        }

        Member m = memberType(nowAccount);
        String option1[] = { "�~��j�M", "��^�D���" };
        if (x != 0) {

            int choice = JOptionPane.showOptionDialog(null, text, "�Ϯ��]���y���٨t��", 1, 1, null,
                    option1, option1[0]);
            if (choice == 0) {
                searchBook();
            }
            if (choice == 1) {
                backToMenu();
            }
        }
        if (x == 0) {
            JOptionPane.showMessageDialog(null, "�d�L��ISBN�����y", "�Ϯ��]���y���٨t��", 1);
            searchBook();
        }
    }

    public void showAllBook() {
        String text = "";
        String option1[] = { "��^�D�e��" };
        for (Book i : booksInLibrary) {
            text += i.toString() + "\n";
        }
        int choose = JOptionPane.showOptionDialog(null, "�]�î��y: \n" + text, "�Ϯ��]���y���٨t��", 1, 1, null, option1,
                option1[0]);
        if (choose == 0) {
            backToMenu();
        }
    }

    public Member memberType(AccountData a) {
        Member m = new Student(a);
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
        return m;
    }

    public void backToMenu() {
        switch (nowAccount.getIdenfication()) {
            case 0:
                Member m = new Student(nowAccount);
                m.menu();
                break;
            case 1:
                m = new Teacher(nowAccount);
                m.menu();
                break;
            case 2:
                m = new Staff(nowAccount);
                m.menu();
                break;
            case 3:
                Admin a = new Admin(nowAccount);
                a.menu();
                break;
        }

    }

}
