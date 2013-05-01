package jemstone.mystuff.model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jemstone.model.Entity;
import jemstone.model.HasDescription;
import jemstone.model.HasName;
import jemstone.util.Printer;

/**
 * Represents an item that is being tracked
 */
public class Item extends Entity implements HasName, HasDescription {
  public enum F { Item, Name, Description, 
                  PurchaseDate, PurchaseAmount, ReplacementAmount, 
                  Category, CategoryId, Photos };
  
  /** The name of the category */
  private String name;

  /** A short description of the category */
  private String description;
  
  /** The date that the item was acquired */
  private Date purchaseDate;
  
  /** The value of this item */
  private double purchaseAmount;
  
  /** The replacement value of this item */
  private double replacementAmount = Double.NaN;
  
  /** The {@link Category} that is used to classify this item */
  private Category category;
  
  /** A collection of photos of the item */
  private List<Photo> photos = new ArrayList<Photo>();

  Item(int id) {
    super(id);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  public Date getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(Date date) {
    this.purchaseDate = date;
  }

  public double getPurchaseAmount() {
    return purchaseAmount;
  }

  public void setPurchaseAmount(double amount) {
    this.purchaseAmount = amount;
  }

  public double getReplacementAmount() {
    return replacementAmount;
  }

  public void setReplacementAmount(double replacementAmount) {
    this.replacementAmount = replacementAmount;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public List<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
  }
  
  public Photo addPhoto(Photo photo) {
    photos.add(photo);
    return photo;
  }
  
  public boolean hasPhotos() {
    return (photos != null) && !photos.isEmpty();
  }

  @Override
  public void print(PrintWriter out, int depth) {
    Printer.print(out, this, depth);
  }
}