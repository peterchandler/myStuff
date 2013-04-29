package jemstone.mystuff.model;

public class IdFactory {
  public enum F { IdFactory, NextCategoryId, NextItemId, NextPhotoId, NextPropertyId };
  
  private int nextCategoryId = 1;
  private int nextItemId = 1;
  private int nextPhotoId = 1;
  private int nextPropertyId = 1;

  public int nextCategoryId() {
    return nextCategoryId++;
  }

  public int nextItemId() {
    return nextItemId++;
  }

  public int nextPhotoId() {
    return nextPhotoId++;
  }

  public int nextPropertyId() {
    return nextPropertyId++;
  }

  /**
   * @return the nextCategoryId
   */
  public int getNextCategoryId() {
    return nextCategoryId;
  }

  /**
   * @param nextCategoryId the nextCategoryId to set
   */
  public void setNextCategoryId(int nextCategoryId) {
    this.nextCategoryId = nextCategoryId;
  }

  public int getNextItemId() {
    return nextItemId;
  }

  public void setNextItemId(int nextItemId) {
    this.nextItemId = nextItemId;
  }

  public int getNextPhotoId() {
    return nextPhotoId;
  }

  public void setNextPhotoId(int nextPhotoId) {
    this.nextPhotoId = nextPhotoId;
  }

  public int getNextPropertyId() {
    return nextPropertyId;
  }

  public void setNextPropertyId(int nextPropertyId) {
    this.nextPropertyId = nextPropertyId;
  }
}
