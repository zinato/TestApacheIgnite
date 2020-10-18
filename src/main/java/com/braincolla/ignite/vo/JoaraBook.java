package com.braincolla.ignite.vo;

import java.util.Arrays;

public class JoaraBook {

  private int bookCode;
  private String novelCode; //[N:노블레스, P:프리미엄, S:무료작품, X:습작]
  private String subject; //작품 제목
  private int category1;  //1차 카테고리 25
  private int catgeory2; //2차 카테고리 25
  private String isFinish; //완결 여부 [y:완결, n: 미완결]
  private String isAdult; //성인 여부 [y:성인, n: 성인작품 아님]
  private int chapterCnt; //전체 회차 수 50 지정
  private int freeCnt; // 무료 회차수 15 지정
  private int favoriteCnt; //선호작 수 [100,200,500,1000]
  private String hasIsbn; //isbn 유무 [y: 있음 , n: 없음]
  private String status ; // 전체,증분데이터 구분 full
  private String writerId; //작성자 id
  private String bookStatus; //작품 상태 [y: 공개, n: 비공개 ]  y 고정
  private String url; //작품 url
  private String intro; // 작품 소개글
  private String redate; // 작품 최근 등록일 2004-12-13T21:39:45.618+09:00
  private String wdate; // 작품 최초 등록일 2004-12-13T21:39:45.618+09:00
  private String[] aboveChapter;

  public int getBookCode() {
    return bookCode;
  }

  public void setBookCode(int bookCode) {
    this.bookCode = bookCode;
  }

  public String getNovelCode() {
    return novelCode;
  }

  public void setNovelCode(String novelCode) {
    this.novelCode = novelCode;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public int getCategory1() {
    return category1;
  }

  public void setCategory1(int category1) {
    this.category1 = category1;
  }

  public int getCatgeory2() {
    return catgeory2;
  }

  public void setCatgeory2(int catgeory2) {
    this.catgeory2 = catgeory2;
  }

  public String getIsFinish() {
    return isFinish;
  }

  public void setIsFinish(String isFinish) {
    this.isFinish = isFinish;
  }

  public String getIsAdult() {
    return isAdult;
  }

  public void setIsAdult(String isAdult) {
    this.isAdult = isAdult;
  }

  public int getChapterCnt() {
    return chapterCnt;
  }

  public void setChapterCnt(int chapterCnt) {
    this.chapterCnt = chapterCnt;
  }

  public int getFreeCnt() {
    return freeCnt;
  }

  public void setFreeCnt(int freeCnt) {
    this.freeCnt = freeCnt;
  }

  public int getFavoriteCnt() {
    return favoriteCnt;
  }

  public void setFavoriteCnt(int favoriteCnt) {
    this.favoriteCnt = favoriteCnt;
  }

  public String getHasIsbn() {
    return hasIsbn;
  }

  public void setHasIsbn(String hasIsbn) {
    this.hasIsbn = hasIsbn;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getWriterId() {
    return writerId;
  }

  public void setWriterId(String writerId) {
    this.writerId = writerId;
  }

  public String getBookStatus() {
    return bookStatus;
  }

  public void setBookStatus(String bookStatus) {
    this.bookStatus = bookStatus;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }

  public String getRedate() {
    return redate;
  }

  public void setRedate(String redate) {
    this.redate = redate;
  }

  public String getWdate() {
    return wdate;
  }

  public void setWdate(String wdate) {
    this.wdate = wdate;
  }

  public String[] getAboveChapter() {
    return aboveChapter;
  }

  public void setAboveChapter(String[] aboveChapter) {
    this.aboveChapter = aboveChapter;
  }

  @Override
  public String toString() {
    return "JoaraBook{" +
      "bookCode=" + bookCode +
      ", novelCode='" + novelCode + '\'' +
      ", subject='" + subject + '\'' +
      ", category1=" + category1 +
      ", catgeory2=" + catgeory2 +
      ", isFinish='" + isFinish + '\'' +
      ", isAdult='" + isAdult + '\'' +
      ", chapterCnt=" + chapterCnt +
      ", freeCnt=" + freeCnt +
      ", favoriteCnt=" + favoriteCnt +
      ", hasIsbn='" + hasIsbn + '\'' +
      ", status='" + status + '\'' +
      ", writerId='" + writerId + '\'' +
      ", bookStatus='" + bookStatus + '\'' +
      ", url='" + url + '\'' +
      ", intro='" + intro + '\'' +
      ", redate='" + redate + '\'' +
      ", wdate='" + wdate + '\'' +
      ", aboveChapter=" + Arrays.toString(aboveChapter) +
      '}';
  }
}
