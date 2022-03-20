package com.capillary.crawler.service;

import com.capillary.crawler.model.Link;
import com.capillary.crawler.model.LinkId;
import com.capillary.crawler.model.Source;
import com.capillary.crawler.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SourceService {
    @Autowired
    SourceRepository sourceRepository;

    public void saveLinks(String url, ArrayList<String> urlList, ArrayList<Integer> levelList){
        Optional<Source> ops = sourceRepository.getSourceByUrl(url);
        Source source;
        if( ops.isEmpty()){
            source = new Source();
            source.setSource(url);
        }
        else{
            source = ops.get();
        }
        System.out.println("source set");
        ArrayList<Link> links = new ArrayList<>();
        for ( int i =0; i < urlList.size() ; i++){
            String currentUrl = urlList.get(i);
            Link link = new Link();
            LinkId linkId = new LinkId();
            linkId.setSource(source);
            linkId.setLink(currentUrl);
            link.setLinkId(linkId);
            Integer level = 0;
            if ( levelList!= null){
                level = levelList.get(i);
            }
            link.setLevel(level);
            links.add(link);
        }
        source.setLinks(links);
        System.out.println("Data set");
        sourceRepository.save(source);
        System.out.println("returning");
    }


    public void savePhoneNumbers(String url, ArrayList<String> phoneNumberList, ArrayList<Integer> levelList){
        Optional<Source> ops = sourceRepository.getSourceByUrl(url);
        Source source;
        if( ops.isEmpty()){
            source = new Source();
            source.setSource(url);
        }
        else{
            source = ops.get();
        }
        ArrayList<Link> links = new ArrayList<>();
        for ( int i =0; i < phoneNumberList.size() ; i++){
            String currentUrl = phoneNumberList.get(i);
            Link link = new Link();
            LinkId linkId = new LinkId();
            linkId.setSource(source);
            linkId.setLink(currentUrl);
            link.setLinkId(linkId);
            Integer level = 0;
            if ( levelList!= null){
                level = levelList.get(i);
            }
            link.setLevel(level);
            links.add(link);
        }
        source.setLinks(links);
        System.out.println("Phone Data set");
        sourceRepository.save(source);
    }

}
