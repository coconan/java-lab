package me.coconan.jbehave;

public class GithubUser {

    private String login;

    public GithubUser() {
        super();
    }

    // API

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }
}
