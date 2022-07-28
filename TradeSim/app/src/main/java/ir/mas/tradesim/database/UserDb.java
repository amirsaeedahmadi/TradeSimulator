package ir.mas.tradesim.database;



import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserDb {

    @PrimaryKey
    @NonNull

    private String authToken;
    private String nickname;
    private double rialCredit;
    private double rialEquivalent;
    private boolean darkMode;


    public UserDb(@NonNull String authToken, String nickname, double rialCredit, double rialEquivalent) {
        this.authToken = authToken;
        this.nickname = nickname;
        this.rialCredit = rialCredit;
        this.rialEquivalent = rialEquivalent;
        this.darkMode = false;
    }


    @NonNull
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(@NonNull String authToken) {
        this.authToken = authToken;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getRialCredit() {
        return rialCredit;
    }

    public void setRialCredit(double rialCredit) {
        this.rialCredit = rialCredit;
    }

    public double getRialEquivalent() {
        return rialEquivalent;
    }

    public void setRialEquivalent(double rialEquivalent) {
        this.rialEquivalent = rialEquivalent;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }
}
