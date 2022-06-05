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
        String option1[] = { "�j�M���y", "�s�W���y", "�s����y", "�R�����y", "�]�î��y", "�n�X" };
        int choice = JOptionPane.showOptionDialog(null, "", "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);
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

    public void addBook() {
        JTextField name = new JTextField();
        JTextField author = new JTextField();
        JTextField publisher = new JTextField();
        JTextField ID = new JTextField();

        String option1[] = { "�s�W", "��^" };
        Object imformation[] = { "�ѦW:", name, "�@��:", author, "�X����:", publisher, "ISBN:", ID };
        int choice = JOptionPane.showOptionDialog(null, imformation, "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);

        if (choice == 0) { // �s�W
            int x = 1; // x:0 �ѭ���
            for (Book b : allBooks) {
                if (b.getName().equals(name.getText())) {
                    x--;
                    break;
                }
            }
            String option2[] = { "�~��s�W", "��^�D�e��" };
            if (x == 1) {
                allBooks.add(new Book(name.getText(), author.getText(), publisher.getText(),
                        Integer.parseInt(ID.getText()), true));
                int choice3 = JOptionPane.showOptionDialog(null, "�s�W���\!", "�Ϯ��]���y���٨t��", 1, 1, null, option2, option2[0]);
                if (choice3 == 0) {
                    addBook();
                }
                if (choice3 == 1) {
                    menu();
                }
            }
            if (x == 0) {
                int choice4 = JOptionPane.showOptionDialog(null, "���Ѥw�Q�x�s!", "�Ϯ��]���y���٨t��", 1, 1, null, option2,
                        option2[0]);
                if (choice4 == 0) {
                    addBook();
                }
                if (choice == 1) {
                    menu();
                }
            }
        }
        if (choice == 1) { // ��^
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
                String option1[] = { "�s��", "�����s��" };
                Object information[] = { "��ѦW: " + b.getName() + " �קאּ: ", name,
                        "��@��: " + b.getAuthor() + " �קאּ: ", author,
                        "��X����: " + b.getPublisher() + " �קאּ: ", publisher,
                        "��ISBN: " + b.getID() + " �קאּ: ", ID };
                int choose1 = JOptionPane.showOptionDialog(null, information, "�Ϯ��]���y���٨t��", 1, 1, null, option1,
                        option1[0]);

                int x = 1; // x:1 �ѦW�S����
                int y = 1; // y:1 ISBN�S����

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
                        JOptionPane.showMessageDialog(null, "�s�覨�\!", "�Ϯ��]���y���٨t��", 1);
                        menu();
                    }
                    if (x != 1) {
                        JOptionPane.showMessageDialog(null, "�ѦW�P��L�ѭ���!", "�Ϯ��]���y���٨t��", 1);
                        editBook();
                    }
                    if (x == 1 && y != 1) {
                        JOptionPane.showMessageDialog(null, "ISBN�P��L�ѭ���!", "�Ϯ��]���y���٨t��", 1);
                        editBook();
                    }
                }
                if (choose1 == 1) {
                    editBook();
                }
            } else {
                JOptionPane.showMessageDialog(null, "���Ѥw�Q�ɾ\�A�L�k�s��", "�Ϯ��]���y���٨t��", 1);
                editBook();
            }
        } else {
            if (b.getPublisher().equals("test")) {
                JOptionPane.showMessageDialog(null, "�d�L�����y", "�Ϯ��]���y���٨t��", 1);
                menu();
            }
        }
    }

    public void deleteBook() {
        Book b = L.searchBookforAdmin();

        String option1[] = { "�T�w", "����" };
        if (b.getName() != null) {
            if (b.getStatus() == true) {
                int choose1 = JOptionPane.showOptionDialog(null, "�j�M���G: \n" + b.toString() + "\n" + "�T�w�n�R����?",
                        "�Ϯ��]���y���٨t��", 1, 1, null, option1, option1[0]);
                if (choose1 == 0) {
                    allBooks.remove(b);
                    String option2[] = { "�~��R��", "��^�D�e��" };
                    int choose2 = JOptionPane.showOptionDialog(null, "�R�����\!", "�Ϯ��]���y���٨t��", 1, 1, null, option2,
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
            JOptionPane.showMessageDialog(null, "�d�L�����y", "�Ϯ��]���y���٨t��", 1);
            menu();
        }

    }

    public void showBooks() {
        L.showAllBook();
    }
}