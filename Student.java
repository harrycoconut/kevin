import java.time.LocalDate;
import javax.swing.*;

public class Student extends Member {

    public Student(AccountData account) {
        super(account);
    }

    public void borrowBook(Book b) {
        LocalDate current = LocalDate.now();
        LocalDate returnday = current.plusDays(14);
        allRecords.add(new Record(b, current, returnday, false, false, 0, 0));
        borrowList.add(b);
        b.setStatus(false);
        String option1[] = { "繼續借閱", "返回主畫面" };
        int choice = JOptionPane.showOptionDialog(null, "借書成功!", "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
        if (choice == 0) {
            L.searchBookforBorrow();
        }
        if (choice == 1) {
            menu();
        }
    }

    public void returnBook() {
        if (borrowList.size() == 0) {
            String option1[] = { "返回主畫面" };
            int choice = JOptionPane.showOptionDialog(null, "您目前沒有借的書籍", "圖書館書籍借還系統", 1, 1, null, option1, option1[0]);
            if (choice == 0) {
                menu();
            }
        } else {
            String option2[] = new String[borrowList.size()];
            String option3[] = { "確認", "返回主畫面" };
            for (int i = 0; i < borrowList.size(); i++) {
                option2[i] = borrowList.get(i).getName();
            }
            JComboBox<String> borrowBox = new JComboBox<String>(option2);
            Object information[] = { "選擇你要還的書:", borrowBox };
            int choice1 = JOptionPane.showOptionDialog(null, information, "圖書館書籍借還系統", 1, 1, null, option3, option3[0]);
            if (choice1 == 0) {
                Book tempBook = new Book(null, null, null, 0, true);
                for (Book b : borrowList) {
                    if (borrowBox.getSelectedItem().equals(b.getName())) {
                        tempBook = b;
                        borrowList.remove(b);
                        break;
                    }
                }

                LocalDate current = LocalDate.now();



                Record tempRecord = new Record(null, current, current, false, false, 0, 0);
                for (Record r : allRecords) {
                    if (tempBook.getName().equals(r.getBook().getName())) {
                        tempRecord = r;
                        r.checkDueStatus();
                        fine += r.getFine();
                        r.setBookStatus(true);
                        break;
                    }
                }

                String text = "還書成功!";
                if (tempRecord.getFine() != 0) {
                    text += "\n此次還書逾期，需繳納罰金: " + tempRecord.getFine() + "元";
                }
                String option4[] = { "繼續還書", "返回主畫面" };
                int choice2 = JOptionPane.showOptionDialog(null, text, "圖書館書籍借還系統", 1, 1, null, option4, option4[0]);

                if (choice2 == 0) {
                    returnBook();
                }
                if (choice2 == 1) {
                    menu();
                }
                if (choice2 == -1) {
                    menu();
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
}