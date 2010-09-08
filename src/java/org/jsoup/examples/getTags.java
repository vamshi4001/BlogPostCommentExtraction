/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jsoup.examples;

/**
 *
 * @author vamsi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



//import org.apache.commons.lang.Validate;
import java.io.File;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.net.URL;
import java.io.IOException;
import org.jsoup.nodes.Node;
/**
 *
 * @author Vamshi Krishna
 */

import java.net.Authenticator;
import java.net.PasswordAuthentication;

 class ProxyAuthenticator extends Authenticator {
     private String user, password;
     public ProxyAuthenticator(String user, String password) {
         this.user = user;
         this.password = password;
     }
     protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(user, password.toCharArray());
     }
 }


public class getTags {
    public static Elements getPostBlogspot(Document doc)
    {
        Elements postbody=doc.select("div.post-body");
        return postbody;
    }
    public static boolean isBlogger(String url, Document doc)
    {
        if(url.indexOf("blogspot.com")!=-1)
        {
            System.out.println("url has .blogspot.com");
            return true;
        }
//        else {
//            Elements met= doc.select("meta");
//                for (Element link : met) {
//                    if(link.attr("content").toString().equals("blogger"))
//                    {
//                        System.out.println("url doesnot has .blogspot.com but identified from meta tag");
//                        return true;
//                    }
//                }
//        }
        return false;
    }
    public static float getPbyTRatio(String text)
    {        
        float count=text.replaceAll("[^,|.|\"|'|`|[|]|(|)|*|&|@|$|/|\\|;|:|<|>|?|{|}|-|_]","").length();
        return count/text.length();
    }
    public static int wordCount(String text)
    {
        String[] arr=text.split(" ");
        int count = 0,length;
        for(int i = 0; i <arr.length;i++)
        {
            if(arr[i].equals(" "))
            {            }
            else
            {  count++; }
        }
        length=count;
        return count;
    }
    public static float getLinkstoWordsRatio(Element tag)
    {
        String linktext=tag.getElementsByTag("a").text();
        String text=tag.text();
        int linkwords,words;
        linkwords=wordCount(linktext);
        words=wordCount(text);
        return linkwords/words;
    }
    public static Element getLargeSibling(Element tag)
    {
        Element mainText=tag;//needs to be changed.
        while(true)
        {
             if(tag.nodeName().equals("div"))
             {                
                if(tag.text().length()>40&&getPbyTRatio(tag.text())>0.015)
                {
                    System.out.println("Accepted "+tag.className()+" has chars="+tag.text().length()+" and punctuations are "+getPbyTRatio(tag.text()));
                    System.out.println("Comparing "+tag.className()+" "+getLinkstoWordsRatio(tag)+" and "+mainText.className()+" "+getLinkstoWordsRatio(tag));
                    if(getLinkstoWordsRatio(tag)<getLinkstoWordsRatio(mainText))
                    {
                        System.out.println("LW ratio of "+tag.className()+" is less than "+mainText.className());
                        mainText=tag;
                    }
                    else
                    {
                        if(getLinkstoWordsRatio(tag)==0.0&&getLinkstoWordsRatio(mainText)==0.0)
                        {
                            if(tag.text().length()>mainText.text().length())
                            {
                                mainText=tag;
                            }
                        }
                    }
                    if(tag.nextElementSibling()==null)
                        break;
                    else
                        tag=tag.nextElementSibling();
                }
                else
                {
                    System.out.println("Discarded: "+tag.className()+" has chars="+tag.text().length()+" and punctuations are "+getPbyTRatio(tag.text()));
                    if(tag.nextElementSibling()==null)
                        break;
                    else
                        tag=tag.nextElementSibling();
                }
             }
             else
             {
                if(tag.nextElementSibling()==null)
                    break;
                else
                    tag=tag.nextElementSibling();
             }
        }
        return mainText;
    }
    public static boolean checkSumCondition(Element tag)
    {
        int outlength=tag.text().length();
        int sum=0;
        System.out.println("class: "+tag.className()+" "+tag.ownText().length());
        Elements children=tag.children();
        for(Element child : children){
            if(child.tagName().indexOf("div")!=-1)
            {
                System.out.println(sum+" "+child.className()+" "+child.text().length());
                sum=sum+child.text().length();
            }
            
        }
        if(outlength>2*sum)//Needs to be decided whether its 2 or more
        {
            return false;
        }
        else
            return true;
    }
    public static String perform(String link)throws Exception
    {
        URL url=new URL(link);
        Document doc = Jsoup.parse(url, 3*1000);
        Elements tags = doc.getElementsByTag("div");
        String post="";
        Element large;
        
                Element tag;
                //Level 0 - 1st run of the loop
                tag=tags.first();
                large=getLargeSibling(tag);
                System.out.println(large.className());

                while(checkSumCondition(large)){
                    tag=large.child(0);
                    large=getLargeSibling(tag);
                    System.out.println("Result for this iteration is "+large.className()+" and "+large.id());
                }
                System.out.println(large.className()+" or "+large.id());
                System.out.println(large.text());
                return large.text();
            
    }
    public static String getComments(String weblink)throws Exception
    {
        URL url=new URL(weblink);
        Document doc = Jsoup.parse(url, 3*1000);
        Elements dls = doc.select("div#Blog1_comments-block-wrapper");
        int i=0;
        String comments="1. ";
        for (Element link : dls) {
            while(link.select("p").get(i).text() == null ? link.select("p").last().text() != null : !link.select("p").get(i).text().equals(link.select("p").last().text())){
                int k=i+2;                
                comments=comments+link.select("p").get(i).text()+"\n\n"+k+". ";
                i++;
            }
            comments=comments+link.select("p").last().text();
        }
        return comments;
    }
    public static void main(String[] args) throws IOException {

        //Authenticator.setDefault(new ProxyAuthenticator("iiitb", "iiitb"));
        System.setProperty("http.proxyHost", "192.168.3.254");
        System.setProperty("http.proxyPort", "8080");

        URL url = new URL("http://blog.flexgeek.in/2010/03/my-schedule-for-the-upcoming-adobe-ug-tour/");
        Document doc = Jsoup.parse(url, 3*1000);        
        Elements tags = doc.getElementsByTag("div");
        Element tag;
//        Element large=doc.select("div.post-outer").first();
//        boolean u=checkSumCondition(large);
//        System.out.println(u);
        //Level 0 - 1st run of the loop
        tag=tags.first();
        Element large=getLargeSibling(tag);
        System.out.println(large.className());

        while(checkSumCondition(large)){
            tag=large.child(0);
            large=getLargeSibling(tag);
            System.out.println("Result for this iteration is "+large.className()+" and "+large.id());
        }
        System.out.println(large.className()+" or "+large.id());
        System.out.println(large.text());
    }
}