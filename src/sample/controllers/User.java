package sample.controllers;

public class User {
    private String login;
    private String password;
    private String email;
    private String city;
    private String gender;
    private String urlSiteToParse;
    private String sortBy;
    private String saveOnComp;
    private String saveFolder;
    private String only18;
    private String language;
    private String history;

    public User(String login, String password, String email, String city, String gender) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.city = city;
        this.gender = gender;
        this.urlSiteToParse = urlSiteToParse;
        this.sortBy = sortBy;
        this.saveOnComp = saveOnComp;
        this.saveFolder = saveFolder;
        this.only18 = only18;
        this.language = language;
        this.history = history;
    }

    public User() {

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUrlSiteToParse() {
        return urlSiteToParse;
    }

    public void setUrlSiteToParse(String urlSiteToParse) {
        this.urlSiteToParse = urlSiteToParse;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSaveOnComp() {
        return saveOnComp;
    }

    public void setSaveOnComp(String saveOnComp) {
        this.saveOnComp = saveOnComp;
    }

    public String getSaveFolder() {
        return saveFolder;
    }

    public void setSaveFolder(String saveFolder) {
        this.saveFolder = saveFolder;
    }

    public String getOnly18() {
        return only18;
    }

    public void setOnly18(String only18) {
        this.only18 = only18;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }



}