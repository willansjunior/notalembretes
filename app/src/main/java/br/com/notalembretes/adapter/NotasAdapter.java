package br.com.notalembretes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.notalembretes.R;
import br.com.notalembretes.adapter.listener.OnItemClickListener;
import br.com.notalembretes.model.Nota;

/**
 * Created by willans on 20/02/19.
 */
public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.NotasViewHolder> {

    private List<Nota> notas;

    private Context context;
    private OnItemClickListener onItemClickListener;

    public NotasAdapter(List<Nota> notas, Context context) {
        this.notas = notas;
        this.context = context;
    }

    @NonNull
    @Override
    public NotasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nota, viewGroup, false);
        return new NotasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotasViewHolder notasViewHolder, int i) {
        Nota nota = notas.get(i);

        notasViewHolder.vincular(nota);
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    class NotasViewHolder extends RecyclerView.ViewHolder {

        private final TextView titulo;
        private final TextView descricao;
        private Nota nota;

        public NotasViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao = itemView.findViewById(R.id.item_nota_descricao);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(nota, getAdapterPosition());
                }
            });
        }

        public void vincular(Nota nota) {
            this.nota = nota;
            setNotas(nota);
        }

        public void setNotas(Nota nota) {
            titulo.setText(nota.getTitulo());
            descricao.setText(nota.getDescricao());
        }
    }

    public void add(Nota nota) {
        notas.add(nota);
        notifyDataSetChanged();
    }

    public void update(int position, Nota nota) {
        notas.set(position, nota);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        notas.remove(position);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
