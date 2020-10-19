package com.braincolla.ignite.fileread;

import com.braincolla.ignite.vo.JoaraBook;
import com.braincolla.ignite.vo.RecommendData;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JsonConvert {

  public static void main(String[] args) throws IOException, URISyntaxException {
    String bookMedataFile = "book_meta_data.jsonl";
    String recommendFile = "recommend_data_100_5m.jsonl";
//    String recommendFile = "test.jsonl";

    JsonConvert jsonConvert = new JsonConvert();
    long start = System.currentTimeMillis(); //시작하는 시점 계산
    jsonConvert.makeRecommendDataMapFromJson(recommendFile);
//    jsonConvert.makeJoaraBookMetaDataMapFromJson(bookMedataFile);
    long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
    System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
  }

  public List<JoaraBook> makeJoaraBookMetaDataMapFromJson(String fileName)
    throws IOException, URISyntaxException {
    System.out.println("FileName : " + fileName);
    FileResourcesUtils app = new FileResourcesUtils();
//    File file = app.getFileFromResource(fileName);
    InputStream file = app.getFileFromResourceAsStream(fileName);
//    BufferedReader inFile = new BufferedReader(new FileReader(file));
    BufferedReader inFile = new BufferedReader(new InputStreamReader(file));
    Gson gson = new Gson();
    List<JoaraBook> joaraBookList = new ArrayList<>();
    String sLine = null;
    long start = System.currentTimeMillis(); // start time
    while ((sLine = inFile.readLine()) != null) {
      JoaraBook joaraBook = gson.fromJson(sLine, JoaraBook.class);
      joaraBookList.add(joaraBook);
//      System.out.println(joaraBook.toString());
    }
    long end = System.currentTimeMillis(); // end time

//    try (Stream<String> lines = Files.lines(Paths.get(String.valueOf(file)), Charset.defaultCharset())) {
//      lines.forEachOrdered(line -> joaraBookList.add(gson.fromJson(line, JoaraBook.class)));
//    }
    System.out.println(">> Joara Book Meta data size : " + joaraBookList.size());
    System.out.println(">> Joara Book Convert Total Time : " + (end - start) / 1000.0 + "초");
    return joaraBookList;
  }

  public List<RecommendData> makeRecommendDataMapFromJson(String fileName)
    throws IOException, URISyntaxException {
    System.out.println("FileName : " + fileName);
    FileResourcesUtils app = new FileResourcesUtils();
//    File file = app.getFileFromResource(fileName);
    InputStream file = app.getFileFromResourceAsStream(fileName);
    BufferedReader inFile = new BufferedReader(new InputStreamReader(file));
//    JsonReader jsonReader = new JsonReader(new FileReader(file));
//    BufferedReader inFile = new BufferedReader(new FileInputStreamReader(file));

    Gson gson = new Gson();
    List<RecommendData> recommendDataList = new ArrayList<>();
    String sLine = null;
    long start = System.currentTimeMillis(); // start time
    while ((sLine = inFile.readLine()) != null) {
      RecommendData recommendData = gson.fromJson(sLine, RecommendData.class);
      recommendDataList.add(recommendData);
    }

//    try (Stream<String> lines = Files.lines(Paths.get(String.valueOf(file)), Charset.defaultCharset())) {
//      lines.forEachOrdered(line -> recommendDataList.add(gson.fromJson(line, RecommendData.class)));
//    }
    long end = System.currentTimeMillis(); // end time
//    try (Stream<String> stream = Files.lines(Paths.get(String.valueOf(file)))) {
//      stream.forEach(System.out::println);
//    }
//      System.out.println(joaraBook.toString());
    System.out.println(">> Joara Recommend Data size : " + recommendDataList.size());
    System.out.println(">> Joara Recommend Convert Time : " +  (end - start) / 1000.0 + "초");
//      recommendDataList.forEach(System.out::println);
      return recommendDataList;
  }
}
