package jemstone.mystuff.command;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.util.Printer;
import jemstone.util.command.UndoableCommand;
import jemstone.util.log.Logger;

public class DeleteCategoryCommand implements UndoableCommand {
  private static final long serialVersionUID = 1L;
  private static final Logger log = Logger.getLogger(DeleteCategoryCommand.class);

  private Category newCategory;
  private List<Category> categories = new ArrayList<Category>(1);

  /**
   * Create a command to delete the specified {@link Category}
   * 
   * @param category The category to delete
   * @param newCategory All {@link Transaction} records attached to
   *          <code>category</code> will be moved to <code>newCategory</code>.
   */
  public DeleteCategoryCommand(Category category) {
    super();
    this.newCategory = EntityManager.getInstance().getCategory(0);
    this.categories = new ArrayList<Category>(1);
  }

  public DeleteCategoryCommand(Collection<Category> categories) {
    super();
    this.categories.addAll(categories);
    this.newCategory = EntityManager.getInstance().getCategory(0);
  }
  
  /**
   * Set the new {@link Category} to which all transactions will be attached
   * @return this to support daisy chaining
   */
  public DeleteCategoryCommand setNewCategory(Category newCategory) {
    this.newCategory = newCategory;
    return this;
  }

  public String getCategoryNames() {
    StringBuilder s = new StringBuilder();
    for (int i=0;  i < categories.size();  i++) {
      if (i > 0) {
        s.append(", ");
      }
      s.append(categories.get(i).getName());
    }
    return s.toString();
  }

  @Override
  public void execute() {
    final EntityManager manager = EntityManager.getInstance();
    manager.setModified(true);

    // Remove the categories
    manager.getCategories().removeAll(categories);
  }

  @Override
  public void undo() {
    final EntityManager manager = EntityManager.getInstance();
    manager.setModified(true);

    // Add the original categories
    manager.getCategories().addAll(categories);
  }

  @Override
  public void redo() {
    execute();
  }

  @Override
  public boolean canExecute() {
    return !categories.isEmpty();
  }

  @Override
  public boolean canUndo() {
    return !categories.isEmpty();
  }

  @Override
  public void print(PrintWriter out, int depth) {
    Printer.indent(out, depth);
    out.format("%s: [entity=%s(id=%d)]");
  }

  @Override
  public String toString() {
    return Printer.toString(this);
  }
}
