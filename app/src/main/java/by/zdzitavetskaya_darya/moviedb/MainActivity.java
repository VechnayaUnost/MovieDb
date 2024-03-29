package by.zdzitavetskaya_darya.moviedb;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;
import butterknife.Unbinder;
import by.zdzitavetskaya_darya.moviedb.adapters.ViewPagerAdapter;
import by.zdzitavetskaya_darya.moviedb.presentation.favouritePresentation.FavouriteFragment;
import by.zdzitavetskaya_darya.moviedb.presentation.searchPresentation.SearchMovieFragment;
import by.zdzitavetskaya_darya.moviedb.presentation.topRatedPresentation.TopRatedFragment;
import by.zdzitavetskaya_darya.moviedb.presentation.upcomingPresentation.UpcomingFragment;
import moxy.MvpAppCompatActivity;
import moxy.MvpView;

public class MainActivity extends MvpAppCompatActivity implements MvpView {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Unbinder unbinder;
    private MenuItem prevMenuItem;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        setupBottomNavigationViewListener(bottomNavigationView);
        invalidateFragmentMenus(viewPager.getCurrentItem());
    }

    @OnPageChange(R.id.view_pager)
    public void onPageSelected(final int position) {
        invalidateFragmentMenus(position);
        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        } else {
            bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }

        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = bottomNavigationView.getMenu().getItem(position);
    }

    private void invalidateFragmentMenus(final int position){
        for(int i = 0; i < adapter.getCount(); i++){
            adapter.getItem(i).setHasOptionsMenu(i == position);
        }
        invalidateOptionsMenu();
    }

    private void setupViewPager(final ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TopRatedFragment());
        adapter.addFragment(new UpcomingFragment());
        adapter.addFragment(new SearchMovieFragment());
        adapter.addFragment(new FavouriteFragment());
        viewPager.setAdapter(adapter);
    }

    private void setupBottomNavigationViewListener(final BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_top_rated:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_upcoming:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_search:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_favourites:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
