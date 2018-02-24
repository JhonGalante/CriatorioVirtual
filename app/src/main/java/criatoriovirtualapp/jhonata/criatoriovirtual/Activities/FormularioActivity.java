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
import android.widget.Toast;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import criatoriovirtualapp.jhonata.criatoriovirtual.Database.Database;
import criatoriovirtualapp.jhonata.criatoriovirtual.Database.PerfilPassaro;
import criatoriovirtualapp.jhonata.criatoriovirtual.R;

/**
 * Created by Jhonata Galante on 23/02/2018.
 * Contato: jhonata.galante@gmail.com
 */

public class FormularioActivity extends AppCompatActivity {

    private Toolbar toolbarFormulario;
    private EditText nome, nAnilha, nomePai, nomeMae, nomeAvoMPai, nomeAvoMMae, nomeAvoFPai, nomeAvoFMae;
    private MaskedEditText dataNasc;
    PerfilPassaro perfil = new PerfilPassaro();
    Database bd = Room.databaseBuilder(this, Database.class,
            "database_criatoriovirtual").allowMainThreadQueries().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        toolbarFormulario = findViewById(R.id.toolbarprincipalId);
        toolbarFormulario.setTitle("Formulário de cadastro");
        setSupportActionBar(toolbarFormulario);

        nome = findViewById(R.id.nomeEdit);
        dataNasc = findViewById(R.id.datanascEdit);
        nAnilha = findViewById(R.id.nanilhaEdit);
        nomePai = findViewById(R.id.nomepaiEdit);
        nomeMae = findViewById(R.id.nomemaeEdit);
        nomeAvoMPai = findViewById(R.id.nomeavompaiEdit);
        nomeAvoMMae = findViewById(R.id.nomeavommaeEdit);
        nomeAvoFPai = findViewById(R.id.nomeavofpaiEdit);
        nomeAvoFMae = findViewById(R.id.nomeavofmaeEdit);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.item_cadastrar:
                if(nome.getText().toString().equals("") || nAnilha.getText().toString().equals("")) {
                    Toast.makeText(FormularioActivity.this, "Favor preencher os campos obrigatórios", Toast.LENGTH_SHORT).show();
                }else {
                    perfil.setNome(nome.getText().toString());
                    perfil.setDatanasc(dataNasc.getText().toString());
                    perfil.setNanilha(nAnilha.getText().toString());
                    perfil.setNomepai(nomePai.getText().toString());
                    perfil.setNomemae(nomeMae.getText().toString());
                    perfil.setNomeavofpai(nomeAvoFPai.getText().toString());
                    perfil.setNomeavofmae(nomeAvoFMae.getText().toString());
                    perfil.setNomeavompai(nomeAvoMPai.getText().toString());
                    perfil.setNomeavommae(nomeAvoMMae.getText().toString());
                    if(bd.daoAcess().findByAnilha(perfil.getNanilha()) != null){
                        Toast.makeText(this, "Perfil atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                    inserirPerfil(perfil);

                    //Limpa histórico de todas as activities abertas
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.item_voltar:
                Intent intent = new Intent(FormularioActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    void inserirPerfil(final PerfilPassaro perfil){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                bd.daoAcess().insertSingle(perfil);
                return null;
            }
        }.execute();
    }
}
