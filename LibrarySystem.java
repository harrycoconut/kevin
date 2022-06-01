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

        allAccounts.add(new Student(new AccountData("凱", "kevin", "2326", 3)));

        String option1[] = { "登入", "註冊" };
        int choice = JOptionPane.showOptionDialog(null, "歡迎來到圖書館書籍借還系統", "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
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
        String option1[] = { "學生", "教師", "職員" };
        String option2[] = { "註冊" };
        JComboBox<String> idenfication = new JComboBox<String>(option1);
        Object information[] = { "名字:", name, "帳號:", account, "密碼:", password, "確認密碼:", repassword, "身分別:",
                idenfication };

        int choice = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option2, option2[0]);
        int x = 0;// x:1 帳號沒有重複
        int y = 0;// y:1 密碼與確認密碼相同
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
                JOptionPane.showMessageDialog(null, "註冊成功!\n將跳轉至登入頁面", "圖書館書籍借還系統", 0);
                login();
            }
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "註冊失敗!\n帳號已存在", "圖書館書籍借還系統", 0);
                register();
            }
            if (x == 1 && y == 0) {
                JOptionPane.showMessageDialog(null, "註冊失敗!\n密碼輸入錯誤", "圖書館書籍借還系統", 0);
                register();
            }
        }

    }

    public void login() {
        JTextField account = new JTextField();
        JPasswordField password = new JPasswordField();
        String option1[] = { "登入" };
        Object information[] = { "帳號:", account, "密碼:", password };

        int choice = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
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
                        JOptionPane.showMessageDialog(null, "您輸入的密碼錯誤!", "圖書館書籍借還系統", 1);
                        login();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "您輸入的帳號錯誤或查無此帳號!", "圖書館書籍借還系統", 1);
                    login();
                }
            }
        }
    }

    public void searchBook() { // 記得把void 改回 Book

    }

    public void showAllBook() {

    }

}
