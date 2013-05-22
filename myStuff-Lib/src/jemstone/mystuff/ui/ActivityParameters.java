package jemstone.mystuff.ui;

import java.io.PrintWriter;

import jemstone.model.Entity;
import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.Property;
import jemstone.util.MyRuntimeException;
import jemstone.util.Printer;

public class ActivityParameters implements jemstone.ui.ActivityParameters {
  private static final long serialVersionUID = 1L;

  public static final int UNKNOWN_ID = -1;

  /** The current {@link Category} */
  private int categoryId = UNKNOWN_ID;

  /** The current {@link Property} */
  private int propertyId = UNKNOWN_ID;
  
  /** Optional flag to indicate that a new {@link Entity} is required */
  private boolean isNewEntity;
  
  /** The class of the parent activity (i.e. the activity that invoked this activity) */
  private String parentActivityClass;

  public Category getCategory() {
    return (categoryId == UNKNOWN_ID) ? null : EntityManager.getInstance().getCategory(categoryId);
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategory(Category category) {
    this.categoryId = (category != null) ? category.getId() : UNKNOWN_ID;
  }

  public Property getProperty() {
    return (propertyId == UNKNOWN_ID) ? null : EntityManager.getInstance().getProperty(propertyId);
  }

  public int getPropertyId() {
    return propertyId;
  }

  public void setProperty(Property property) {
    this.propertyId = (property != null) ? property.getId() : UNKNOWN_ID;
  }

  public boolean isNewEntity() {
    return isNewEntity;
  }

  public void setNewEntity(boolean isNewEntity) {
    this.isNewEntity = isNewEntity;
  }

  public String getParentActivityClass() {
    return parentActivityClass;
  }

  public void setParentActivityClass(String parentActivityClassName) {
    this.parentActivityClass = parentActivityClassName;
  }

  @Override
  public ActivityParameters clone() {
    try {
      return (ActivityParameters) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new MyRuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return Printer.toString(this);
  }

  @Override
  public void print(PrintWriter out, int depth) {
    out.print("[categoryId=");
    out.print(categoryId);
    out.print(", propertyId=");
    out.print(propertyId);
    out.print(", parentActivityClass=");
    out.print(parentActivityClass);
    out.print("]");
  }
}
