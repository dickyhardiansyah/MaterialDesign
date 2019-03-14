package me.dicky.materialdesign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import me.dicky.materialdesign.Fragment.ChatFragment;
import me.dicky.materialdesign.Fragment.ExploreFragment;
import me.dicky.materialdesign.Fragment.FriendFragment;
import me.dicky.materialdesign.Fragment.HomeFragment;
import me.dicky.materialdesign.SlidingTab.SlidingTabAdapter;
import me.dicky.materialdesign.SlidingTab.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;
    private Drawer.Result navigationDrawerLeft;
    protected AccountHeader.Result headerNavigationLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = findViewById(R.id.vp_tab);
        mViewPager.setAdapter(new SlidingTabAdapter(getSupportFragmentManager(), this));

        mSlidingTabLayout = findViewById(R.id.stl_tab);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tv_Tab);
        mSlidingTabLayout.setViewPager(mViewPager);

        //========================= Navigation Drawe
        headerNavigationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.color.colorPrimary)
                .addProfiles(
                        new ProfileDrawerItem().withName("Kamus Koding").withEmail("kampus@email.com")
                                .withIcon(getResources().getDrawable(R.drawable.ic_smile))
                )
                .build();

        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withAccountHeader(headerNavigationLeft)
                .withSelectedItem(0)
                .build();

        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Home").withIcon(getResources().getDrawable(R.drawable.ic_home)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Explore").withIcon(getResources().getDrawable(R.drawable.ic_explore)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Chat").withIcon(getResources().getDrawable(R.drawable.ic_chat)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Friend").withIcon(getResources().getDrawable(R.drawable.ic_friend)));

        //========================= BottomBar
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.vp_tab,
                new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;

                case R.id.nav_explore:
                    selectedFragment = new ExploreFragment();
                    break;

                case R.id.nav_chat:
                    selectedFragment = new ChatFragment();
                    break;

                case R.id.nav_friend:
                    selectedFragment = new FriendFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.vp_tab, selectedFragment).commit();
            return true;
        }
    };
}
