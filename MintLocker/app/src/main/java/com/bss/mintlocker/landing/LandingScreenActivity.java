package com.bss.mintlocker.landing;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;
import com.bss.mintlocker.adapter.OncePerMonthAdapter;
import com.bss.mintlocker.adapter.PortfolioAdapter;
import com.bss.mintlocker.model.OncePerMonthModel;
import com.bss.mintlocker.model.PortfolioModel;
import com.bss.mintlocker.ui.fragments.MainActivity;
import com.bss.mintlocker.ui.fragments.Projection;
import com.bss.mintlocker.ui.fragments.Settings;
import com.bss.mintlocker.util.SharedPreferencesHandler;

/**
 * Created by bhawanisingh on 19/11/15.
 */
public class LandingScreenActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, PortfolioAdapter.Portfoliolistener, OncePerMonthAdapter.datelistener {

    private Toolbar toolbar;
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle
    Fragment currFragment;
    public static TextView mToolbarTitle;
    ImageView addUserImg;

    com.bss.mintlocker.util.CustomViewpager mViewfliper;
    private float lastX;

    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_landing_screen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        init();

        mToolbarTitle.setText("Hi, John");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle

        mDrawerToggle.syncState();

        //After instantiating your ActionBarDrawerToggle
        mDrawerToggle.setDrawerIndicatorEnabled(false);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.menu_custom, LandingScreenActivity.this.getTheme());

        mDrawerToggle.setHomeAsUpIndicator(drawable);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Drawer.isDrawerVisible(GravityCompat.START)) {
                    Drawer.closeDrawer(GravityCompat.START);
                } else {
                    Drawer.openDrawer(GravityCompat.START);
                }
            }
        });
//              View TopLayout = findViewById(R.id.header_main_activity_mainframe);
//            mViewfliper=(app.itpro.mintlocker.util.CustomViewpager)TopLayout.findViewById(R.id.after_bank_account_viewflipper);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayView(1);

//        mViewfliper.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//
//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_DOWN:
//                        Drawer.requestDisallowInterceptTouchEvent(true);
//                        lastX = event.getX();
//                        break;
//                    case MotionEvent.ACTION_UP:
//
//
//
//                        float currentX = event.getX();
//
//                        // Handling left to right screen swap.
//                        if (lastX < currentX) {
//
//                            // If there aren't any other children, just break.
//                            if (mViewfliper.getDisplayedChild() == 0)
//                                break;
//
//                            // Next screen comes in from left.
//                            mViewfliper.setInAnimation(LandingScreenActivity.this, R.anim.slide_in_from_left);
//                            // Current screen goes out from right.
//                            mViewfliper.setOutAnimation(LandingScreenActivity.this, R.anim.slide_out_to_right);
//
//                            // Display next screen.
//                            System.out.println("movenext");
//                            mViewfliper.showNext();
//                        }
//
//                        // Handling right to left screen swap.
//                        if (lastX > currentX) {
//
//                            // If there is a child (to the left), kust break.
//                            if (mViewfliper.getDisplayedChild() == 1)
//                                break;
//
//                            // Next screen comes in from right.
//                            mViewfliper.setInAnimation(LandingScreenActivity.this, R.anim.slide_in_from_right);
//                            // Current screen goes out from left.
//                            mViewfliper.setOutAnimation(LandingScreenActivity.this, R.anim.slide_out_to_left);
//
//                            // Display previous screen.
//                            System.out.println("moveprevious");
//                            mViewfliper.showPrevious();
//                        }
//                        break;
//                }
//                return true;
//            }
//        });

    }

    private void init() {
        try {
            toolbar = (Toolbar) findViewById(R.id.tool_bar);
            mToolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            addUserImg = (ImageView) toolbar.findViewById(R.id.toolbar_add_user);
            Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
            addUserImg.setOnClickListener(this);
//            View TopLayout = findViewById(R.id.header_main_layout);
//            mViewfliper=(app.itpro.mintlocker.util.CustomViewpager)TopLayout.findViewById(R.id.after_bank_account_viewflipper);

        } catch (Exception e) {

            e.printStackTrace();

        }


    }


    private void displayView(int position) {
        try {

            // clear back stack
            FragmentManager fm = this.getSupportFragmentManager();
            for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStackImmediate(null,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            // update the activity_main_one content by replacing fragments
            Fragment fragment = null;
            switch (position) {

                case 1:
                    Drawer.closeDrawers();
                    addUserImg.setVisibility(View.VISIBLE);
                    fragment = new com.bss.mintlocker.ui.fragments.SelectionActivity();
//                    notification_icon.setVisibility(View.VISIBLE);
//                    mToolbarTitle.setText("Dashboard");


                    break;

                case 2:
                    Drawer.closeDrawers();
                    addUserImg.setVisibility(View.GONE);
                    fragment = new com.bss.mintlocker.ui.fragments.Summary();
//                    notification_icon.setVisibility(View.VISIBLE);
//                    mToolbarTitle.setText("Dashboard");


                    break;


                case 3:
                    Drawer.closeDrawers();
                    addUserImg.setVisibility(View.GONE);
                    fragment = new com.bss.mintlocker.ui.fragments.Transfer();
//                    notification_icon.setVisibility(View.VISIBLE);
//                    mToolbarTitle.setText("Dashboard");


                    break;


                case 4:
                    Drawer.closeDrawers();
                    addUserImg.setVisibility(View.GONE);
                    fragment = new com.bss.mintlocker.ui.fragments.PortfolioNew();
//                    notification_icon.setVisibility(View.VISIBLE);
//                    mToolbarTitle.setText("Dashboard");


                    break;


                case 5:
                    Drawer.closeDrawers();
                    addUserImg.setVisibility(View.GONE);
                    fragment = new Projection();
//                    notification_icon.setVisibility(View.VISIBLE);
//                    mToolbarTitle.setText("Dashboard");


                    break;

                case 6:
                    Drawer.closeDrawers();
                    addUserImg.setVisibility(View.GONE);
                    fragment = new MainActivity();
//                    notification_icon.setVisibility(View.VISIBLE);
//                    mToolbarTitle.setText("Dashboard");


                    break;


                case 7:
                    Drawer.closeDrawers();
                    addUserImg.setVisibility(View.GONE);
                    fragment = new Settings();
//                    notification_icon.setVisibility(View.VISIBLE);
//                    mToolbarTitle.setText("Dashboard");


                    break;

                case 8:
                    Drawer.closeDrawers();
                    addUserImg.setVisibility(View.GONE);
                    mToolbarTitle.setText("Invite");
                    fragment = new com.bss.mintlocker.ui.fragments.InviteFragment();

//                    notification_icon.setVisibility(View.VISIBLE);
//                    mToolbarTitle.setText("Dashboard");


                    break;
                default:

                    Drawer.closeDrawers();
//                    fragment = new Dashboard();
//                    notification_icon.setVisibility(View.VISIBLE);
//                    mToolbarTitle.setText("Dashboard");
                    break;
            }


            if (currFragment != null && fragment != null) {
                boolean result = fragment.getClass().equals(
                        currFragment.getClass());
                if (result)
                    return;
            }

            if (fragment != null) {
                try {

                } catch (Exception e) {
                    // TODO: handle exception
                }
                // if(fragment instanceof MapFragment) {
                currFragment = fragment;
                Handler h = new Handler();
                h.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        FragmentManager fragmentManager = LandingScreenActivity.this
                                .getSupportFragmentManager();
//                        fragmentManager.beginTransaction()
//                                .replace(R.id.frame_container, currFragment)
//                                .commit();
//updated code
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                        transaction.replace(R.id.frame_container, currFragment);
//                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                }, 300);
            } else {

                currFragment = fragment;
                FragmentManager fragmentManager = this
                        .getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.frame_container, fragment).commit();
//updated code
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                transaction.replace(R.id.frame_container, currFragment);
//                transaction.addToBackStack(null);

                transaction.commit();

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


//    // Using the following method, we will handle all screen swaps.
//    @Override
//    public boolean onTouchEvent(MotionEvent touchevent) {
//        switch (touchevent.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                Drawer.requestDisallowInterceptTouchEvent(true);
//                lastX = touchevent.getX();
//                break;
//            case MotionEvent.ACTION_UP:
//
//
//
//                float currentX = touchevent.getX();
//
//                // Handling left to right screen swap.
//                if (lastX < currentX) {
//
//                    // If there aren't any other children, just break.
//                    if (mViewfliper.getDisplayedChild() == 0)
//                        break;
//
//                    // Next screen comes in from left.
//                    mViewfliper.setInAnimation(LandingScreenActivity.this, R.anim.slide_in_from_left);
//                    // Current screen goes out from right.
//                    mViewfliper.setOutAnimation(LandingScreenActivity.this, R.anim.slide_out_to_right);
//
//                    // Display next screen.
//                    System.out.println("movenext");
//                    mViewfliper.showNext();
//                }
//
//                // Handling right to left screen swap.
//                if (lastX > currentX) {
//
//                    // If there is a child (to the left), kust break.
//                    if (mViewfliper.getDisplayedChild() == 1)
//                        break;
//
//                    // Next screen comes in from right.
//                    mViewfliper.setInAnimation(LandingScreenActivity.this, R.anim.slide_in_from_right);
//                    // Current screen goes out from left.
//                    mViewfliper.setOutAnimation(LandingScreenActivity.this, R.anim.slide_out_to_left);
//
//                    // Display previous screen.
//                    System.out.println("moveprevious");
//                    mViewfliper.showPrevious();
//                }
//                break;
//        }
//        return true;
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // TODO Auto-generated method stub
//        return gestureDetector.onTouchEvent(event);
//    }
//
//    GestureDetector.SimpleOnGestureListener simpleOnGestureListener
//            = new GestureDetector.SimpleOnGestureListener(){
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
//                               float velocityY) {
//
//            float sensitvity = 50;
//            if((e1.getX() - e2.getX()) > sensitvity){
//                // Next screen comes in from left.
//                    mViewfliper.setInAnimation(LandingScreenActivity.this, R.anim.slide_in_from_left);
//                    // Current screen goes out from right.
//                    mViewfliper.setOutAnimation(LandingScreenActivity.this, R.anim.slide_out_to_right);
//
//                    // Display next screen.
//                    System.out.println("movenext");
//                    mViewfliper.showNext();
//            }else if((e2.getX() - e1.getX()) > sensitvity){
//                // Next screen comes in from right.
//                    mViewfliper.setInAnimation(LandingScreenActivity.this, R.anim.slide_in_from_right);
//                    // Current screen goes out from left.
//                    mViewfliper.setOutAnimation(LandingScreenActivity.this, R.anim.slide_out_to_left);
//
//                    // Display previous screen.
//                    System.out.println("moveprevious");
//                    mViewfliper.showPrevious();
//            }
//
//            return true;
//        }
//
//    };
//
//    GestureDetector gestureDetector
//            = new GestureDetector(simpleOnGestureListener);


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            mToolbarTitle.setText("Hi,John");
            displayView(1);
        } else if (id == R.id.summary) {
//            startActivity(new Intent(Feeds_Activity.this, CreateNewEvent.class));
//            finish();

            mToolbarTitle.setText("Summary");
            displayView(2);
        } else if (id == R.id.transfer) {
//            startActivity(new Intent(Feeds_Activity.this, MainActivity.class));
//            finish();

            mToolbarTitle.setText("Transfer");
            displayView(3);
        } else if (id == R.id.portfolio) {
//            startActivity(new Intent(Feeds_Activity.this, UserProfile.class));
//            finish();
            mToolbarTitle.setText("Portfolio");
            displayView(4);
        } else if (id == R.id.projection) {
//            startActivity(new Intent(Feeds_Activity.this, MessageActivity.class));
//         /
            mToolbarTitle.setText("Projection");
            displayView(5);
        } else if (id == R.id.activity) {
//            startActivity(new Intent(Feeds_Activity.this, FriendsTabbedActivity.class));
//            finish();
            mToolbarTitle.setText("Activity");
            displayView(6);


        } else if (id == R.id.settings) {
            mToolbarTitle.setText("Settings");
            displayView(7);
        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.END);
        Drawer.closeDrawers();
        return true;
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = LandingScreenActivity.this.getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
//        Toast.makeText(this, "count"+count, Toast.LENGTH_SHORT).show();
        if (count == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 5000);
        } else if (count > 0) {
            super.onBackPressed();
            return;
        }
    }


    @Override
    public void onAcceptItemClicked(PortfolioModel s) {

    }

    @Override
    public void onRejectItemClicked(PortfolioModel s) {

    }

    @Override
    public void ondateItemClicked(OncePerMonthModel s) {
        Constants.str_pickdate = s.getNoofday() + "";

        System.out.println("value in activity_main_one screen click: " + Constants.str_pickdate);
//Constants.showToast("value in activity_main_one screen click: " + Constants.str_pickdate,3000);
//        AutoDepositeNew.mPickDate.setText(Constants.str_pickdate);
        SharedPreferencesHandler.setStringValues(LandingScreenActivity.this, "vall", Constants.str_pickdate);

        FragmentManager fragmentManager5 = this.getSupportFragmentManager();

        FragmentTransaction transaction5 = fragmentManager5.beginTransaction();
        transaction5.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

//        Fragment duedateFrag5 = new AutoDepositeNew();
//        transaction5.replace(R.id.frame_container, duedateFrag5);
//      transaction5.addToBackStack(null);
        fragmentManager5.popBackStack();
        transaction5.commit();


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        super.dispatchTouchEvent(ev);
        return detector.onTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_add_user:
                displayView(8);
                break;

        }
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            try {
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    if (Constants.scrFragmentTag.equalsIgnoreCase("projection")) {
                        Projection.mViewfliper.setInAnimation(AnimationUtils.loadAnimation(
                                LandingScreenActivity.this, R.anim.slide_in_from_right));
                        Projection.mViewfliper.setOutAnimation(AnimationUtils.loadAnimation(
                                LandingScreenActivity.this, R.anim.slide_out_to_left));

                        Projection.mViewfliper.showNext();
                    } else if (Constants.scrFragmentTag.equalsIgnoreCase("activity_main_one")) {
                        com.bss.mintlocker.ui.fragments.SelectionActivity.mViewfliper.setInAnimation(AnimationUtils.loadAnimation(
                                LandingScreenActivity.this, R.anim.slide_in_from_right));
                        com.bss.mintlocker.ui.fragments.SelectionActivity.mViewfliper.setOutAnimation(AnimationUtils.loadAnimation(
                                LandingScreenActivity.this, R.anim.slide_out_to_left));

                        com.bss.mintlocker.ui.fragments.SelectionActivity.mViewfliper.showNext();
                    } else {
                        return false;
                    }
//                    else if(Constants.scrFragmentTag.equalsIgnoreCase("fragment_portfolio")){
//                        if(PortfolioNew.mViewpage.getCurrentItem()==0) {
//                            PortfolioNew.rb1.setChecked(true);
//                            PortfolioNew.rb2.setChecked(false);
//                            PortfolioNew.rb3.setChecked(false);
//                        }else if(PortfolioNew.mViewpage.getCurrentItem()==1) {
//                            PortfolioNew.rb1.setChecked(false);
//                            PortfolioNew.rb2.setChecked(true);
//                            PortfolioNew.rb3.setChecked(false);
//                        }else if(PortfolioNew.mViewpage.getCurrentItem()==2) {
//                            PortfolioNew.rb1.setChecked(false);
//                            PortfolioNew.rb2.setChecked(false);
//                            PortfolioNew.rb3.setChecked(true);
//                        }
//                    }
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    if (Constants.scrFragmentTag.equalsIgnoreCase("projection")) {
                        Projection.mViewfliper.setInAnimation(AnimationUtils.loadAnimation(
                                LandingScreenActivity.this, R.anim.slide_in_from_left));
                        Projection.mViewfliper.setOutAnimation(AnimationUtils.loadAnimation(
                                LandingScreenActivity.this, R.anim.slide_out_to_right));

                        Projection.mViewfliper.showPrevious();

                    } else if (Constants.scrFragmentTag.equalsIgnoreCase("activity_main_one")) {
                        com.bss.mintlocker.ui.fragments.SelectionActivity.mViewfliper.setInAnimation(AnimationUtils.loadAnimation(
                                LandingScreenActivity.this, R.anim.slide_in_from_left));
                        com.bss.mintlocker.ui.fragments.SelectionActivity.mViewfliper.setOutAnimation(AnimationUtils.loadAnimation(
                                LandingScreenActivity.this, R.anim.slide_out_to_right));

                        com.bss.mintlocker.ui.fragments.SelectionActivity.mViewfliper.showPrevious();
                    } else {
                        return false;
                    }
//                    else if(Constants.scrFragmentTag.equalsIgnoreCase("fragment_portfolio")){
//                       if(PortfolioNew.mViewpage.getCurrentItem()==0) {
//                           PortfolioNew.rb1.setChecked(true);
//                           PortfolioNew.rb2.setChecked(false);
//                           PortfolioNew.rb3.setChecked(false);
//                       }else if(PortfolioNew.mViewpage.getCurrentItem()==1) {
//                           PortfolioNew.rb1.setChecked(false);
//                           PortfolioNew.rb2.setChecked(true);
//                           PortfolioNew.rb3.setChecked(false);
//                       }else if(PortfolioNew.mViewpage.getCurrentItem()==2) {
//                           PortfolioNew.rb1.setChecked(false);
//                           PortfolioNew.rb2.setChecked(false);
//                           PortfolioNew.rb3.setChecked(true);
//                       }
//                    }
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }
}
