package jemstone.mystuff.ui;

import jemstone.mystuff.model.DemoScenario;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.ui.free.FreeLimitManager;
import jemstone.mystuff.util.MyAndroidFileManagerFactory;
import jemstone.util.file.FileManager;
import jemstone.util.log.Logger;
import android.app.Application;
import android.content.Context;

public class MyStuffApp extends Application {
  private static final Logger log = Logger.getLogger(MyStuffApp.class);
  
  public static final String paidPackageName = "jemstone.mystuff";
  public static final String freePackageName = "jemstone.mystuff.free";

  @Override
  public void onCreate() {
    super.onCreate();
    
    // Is this the free version?
    FreeLimitManager.setFreeVersion(freePackageName.equals(getPackageName()));

    // Set some string constants from the resources
    initializeResources(getApplicationContext());
    
    // Load file, or create a demo scenario
    FileManager fileManager = null;
    try {
      fileManager = new MyAndroidFileManagerFactory().getInstance(this);
      fileManager.load();
    } catch (Exception e) {
      log.debug(e);
      log.debug("Creating Demo Scenario");

      try {
        DemoScenario.create();
        if (fileManager != null) {
          fileManager.save(EntityManager.getInstance());
        } 
      } catch (Exception e1) {
        log.debug(e1);
      }
    }
  }

  private void initializeResources(Context context) {
  }
}
