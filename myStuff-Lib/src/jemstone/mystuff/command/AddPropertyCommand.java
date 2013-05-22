package jemstone.mystuff.command;

import java.io.PrintWriter;

import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.HasProperty;
import jemstone.mystuff.model.Property;
import jemstone.util.Printer;
import jemstone.util.command.UndoableCommand;

public class AddPropertyCommand implements UndoableCommand, HasProperty {
  private static final long serialVersionUID = 1L;

  private Property property;

  @Override
  public Property getProperty() {
    return property;
  }

  @Override
  public void execute() {
    final EntityManager manager = EntityManager.getInstance();
    manager.setModified(true);
    property = manager.getProperty(null);
  }

  @Override
  public void undo() {
    final EntityManager manager = EntityManager.getInstance();
    manager.setModified(true);
    manager.getProperties().remove(property);
  }

  @Override
  public void redo() {
    if (property != null) {
      final EntityManager manager = EntityManager.getInstance();
      manager.setModified(true);
      manager.getProperties().add(property);
    }
  }

  @Override
  public boolean canExecute() {
    return true;
  }

  @Override
  public boolean canUndo() {
    return (property != null);
  }

  @Override
  public void print(PrintWriter out, int depth) {
    Printer.indent(out, depth);
    out.format("%s: [entity=%s(id=%d)]",
               getClass().getSimpleName(),
               property.getClass().getSimpleName(),
               property.getId());
  }

  @Override
  public String toString() {
    return Printer.toString(this);
  }
}
