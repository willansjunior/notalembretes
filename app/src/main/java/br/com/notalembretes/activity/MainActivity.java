package br.com.notalembretes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.com.notalembretes.R;
import br.com.notalembretes.dao.NotaDAO;
import br.com.notalembretes.model.Nota;
import br.com.notalembretes.adapter.NotasAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListaLembretes;

    private List<Nota> notas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mountNotas();
    }

    private void initViews() {
        rvListaLembretes = findViewById(R.id.rv_notas);
    }

    private void mountNotas() {
        NotaDAO dao = new NotaDAO();
//        for (int i = 0; i < 10000; i++) {
//            dao.create(new Nota(getString(R.string.label_titulo_exemplo) + i,
//                    getString(R.string.label_descricao_exemplo) + i));
//        }
        dao.create(new Nota("Pequena", "Descrição"));
        dao.create(new Nota("Grande", "Teste de descrição de exmplo de crescimento de layout"));
        dao.create(new Nota("Testando", "Mais um teste de view!"));

        notas = dao.listAll();
        rvListaLembretes.setAdapter(new NotasAdapter(notas, this));
    }
}
