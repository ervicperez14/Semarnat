package semarnat.mac.ervic.nexura.semarnat;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class ApartadoC extends AppCompatActivity {
    private static final String SELECTED_ITEM = "arg_selected_item";
    private BottomNavigationView mBottomNav;
    private int mSelectedItem;
    private ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartado_c);
        mBottomNav = (BottomNavigationView) findViewById(R.id.bottomBar);
        btn_back = (ImageView)findViewById(R.id.btn_back);
        Fragment frag = null;
        frag = new ViajaCon().newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,frag).commit();
        mBottomNav.setItemBackgroundResource(R.color.color1);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frag = null;
                switch(item.getItemId()){

                    case R.id.menu_tarjetas:
                        frag = new ViajaCon().newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,frag).commit();
                        mBottomNav.setItemBackgroundResource(R.color.color1);

                        break;
                    case R.id.menu_promociones:
                        frag = new PromocionesPrice().newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,frag).commit();
                        mBottomNav.setItemBackgroundResource(R.color.color2);

                        break;
                    case R.id.menu_multimedia:
                        frag = new MultimediaPrice().newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,frag).commit();
                        mBottomNav.setItemBackgroundResource(R.color.color3);

                        break;

                }

                return true;
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // Magic happens to center it.


    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }
    public static Tarjetas newInstance() {
        Tarjetas fragment = new Tarjetas();
        return fragment;
    }
    private int getColorFromRes(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }
}
