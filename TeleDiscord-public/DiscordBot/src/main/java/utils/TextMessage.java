package utils;

public class TextMessage {
    private String userName;
    private String text;

    public TextMessage(String userName, String text) {
        this.text = text;
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
