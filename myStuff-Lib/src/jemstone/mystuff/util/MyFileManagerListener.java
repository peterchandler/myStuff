package jemstone.mystuff.util;


import jemstone.mystuff.model.EntityManager;
import jemstone.util.file.FileManager;
import jemstone.util.log.Logger;

public class MyFileManagerListener implements FileManager.Listener {
  private Logger log = Logger.getLogger(getClass());
  
  public MyFileManagerListener() {
  }

  @Override
  public void onPreLoad() {
    log.debug("onPreLoad");
  }

  @Override
  public void onPostLoad() {
    log.debug("onPostLoad");
  }

  @Override
  public void onPreSave() {
    log.debug("onPreSave");
  }

  @Override
  public void onPostSave() {
    log.debug("onPostSave");
    EntityManager.getInstance().setModified(false);
  }
}
