package criatoriovirtualapp.jhonata.criatoriovirtual.Activities;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import criatoriovirtualapp.jhonata.criatoriovirtual.Database.Database;
import criatoriovirtualapp.jhonata.criatoriovirtual.Database.PerfilPassaro;
import criatoriovirtualapp.jhonata.criatoriovirtual.R;
import criatoriovirtualapp.jhonata.criatoriovirtual.RecyclerView.ClickRecyclerView_Interface;
import criatoriovirtualapp.jhonata.criatoriovirtual.RecyclerView.MyClickDelete;
import criatoriovirtualapp.jhonata.criatoriovirtual.RecyclerView.PerfilPassaroAdapter;

/**
 * Created by Jhonata Galante on 23/02/2018.
 * Contato: jhonata.galante@gmail.com
 */

public class MainActivity extends AppCompatActivity implements ClickRecyclerView_Interface, android.support.v7.widget.SearchView.OnQueryTextListener, MyClickDelete {

    private android.support.v7.widget.Toolbar toolbarPrincipal;
    Database bd = Room.databaseBuilder(this, Database.class,
            "database_criatoriovirtual").allowMainThreadQueries().build();
    private RecyclerView recyclerView;
    private PerfilPassaroAdapter adapter;
    private List<PerfilPassaro> passaros;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton fltBt;
    private ImageView imgGaiola;
    private Layout land;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarPrincipal = findViewById(R.id.toolbarprincipalId);
        toolbarPrincipal.setTitle("Criatório Virtual");
        setSupportActionBar(toolbarPrincipal);
        imgGaiola = findViewById(R.id.gaiolaImg);
        fltBt = findViewById(R.id.FltBt);
        passaros = bd.daoAcess().getAll();

        if(passaros.size() == 0){
            imgGaiola.setVisibility(View.VISIBLE);
        }else{
            setarRecyclerView();
        }
        listenerButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.item_pesquisar).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    public void setarRecyclerView(){
        recyclerView = findViewById(R.id.recyclerviewmain);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (passaros!=null) {
            adapter = new PerfilPassaroAdapter(this, passaros, this, this);
            recyclerView.setAdapter(adapter);
        }else{

        }
    }

    public void listenerButtons(){
        fltBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClickCustom(final Object object) {
        PerfilPassaro nomepassaro = (PerfilPassaro) object;
        PerfilPassaro passaro = bd.daoAcess().findByName(nomepassaro.getNome());
        Intent intent = new Intent(getApplicationContext(), DadosActivity.class);
        intent.putExtra("nome", passaro.getNome());
        intent.putExtra("datanasc", passaro.getDatanasc());
        intent.putExtra("nanilha", passaro.getNanilha());
        intent.putExtra("nomepai", passaro.getNomepai());
        intent.putExtra("nomemae", passaro.getNomemae());
        intent.putExtra("nomeavompai", passaro.getNomeavompai());
        intent.putExtra("nomeavommae", passaro.getNomeavommae());
        intent.putExtra("nomeavofpai", passaro.getNomeavofpai());
        intent.putExtra("nomeavofmae", passaro.getNomeavofmae());
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();
        ArrayList<PerfilPassaro> novoArray = new ArrayList<>();
        if(passaros.size() != 0) {
            for (PerfilPassaro passaro : passaros) {
                String nome = passaro.getNome().toLowerCase();
                if (nome.contains(newText)) { //Verifica se contém o texto digitado nos dados do recycler
                    novoArray.add(passaro);
                }
            }
            adapter.setFilter(novoArray); //Cria um array com todos os dados que batem com o texto digitado e cria um novo array
        }
        return true;
    }

    @Override
    public boolean onClickDelete(final int position) {
        final boolean[] resp = new boolean[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setTitle("Confirmação");
        builder.setMessage("Tem certeza?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                passaros = bd.daoAcess().getAll();
                PerfilPassaro passaro = passaros.get(position);
                bd.daoAcess().delete(bd.daoAcess().findByName(passaro.getNome()));
                passaros = bd.daoAcess().getAll();
                setarRecyclerView();
                if(passaros.size() == 0){
                    imgGaiola.setVisibility(View.VISIBLE);
                }
                dialog.dismiss();
                resp[0] = true;

            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resp[0] = false;
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        return resp[0];
        }
}
