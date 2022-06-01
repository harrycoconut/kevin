public class Member{
    protected AccountData account;

    public Member(AccountData account){
        setAccount(account);
    }

    public AccountData getAccount() {
        return account;
    }

    public void setAccount(AccountData account) {
        this.account = account;
    }

    public void menu(){
        System.out.println("µn¤J¦¨¥\!");
    }

}