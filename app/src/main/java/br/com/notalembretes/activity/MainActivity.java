package br.com.notalembretes.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import br.com.notalembretes.R;
import br.com.notalembretes.dao.NotaDAO;
import br.com.notalembretes.model.Nota;
import br.com.notalembretes.adapter.NotasAdapter;
import br.com.notalembretes.util.Constante;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListaLembretes;

    private List<Nota> notas;

    private TextView txtNovaNota;

    private NotaDAO dao;
    private NotasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        dao = new NotaDAO();

        mountNotas();
    }

    private void initViews() {
        rvListaLembretes = findViewById(R.id.rv_notas);

        txtNovaNota = findViewById(R.id.txt_nova_nota);
        txtNovaNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioLembreteActivity.class);
                startActivityForResult(intent, Constante.REQUEST_CODE);
            }
        });
    }

    private void mountNotas() {
        notas = dao.listAll();
        adapter = new NotasAdapter(notas, this);
        rvListaLembretes.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constante.REQUEST_CODE && requestCode == Constante.RESPONSE_CODE && data.hasExtra(Constante.NOTA)) {
            Nota novaNota = (Nota) data.getSerializableExtra(Constante.NOTA);
            new NotaDAO().create(novaNota);
            adapter.add(novaNota);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
