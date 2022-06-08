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
        String option1[] = { "設定", "搜尋書籍", "借書", "還書", "借還紀錄", "繳納罰金", "登出" };
        int choice = JOptionPane.showOptionDialog(null, "", "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
        switch (choice) {
            case 0:
                data();
                break;
            case 1:
                L.searchBook();
                break;
            case 2:
                L.searchBookforBorrow();
                break;
            case 3:
                returnBook();
                break;
            case 4:
                history();
                break;
            case 5:
                payFine();
                break;
            case 6:
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

    public void data() {
        String option1[] = { "編輯姓名帳號", "修改密碼", "返回主畫面" };
        String text = "個人資料:\n姓名: " + account.getName() + "\n帳號: " + account.getID() + "\n身分別: "
                + account.getIdenfication() + "\n需繳罰金: " + fine + "元";
        int choice = JOptionPane.showOptionDialog(null, text, "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
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
        String option1[] = { "確定更改", "取消" };
        Object information[] = { "姓名:\t" + this.account.getName() + "\n輸入新姓名(如果無須更改請空白):", name,
                "帳號:\t" + this.account.getID() + "\n輸入新帳號(如果無須更改請空白):", account };

        int choice = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);

        int x = 1; // x:1 帳號沒重複
        for (AccountData a : L.getAllAccounts()) {
            if (account.getText().equals(a.getID())) {
                x--;
            }
        }

        if (choice == 0) {
            if (name.getText().equals("") && account.getText().equals("")) {
                String option2[] = { "確定" };
                int choice1 = JOptionPane.showOptionDialog(null, "錯誤:名字帳號皆留白", "圖書館書籍借還系統", 1, 1, null, option2,
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
                    String option3[] = { "確定" };
                    int choice2 = JOptionPane.showOptionDialog(null, "編輯成功!", "圖書館書籍借還系統", 1, 1, null, option3,
                            option3[0]);
                    if (choice2 == 0) {
                        data();
                    }
                } else {
                    String option4[] = { "確定" };
                    int choice3 = JOptionPane.showOptionDialog(null, "帳號與其他人重複!", "圖書館書籍借還系統", 1, 1, null, option4,
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
        String option1[] = { "確定更改", "取消" };
        Object information[] = { "請輸入新密碼:", password, "確認密碼:", repassword };
        int choice = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
        if (choice == 0) {
            if (password.getText().equals(repassword.getText())) {
                account.setPassword(password.getText());
                String option2[] = { "確定" };
                int choice1 = JOptionPane.showOptionDialog(null, "更改密碼成功!", "圖書館書籍借還系統", 1, 1, null, option2,
                        option2[0]);
                if (choice1 == 0) {
                    data();
                }
            } else {
                String option3[] = { "確定" };
                int choice2 = JOptionPane.showOptionDialog(null, "更改失敗!密碼輸入不正確", "圖書館書籍借還系統", 1, 1, null, option3,
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
            text += String.format("%-20s%-15s%-15s%-10s%-10s%-10s\n", "書名:", "借閱日期:", "歸還期限:", "歸還狀態:", "逾期顯示:",
                    "罰金:");
            for (Record r : allRecords) {
                r.calculusFine();
                r.checkDueStatus();
                text += r.toString() + "\n";
            }
        } else {
            text += "沒有紀錄!";
        }

        String Option1[] = { "確定" };
        int choice = JOptionPane.showOptionDialog(null, text, "圖書館書籍借還系統", 1, 1, null, Option1, Option1[0]);
        if (choice == 0) {
            menu();
        }
        if (choice == -1) {
            menu();
        }
    }

    public void payFine() {
        if (fine == 0) {
            String option1[] = { "確定" };
            int choice = JOptionPane.showOptionDialog(null, "你目前沒有罰金!", "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
            if (choice == 0) {
                menu();
            }
            if (choice == 0) {
                menu();
            }
        } else {
            String option2[] = { "確定", "返回" };
            JTextField pay = new JTextField();
            Object information[] = { "目前累計罰金:" + fine + "\n請輸入還款金額:", pay };
            int choice1 = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option2, option2[0]);
            if (choice1 == 0) {
                if (Integer.parseInt(pay.getText()) <= 0) {
                    String option3[] = { "確定" };
                    int choice2 = JOptionPane.showOptionDialog(null, "繳納金額不可以小於0!", "圖書館書籍借還系統", 0, 1, null, option3,
                            option3[0]);
                    switch (choice2) {
                        case 0:
                        case -1:
                            payFine();
                            break;
                    }

                } else if (Integer.parseInt(pay.getText()) > fine) {
                    String option4[] = { "確定" };
                    int choice3 = JOptionPane.showOptionDialog(null, "繳納金額不可以大於罰金!", "圖書館書籍借還系統", 0, 1, null, option4,
                            option4[0]);
                    switch (choice3) {
                        case 0:
                        case -1:
                            payFine();
                            break;
                    }
                } else {
                    fine -= Integer.parseInt(pay.getText());
                    String option5[] = { "確定" };
                    int choice4 = JOptionPane.showOptionDialog(null, "繳納成功!\n目前剩餘罰金: " + fine + "元", "圖書館書籍借還系統", 0, 1,
                            null, option5, option5[0]);
                    switch (choice4) {
                        case 0:
                        case -1:
                            menu();
                            break;
                    }
                }
            }
            if (choice1 == 1) {
                menu();
            }
            if (choice1 == -1) {
                menu();
            }
        }
    }

    public abstract void borrowBook(Book b);

    public abstract void returnBook();

}