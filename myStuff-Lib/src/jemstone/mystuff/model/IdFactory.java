package jemstone.mystuff.model;

public class IdFactory {
  private int nextCategoryId = 1;

  public int nextCategoryId() {
    return nextCategoryId++;
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
}
