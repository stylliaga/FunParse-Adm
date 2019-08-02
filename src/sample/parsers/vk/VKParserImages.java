package sample.parsers.vk;


import com.google.gson.*;

import java.awt.*;
import java.io.IOException;

import javafx.application.Platform;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import java.io.StringWriter;

import sample.Constant;
import sample.DatabaseHandler;
import sample.controllers.ImageController;
import sample.controllers.User;

import javax.swing.*;

import static sample.Constant.*;


public class VKParserImages  {
//public class VKParserImages implements Runnable {
    DatabaseHandler dbHandler = new DatabaseHandler();
    User user = new User();


    public volatile boolean stopAll = false;

    int counter         = 0;
    int offsetSrcImages = 0;
    private int rev;
    private String groupDomain, typeOfContent;
    private ImageController imageController;


    public VKParserImages() {}

    public VKParserImages(int rev, String groupDomain, String typeOfContent) {
        this.rev            = rev;
        this.groupDomain    = groupDomain;
        this.typeOfContent  = typeOfContent;
    }
    public void setStopParseThread(){
        stopAll = true;
    }


}


/*
* <a onclick="return showPhoto('-105702650_457255248', 'wall-105702650_38714', {&quot;temp&quot;:{&quot;base&quot;:&quot;https://pp.userapi.com/&quot;,&quot;x_&quot;:[&quot;c848620/v848620251/1d9c0f/CtcZIlrShaM&quot;,416,604],&quot;y_&quot;:[&quot;c848620/v848620251/1d9c10/L7LT67k072E&quot;,534,776]},queue:1}, event)" style="width: 351px; height: 510px;background-image: url(https://pp.userapi.com/c848620/v848620251/1d9c0f/CtcZIlrShaM.jpg);" class="page_post_thumb_wrap image_cover  page_post_thumb_last_column page_post_thumb_last_row"></a>
https://vh273.spaceweb.ru/phpMyAdmin/sql.php?server=1&db=proficeram_fnprs&table=users&pos=0

* */
