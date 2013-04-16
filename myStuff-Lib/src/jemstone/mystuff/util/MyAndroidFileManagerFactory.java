package jemstone.mystuff.util;

import jemstone.util.file.AndroidFileManager;
import jemstone.util.file.FileManager;
import android.content.Context;

public class MyAndroidFileManagerFactory extends MyFileManagerFactory {

  public MyAndroidFileManagerFactory() {
    super();
  }

  @Override
  protected FileManager createInstance(Context context) {
    log.debug("Creating new AndroidFileManager");
    return new AndroidFileManager(context, true);
  }
}
