package jemstone.mystuff.ui;

import jemstone.mystuff.command.SaveFileTask;
import jemstone.mystuff.util.MyAndroidFileManagerFactory;
import jemstone.ui.AbstractActivity;
import jemstone.util.command.AsyncTaskManager;
import jemstone.util.file.FileManager;
import jemstone.util.file.FileManagerFactory;

public abstract class BaseActivity extends AbstractActivity<ActivityManager, ActivityParameters> {
  public BaseActivity() {
    super();
    setActivityManager(new ActivityManager(this));
    setParameters(new ActivityParameters());
    
    setHomeActivity(ActivityManager.isHomeActivity(getClass()));
  }

  @Override
  public void onPause() {
    super.onPause();
    log.debug("onPause: %s", getParameters());

    // Save any modifications
    AsyncTaskManager.execute(new SaveFileTask(getFileManager()));
  }

  public FileManager getFileManager() {
    return getFileManagerFactory().getInstance(this);
  }

  public FileManagerFactory getFileManagerFactory() {
    return new MyAndroidFileManagerFactory();
  }

  protected class MenuItemHandler extends BaseMenuItemHandler {
    public MenuItemHandler() {
      super();
    }
  }
}
