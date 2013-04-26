package jemstone.mystuff.model;

import java.io.PrintWriter;

import jemstone.model.Entity;
import jemstone.model.HasName;
import jemstone.util.Printer;

/**
 * Represents a photo of an {@link Item}
 */
public class Photo extends Entity implements HasName {
  /** The name of the photo file */
  private String name;

  /** A caption for the photo */
  private String caption;

  Photo(int id) {
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

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  @Override
  public void print(PrintWriter out, int depth) {
    Printer.print(out, this, depth);
  }
}