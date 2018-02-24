package criatoriovirtualapp.jhonata.criatoriovirtual.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import criatoriovirtualapp.jhonata.criatoriovirtual.Database.Database;
import criatoriovirtualapp.jhonata.criatoriovirtual.Database.PerfilPassaro;
import criatoriovirtualapp.jhonata.criatoriovirtual.R;

/**
 * Created by Jhonata Galante on 23/02/2018.
 * Contato: jhonata.galante@gmail.com
 */

public class EditActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView nomeView, nanilhaView;
    private EditText dataNasc, nomePai, nomeMae, nomeAvoMPai, nomeAvoMMae, nomeAvoFPai, nomeAvoFMae;
    Database bd = Room.databaseBuilder(this, Database.class,
            "database_criatoriovirtual").allowMainThreadQueries().build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        toolbar = findViewById(R.id.toolbarprincipalId);
        toolbar.setTitle("Editar Registro");
        setSupportActionBar(toolbar);

        nomeView = findViewById(R.id.nomeview);
        nanilhaView = findViewById(R.id.nanilhaView);
        dataNasc = findViewById(R.id.datanascEdit);
        nomePai = findViewById(R.id.nomePaiEdit);
        nomeMae = findViewById(R.id.nomeMaeEdit);
        nomeAvoMPai = findViewById(R.id.nomeAvoMPaiEdit);
        nomeAvoMMae =findViewById(R.id.nomeAvoMMaeEdit);
        nomeAvoFPai = findViewById(R.id.nomeAvoFPaiEdit);
        nomeAvoFMae = findViewById(R.id.nomeAvoFMaeEdit);

        Intent intent = getIntent();

        nomeView.setText(intent.getStringExtra("nome"));
        nanilhaView.setText(intent.getStringExtra("nanilha"));
        dataNasc.setText(intent.getStringExtra("datanasc"));
        nomePai.setText(intent.getStringExtra("nomepai"));
        nomeMae.setText(intent.getStringExtra("nomemae"));
        nomeAvoMPai.setText(intent.getStringExtra("nomeavompai"));
        nomeAvoMMae.setText(intent.getStringExtra("nomeavommae"));
        nomeAvoFPai.setText(intent.getStringExtra("nomeavofpai"));
        nomeAvoFMae.setText(intent.getStringExtra("nomeavofmae"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_editar:
                PerfilPassaro perfil = new PerfilPassaro();
                perfil.setNome(nomeView.getText().toString());
                perfil.setDatanasc(dataNasc.getText().toString());
                perfil.setNanilha(nanilhaView.getText().toString());
                perfil.setNomepai(nomePai.getText().toString());
                perfil.setNomemae(nomeMae.getText().toString());
                perfil.setNomeavompai(nomeAvoMPai.getText().toString());
                perfil.setNomeavommae(nomeAvoMMae.getText().toString());
                perfil.setNomeavofpai(nomeAvoFPai.getText().toString());
                perfil.setNomeavofmae(nomeAvoFMae.getText().toString());
                updatePerfil(perfil);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Toast.makeText(this, "Registro Atualizado", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
                break;
            case R.id.item_voltar:
                Intent intentback = new Intent(getApplicationContext(), MainActivity.class);
                intentback.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentback);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    void updatePerfil(final PerfilPassaro perfil){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                bd.daoAcess().update(perfil);
                return null;
            }
        }.execute();
    }
}
