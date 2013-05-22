package jemstone.mystuff.command;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.Property;
import jemstone.util.Printer;
import jemstone.util.command.UndoableCommand;
import jemstone.util.log.Logger;

public class DeletePropertyCommand implements UndoableCommand {
  private static final long serialVersionUID = 1L;
  private static final Logger log = Logger.getLogger(DeletePropertyCommand.class);

  private Property newProperty;
  private List<Property> properties = new ArrayList<Property>(1);

  /**
   * Create a command to delete the specified {@link Property}
   * 
   * @param Property The Property to delete
   * @param newProperty All {@link Transaction} records attached to
   *          <code>Property</code> will be moved to <code>newProperty</code>.
   */
  public DeletePropertyCommand(Property property) {
    super();
    this.properties = new ArrayList<Property>(1);
    this.properties.add(property);
    this.newProperty = EntityManager.getInstance().getProperty(0);
  }

  public DeletePropertyCommand(Collection<Property> properties) {
    super();
    this.properties.addAll(properties);
    this.newProperty = EntityManager.getInstance().getProperty(0);
  }
  
  /**
   * Set the new {@link Property} to which all transactions will be attached
   * @return this to support daisy chaining
   */
  public DeletePropertyCommand setNewProperty(Property newProperty) {
    this.newProperty = newProperty;
    return this;
  }

  public String getPropertyNames() {
    StringBuilder s = new StringBuilder();
    for (int i=0;  i < properties.size();  i++) {
      if (i > 0) {
        s.append(", ");
      }
      s.append(properties.get(i).getName());
    }
    return s.toString();
  }

  @Override
  public void execute() {
    final EntityManager manager = EntityManager.getInstance();
    manager.setModified(true);

    // Remove the categories
    manager.getProperties().removeAll(properties);
  }

  @Override
  public void undo() {
    final EntityManager manager = EntityManager.getInstance();
    manager.setModified(true);

    // Add the original categories
    manager.getProperties().addAll(properties);
  }

  @Override
  public void redo() {
    execute();
  }

  @Override
  public boolean canExecute() {
    return !properties.isEmpty();
  }

  @Override
  public boolean canUndo() {
    return !properties.isEmpty();
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
