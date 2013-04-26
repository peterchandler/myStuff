package jemstone.mystuff.model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jemstone.model.Entity;
import jemstone.model.HasAmount;
import jemstone.model.HasDate;
import jemstone.model.HasDescription;
import jemstone.model.HasName;
import jemstone.util.Printer;

/**
 * Represents an item that is being tracked
 */
public class Item extends Entity implements HasName, HasDescription, HasDate, HasAmount {
  /** The name of the category */
  private String name;

  /** A short description of the category */
  private String description;
  
  /** The date that the item was acquired */
  private Date date;
  
  /** The value of this item */
  private double amount;
  
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

  @Override
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
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