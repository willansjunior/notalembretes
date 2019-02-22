package br.com.notalembretes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.notalembretes.R;
import br.com.notalembretes.adapter.NotasAdapter;
import br.com.notalembretes.adapter.listener.OnItemClickListener;
import br.com.notalembretes.dao.NotaDAO;
import br.com.notalembretes.model.Nota;
import br.com.notalembretes.util.Constante;

import static br.com.notalembretes.util.Constante.NOTA;
import static br.com.notalembretes.util.Constante.POSICAO_INVALIDA;
import static br.com.notalembretes.util.Constante.POSITION;
import static br.com.notalembretes.util.Constante.REQUEST_CODE;
import static br.com.notalembretes.util.Constante.REQUEST_CODE_UPDATE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListaLembretes;

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
                loadFormulario();
            }
        });
    }

    private void loadFormulario() {
        Intent intent = new Intent(MainActivity.this, FormularioLembreteActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void mountNotas() {
        adapter = new NotasAdapter(dao.listAll(), this);
        rvListaLembretes.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Nota nota, int position) {
                Intent intent = new Intent(MainActivity.this, FormularioLembreteActivity.class);
                intent.putExtra(Constante.NOTA, nota);
                intent.putExtra(POSITION, position);
                startActivityForResult(intent, REQUEST_CODE_UPDATE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (isCode(requestCode, REQUEST_CODE) && isCode(resultCode, Activity.RESULT_OK) && data.hasExtra(NOTA)) {
            Nota novaNota = (Nota) data.getSerializableExtra(NOTA);
            new NotaDAO().create(novaNota);
            adapter.add(novaNota);
        }

        if (isCode(requestCode, REQUEST_CODE_UPDATE) && isCode(resultCode, Activity.RESULT_OK)
                && data.hasExtra(NOTA) && data.hasExtra(POSITION)) {
            Nota novaNota = (Nota) data.getSerializableExtra(NOTA);
            int position = data.getIntExtra(POSITION, POSICAO_INVALIDA);
            new NotaDAO().update(position, novaNota);
            adapter.update(position, novaNota);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean isCode(int requestCode, int requestCode2) {
        return requestCode == requestCode2;
    }
}
