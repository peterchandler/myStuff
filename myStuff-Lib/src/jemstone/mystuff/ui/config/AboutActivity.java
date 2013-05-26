package jemstone.mystuff.ui.config;

import jemstone.mystuff.lib.R;
import jemstone.mystuff.ui.BaseActivity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends BaseActivity {
  public AboutActivity() {
    super();
    setMenuItemHandler(new MenuItemHandler());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.about_activity);

    // Initialize the header
    setTitle(R.string.titleApplication);
    
    // Get the version number
    try {
      PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
      
      TextView version = (TextView)findViewById(R.id.version);
      version.setText(getString(R.string.titleVersionString, packageInfo.versionName));
    } catch (NameNotFoundException e) {
    }
    
    // Set handler to provide feedback
    View linkToGooglePlay = findViewById(R.id.link_to_google_play);
    linkToGooglePlay.setOnClickListener(new OnClickListener());
  }

  private class MenuItemHandler extends BaseActivity.MenuItemHandler {
    @Override
    public boolean canConfig() {
      return false;
    }
  }
  
  private class OnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
      navigateToGooglePlay(getPackageName());
    }
  }
}
