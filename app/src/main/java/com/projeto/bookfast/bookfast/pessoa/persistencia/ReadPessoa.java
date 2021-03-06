package com.projeto.bookfast.bookfast.pessoa.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;

import java.util.ArrayList;

/**
 * classe ReadPessoa cria associações das classes SQLiteDatabase e CreatBancoDados
 */

public class ReadPessoa {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;

    /**
     * Construtor da classe ReadPessoa
     */
    public ReadPessoa(Context context) {
        dbHelper = new CreatBancoDados(context);
    }

    public ReadPessoa() {
    }

    /**
     * método getListaCpfPessoas retorna uma lista com os Cpf dos usuários.
     */
    public ArrayList<Long> getListaCpfPessoas() {
        db = dbHelper.getReadableDatabase();
        ArrayList<Long> listaCpf = new ArrayList<Long>();
        String getPessoas = "SELECT * FROM " + CreatBancoDados.getNomeTabelaPessoa();
        try {
            Cursor cursor = db.rawQuery(getPessoas, null);
            if (cursor.moveToFirst()) {
                do {
                    listaCpf.add(cursor.getLong(1));
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }
        return listaCpf;
    }

    /** método getListaPessoas retorna uma lista com os usuários.*/
    public ArrayList<Pessoa> getListaPessoas() {
        db = dbHelper.getReadableDatabase();
        ArrayList<Pessoa> listaPessoa = new ArrayList<Pessoa>();
        String getPessoas = "SELECT * FROM " + CreatBancoDados.getNomeTabelaPessoa();
        try {
            Cursor cursor = db.rawQuery(getPessoas, null);
            if (cursor.moveToFirst()) {
                do {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(cursor.getInt(0));
                    pessoa.setCpf(cursor.getLong(1));
                    pessoa.setNome(cursor.getString(2));
                    pessoa.setEmail(cursor.getString(3));
                    pessoa.setSenha(cursor.getString(4));
                    pessoa.setListaAluguel(cursor.getString(5));
                    pessoa.setStatus(cursor.getString(6));
                    pessoa.setCurso(cursor.getString(7));
                    listaPessoa.add(pessoa);
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }
        return listaPessoa;
    }

    /** método getPessoa retorna a pessoa através de seu Cpf.*/
    public Pessoa getPessoa(Long cpf) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(CreatBancoDados.getNomeTabelaPessoa(), new String[]{CreatBancoDados.getColunaId(), CreatBancoDados.getColunaCpf(),
                        CreatBancoDados.getColunaNome(), CreatBancoDados.getColunaEmail(), CreatBancoDados.getColunaSenha(),
                        CreatBancoDados.getColunaIdsAluguel(), CreatBancoDados.getColunaStatusPessoa(), CreatBancoDados.getColunaCursoPessoa()},
                CreatBancoDados.getColunaCpf() + " = ?",
                new String[]{String.valueOf(cpf)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Pessoa pessoa = new Pessoa(cursor.getInt(0), cursor.getLong(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));

            cursor.close();
            db.close();
            return pessoa;
        } else {
            db.close();
            return null;
        }

    }

}
