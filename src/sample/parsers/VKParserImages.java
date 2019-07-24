package sample.parsers;


import com.google.gson.*;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import java.io.StringWriter;
import java.sql.Array;

import static sample.Constant.VK_ACCESS_TOKEN;
import static sample.Constant.VK_GROUP_DOMAIN;


public class VKParserImages {
    String[] arraySrcImages = new String[5000];
    int counter = 0;
    int offsetSrcImages = 0;
    public void vkParserImages() {
        URIBuilder uriBuilder = new URIBuilder();
        // создаем и отправляем запрос к ВК апи - указываем какая группа, ключ доступа, отображать ли в ответе размер
        // сколько записей надо спарсить, откуда парсить(в данном случае из альбома стены группы), версия АПИ ВК
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/photos.get")
                .setParameter("owner_id", VK_GROUP_DOMAIN)
                .setParameter("access_token", VK_ACCESS_TOKEN)
                .setParameter("photo_sizes", "1")
                .setParameter("count", "1000")
                .setParameter("album_id", "wall")
                .setParameter("rev", "0")
               .setParameter("offset", ""+offsetSrcImages+"")
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
                    // преобразуем обьект в массив и обращаемся к элементу items, где содержаться ссылки на размеры картинки
                    JsonArray pItem =  mainObject.getAsJsonArray("items");
                    // в цикле обращаемся к каждому элементу items выдираем оттуда адрес изображения по фильтру
                    // - нам нужен максимальный "w"
                    for (int i = 0; i < pItem.size(); i++){
                        JsonObject pItems = (JsonObject) pItem.getAsJsonArray().get(i);
                        JsonArray pSizes  =   pItems.getAsJsonArray("sizes");
                       // int index = Array.asList(pSizes).indexOf("\"w\"");
                        // из каждого sizes фильтруем по типу "x"(604x403) и пишем в массив
                        for(int j = 0; j < pSizes.size(); j++) {

                            JsonObject pWeightSizes = (JsonObject) pSizes.getAsJsonArray().get(j);
                            String switchCasePWeightSize = pWeightSizes.get("type").toString();
                            if(switchCasePWeightSize.equals("\"x\"")){
                                System.out.println("literaTypeSize - " + switchCasePWeightSize + " : " +
                                        "" + pWeightSizes.get("src").toString());
                                arraySrcImages[j] =  pWeightSizes.get("src").toString()+"";
                                counter++;
                                break;
                            }
                        }
                    }
                    // если количество спарсенных картинок равно 1000 то логично предположить что их там больше
                    // а значит добавляем еще один проход на 1000
                    if(counter >= 1000){
                        offsetSrcImages = offsetSrcImages + 1000;
                        System.out.println("Images on the Wall - " + counter);
                    }else{
                        System.out.println("Images on the Wall - " + counter);
                        counter = 0;
                    }
                }
    }

}


/*
* <a onclick="return showPhoto('-105702650_457255248', 'wall-105702650_38714', {&quot;temp&quot;:{&quot;base&quot;:&quot;https://pp.userapi.com/&quot;,&quot;x_&quot;:[&quot;c848620/v848620251/1d9c0f/CtcZIlrShaM&quot;,416,604],&quot;y_&quot;:[&quot;c848620/v848620251/1d9c10/L7LT67k072E&quot;,534,776]},queue:1}, event)" style="width: 351px; height: 510px;background-image: url(https://pp.userapi.com/c848620/v848620251/1d9c0f/CtcZIlrShaM.jpg);" class="page_post_thumb_wrap image_cover  page_post_thumb_last_column page_post_thumb_last_row"></a>
* */
