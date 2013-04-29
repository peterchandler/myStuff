package jemstone.mystuff.model;

import java.io.PrintWriter;

import jemstone.model.Entity;
import jemstone.model.HasDescription;
import jemstone.model.HasName;
import jemstone.util.Printer;

/**
 * @author Peter
 * @version 1.0
 * @created 08-Jun-2011 21:25:47
 */
public class Category extends Entity implements HasName, HasDescription {
  public enum F { Category, Name, Description };
  
  /** The name of the category */
  private String name;

  /** A short description of the category */
  private String description;

  Category(int id) {
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
  public void print(PrintWriter out, int depth) {
    Printer.print(out, this, depth);
  }
}