package osymtakip.yakuprnk.com;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import osymtakip.yakuprnk.com.fragments.AnaSayfa;
import osymtakip.yakuprnk.com.fragments.Ayarlar;
import osymtakip.yakuprnk.com.fragments.Duyurular;
import osymtakip.yakuprnk.com.fragments.FavoriSinavlar;
import osymtakip.yakuprnk.com.fragments.TumSinavlar;
import osymtakip.yakuprnk.com.fragments.Uygulama;

public class OsymTakip extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osym_takip);


        AnaSayfa fragment=new AnaSayfa();
        FragmentTransaction fragtrans=getSupportFragmentManager().beginTransaction();
        fragtrans.replace(R.id.fragment_container,fragment);
        fragtrans.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.anasayfa) {
            AnaSayfa fragment=new AnaSayfa();
            FragmentTransaction fragtrans=getSupportFragmentManager().beginTransaction();
            fragtrans.replace(R.id.fragment_container,fragment);
            fragtrans.commit();
            getSupportActionBar().setTitle("Ana Sayfa");

        }/*else if (id == R.id.forum) {
            Forum fragment=new Forum();
            FragmentTransaction fragtrans=getSupportFragmentManager().beginTransaction();
            fragtrans.replace(R.id.fragment_container,fragment);
            fragtrans.commit();
            getSupportActionBar().setTitle("Forum");

        }*/else if (id == R.id.favorisinav) {
            FavoriSinavlar fragment=new FavoriSinavlar();
            FragmentTransaction fragtrans=getSupportFragmentManager().beginTransaction();
            fragtrans.replace(R.id.fragment_container,fragment);
            fragtrans.commit();
            getSupportActionBar().setTitle("Favori Sınavlar");

        } else if (id == R.id.tumsinavlar) {
            TumSinavlar fragment=new TumSinavlar();
            FragmentTransaction fragtrans=getSupportFragmentManager().beginTransaction();
            fragtrans.replace(R.id.fragment_container,fragment);
            fragtrans.commit();
            getSupportActionBar().setTitle("Sınav Takvimi");

        }  else if (id == R.id.duyuru) {
            Duyurular fragment=new Duyurular();
            FragmentTransaction fragtrans=getSupportFragmentManager().beginTransaction();
            fragtrans.replace(R.id.fragment_container,fragment);
            fragtrans.commit();
            getSupportActionBar().setTitle("Duyurular");


        } else if (id == R.id.ayarlar) {
            Ayarlar fragment=new Ayarlar();
            FragmentTransaction fragtrans=getSupportFragmentManager().beginTransaction();
            fragtrans.replace(R.id.fragment_container,fragment);
            fragtrans.commit();
            getSupportActionBar().setTitle("Ayarlar");


        }else if (id == R.id.hakkimizda) {

            Uygulama fragment=new Uygulama();
            FragmentTransaction fragtrans=getSupportFragmentManager().beginTransaction();
            fragtrans.replace(R.id.fragment_container,fragment);
            fragtrans.commit();
            getSupportActionBar().setTitle("Uygulama");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
