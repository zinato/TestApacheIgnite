package com.braincolla.ignite.vo;

import java.util.Arrays;

public class RecommendData {

  private String userId;
  private String[] itemList;

  public String[] getItemList() {
    return itemList;
  }

  public void setItemList(String[] itemList) {
    this.itemList = itemList;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "RecommendData{" +
      "userId='" + userId + '\'' +
      ", itemList=" + Arrays.toString(itemList) +
      '}';
  }


}
