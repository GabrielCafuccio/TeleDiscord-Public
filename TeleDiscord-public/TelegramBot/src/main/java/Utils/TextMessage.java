package Utils;

public class TextMessage {
    private String userName;
    private String text;

    public TextMessage(String text, String userName) {
        this.text = text;
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
