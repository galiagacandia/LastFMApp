package bo.org.groverleopoldo.lastfmapp.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import bo.org.groverleopoldo.lastfmapp.R;
import bo.org.groverleopoldo.lastfmapp.fragments.AlbumFragment;
import bo.org.groverleopoldo.lastfmapp.fragments.ArtistFragment;
import bo.org.groverleopoldo.lastfmapp.fragments.ConnectionFragment;
import bo.org.groverleopoldo.lastfmapp.fragments.SongsFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mainDrawerLayout;
    private NavigationView menuLeftNavigationView;
    private ConnectivityManager connectivityManager;
    private boolean internetDisponible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Verificamos si tenemos conexion de Internet
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            internetDisponible = true;
        } else {
            internetDisponible = false;
        }
        // Instanciamos el ToolBar
        setToolbar();

        mainDrawerLayout = (DrawerLayout) findViewById(R.id.mainDrawerLayout);
        menuLeftNavigationView = (NavigationView) findViewById(R.id.menuLeftNavigationView);
        setFragmentByDefault();
        menuLeftNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                if (internetDisponible){
                    switch (item.getItemId()) {
                        case R.id.menuArtists:
                            fragment = new ArtistFragment();
                            fragmentTransaction = true;
                            break;
                        case R.id.menuAlbums:
                            fragment = new AlbumFragment();
                            fragmentTransaction = true;
                            break;
                        case R.id.menuSongs:
                            fragment = new SongsFragment();
                            fragmentTransaction = true;
                            break;
                    }
                } else {
                    fragment = new ConnectionFragment();
                    fragmentTransaction = true;
                }

                if (fragmentTransaction) {
                    changeFragment(fragment, item);
                    mainDrawerLayout.closeDrawers();
                }

                return true;
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefault(){
        if (internetDisponible){
            changeFragment(new ArtistFragment(), menuLeftNavigationView.getMenu().getItem(0));
        } else {
            changeFragment(new ConnectionFragment(), menuLeftNavigationView.getMenu().getItem(0));
        }
    }

    private void changeFragment(Fragment fragment, MenuItem item){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentFrameLayout, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mainDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
