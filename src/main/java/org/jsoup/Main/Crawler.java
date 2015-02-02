package org.jsoup.Main;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by akeske
 */
public class Crawler {

    private final Task task;
    private Document doc = null;
    private static final int timeout = 10 * 1000;
    private static final String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
    private ArrayList<String> folllowersUrl = new ArrayList<String>();
    private String prefix = "https://soundcloud.com";
    private String postfix = "/followers";
    private String url;
    private int total;
    private int maxDepth;

    public Crawler(String url, int depth, Task task, int maxDepth){

        this.maxDepth = maxDepth;
        total = 1;
        for(int i=0; i<maxDepth; i++)
            total *= 10;
        this.task = task;
        this.url = url;
        depth++;
        double pro = (double) Main.users.size() / (double) total *100;
        task.publicSetProgress( (int) pro ) ;
        execCrawler(url, depth);
    }

    public void execCrawler(String url, int depth){
        Main.counter++;
        Boolean success = false;
        int tries = 0;
        for(int i=0; i<depth-1; i++){
            System.out.print("\t");
        }
        System.out.println(depth + " --> " + url);
        do{
            try {
                doc = Jsoup.connect(url).ignoreContentType(true).userAgent(userAgent).timeout(timeout).followRedirects(true).get();
                success = true;
            } catch (IOException e) {
                System.out.println("Timeout occured");
                tries++;
            }
        }while (success==false && tries<6);

        if(success == true) {
            // take the urls from followers
            // and use them to find more information
            Elements noscriptElements = doc.getElementsByTag("noscript");
            for (Element el1 : noscriptElements) {
                Elements articleElements = el1.getElementsByTag("article");
                for (Element el2 : articleElements) {
                    for (Element el3 : el2.getElementsByTag("section")) {
                        for (Element el4 : el3.getElementsByTag("article")) {
                            Elements el5 = el4.select("h1").select("a");
                            //    String text1=el5.get(0).text();
                            folllowersUrl.add(el5.attr("href"));
                        }
                    }
                }
            }

            // find the json and use it to parse the info from user
            Elements scriptElements = doc.getElementsByTag("script");
            String js = null;
            for (Element element : scriptElements) {
                for (DataNode node : element.dataNodes()) {
                    js = node.getWholeData();
                }
            }
            // clean json from rubbish
            js = js.substring(45, js.length());
            js = js.substring(0, js.length() - 184);

            JSONObject obj = new JSONObject(js);
            JSONArray arr = obj.getJSONArray("78");
            for (int i = 0; i < arr.length(); i++) {
                UserInfo user = new UserInfo();
                JSONObject item = arr.getJSONObject(i);

                user.setId(item.getInt("id"));
                if (item.isNull("country")) {
                    user.setCountry(null);
                } else {
                    user.setCountry((String) item.get("country"));
                }
                if (item.isNull("city")) {
                    user.setCity(null);
                } else {
                    user.setCity((String) item.get("city"));
                }
                if (item.isNull("description")) {
                    user.setDescription(null);
                } else {
                    user.setDescription((String) item.get("description"));
                }
                user.setTrack_count(item.getInt("track_count"));
                user.setPublic_favorites_count(item.getInt("public_favorites_count"));
                user.setFollowers_count(item.getInt("followers_count"));
                user.setFollowings_count(item.getInt("followings_count"));
                user.setComments_count(item.getInt("comments_count"));
                user.setFull_name((String) item.get("full_name"));
                user.setUsername((String) item.get("username"));
                user.setLikes_count(item.getInt("likes_count"));

                Boolean exist = false;
                for (UserInfo u : Main.users) {
                    if (u.getId() == user.getId()) {
                        exist = true;
                    }
                }
                if (exist == false) {
                    Main.users.add(user);
                    Main.db.insert(user);
                }
            }

            for (String u : folllowersUrl) {
                System.out.print("\t");
                for (int i = 0; i < depth - 1; i++) {
                    System.out.print("\t");
                }
                System.out.println(u);
            }
            if (depth <= maxDepth) {
                for (String u : folllowersUrl) {
                    new Crawler(prefix + u + postfix, depth, task, maxDepth);
                }
            }
        }
    }
}
