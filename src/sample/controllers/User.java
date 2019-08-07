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
    private String imgFun;
    private String imgAdult;
    private String videoGifFun;
    private String videoGifAdult;
    private String tableField;
    private String publicURL;
    private String imgURLs;
    private String typeOfContent;

    public User(String login, String imgFun, String imgAdult, String videoGifFun, String videoGifAdult, String tableField) {
        this.login  = login;
        this.imgFun = imgFun;
        this.imgAdult = imgAdult;
        this.videoGifFun   = videoGifFun;
        this.videoGifAdult = videoGifAdult;
        this.tableField = tableField;

    }

    public User(String login, String imgURLs, String typeOfContent, String publicURL) {
        this.login  = login;
        this.imgURLs = imgURLs;
        this.typeOfContent = typeOfContent;
        this.publicURL   = publicURL;
    }


    public User(String login, String password, String email, String city, String gender) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.city = city;
        this.gender = gender;
    }
    public User(String password, String email, String language, String only18, String saveFolder,
                String saveOnComp, String sortBy, String parseFrom, String login) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.city = city;
        this.gender = gender;
        this.urlSiteToParse = parseFrom;
        this.sortBy = sortBy;
        this.saveOnComp = saveOnComp;
        this.saveFolder = saveFolder;
        this.only18 = only18;
        this.language = language;
    }


    public User() {

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() { return password; }

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

    public String getImgFun() {
        return imgFun;
    }

    public String getImgURLs() {
        return imgURLs;
    }

    public void setImgURLs(String imgURLs) {
        this.imgURLs = imgURLs;
    }

    public String getTypeOfContent() {
        return typeOfContent;
    }

    public void setTypeOfContent(String typeOfContent) {
        this.typeOfContent = typeOfContent;
    }

    public void setImgFun(String imgFun) {
        this.imgFun = imgFun;
    }


    public String getImgAdult() {
        return imgAdult;
    }

    public void setImgAdult(String imgAdult) {
        this.imgAdult = imgAdult;
    }

    public String getVideoGifFun() {
        return videoGifFun;
    }

    public void setVideoGifFun(String videoGifFun) {
        this.videoGifFun= videoGifFun;
    }

    public String getVideoGifAdult() {
        return videoGifAdult;
    }

    public void setVideoGifAdult(String videoGifAdult) {
        this.videoGifAdult= videoGifAdult;
    }

    public String getTableField() {
        return tableField;
    }

    public void setTableField(String tableField) {
        this.tableField= tableField;
    }

    public String getPublicURL() {
        return publicURL;
    }

    public void setPublicURL(String publicURL) {
        this.publicURL= publicURL;
    }

}
