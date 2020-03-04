package br.com.notalembretes.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.notalembretes.model.Nota;

/**
 * Created by willans on 20/02/19.
 */
public class NotaDAO {

    private final static ArrayList<Nota> notas = new ArrayList<>();

    public List<Nota> listAll() {
        return (List<Nota>) notas.clone();
    }

    public void create(Nota... notas) {
        NotaDAO.notas.addAll(Arrays.asList(notas));
    }

    public void update(int position, Nota nota) {
        notas.set(position, nota);
    }

    public void delete(int position) {
        notas.remove(position);
    }

    public void trade(int posicaoInicio, int posicaoFim) {
        Collections.swap(notas, posicaoInicio, posicaoFim);
    }

    public void removeAll() {
        notas.clear();
    }

}
