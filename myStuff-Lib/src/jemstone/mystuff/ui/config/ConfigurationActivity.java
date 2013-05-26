package jemstone.mystuff.ui.config;

import jemstone.mystuff.lib.R;
import jemstone.mystuff.ui.BaseActivity;
import jemstone.ui.SelectThemeDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ConfigurationActivity extends BaseActivity {
  private ConfigurationListAdapter adapter;

  public ConfigurationActivity() {
    super();
    setMenuItemHandler(new MenuItemHandler());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);

    // Initialize the header
    setTitle(R.string.titleApplication);

    // Set the data adapter
    adapter = new ConfigurationListAdapter(this);

    ListView list = (ListView)findViewById(R.id.main_list);
    list.setAdapter(adapter);
    list.setOnItemClickListener(new OnItemClickListener());
    list.setOnItemLongClickListener(new OnItemLongClickListener());
  }

  @Override
  public void onResume() {
    super.onResume();
    adapter.notifyDataSetChanged();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    log.debug("onCreateOptionsMenu: %s", getParameters());
    
    getMenuItemHandler().onCreateMenu(menu, getMenuInflater(), getParameters());
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    log.debug("onOptionsItemSelected: %s", getParameters());
    
    return getMenuItemHandler().onMenuItemSelected(item, getParameters(), getActivityManager());
  }
  
  public void selectTheme() {
    SelectThemeDialog dialog = new SelectThemeDialog(this);
    dialog.show(getFragmentManager(), SelectThemeDialog.TAG);
  }

  private class OnItemClickListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      switch (position) {
        case 0: getActivityManager().startCategoryListActivity();  break;
        case 2: getActivityManager().startAboutActivity();         break;
        case 1: selectTheme(); break;
      }
    }
  }
  
  private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
      switch (position) {
        case 0: getActivityManager().startCategoryListActivity();  break;
        case 2: getActivityManager().startAboutActivity();         break;
        case 1: selectTheme(); break;
      }
      return false;
    }
  }

  private class MenuItemHandler extends BaseActivity.MenuItemHandler {
    @Override
    public boolean canConfig() {
      return false;
    }
  }
}
