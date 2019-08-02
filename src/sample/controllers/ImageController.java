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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.cj.xdevapi.Result;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    int counter, offsetSrcImages, countAllImgInPublic = 0;
    private int rev;
    private String checkGroupDomain, groupDomain, typeOfContent;

///-----------------------------------------------//


    @FXML
    private AnchorPane paneVk;

    @FXML
    private Accordion accordionVk;

    @FXML
    private TitledPane accordionVkImage;

    @FXML
    private Button buttonStartImgParse;

    @FXML
    private TextField inputUrlOtherPublicImg;

    @FXML
    private RadioButton radioParsImgOld;

    @FXML
    private RadioButton radioParsImgNew;

    @FXML
    private RadioButton radioImgFun;

    @FXML
    private RadioButton radioImgAdult;

    @FXML
    private RadioButton radioDownloadImgFromOwn;

    @FXML
    private RadioButton radioDownloadImgFromOther;

    @FXML
    private Label labelPreResultParsing;

    @FXML
    private Label labelResultParsing;

    @FXML
    private Label labelAlert;

    @FXML
    private TitledPane accordionVkGif;

    @FXML
    private Button buttonStartGifParse;

    @FXML
    private TextField inputUrlOtherPublicGif;

    @FXML
    private RadioButton radioParsGifOld;

    @FXML
    private RadioButton radioParsGifNew;

    @FXML
    private RadioButton radioGifFun;

    @FXML
    private RadioButton radioGifAdult;

    @FXML
    private RadioButton radioDowloadGifFromOwn;

    @FXML
    private RadioButton radioDowloadGifFromOther;

    @FXML
    private Button buttonStopParsingGif;

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
    private ImageView imgParsinEndOk;

    @FXML
    void buttonClearTableField(ActionEvent event) {

    }

    @FXML
    void buttonStartGifParse(ActionEvent event) {

    }

    @FXML
    void buttonStartImgParse(ActionEvent event) {
    }

    @FXML
    void buttonStopParsingImg(ActionEvent event) {
        //stopParsing = 1;

        //Runnable parsAndDownloadImages = new VKParserImages('0', "", "", stopParsing);
        //new Thread(parsAndDownloadImages).interrupt();
        //parsAndDownloadImages.vkParserImages();
    }

    @FXML
    void buttonStopParsingGif(ActionEvent event) {

    }

    @FXML
    void inputUrlOtherPublicGif(ActionEvent event) {

    }

    @FXML
    void inputUrlOtherPublicImg(ActionEvent event) {

    }

    @FXML
    void radioDowloadGifFromOther(ActionEvent event) {
        radioDowloadGifFromOwn.setSelected(false);
        inputUrlOtherPublicGif.setDisable(false);
    }

    @FXML
    void radioDowloadGifFromOwn(ActionEvent event) {
        radioDowloadGifFromOther.setSelected(false);
        inputUrlOtherPublicGif.setDisable(false);
    }

    @FXML
    void radioDownloadImgFromOther(ActionEvent event) {
        radioDownloadImgFromOwn.setSelected(false);
        inputUrlOtherPublicImg.setDisable(false);
        checkRadiosForStartParsingButton();
    }

    @FXML
    void radioDownloadImgFromOwn(ActionEvent event) {
        inputUrlOtherPublicImg.setDisable(true);
        radioDownloadImgFromOther.setSelected(false);
        checkRadiosForStartParsingButton();
    }

    @FXML
    void radioGifAdult(ActionEvent event) {
        radioGifFun.setSelected(false);
        inputUrlOtherPublicGif.setDisable(false);
    }

    @FXML
    void radioGifFun(ActionEvent event) {
        radioGifAdult.setSelected(false);
        inputUrlOtherPublicGif.setDisable(false);
    }

    @FXML
    void radioImgAdult(ActionEvent event) {
        radioImgFun.setSelected(false);
        checkRadiosForStartParsingButton();
    }

    @FXML
    void radioImgFun(ActionEvent event) {
        radioImgAdult.setSelected(false);
        checkRadiosForStartParsingButton();
    }

    @FXML
    void radioParsGifNew(ActionEvent event) {
        radioParsGifOld.setSelected(false);
        buttonStartGifParse.setDisable(false);
    }

    @FXML
    void radioParsGifOld(ActionEvent event) {
        radioParsGifNew.setSelected(false);
        buttonStartGifParse.setDisable(false);
    }

    @FXML
    void radioParsImgNew(ActionEvent event) {
        radioParsImgOld.setSelected(false);
        checkRadiosForStartParsingButton();
    }

    @FXML
    void radioParsImgOld(ActionEvent event) {
        radioParsImgNew.setSelected(false);
        checkRadiosForStartParsingButton();
    }


    @FXML
    void initialize(){

        radioParsImgNew.setDisable(true);
        radioParsImgOld.setDisable(true);
        imgParsinEndOk.setVisible(false);

        accordionVk.setExpandedPane(accordionVkImage);
        makeLabelResultParsingVisible();


        buttonStartGifParse.setOnAction(event ->{
        });

        buttonStartGifParse.setOnAction(event ->{
        });

        buttonStartImgParse.setOnAction(event ->{

            counter         = 0;
            offsetSrcImages = 0;

            if(!radioDownloadImgFromOwn.isSelected() || !radioDowloadGifFromOwn.isSelected()) {groupDomain = "";}
            makeLabelResultParsingVisible();
            labelResultParsing.setText("0");
            buttonStartImgParse.setDisable(true);
            imgParsinEndOk.setVisible(false);

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
                t.setName("ParsCounter");
                t.setDaemon(true);
                t.start();
            //System.out.println(t.getState().toString());
        });
    }

    public void doParsePublicId() throws MalformedURLException {
        if (radioParsImgNew.isSelected()) {
            rev = 1;
        } else {
            rev = 0;
        }
        if (radioImgAdult.isSelected()) {
            typeOfContent = "adult";
        }
        if (radioImgFun.isSelected()) {
            typeOfContent = "fun";
        }
        if (radioImgAdult.isSelected() && radioDownloadImgFromOwn.isSelected()) {
            groupDomain = Constant.VK_ADULT_GROUP_DOMAIN;
        }

        if (radioImgFun.isSelected() && radioDownloadImgFromOwn.isSelected()) {
            groupDomain = Constant.VK_FUN_GROUP_DOMAIN;
        }

        if (radioDownloadImgFromOther.isSelected()) {
            URL inputOtherGroupDomainURL = null;
            String otherGroupDomainURL = inputUrlOtherPublicImg.getText().trim();
            if (otherGroupDomainURL != "") {
                String regexp = "vk.com";
                String regexp2 = "public";
                //String replace = "";
                Pattern pattern = Pattern.compile(regexp);
                Matcher matcher = pattern.matcher(otherGroupDomainURL);
                //System.out.println(matcher.find());
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



    public void checkRadiosForStartParsingButton(){

        if(radioDownloadImgFromOwn.isSelected() || radioDownloadImgFromOther.isSelected()){
            radioParsImgNew.setDisable(false);
            radioParsImgOld.setDisable(false);
        }else{
            radioParsImgOld.setDisable(true);
            radioParsImgNew.setDisable(true);
            radioParsImgNew.setSelected(false);
            radioParsImgOld.setSelected(false);
            buttonStartImgParse.setDisable(true);
        }


        if(radioParsImgOld.isSelected() || radioParsImgNew.isSelected()){
            buttonStartImgParse.setDisable(false);
        }else{
            buttonStartImgParse.setDisable(true);
        }

    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public static String removeWordClubAt(String s) {
        return s.substring(6);
    }


    public void makeLabelResultParsingUnvisible(){
        labelResultParsing.setVisible(false);
        labelPreResultParsing.setVisible(false);
        labelAlert.setVisible(true);
    }


    public void makeLabelResultParsingVisible(){
        labelResultParsing.setVisible(true);
        labelPreResultParsing.setVisible(true);
        labelAlert.setVisible(false);
    }

    public void doParseAndSave() throws MalformedURLException {
        String stringSrcImages = "";
        String stringSrcImagesAdult = "";
        String stringSrcImagesFun = "";
        // подключаем метод парсинга id паблика
        doParsePublicId();
        // если мы уже парсили эту группу то останавлдиваем поток и метод
        if(checkGroupDomain == groupDomain){
            makeLabelResultParsingUnvisible();
            try{
                //System.out.println("Thread stopped now2!");

                Thread.sleep(100);
                //System.out.println("Thread stopped now3!");

            }catch (InterruptedException ex){
                //System.out.println("Thread stopped now4!");
                Thread.currentThread().interrupt();
                //System.out.println("Thread stopped now5!");
            }
        }

            for (int cicle = 0; cicle <= counter; cicle += 1000) {
                //counter2 = counter;
                URIBuilder uriBuilder = new URIBuilder();
                // создаем и отправляем запрос к ВК апи - указываем какая группа, ключ доступа, отображать ли в ответе размер
                // сколько записей надо спарсить, откуда парсить(в данном случае из альбома стены группы), версия АПИ ВК
                uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/photos.get")
                        .setParameter("owner_id", "" + groupDomain + "")
                        .setParameter("access_token", VK_ACCESS_TOKEN)
                        .setParameter("photo_sizes", "1")
                        .setParameter("count", "1000")
                        .setParameter("album_id", "wall")
                        .setParameter("rev", "" + rev + "")
                        .setParameter("offset", "" + offsetSrcImages + "")
                        .setParameter("v", "5.73");
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
                    // получаем количестов изображений
                    //JsonArray mainArrayCount = mainObject.getAsJsonArray("count");
                    countAllImgInPublic = mainObject.get("count").getAsInt();
                    // преобразуем обьект в массив и обращаемся к элементу items, где содержаться ссылки на размеры картинки
                    JsonArray pItem = mainObject.getAsJsonArray("items");
                    // в цикле обращаемся к каждому элементу items выдираем оттуда адрес изображения по фильтру
                    // - нам нужен максимальный "w"
                    for (int i = 0; i < pItem.size(); i++) {
                        JsonObject pItems = (JsonObject) pItem.getAsJsonArray().get(i);
                        JsonArray pSizes = pItems.getAsJsonArray("sizes");
                        // int index = Array.asList(pSizes).indexOf("\"w\"");
                        // из каждого sizes фильтруем по типу "x"(604x403) и пишем в массив
                        for (int j = 0; j < pSizes.size(); j++) {

                            JsonObject pWeightSizes = (JsonObject) pSizes.getAsJsonArray().get(j);
                            String switchCasePWeightSize = pWeightSizes.get("type").toString();
                            if (switchCasePWeightSize.equals("\"x\"") || switchCasePWeightSize.equals("\"w\"")) {
                                //System.out.println("literaTypeSize - " + switchCasePWeightSize + " : " +
                                //         "" + pWeightSizes.get("src").toString());
                               // stringSrcImages = pWeightSizes.get("src").toString();
                                stringSrcImages = stringSrcImages + pWeightSizes.get("src").toString() + ";";
                                counter++;
                                //counterCount = counter;
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        labelResultParsing.setText(""+counter+"");
                                    }
                                });
                                break;
                            }
                        }
                    }
                    // если колво картинок в паблике равно количеству спарсенных
                    // то делаем видимым галочку что все спарсено
                    if(counter == countAllImgInPublic){
                        imgParsinEndOk.setVisible(true);

                        //System.out.println("Thread stopped now1!");
                        try{
                            //System.out.println("Thread stopped now2!");

                            Thread.sleep(100);
                           //System.out.println("Thread stopped now3!");

                        }catch (InterruptedException ex){
                            //System.out.println("Thread stopped now4!");
                            Thread.currentThread().interrupt();
                           //System.out.println("Thread stopped now5!");
                        }
                    }else{
                        makeLabelResultParsingVisible();
                    }

                    // если количество спарсенных картинок равно 1000 то логично предположить что их там больше
                    // а значит добавляем еще один проход на 1000
                    if (counter >= 1000) {
                        offsetSrcImages = offsetSrcImages + 1000;
                        System.out.println("Images on the Wall - " + counter);
                        //imageController.changeJLabel(""+counter+"");
                        //Platform.runLater(() -> imageController.labelResultParsing.setText(String.valueOf(counter)));
                    } else {
                        System.out.println("Images on the Wall - " + counter);
                        //imageController.changeJLabel(""+counter+"");
                        counter = 0;
                    }
                     //System.out.println(stringSrcImages);
                    // записываем в бд в таблицу спарсенные адреса
                    // картинок

                    if (typeOfContent == "adult") {
                        stringSrcImagesAdult = stringSrcImages;
                    }
                    if (typeOfContent == "fun") {
                        stringSrcImagesFun = stringSrcImages;
                    }

                    user.setImgFun(stringSrcImagesFun);
                    user.setImgAdult(stringSrcImagesAdult);
                    user.setVideoGifFun("");
                    user.setVideoGifAdult("");
                    user.setLogin(Constant.ADMIN_LOGIN);
                    dbHandler.insertNewDataInDB(user);
                    //System.out.println(stringSrcImages);
                    stringSrcImages = "";
                    stringSrcImagesAdult = "";
                    stringSrcImagesFun = "";
                }
            }
            // создаем переменную для проверки чтьо бы если группа уже парсилась то мы выкинули предупреждлеие
            checkGroupDomain = groupDomain;
    }


}
