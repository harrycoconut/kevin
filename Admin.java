import javax.swing.*;
import java.util.ArrayList;

public class Admin {
    private AccountData account;
    LibrarySystem L = new LibrarySystem();
    private ArrayList<Book> allBooks = new ArrayList<Book>();

    public Admin(AccountData account) {
        setAccount(account);
        allBooks = L.getBook();
    }

    public AccountData getAccount() {
        return account;
    }

    public void setAccount(AccountData account) {
        this.account = account;
    }

    public void menu() {
        String option1[] = { "搜尋書籍", "新增書籍", "編輯書籍", "刪除書籍", "館藏書籍", "登出" };
        int choice = JOptionPane.showOptionDialog(null, "", "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
        switch (choice) {
            case 0:
                L.searchBook();
                break;
            case 1:
                addBook();
                break;
            case 2:
                editBook();
                break;
            case 3:
                deleteBook();
                break;
            case 4:
                showBooks();
                break;
            case 5:
                L.logout();
                break;
            case -1:
                String option2[] = { "確定", "取消" };
                int choice1 = JOptionPane.showOptionDialog(null, "確定要關閉程式嗎?", "圖書館書籍借還系統", 1, 1, null, option2,
                        option2[0]);
                if (choice1 == 1) {
                    menu();
                } else {
                    System.exit(0);
                }
                break;
        }
    }

    public void addBook() {
        JTextField name = new JTextField();
        JTextField author = new JTextField();
        JTextField publisher = new JTextField();
        JTextField ID = new JTextField();

        String option1[] = { "新增", "返回" };
        Object imformation[] = { "書名:", name, "作者:", author, "出版商:", publisher, "ISBN:", ID };
        int choice = JOptionPane.showOptionDialog(null, imformation, "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);

        if (choice == 0) { // 新增
            int x = 1; // x:0 書重複
            for (Book b : allBooks) {
                if (b.getName().equals(name.getText())) {
                    x--;
                    break;
                }
            }
            String option2[] = { "繼續新增", "返回主畫面" };
            if (x == 1) {
                allBooks.add(new Book(name.getText(), author.getText(), publisher.getText(),
                        Integer.parseInt(ID.getText()), true));
                int choice3 = JOptionPane.showOptionDialog(null, "新增成功!", "圖書館書籍借還系統", 1, 1, null, option2, option2[0]);
                if (choice3 == 0) {
                    addBook();
                }
                if (choice3 == 1) {
                    menu();
                }
            }
            if (x == 0) {
                int choice4 = JOptionPane.showOptionDialog(null, "此書已被儲存!", "圖書館書籍借還系統", 1, 1, null, option2,
                        option2[0]);
                if (choice4 == 0) {
                    addBook();
                }
                if (choice == 1) {
                    menu();
                }
            }
        }
        if (choice == 1) { // 返回
            menu();
        }
    }

    public void editBook() {
        Book b = L.searchBookforAdmin();
        if (b.getName() != null) {
            if (b.getStatus() == true) {
                JTextField name = new JTextField();
                JTextField author = new JTextField();
                JTextField publisher = new JTextField();
                JTextField ID = new JTextField();
                String option1[] = { "編輯", "取消編輯" };
                Object information[] = { "原書名: " + b.getName() + " 修改為: ", name,
                        "原作者: " + b.getAuthor() + " 修改為: ", author,
                        "原出版商: " + b.getPublisher() + " 修改為: ", publisher,
                        "原ISBN: " + b.getID() + " 修改為: ", ID };
                int choose1 = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option1,
                        option1[0]);

                int x = 1; // x:1 書名沒重複
                int y = 1; // y:1 ISBN沒重複

                allBooks.remove(b);
                for (Book i : allBooks) {
                    if (name.getText().equals(i.getName())) {
                        x--;
                    }
                    if (Integer.parseInt(ID.getText()) == i.getID()) {
                        y--;
                    }
                }
                allBooks.add(b);

                if (choose1 == 0) {
                    if (x == 1 && y == 1) {
                        allBooks.remove(b);
                        allBooks.add(new Book(name.getText(), author.getText(), publisher.getText(),
                                Integer.parseInt(ID.getText()), true));
                        JOptionPane.showMessageDialog(null, "編輯成功!", "圖書館書籍借還系統", 1);
                        menu();
                    }
                    if (x != 1) {
                        JOptionPane.showMessageDialog(null, "書名與其他書重複!", "圖書館書籍借還系統", 1);
                        editBook();
                    }
                    if (x == 1 && y != 1) {
                        JOptionPane.showMessageDialog(null, "ISBN與其他書重複!", "圖書館書籍借還系統", 1);
                        editBook();
                    }
                }
                if (choose1 == 1) {
                    editBook();
                }
            } else {
                JOptionPane.showMessageDialog(null, "此書已被借閱，無法編輯", "圖書館書籍借還系統", 1);
                editBook();
            }
        } else {
            if (b.getPublisher().equals("test")) {
                JOptionPane.showMessageDialog(null, "查無此書籍", "圖書館書籍借還系統", 1);
                menu();
            }
        }
    }

    public void deleteBook() {
        Book b = L.searchBookforAdmin();

        String option1[] = { "確定", "取消" };
        if (b.getName() != null) {
            if (b.getStatus() == true) {
                int choose1 = JOptionPane.showOptionDialog(null, "搜尋結果: \n" + b.toString() + "\n" + "確定要刪除嗎?",
                        "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
                if (choose1 == 0) {
                    allBooks.remove(b);
                    String option2[] = { "繼續刪除", "返回主畫面" };
                    int choose2 = JOptionPane.showOptionDialog(null, "刪除成功!", "圖書館書籍借還系統", 1, 1, null, option2,
                            option2[0]);
                    if (choose2 == 0) {
                        deleteBook();
                    }
                    if (choose2 == 1) {
                        menu();
                    }
                }
                if (choose1 == 1) {
                    menu();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "查無此書籍", "圖書館書籍借還系統", 1);
            menu();
        }

    }

    public void showBooks() {
        L.showAllBook();
    }
}