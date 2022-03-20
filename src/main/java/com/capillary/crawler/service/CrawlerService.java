package com.capillary.crawler.service;

import com.capillary.crawler.exception.UrlReadingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

@Service
public class CrawlerService {
    @Autowired
    SourceService sourceService;

    public static Integer DEPTH = 1;

    public String crawl( String url) throws UrlReadingException {
        Document doc;
        try{
            doc = Jsoup.connect(url).get();
        }
        catch ( IOException exp){
            throw new UrlReadingException( exp.getMessage() );
        }
        return doc.html();
    }

    // get all the links and saves them into the database.
    public ArrayList<String> getAllLinks(String url ) throws UrlReadingException{
        ArrayList<String> urlList;
        try{
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("a[href]");
            urlList = new ArrayList<String>();
            urlList.add(url);
            for (Element ele : elements){
                urlList.add(ele.attr("abs:href"));
            }
        }
        catch (IOException exp){
            throw new UrlReadingException(exp.getMessage());
        }
        sourceService.saveLinks(url, urlList, null);
        return urlList;
    }

    public ArrayList<String> getAllLinksR(String url ) throws UrlReadingException{
        ArrayList<String> urlList;
        ArrayList<Integer> levelList;
        try{
            Stack<String> stack = new Stack<>();
            Stack<Integer> levelStack = new Stack<>();
            stack.push(url);
            levelStack.push(0);
            urlList = new ArrayList<String>();
            levelList = new ArrayList<>();
            while( !stack.isEmpty()){
                String currentUrl = stack.pop();
                Integer currentLevel = levelStack.pop();
                urlList.add(currentUrl);
                levelList.add(currentLevel);
                if ( currentLevel >= DEPTH) // just for debugging
                    continue;
                Document doc = Jsoup.connect(currentUrl).get();
                Elements elements = doc.select("a[href]");
                for ( Element ele : elements){
                   String href= ele.attr("abs:href");
                   stack.push(href);
                   levelStack.push(currentLevel + 1);
                }
            }
        }
        catch (IOException exp){
            throw new UrlReadingException(exp.getMessage());
        }
        sourceService.saveLinks(url, urlList, levelList);
        return urlList;
    }

    public ArrayList<String> getAllPhoneNumbers(String url ) throws UrlReadingException{
        ArrayList<String> phoneNumbers;
        try{
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("a[href*=tel]");
            phoneNumbers = new ArrayList<>();
            for (Element ele : elements){
                phoneNumbers.add(ele.attr("href"));
            }
        }
        catch (IOException exp){
            throw new UrlReadingException(exp.getMessage());
        }
        sourceService.savePhoneNumbers(url, phoneNumbers, null);
        return phoneNumbers;
    }

    public ArrayList<String> getAllPhoneNumbersR(String url) throws UrlReadingException{
        ArrayList<String> phoneNumbers;
        ArrayList<Integer> levelList;
        try{
            phoneNumbers = new ArrayList<>();
            levelList = new ArrayList<>();
            Stack<String> linkStack = new Stack();
            Stack<Integer> levelStack = new Stack();
            linkStack.add(url);
            levelStack.push(0);
            while( !linkStack.isEmpty()){
                String currentUrl = linkStack.pop();
                Integer currentLevel = levelStack.pop();
                if ( currentLevel >= DEPTH)
                    continue;
                Document doc = Jsoup.connect(url).get();
                Elements eles = doc.select("a[href*=tel]");
                for ( Element ele : eles){
                    phoneNumbers.add(ele.attr("href"));
                    levelList.add(currentLevel);
                }
                eles= doc.select("a[href]");
                for ( Element ele : eles){
                    linkStack.push(ele.attr("abs:href"));
                    levelStack.push(currentLevel + 1);
                }
            }

        }
        catch (Exception exp){
            throw new UrlReadingException(exp.getMessage());
        }
        sourceService.savePhoneNumbers(url,phoneNumbers,levelList);
        return phoneNumbers;
    }

}
