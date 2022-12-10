package bankATM.lib;

public class userATM {
    private String idUser;
    private String nameUser;
    private String password;
    private float balance;

    public userATM(String id, String name, String password, float balance) {
        this.idUser = id;
        this.nameUser = name;
        this.password = password;
        this.balance = balance;
    }

    public String getID() {
        return this.idUser;
    }

    public String getPassword() {
        return this.password;
    }

    public float getBalance() {
        return this.balance;
    }

    public void setBalance(float drawal) {
        this.balance = this.balance - drawal;
    }
}
