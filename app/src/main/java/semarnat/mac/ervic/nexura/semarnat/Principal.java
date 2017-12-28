package semarnat.mac.ervic.nexura.semarnat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.pushbots.push.Pushbots;

public class Principal extends AppCompatActivity {
    private ImageView iv_logo, iv_mensajes;
    private ImageButton btn_apartadoA, btn_apartadoB, btn_apartadoC, btn_apartadoD;
    static boolean active = false;
    private static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mContext = this;
        btn_apartadoA = (ImageButton) findViewById(R.id.btn_apartado_a);
        btn_apartadoC = (ImageButton) findViewById(R.id.btn_apartado_c);
        iv_logo = (ImageView) findViewById(R.id.iv_home_logo);
        iv_mensajes = (ImageView) findViewById(R.id.iv_mensajes);
        Pushbots.sharedInstance().registerForRemoteNotifications();
//Register for Push Notifications

        iv_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        iv_mensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this,Mensajes.class);
                startActivity(intent);
            }
        });

        btn_apartadoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Socio comercial inactivo.", Toast.LENGTH_SHORT).show();
                /**
                Intent intent = new Intent(Principal.this,ApartadoA.class);
                startActivity(intent);
                 **/
            }
        });



        btn_apartadoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this,ApartadoE.class);
                startActivity(intent);
            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_mensajes:
                    Intent intent = new Intent(Principal.this,Mensajes.class);
                    startActivity(intent);
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    public static boolean isActive(){
        Intent intent = new Intent(mContext,Mensajes.class);
        mContext.startActivity(intent);
        return active;
    }

    public void onStop(){
        super.onStop();
        active = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

}
