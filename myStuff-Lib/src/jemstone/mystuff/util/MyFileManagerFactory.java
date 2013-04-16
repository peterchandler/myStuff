package jemstone.mystuff.util;

import jemstone.mystuff.dao.LoadXmlDao;
import jemstone.mystuff.dao.SaveXmlDao;
import jemstone.util.file.FileManager;
import jemstone.util.file.FileManagerFactory;
import android.content.Context;

public class MyFileManagerFactory extends FileManagerFactory {
  public MyFileManagerFactory() {
    super(LoadXmlDao.class, SaveXmlDao.class, MyFileManagerListener.class);
    setFileName("mystuff.xml");
  }

  @Override
  protected FileManager createInstance(Context context) {
    return super.createInstance(context);
  }
}
