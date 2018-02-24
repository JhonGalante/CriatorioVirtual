package criatoriovirtualapp.jhonata.criatoriovirtual.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import criatoriovirtualapp.jhonata.criatoriovirtual.R;

/**
 * Created by Jhonata Galante on 23/02/2018.
 * Contato: jhonata.galante@gmail.com
 * Imagens: https://www.vexels.com/
 */


public class LogoAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_app);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(LogoAppActivity.this, MainActivity.class);
                startActivity(intent);
                LogoAppActivity.this.finish();
            }
        }, 2500);
    }
}
