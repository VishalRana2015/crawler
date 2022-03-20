package com.capillary.crawler.controller;

import com.capillary.crawler.dto.InputDataDto;
import com.capillary.crawler.exception.UrlReadingException;
import com.capillary.crawler.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class ScrapController {
    @Autowired
    CrawlerService crawlerService;

    @GetMapping("/health")
    public ResponseEntity<String> isWorking( ){
        return new ResponseEntity<>("WEbsite is working", HttpStatus.OK);
    }

    @PostMapping("/getHtml")
    public ResponseEntity<String> getHtml(@RequestBody @Valid InputDataDto inputDataDto) throws UrlReadingException {
        String data = crawlerService.crawl(inputDataDto.getUrl());
        return new ResponseEntity<String>(data, HttpStatus.OK);
    }

    @PostMapping("/getAllLinks")
    public ResponseEntity<ArrayList<String>> getAllLinks(@RequestBody @Valid InputDataDto inputDataDto) throws  UrlReadingException{
        ArrayList<String> linksDto = crawlerService.getAllLinks(inputDataDto.getUrl());
        return new ResponseEntity<>(linksDto, HttpStatus.OK);
    }

    @PostMapping("/getAllLinksR")
    public ResponseEntity<ArrayList<String>> getAllLinksR(@RequestBody @Valid InputDataDto inputDataDto) throws UrlReadingException{
        System.out.println("in getAllLinksR");
        ArrayList<String> linksDto = crawlerService.getAllLinksR(inputDataDto.getUrl());
        return new ResponseEntity<>(linksDto, HttpStatus.OK);
    }

    @PostMapping("/getAllPhoneNumbers")
    public ResponseEntity<ArrayList<String>> getAllPhoneNumbers(@RequestBody @Valid InputDataDto inputDataDto) throws UrlReadingException{
        ArrayList<String> list = crawlerService.getAllPhoneNumbers(inputDataDto.getUrl());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/getAllPhoneNumbersR")
    public ResponseEntity<ArrayList<String>> getAllPhoneNumbersRecursively(@RequestBody @Valid InputDataDto inputDataDto) throws UrlReadingException{
        ArrayList<String> list = crawlerService.getAllPhoneNumbersR(inputDataDto.getUrl());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
