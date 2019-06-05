package bean;

/**
 * @author 黎江
 */
public class AccountBean {
    private String account;
    private String password;
    private String email = null;
    private String mobile = null;
    private long registerTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getRegisterTime() {
        return registerTime;
    }
}
