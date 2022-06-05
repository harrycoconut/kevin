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
        allAccounts.add(new AccountData("韋凱翔", "kxwai", "2326", 0));
        allAccounts.add(new AccountData("凱", "kevin", "2326", 3));
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

    // 程式開始
    public void start() {

        String option1[] = { "登入", "註冊" };
        int choice = JOptionPane.showOptionDialog(null, "歡迎來到圖書館書籍借還系統", "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
        if (choice == 0) {
            login();
        }
        if (choice == 1) {
            register();
        }
        if (choice == -1) {
            String option2[] = { "確定", "取消" };
            int choice1 = JOptionPane.showOptionDialog(null, "確定要關閉程式嗎?", "圖書館書籍借還系統", 1, 1, null, option2,
                    option2[0]);
            if (choice1 == 1) {
                start();
            } else {
                System.exit(0);
            }
        }
    }

    // 註冊
    public void register() {
        JTextField name = new JTextField();
        JTextField account = new JTextField();
        JPasswordField password = new JPasswordField();
        JPasswordField repassword = new JPasswordField();
        String option1[] = { "學生", "教師", "職員" };
        String option2[] = { "註冊", "返回" };
        JComboBox<String> idenfication = new JComboBox<String>(option1);
        Object information[] = { "名字:", name, "帳號:", account, "密碼:", password, "確認密碼:", repassword, "身分別:",
                idenfication };

        int choice = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option2, option2[0]);
        int x = 0; // x:1 帳號沒有重複
        int y = 0; // y:1 密碼與確認密碼相同
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
                JOptionPane.showMessageDialog(null, "註冊成功!\n將跳轉至登入頁面", "圖書館書籍借還系統", 1);
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
        if (choice == 1) {
            start();
        }

    }

    // 登入
    public void login() {

        JTextField account = new JTextField();
        JPasswordField password = new JPasswordField();
        String option1[] = { "登入", "返回" };
        Object information[] = { "帳號:", account, "密碼:", password };

        int choice = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);

        int x = 0; // x:1 帳號正確
        int y = 0; // y;1 密碼正確

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
                JOptionPane.showMessageDialog(null, "您輸入的密碼錯誤!", "圖書館書籍借還系統", 1);
                login();
            }
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "您輸入的帳號錯誤或查無此帳號!", "圖書館書籍借還系統", 1);
                login();
            }
        }
        if (choice == 1) {
            start();
        }
    }

    // 登出
    public void logout() {
        start();
        nowAccount = null;
    }

    // 找書
    public void searchBook() {
        String option1[] = { "書名", "作者", "出版商", "ISBN", "返回主畫面" };
        int choice = JOptionPane.showOptionDialog(null, "以什麼搜尋?", "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
        if (choice == 4) {
            backToMenu();
        } else if (choice != -1) {
            JTextField key = new JTextField();
            String option2[] = { "確定", "返回主畫面" };
            Object information[] = { "輸入書名:", key };
            int choose = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option2, option2[0]);
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
        String option[] = { "確定", "返回主畫面" };
        Object information[] = { "輸入書名:", key };
        int choose = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option, option[0]);
        if (choose == 1) {
            backToMenu();
        }
        if (choose == -1) {
            backToMenu();
        }

        String text = "搜尋結果: \n";
        int x = 0; // x:0 未找到
        Book temp = new Book(null, null, null, 0, false);
        for (Book b : booksInLibrary) {
            if (b.getName().equals(key.getText())) {
                x++;
                text += b.toString() + "\n";
                temp = b;
            }
        }

        Member m = memberType(nowAccount);
        String option1[] = { "借閱", "返回主選單" };
        if (x != 0) {
            int choice = JOptionPane.showOptionDialog(null, text, "圖書館書籍借還系統", 1, 1, null,
                    option1, option1[0]);
            if (choice == 0) {
                if (temp.getStatus() == true) {
                    m.borrowBook(temp);
                } else {
                    String option2[] = { "繼續借閱", "回主畫面" };
                    int choice1 = JOptionPane.showOptionDialog(null, "此書已被借走!", "圖書館書籍借還系統", 1, 1, null, option2,
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
            JOptionPane.showMessageDialog(null, "查無此書籍", "圖書館書籍借還系統", 1);
            searchBookforBorrow();
        }
    }

    public Book searchBookforAdmin() {
        JTextField target = new JTextField();
        String option1[] = { "確定", "返回主畫面" };
        Object information[] = { "輸入書名:", target };

        int choose = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
        Book temp = new Book(null, null, "test", 0, false);
        if (choose == 0) {
            String text = "搜尋結果: \n";
            int x = 0; // x:0 未找到
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
        String text = "搜尋結果: \n";
        int x = 0; // x:0 未找到
        Book temp = new Book(null, null, null, 0, false);
        for (Book b : booksInLibrary) {
            if (b.getName().equals(key)) {
                x++;
                text += b.toString() + "\n";
                temp = b;
            }
        }

        Member m = memberType(nowAccount);
        String option1[] = { "繼續搜尋", "借閱", "返回主選單" };
        if (x != 0) {
            int choice = JOptionPane.showOptionDialog(null, text, "圖書館書籍借還系統", 1, 1, null,
                    option1, option1[0]);
            if (choice == 0) {
                searchBook();
            }
            if (choice == 1) {
                if (temp.getStatus() == true) {
                    if (nowAccount.getIdenfication() != 3) {
                        m.borrowBook(temp);
                    } else {
                        String o[] = { "返回主選單" };
                        int c = JOptionPane.showOptionDialog(null, "管理員無法借閱!", "圖書館書籍借還系統", 1, 1, null, o, o[0]);
                        if (c == 0) {
                            backToMenu();
                        }
                    }
                } else {
                    String option2[] = { "繼續搜尋", "回主畫面" };
                    int choice1 = JOptionPane.showOptionDialog(null, "此書已被借走!", "圖書館書籍借還系統", 1, 1, null, option2,
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
            JOptionPane.showMessageDialog(null, "查無此書籍", "圖書館書籍借還系統", 1);
            searchBook();
        }
    }

    public void searchBookAuthor(String key) {
        String text = "搜尋結果: \n";
        int x = 0; // x:0 未找到
        Book temp = new Book(null, null, null, 0, false);
        for (Book b : booksInLibrary) {
            if (b.getAuthor().equals(key)) {
                x++;
                text += b.toString() + "\n";
            }
        }

        Member m = memberType(nowAccount);
        String option1[] = { "繼續搜尋", "返回主選單" };
        if (x != 0) {

            int choice = JOptionPane.showOptionDialog(null, text, "圖書館書籍借還系統", 1, 1, null,
                    option1, option1[0]);
            if (choice == 0) {
                searchBook();
            }
            if (choice == 1) {
                backToMenu();
            }
        }
        if (x == 0) {
            JOptionPane.showMessageDialog(null, "查無此作者的書籍", "圖書館書籍借還系統", 1);
            searchBook();
        }
    }

    public void searchBookPublisher(String key) {
        String text = "搜尋結果: \n";
        int x = 0; // x:0 未找到
        Book temp = new Book(null, null, null, 0, false);
        for (Book b : booksInLibrary) {
            if (b.getPublisher().equals(key)) {
                x++;
                text += b.toString() + "\n";
            }
        }

        Member m = memberType(nowAccount);
        String option1[] = { "繼續搜尋", "返回主選單" };
        if (x != 0) {

            int choice = JOptionPane.showOptionDialog(null, text, "圖書館書籍借還系統", 1, 1, null,
                    option1, option1[0]);
            if (choice == 0) {
                searchBook();
            }
            if (choice == 1) {
                backToMenu();
            }
        }
        if (x == 0) {
            JOptionPane.showMessageDialog(null, "查無此出版商的書籍", "圖書館書籍借還系統", 1);
            searchBook();
        }
    }

    public void searchID(int key) {
        String text = "搜尋結果: \n";
        int x = 0; // x:0 未找到
        Book temp = new Book(null, null, null, 0, false);
        for (Book b : booksInLibrary) {
            if (b.getID() == key) {
                x++;
                text += b.toString() + "\n";
            }
        }

        Member m = memberType(nowAccount);
        String option1[] = { "繼續搜尋", "返回主選單" };
        if (x != 0) {

            int choice = JOptionPane.showOptionDialog(null, text, "圖書館書籍借還系統", 1, 1, null,
                    option1, option1[0]);
            if (choice == 0) {
                searchBook();
            }
            if (choice == 1) {
                backToMenu();
            }
        }
        if (x == 0) {
            JOptionPane.showMessageDialog(null, "查無此ISBN的書籍", "圖書館書籍借還系統", 1);
            searchBook();
        }
    }

    public void showAllBook() {
        String text = "";
        String option1[] = { "返回主畫面" };
        for (Book i : booksInLibrary) {
            text += i.toString() + "\n";
        }
        int choose = JOptionPane.showOptionDialog(null, "館藏書籍: \n" + text, "圖書館書籍借還系統", 1, 1, null, option1,
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
