package com.projeto.bookfast.bookfast.aluguel.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.aluguel.dominio.Aluguel;
import com.projeto.bookfast.bookfast.aluguel.persistencia.AluguelDao;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.UpdateLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.UpdatePessoa;

/**
 * classe DevolverLivro cria uma associação de Context e outra de AluguelDao.
 */

public class DevolverLivro {
    private Context context;
    private AluguelDao aluguelDao;

    public DevolverLivro(Context context) {
        this.context = context;
        aluguelDao = new AluguelDao(this.context);
    }
    public boolean devolverLivro(Livro livro, Pessoa pessoa){
        UpdateLivro atualizaLivro = new UpdateLivro(context);
        UpdatePessoa atualizaPessoa = new UpdatePessoa(context);
        Aluguel aluguel = null;
        String[] idsAluguel = pessoa.getListaAluguel().trim().split(" ");
        String temp = String.valueOf(livro.getId());
        String novaListaIdsPessoa = "";
        String temp2  = "0";
        for (String idAluguel : idsAluguel) {
            if (idAluguel.trim().equals("")) {
                continue;
            }
            Aluguel temporario = aluguelDao.getAluguel(Integer.parseInt(idAluguel));
            if (temp.equals(String.valueOf(temporario.getIdLivro()))) {
                aluguel = temporario;
                temp2 = "1";
            } else {
                novaListaIdsPessoa += " " + idAluguel;
            }
        }
        if (temp2.equals("1") ){
            aluguel.setStatus("0");
            aluguelDao.updateAluguel(aluguel);
            livro.setQtdAlugado(livro.getQtdAlugado()-1);
            atualizaLivro.updateLivro(livro);
            pessoa.setListaAluguel(novaListaIdsPessoa);
            atualizaPessoa.updatePessoa(pessoa);
            return true;
        } else {
            return false;
        }
    }
}
