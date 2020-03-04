package br.com.notalembretes.adapter.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import br.com.notalembretes.adapter.NotasAdapter;
import br.com.notalembretes.dao.NotaDAO;

/**
 * Created by willans on 22/02/19.
 */
public class NotaItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final NotasAdapter adapter;

    public NotaItemTouchHelperCallback(NotasAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcadorDeslize = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int marcacaoArrastar = ItemTouchHelper.DOWN | ItemTouchHelper.UP
                | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(marcacaoArrastar, marcadorDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        new NotaDAO().trade(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
        adapter.trade(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int position = viewHolder.getAdapterPosition();
        new NotaDAO().delete(position);
        adapter.remove(position);
    }
}
