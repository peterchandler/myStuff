package jemstone.mystuff.ui;

import java.util.List;

import jemstone.model.HasName;
import jemstone.mystuff.lib.R;
import jemstone.ui.AbstractActionBarDropdownAdapter;
import jemstone.ui.AbstractFragmentPagerAdapter;
import jemstone.ui.BaseViewPager;
import android.os.Bundle;

/**
 * Provide a base implementation of an activity that hosts a ViewPager. One of the
 * main objectives is to encapsulate all references to the Android Compatibility
 * package.
 * @param <T> The type of entity that will be shown in each page
 * @param <F> The type of fragment that will be managed by the pager
 */
public abstract class BaseFragmentPagerActivity<T extends HasName, F extends BaseFragment> 
              extends BaseActivity {
  private AbstractFragmentPagerAdapter<T,F> adapter;
  private BaseViewPager viewPager;

  public AbstractFragmentPagerAdapter<T,F> getAdapter() {
    return adapter;
  }

  public void setAdapter(AbstractFragmentPagerAdapter<T,F> adapter) {
    this.adapter = adapter;
    getViewPager().setAdapter(adapter);
    getViewPager().setOnPageChangeListener(adapter);
  }
  
  public void setShowActionBarDropDown() {
    ActionBarAdapter actionBarAdapter = new ActionBarAdapter(getTitle(), adapter.getEntities());
    adapter.setActionBarDropdownAdapter(actionBarAdapter);
    
    setShowActionBarDropDown(actionBarAdapter);
  }

  protected BaseViewPager getViewPager() {
    if (viewPager == null) {
      viewPager = (BaseViewPager) findViewById(R.id.viewpager);
    }
    return viewPager;
  }

  public void setCurrentItem(T entity) {
    adapter.setCurrentItem(entity);
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
  }

  @Override
  public void onResume() {
    super.onResume();
    adapter.notifyDataSetChanged();
  }

  public abstract class Adapter extends AbstractFragmentPagerAdapter<T,F> {
    public Adapter(List<T> entities) {
      super(BaseFragmentPagerActivity.this, entities, getViewPager());
    }
  }
  
  public class ActionBarAdapter extends AbstractActionBarDropdownAdapter<T> {
    public ActionBarAdapter(CharSequence title, List<T> items) {
      super(BaseFragmentPagerActivity.this, title, items);
    }
    
    @Override
    public boolean onNavigationItemSelected(int position, long id) {
      adapter.setCurrentPosition(position);
      return true;
    }

//    @Override
//    public CharSequence getTitle(int position) {
//      return adapter.getPageTitle(position);
//    }

    @Override
    public CharSequence getSubtitle(int position) {
      return adapter.getPageSubtitle(position);
    }
  }
}
