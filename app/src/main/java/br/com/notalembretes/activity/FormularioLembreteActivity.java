package br.com.notalembretes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.notalembretes.R;
import br.com.notalembretes.model.Nota;
import br.com.notalembretes.util.Constante;

import static br.com.notalembretes.util.Constante.NOTA;
import static br.com.notalembretes.util.Constante.POSICAO_INVALIDA;
import static br.com.notalembretes.util.Constante.POSITION;

public class FormularioLembreteActivity extends AppCompatActivity {


    private EditText edtTitulo;
    private EditText edtDescricao;

    private int position = POSICAO_INVALIDA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_lembrete);

        initViews();

        Intent intent = getIntent();
        if (intent.hasExtra(Constante.NOTA)) {
            Nota nota = (Nota) intent.getSerializableExtra(Constante.NOTA);
            position = intent.getIntExtra(POSITION, POSICAO_INVALIDA);
            montarNota(nota);
        }
    }

    private void montarNota(Nota nota) {
        edtTitulo.setText(nota.getTitulo());
        edtDescricao.setText(nota.getDescricao());
    }

    private void initViews() {
        edtTitulo = findViewById(R.id.edt_formulario_titulo);
        edtDescricao = findViewById(R.id.edt_formulario_descricao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_save:
                gerarResult();
        }

        return super.onOptionsItemSelected(item);
    }

    private void gerarResult() {
        Nota nota = new Nota(edtTitulo.getText().toString(), edtDescricao.getText().toString());
        Intent intent = new Intent();
        intent.putExtra(NOTA, nota);
        intent.putExtra(POSITION, position);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
