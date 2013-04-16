package jemstone.mystuff.dao;

public interface XmlDAO {
  public static final String ENCODING = "UTF-8";
  public static final String NAMESPACE = "http://jemstone.co.nz/schema/mycashflow";
  public static final String PREFIX = "js";
  public static final String DATE_FORMAT = "yyyy-MM-dd";

  public static final String AMOUNT = "amount";
  public static final String CATEGORIES = "categories";
  public static final String CATEGORY = "category";
  public static final String CATEGORY_ID = "categoryId";
  public static final String DATE = "date";
  public static final String DESCRIPTION = "description";
  public static final String ID = "id";
  public static final String ID_FACTORY = "idFactory";
  public static final String IS_DELETED = "isDeleted";
  public static final String NAME = "name";
  public static final String NEXT_CATEGORY_ID = "nextCategoryId";
}