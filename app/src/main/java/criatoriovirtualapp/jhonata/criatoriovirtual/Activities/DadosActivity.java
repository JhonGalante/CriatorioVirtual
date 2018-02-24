package criatoriovirtualapp.jhonata.criatoriovirtual.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import criatoriovirtualapp.jhonata.criatoriovirtual.R;
/**
 * Created by Jhonata Galante on 23/02/2018.
 * Contato: jhonata.galante@gmail.com
 */

public class DadosActivity extends AppCompatActivity {

    private Toolbar toolbarFormulario;
    private Button btVoltar;
    private TextView nomeView, datanascView, anilhaView, nomePaiView, nomeMaeView, nomeAvoMPai, nomeAvoMMae, nomeAvoFPai, nomeAvoFMae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        toolbarFormulario = findViewById(R.id.toolbarprincipalId);
        toolbarFormulario.setTitle("Registro");
        setSupportActionBar(toolbarFormulario);

        nomeView = findViewById(R.id.nome);
        datanascView = findViewById(R.id.datanasc);
        anilhaView = findViewById(R.id.nanilha);
        nomePaiView = findViewById(R.id.nomepai);
        nomeMaeView = findViewById(R.id.nomemae);
        nomeAvoMPai = findViewById(R.id.nomeavompai);
        nomeAvoMMae = findViewById(R.id.nomeavommae);
        nomeAvoFPai = findViewById(R.id.nomeavofpai);
        nomeAvoFMae = findViewById(R.id.nomeavofmae);

        final Intent intent = getIntent();
        nomeView.setText(intent.getStringExtra("nome"));
        datanascView.setText(intent.getStringExtra("datanasc"));
        anilhaView.setText(intent.getStringExtra("nanilha"));
        nomePaiView.setText(intent.getStringExtra("nomepai"));
        nomeMaeView.setText(intent.getStringExtra("nomemae"));
        nomeAvoMPai.setText(intent.getStringExtra("nomeavompai"));
        nomeAvoMMae.setText(intent.getStringExtra("nomeavommae"));
        nomeAvoFPai.setText(intent.getStringExtra("nomeavofpai"));
        nomeAvoFMae.setText(intent.getStringExtra("nomeavofmae"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_editar:
                Intent mainintent = getIntent();
                Bundle bd = mainintent.getExtras();
                Intent intent2 = new Intent (getApplicationContext(), EditActivity.class);
                intent2.putExtras(bd);
                startActivity(intent2);
                break;
            case R.id.item_voltar:
                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent3);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_perfil, menu);
        return true;
    }


}
