package jemstone.mystuff.command;

import java.io.PrintWriter;

import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.HasCategory;
import jemstone.util.Printer;
import jemstone.util.command.UndoableCommand;

public class AddCategoryCommand implements UndoableCommand, HasCategory {
  private static final long serialVersionUID = 1L;

  private Category category;

  @Override
  public Category getCategory() {
    return category;
  }

  @Override
  public void execute() {
    final EntityManager manager = EntityManager.getInstance();
    manager.setModified(true);
    category = manager.getCategory(null);
  }

  @Override
  public void undo() {
    final EntityManager manager = EntityManager.getInstance();
    manager.setModified(true);
    manager.getCategories().remove(category);
  }

  @Override
  public void redo() {
    if (category != null) {
      final EntityManager manager = EntityManager.getInstance();
      manager.setModified(true);
      manager.getCategories().add(category);
    }
  }

  @Override
  public boolean canExecute() {
    return true;
  }

  @Override
  public boolean canUndo() {
    return (category != null);
  }

  @Override
  public void print(PrintWriter out, int depth) {
    Printer.indent(out, depth);
    out.format("%s: [entity=%s(id=%d)]",
               getClass().getSimpleName(),
               category.getClass().getSimpleName(),
               category.getId());
  }

  @Override
  public String toString() {
    return Printer.toString(this);
  }
}
