package jemstone.mystuff.command;

import jemstone.mystuff.model.EntityManager;
import jemstone.util.MyRuntimeException;
import jemstone.util.file.FileManager;

public class SaveFileTask implements Runnable {
  private FileManager fileManager;

  public SaveFileTask(FileManager fileManager) {
    super();
    this.fileManager = fileManager;
  }

  @Override
  public void run() {
    final EntityManager manager = EntityManager.getInstance();
    if (manager.isModified()) {
      try {
        fileManager.save(manager);
      } catch (Exception e) {
        throw new MyRuntimeException(e, "Error saving to file");
      }
    }
  }
}
