package criatoriovirtualapp.jhonata.criatoriovirtual.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import criatoriovirtualapp.jhonata.criatoriovirtual.Activities.MainActivity;
import criatoriovirtualapp.jhonata.criatoriovirtual.Database.PerfilPassaro;
import criatoriovirtualapp.jhonata.criatoriovirtual.R;

/**
 * Created by Jhonata Galante on 23/02/2018.
 * Contato: jhonata.galante@gmail.com
 */


public class PerfilPassaroAdapter extends RecyclerView.Adapter<PerfilPassaroAdapter.CViewHolder>{

    ClickRecyclerView_Interface clickRecyclerView_interface;
    MyClickDelete myClickDelete;
    List<PerfilPassaro> passaros;
    Context ctx;

    public PerfilPassaroAdapter(ClickRecyclerView_Interface clickRecyclerView_interface, List<PerfilPassaro> passaros, Context ctx, MyClickDelete myClickDelete) {
        this.clickRecyclerView_interface = clickRecyclerView_interface;
        this.passaros = passaros;
        this.ctx = ctx;
        this.myClickDelete = myClickDelete;
    }

    @Override
    public CViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itemlista, parent, false);
        return new CViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CViewHolder holder, final int position) {
        PerfilPassaro passaro = passaros.get(position);
        holder.viewNome.setText(passaro.getNome());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myClickDelete.onClickDelete(position) == true) {
                    removerItem(position);
                    myClickDelete.onClickDelete(position);
                }
            }
        });
    }

    private void removerItem(int position){
        passaros.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, passaros.size());
    }

    @Override
    public int getItemCount() {
        return passaros.size();
    }

    protected class CViewHolder extends RecyclerView.ViewHolder{

        protected TextView viewNome;
        protected ImageButton deleteButton;

        public CViewHolder(View itemView) {
            super(itemView);

            viewNome = itemView.findViewById(R.id.textview_nome);
            deleteButton = itemView.findViewById(R.id.btDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerView_interface.onClickCustom(passaros.get(getLayoutPosition()));
                }
            });
        }
    }

    public void setFilter(ArrayList<PerfilPassaro> novoArray){
        passaros = new ArrayList<>();
        passaros.addAll(novoArray);
        notifyDataSetChanged();
    }
}
