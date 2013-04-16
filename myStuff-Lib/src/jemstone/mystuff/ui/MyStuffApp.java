package jemstone.mystuff.ui;

import jemstone.mystuff.ui.free.FreeLimitManager;
import jemstone.util.log.Logger;
import android.app.Application;
import android.content.Context;

public class MyStuffApp extends Application {
  private static final Logger log = Logger.getLogger(MyStuffApp.class);
  
  public static final String paidPackageName = "jemstone.mystuff.ui";
  public static final String freePackageName = "jemstone.mystuff.free";

  @Override
  public void onCreate() {
    super.onCreate();
    
    // Is this the free version?
    FreeLimitManager.setFreeVersion(freePackageName.equals(getPackageName()));

    // Load file, or create a demo scenario
    try {
//      AndroidFileManager manager = new AndroidFileManager(this, true);
//      manager.load();
    } catch (Exception e) {
    }

    initializeResources(getApplicationContext());
  }

  private void initializeResources(Context context) {
  }

}
