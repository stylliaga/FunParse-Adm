package sample.controllers;


import java.awt.*;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.*;
import com.mysql.cj.xdevapi.Result;
import javafx.application.Platform;
import javafx.event.ActionEvent;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyEvent;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import sample.Constant;
import sample.DatabaseHandler;
import sample.parsers.vk.HttpConnectionAgent;
import sample.parsers.vk.VKParserImages;


import javax.swing.*;

import static javafx.scene.paint.Color.GREEN;
import static sample.Constant.VK_ACCESS_TOKEN;
import static sample.Constant.VK_USER_ACCESS_TOKEN;


public class ImageController {

    //int rev;
    //String groupDomain      = "";
    //String typeOfContent    = "";
    private String folderOnComp;


    //VKParserImages l            = new VKParserImages();
    //VKParserImages pressButtonNext = new VKParserImages();

///-----------------------------------------------//

    DatabaseHandler dbHandler = new DatabaseHandler();
    User user = new User();
    int counter, offsetOfParsing, offsetOfParsingCount, countAllContentInPublic, countAllImgOfParsingSession = 0;
    private int rev;
    private String groupDomain;
    public String typeOfContent = "";
    public String typeOfResourceContent = "";
    public String whichRadioButton      = "";


///-----------------------------------------------//

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneVk;

    @FXML
    private Accordion accordionVk;

    @FXML
    private TitledPane accordionVkImage;

    @FXML
    private TextField inputUrlOtherPublicImg;

    @FXML
    private RadioButton radioParsImgOld;

    @FXML
    private ToggleGroup tgRBDownloadRev;

    @FXML
    private RadioButton radioParsImgNew;

    @FXML
    private RadioButton radioImgFun;

    @FXML
    private ToggleGroup tgRBDownloadTheme;

    @FXML
    private RadioButton radioImgAdult;

    @FXML
    private RadioButton radioDownloadImgFromOwn;

    @FXML
    private ToggleGroup tgRBDownloadFromPublic;

    @FXML
    private RadioButton radioDownloadImgFromOther;

    @FXML
    private Label labelResultParsingImgURFound;

    @FXML
    private Label labelResultParsingImgURLNow;

    @FXML
    private Label labelResultParsingImgURLSum;

    @FXML
    private Label labelParsingImgURLIsRunning;

    @FXML
    private Label labelParsingImgURLFinished;

    @FXML
    private Label labelParsingImgURLReady;

    @FXML
    private ProgressBar progressbarImgParsing;

    @FXML
    private Button buttonStartImgParse;

    @FXML
    private TitledPane accordionVkGif;

    @FXML
    private TextField inputUrlOtherPublicVideo;

    @FXML
    private RadioButton radioParsVideoOld;

    @FXML
    private RadioButton radioParsVideoNew;

    @FXML
    private RadioButton radioVideoFun;

    @FXML
    private RadioButton radioVideoAdult;

    @FXML
    private RadioButton radioDownloadVideoFromOwn;

    @FXML
    private RadioButton radioDownloadVideoFromOther;

    @FXML
    private Label labelResultParsingVideoURFound;

    @FXML
    private Label labelResultParsingVideoURLNow;

    @FXML
    private Label labelResultParsingVideoURLSum;

    @FXML
    private Label labelParsingVideoURLIsRunning;

    @FXML
    private Label labelParsingVideoURLFinished;

    @FXML
    private Label labelParsingVideoURLReady;

    @FXML
    private ProgressBar progressbarVideoParsing;

    @FXML
    private Button buttonStartVideoParse;

    @FXML
    private AnchorPane panePikabu;

    @FXML
    private AnchorPane paneYaplakal;

    @FXML
    private AnchorPane paneFishki;

    @FXML
    private AnchorPane paneSettings;

    @FXML
    private CheckBox checkboxClearFieldImgAdult;

    @FXML
    private CheckBox checkboxClearFieldImgFun;

    @FXML
    private CheckBox checkboxClearFieldVideoAdult;

    @FXML
    private CheckBox checkboxClearFieldVideoFun;

    @FXML
    private Button buttonClearTableField;

    @FXML
    void buttonClearTableField(ActionEvent event) {

    }

    @FXML
    void buttonStartImgParse(ActionEvent event) {

    }

    @FXML
    void buttonStartVideoParse(ActionEvent event) {

    }

    @FXML
    void inputUrlOtherPublicImg(InputMethodEvent event) {

    }

    @FXML
    void inputUrlOtherPublicVideo(ActionEvent event) {

    }

    @FXML
    void radioVideoAdult(ActionEvent event) {
        whichRadioButton = "video";
        radioVideoFun.setSelected(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }

    @FXML
    void radioVideoFun(ActionEvent event) {
        whichRadioButton = "video";
        radioVideoAdult.setSelected(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }

    @FXML
    void radioImgAdult(ActionEvent event) {
        whichRadioButton = "img";
        radioImgFun.setSelected(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }

    @FXML
    void radioImgFun(ActionEvent event) {
        whichRadioButton = "img";
        radioImgAdult.setSelected(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }

    @FXML
    void radioDownloadImgFromOther(ActionEvent event) {
        radioDownloadImgFromOwn.setSelected(false);
        inputUrlOtherPublicImg.setDisable(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }

    @FXML
    void radioDownloadImgFromOwn(ActionEvent event) {
        inputUrlOtherPublicImg.setDisable(true);
        radioDownloadImgFromOther.setSelected(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }

    @FXML
    void radioDownloadVideoFromOther(ActionEvent event) {
        radioDownloadVideoFromOwn.setSelected(false);
        inputUrlOtherPublicVideo.setDisable(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }

    @FXML
    void radioDownloadVideoFromOwn(ActionEvent event) {
        inputUrlOtherPublicVideo.setDisable(true);
        radioDownloadVideoFromOther.setSelected(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }

    @FXML
    void radioParsImgNew(ActionEvent event) {
        radioParsImgOld.setSelected(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }

    @FXML
    void radioParsImgOld(ActionEvent event) {
        radioParsImgNew.setSelected(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }

    @FXML
    void radioParsVideoNew(ActionEvent event) {
        radioParsVideoOld.setSelected(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }

    @FXML
    void radioParsVideoOld(ActionEvent event) {
        radioParsVideoNew.setSelected(false);
        checkRadiosForStartParsingButton(whichRadioButton);
    }



    @FXML
    void initialize(){
        radioParsImgNew.setDisable(true);
        radioParsImgOld.setDisable(true);
        radioParsVideoNew.setDisable(true);
        radioParsVideoOld.setDisable(true);

        labelParsingImgURLIsRunning.setVisible(false);
        labelParsingImgURLFinished.setVisible(false);
        labelParsingImgURLReady.setVisible(true);

        labelParsingVideoURLFinished.setVisible(false);
        labelParsingVideoURLIsRunning.setVisible(false);
        labelParsingVideoURLReady.setVisible(true);

        accordionVk.setExpandedPane(accordionVkImage);
        makeLabelParsingImgURLReadyVisible();


        // если что то меняют в поле "чужой паблик" делаем кнопку запуска парсера активной
        inputUrlOtherPublicImg.addEventFilter(KeyEvent.ANY, e ->{
            buttonStartImgParse.setDisable(false);
        });
        inputUrlOtherPublicVideo.addEventFilter(KeyEvent.ANY, e ->{
            buttonStartImgParse.setDisable(false);
        });

        // кнопка запуска прсера видео
        buttonStartVideoParse.setOnAction(event ->{
            typeOfResourceContent = "video";
            counter         = 0;
            offsetOfParsing = 0;
            makeParseThread(typeOfResourceContent,"VKVideoParser");
        });

        // КНОПКА ЗАПУСКА ПАРСЕРА КАРТИНОК
        buttonStartImgParse.setOnAction(event ->{
            typeOfResourceContent = "img";
            counter         = 0;
            offsetOfParsing = 0;
            makeParseThread(typeOfResourceContent,"VKImageParser");
        });
    }
    // создание потоака в отдельный метод
    public void makeParseThread(String typeOfResourceContent, String nameOfThread){
        if(typeOfResourceContent == "video") {
            if (!radioDownloadVideoFromOwn.isSelected()) {
                groupDomain = "";
            }
            labelResultParsingVideoURLNow.setText("0");
            labelResultParsingVideoURFound.setText("0");
            buttonStartVideoParse.setDisable(true);
        }else{
            if(!radioDownloadImgFromOwn.isSelected()) {groupDomain = "";}
            labelResultParsingImgURLNow.setText("0");
            labelResultParsingImgURFound.setText("0");
            buttonStartImgParse.setDisable(true);
        }
        makeLabelProcessParsingVisible();
        Thread t = new Thread(new Runnable() {
                private volatile boolean running = true;

                public void stop() {
                    running = false;
                }

                @Override
                public void run() {
                    try {
                        doParseAndSave();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.setName("" + nameOfThread + "");
            t.setDaemon(true);
            t.start();
    }
/* ------------- парсим Id паблика для если человек ввел короткое имя вместо ссылки ------------- -*/
    public void checkRadiosForStartParsingButton(String whichRadioButton){
        if(whichRadioButton == "img") {
            if (radioDownloadImgFromOwn.isSelected() || radioDownloadImgFromOther.isSelected()) {
                radioParsImgNew.setDisable(false);
                radioParsImgOld.setDisable(false);
            } else {
                radioParsImgOld.setDisable(true);
                radioParsImgNew.setDisable(true);
                radioParsImgNew.setSelected(false);
                radioParsImgOld.setSelected(false);
                buttonStartImgParse.setDisable(true);
            }

            if (radioParsImgOld.isSelected() || radioParsImgNew.isSelected()) {
                buttonStartImgParse.setDisable(false);
            } else {
                buttonStartImgParse.setDisable(true);
            }
        }else{
            if(radioDownloadVideoFromOwn.isSelected() || radioDownloadVideoFromOther.isSelected()){
                radioParsVideoNew.setDisable(false);
                radioParsVideoOld.setDisable(false);
            }else{
                radioParsVideoOld.setDisable(true);
                radioParsVideoNew.setDisable(true);
                radioParsVideoNew.setSelected(false);
                radioParsVideoOld.setSelected(false);
                buttonStartVideoParse.setDisable(true);
            }
            if(radioParsVideoOld.isSelected() || radioParsVideoNew.isSelected()){
                buttonStartVideoParse.setDisable(false);
            }else{
                buttonStartVideoParse.setDisable(true);
            }
        }
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public static String removeWordClubAt(String s) {
        return s.substring(6);
    }



    public void makeLabelProcessParsingVisible(){
        if(typeOfResourceContent == "img") {
            labelParsingImgURLIsRunning.setVisible(true);
            labelParsingImgURLFinished.setVisible(false);
            labelParsingImgURLReady.setVisible(false);
        }else {
            labelParsingVideoURLIsRunning.setVisible(true);
            labelParsingVideoURLFinished.setVisible(false);
            labelParsingVideoURLReady.setVisible(false);
        }
    }

    public void makeLabelFinishedParsingVisible(){
        if(typeOfResourceContent == "img") {
            labelParsingImgURLIsRunning.setVisible(false);
            labelParsingImgURLFinished.setVisible(true);
            labelParsingImgURLReady.setVisible(false);
        }else{
            labelParsingVideoURLIsRunning.setVisible(false);
            labelParsingVideoURLFinished.setVisible(true);
            labelParsingVideoURLReady.setVisible(false);
        }
    }

    public void makeLabelParsingImgURLReadyVisible(){
        if(typeOfResourceContent == "img") {
            labelParsingImgURLIsRunning.setVisible(false);
            labelParsingImgURLFinished.setVisible(false);
            labelParsingImgURLReady.setVisible(true);
        }else{
            labelParsingVideoURLIsRunning.setVisible(false);
            labelParsingVideoURLFinished.setVisible(false);
            labelParsingVideoURLReady.setVisible(true);
        }
    }
    public void doParsePublicId() throws MalformedURLException {
        if (radioParsImgNew.isSelected() || radioParsVideoNew.isSelected()) {
            rev = 1;
        } else {
            rev = 0;
        }
        if (radioVideoAdult.isSelected()) {
            typeOfContent = "videoAdult";
        }
        if (radioVideoFun.isSelected()) {
            typeOfContent = "videoFun";
        }
        if (radioImgAdult.isSelected()) {
            typeOfContent = "imgAdult";
        }
        if (radioImgFun.isSelected()) {
            typeOfContent = "imgFun";
        }
        if (radioImgAdult.isSelected() && radioDownloadImgFromOwn.isSelected() ||
            radioVideoAdult.isSelected() && radioDownloadVideoFromOwn.isSelected()) {
            groupDomain = Constant.VK_ADULT_GROUP_DOMAIN;
        }

        if (radioImgFun.isSelected() && radioDownloadImgFromOwn.isSelected() ||
            radioVideoFun.isSelected() && radioDownloadVideoFromOwn.isSelected()) {
            groupDomain = Constant.VK_FUN_GROUP_DOMAIN;
        }

        if (radioDownloadImgFromOther.isSelected() ||
            radioDownloadVideoFromOther.isSelected()) {
            URL inputOtherGroupDomainURL = null;
            String otherGroupDomainURL = ""+inputUrlOtherPublicImg.getText().trim()+""
                                           +inputUrlOtherPublicVideo.getText().trim()+"";
            if (otherGroupDomainURL != "") {
                String regexp = "vk.com";
                String regexp2 = "public";
                Pattern pattern = Pattern.compile(regexp);
                Matcher matcher = pattern.matcher(otherGroupDomainURL);
                if(matcher.find()) {
                    inputOtherGroupDomainURL = new URL(otherGroupDomainURL);
                    groupDomain = inputOtherGroupDomainURL.getPath();
                    groupDomain = removeCharAt(groupDomain, 0);
                }
                Pattern pattern2 = Pattern.compile(regexp2);
                Matcher matcher2 = pattern2.matcher(groupDomain);
                if(matcher2.find()){
                    groupDomain = removeWordClubAt(groupDomain);
                }
            }

            URIBuilder uriBuilder = new URIBuilder();
            // создаем и отправляем запрос к ВК апи - указываем какая группа, ключ доступа, отображать ли в ответе размер
            // сколько записей надо спарсить, откуда парсить(в данном случае из альбома стены группы), версия АПИ ВК
            uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/groups.getById")
                    .setParameter("group_ids", "" + groupDomain + "")
                    .setParameter("access_token", VK_ACCESS_TOKEN)
                    .setParameter("v", "5.61");

            // создаем соединение
            HttpResponse response = HttpConnectionAgent.connectResponse(uriBuilder);
            // считываем номер ответа соединения
            Integer status = response.getStatusLine().getStatusCode();
            // если ответ 200 - т.е. ответ пришел, сервер дотупен то получаем контент
            if (status == 200) {
                StringWriter content = new StringWriter();

                try {
                    IOUtils.copy(response.getEntity().getContent(), content);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
                // записываем в строку ответ
                String input = content.toString();
                JsonParser parser = new JsonParser();
                // парсим ответ и преобразыем в Джсон обьект с получение главного элемент response
                JsonObject mainObject = parser.parse(input).getAsJsonObject();
                // преобразуем обьект в массив и обращаемся к элементу items, где содержаться ссылки на размеры картинки
                JsonArray pItem = mainObject.getAsJsonArray("response");
                groupDomain = "-" + pItem.get(0).getAsJsonObject().get("id") + "";
            }

        }

    }

    public void doParseAndSave() throws MalformedURLException {
        String stringSrcImages  = "";
        String stringSrcVideos  = "";
        String typeOfMethod     = "";
        String paramRev         = "";
        String paramRevValue    = "";
        String paramOfPhoto     = "";
        String paramOfPhotoAlbum     = "";
        String valueParamOfPhoto     = "";
        String valueParamOfPhotoAlbum= "";
        String apiVersion            = "";
        String jsonArrayItem        = "";
        String paramAccessToken     = "";
        String paramAccessTokenValue    = "";
        String jsonObjectOfpWeightSizes   = "";
        String valuParamExtended   = "";
        String[] foundNeedlePWeightSizes;
        int countOfTypes;
        // подключаем метод парсинга id паблика
        doParsePublicId();
        for (int cicle = 0; cicle <= counter; cicle += 1000) {
                URIBuilder uriBuilder = new URIBuilder();
               // если запрос по кртинка то подставляем схему для картинки, если по видео - видео
            if(typeOfContent == "imgAdult" || typeOfContent == "imgFun"){
                typeOfMethod = "photos.get";
                countOfTypes = 1000;
                paramOfPhoto = "photo_sizes";
                valueParamOfPhoto = "1";
                paramOfPhotoAlbum = "album_id";
                valueParamOfPhotoAlbum  = "wall";
                paramRev                = "rev";
                paramRevValue           = "" + rev + "";
                offsetOfParsingCount    = 1000;
                apiVersion              = "5.73";
                jsonArrayItem           ="sizes";
                paramAccessToken        = "access_token";
                paramAccessTokenValue   = VK_ACCESS_TOKEN;
                jsonObjectOfpWeightSizes= "type";
                foundNeedlePWeightSizes = new String[]{"x", "w"};
                valuParamExtended = "0";

            }else{
                typeOfMethod = "video.get";
                countOfTypes = 200;
                paramOfPhoto = "";
                valueParamOfPhoto = "";
                paramOfPhotoAlbum = "";
                valueParamOfPhotoAlbum = "";
                paramRev         = "";
                paramRevValue    = "";
                offsetOfParsingCount  = 200;
                apiVersion       = "5.101";
                jsonArrayItem    = "files";
                paramAccessToken        = "access_token";
                paramAccessTokenValue   = VK_USER_ACCESS_TOKEN;
                jsonObjectOfpWeightSizes= "mp4_360";
                foundNeedlePWeightSizes = new String[]{"mp4_720", "mp4_360"};
                valuParamExtended = "1";

            }
                // создаем и отправляем запрос к ВК апи - указываем какая группа, ключ доступа, отображать ли в ответе размер
                // сколько записей надо спарсить, откуда парсить(в данном случае из альбома стены группы), версия АПИ ВК
                uriBuilder.setScheme("https")
                        .setHost("api.vk.com")
                        .setPath("/method/" + typeOfMethod + "")
                        .setParameter("owner_id", "" + groupDomain + "")
                        .setParameter("" + paramAccessToken + "", "" + paramAccessTokenValue + "")
                        .setParameter("" + paramOfPhoto + "", "" + valueParamOfPhoto + "")
                        .setParameter("count", "" + countOfTypes + "")
                        .setParameter("" + paramOfPhotoAlbum + "", "" + valueParamOfPhotoAlbum + "")
                        .setParameter("" + paramRev + "", "" + paramRevValue + "")
                        .setParameter("offset", "" + offsetOfParsing + "")
                        .setParameter("extended", "" + valuParamExtended + "")
                        .setParameter("v", "" + apiVersion + "");
                // создаем соединение
                HttpResponse response = HttpConnectionAgent.connectResponse(uriBuilder);
                // считываем номер ответа соединения
                Integer status = response.getStatusLine().getStatusCode();
                // если ответ 200 - т.е. ответ пришел, сервер дотупен то получаем контент
                if (status == 200) {
                    StringWriter content = new StringWriter();

                    try {
                        IOUtils.copy(response.getEntity().getContent(), content);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.exit(-1);
                    }
                    // записываем в строку ответ
                    String input = content.toString();
                    JsonParser parser = new JsonParser();
                    // парсим ответ и преобразыем в Джсон обьект с получение главного элемент response
                    JsonObject mainObject = parser.parse(input).getAsJsonObject().getAsJsonObject("response");
                    // получаем общее количестов изображений в паблике
                    countAllContentInPublic = mainObject.get("count").getAsInt();
                    // выводим на панель
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(typeOfResourceContent == "img"){
                                labelResultParsingImgURFound.setText("" + countAllContentInPublic + "");
                            }else{
                                labelResultParsingVideoURFound.setText("" + countAllContentInPublic + "");
                            }
                        }
                    });

                    // преобразуем обьект в массив и обращаемся к элементу items, где содержаться ссылки на размеры картинки
                    JsonArray pItem = mainObject.getAsJsonArray("items");
                    // в цикле обращаемся к каждому элементу items выдираем оттуда адрес изображения по фильтру
                    // - нам нужен максимальный "w"
                    for (int i = 0; i < pItem.size(); i++) {
                        JsonObject pItems = (JsonObject) pItem.getAsJsonArray().get(i);
                        //JsonElement stringFilesFromJsonObject = pItems.getAsJsonArray("files").get(0);
                        //String stringFilesFromJsonObject = pItems.getAsJsonObject("files").toString();
                        //System.out.println(stringFilesFromJsonObject.toString());
                        //JsonArray jsonArraypItems = (JsonArray) pItems.getAsJsonObject("files");
                        System.out.println(jsonArrayItem + " - " );
                        JsonObject pSizes2 =  pItems.getAsJsonObject("" + jsonArrayItem + "");
                        JsonPrimitive arrayPSizes2 = pSizes2.getAsJsonPrimitive("mp4_360");
                        System.out.println(arrayPSizes2.toString());

                        JsonArray pSizes =  pItems.getAsJsonArray("" + jsonArrayItem + "");

                        // из каждого sizes фильтруем по типу "x"(604x403) и пишем в массив
                        for (int j = 0; j < pSizes.size(); j++) {
                            JsonObject pWeightSizes = (JsonObject) pSizes.getAsJsonArray().get(j);

                            String needlePWeightSize = pWeightSizes.get("" + jsonObjectOfpWeightSizes + "").toString();
                            if (needlePWeightSize.equals("\"" + foundNeedlePWeightSizes[0] + "\"") || needlePWeightSize.equals("\"" + foundNeedlePWeightSizes[1] + "\"")) {
                                stringSrcImages = stringSrcImages + pWeightSizes.get("src").toString() + ";";
                                counter++;
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        labelResultParsingImgURLNow.setText(""+counter+"");
                                        progressbarImgParsing.progressProperty().setValue(counter*1.0/countAllContentInPublic);
                                    }
                                });
                                break;
                            }
                        }
                    }
                    // если колво картинок в паблике равно количеству спарсенных
                    // то делаем видимым галочку что все спарсено
                    if(counter == countAllContentInPublic){
                        makeLabelFinishedParsingVisible();
                        try{
                            Thread.sleep(100);
                          }catch (InterruptedException ex){
                            Thread.currentThread().interrupt();
                        }
                    }

                    // если количество спарсенных картинок равно 1000 или 200 видео то логично предположить что их там больше
                    // а значит добавляем еще один проход на 1000 или 200 в зависимосит от того что парсим
                    if (counter >= offsetOfParsingCount) {
                        offsetOfParsing = offsetOfParsing + offsetOfParsingCount;
                    } else {
                        counter = 0;
                    }
                    // записываем в бд в таблицу спарсенные адреса
                    // картинок

                    user.setTypeOfContent(typeOfContent);
                    user.setImgURLs(stringSrcImages);
                    user.setPublicURL(groupDomain);
                    user.setLogin(Constant.ADMIN_LOGIN);
                    dbHandler.insertNewDataInDB(user);
                    stringSrcImages = "";
                }
        }
        // выводим общую сумму спарсенных изображений из всех групп за сессию
        // пишем в переменную чтобы вести суммарную статистику спарсенных изображений
        countAllImgOfParsingSession = countAllImgOfParsingSession + countAllContentInPublic;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                labelResultParsingImgURLSum.setText("" + countAllImgOfParsingSession + "");
            }
        });
    }
}
