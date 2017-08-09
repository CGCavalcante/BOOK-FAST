package com.projeto.bookfast.bookfast.livro.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.percistencia.ReadLivro;
import com.projeto.bookfast.bookfast.livro.percistencia.UpdateLivro;
import com.projeto.bookfast.bookfast.negocio.LimparTela;
import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarIsbn;

public class TelaEditarLivroAdministrador extends AppCompatActivity {
    Button btEdtarLivro, btCancelar;
    EditText editIsbn, editNome, editGenero, editAutor, editEdicao, editAno, editQuantidadeTotal, editQuantidadeAlugada;
    Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_livro);
        editIsbn = (EditText) findViewById(R.id.editIsbn);
        editNome = (EditText) findViewById(R.id.editNome);
        editGenero = (EditText) findViewById(R.id.editGenero);
        editAutor = (EditText) findViewById(R.id.editAutor);
        editEdicao = (EditText) findViewById(R.id.editEdicao);
        editAno = (EditText) findViewById(R.id.editAno);
        editQuantidadeTotal = (EditText) findViewById(R.id.editQuantidadeTotal);
        editQuantidadeAlugada = (EditText) findViewById(R.id.editQuantidadeAlugada);

        btEdtarLivro = (Button) findViewById(R.id.btEditarLivro);
        btCancelar = (Button) findViewById(R.id.btCancelar);

        btEdtarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateLivro atualizarLivro = new UpdateLivro(getApplicationContext());
                ViewGroup group = (ViewGroup) findViewById(R.id.raizEditLivro);
                boolean resultado = false;
                String Isbn = editIsbn.getText().toString();
                String Nome = editNome.getText().toString();
                String QtAlugado = editQuantidadeAlugada.getText().toString();
                String Autor = editAutor.getText().toString();
                String Genero = editGenero.getText().toString();
                String QtTotal = editQuantidadeTotal.getText().toString();
                String Ano = editAno.getText().toString();
                String NumEdicao = editEdicao.getText().toString();

                if (ValidarIsbn.validarIsbn(Isbn)) {
                    resultado = true;
                    editIsbn.setError("Campo ISBN inválido!");
                    editIsbn.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(Nome)) {
                    resultado = true;
                    editNome.setError("Campo Nome inválido!");
                    editNome.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(QtAlugado)) {
                    resultado = true;
                    editQuantidadeAlugada.setError("Campo quantidade de livros alugados inválido!");
                    editQuantidadeAlugada.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(Autor)) {
                    resultado = true;
                    editAutor.setError("Campo Nome autor inválido!");
                    editAutor.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(Genero)) {
                    resultado = true;
                    editGenero.setError("Campo Gênero inválido!");
                    editGenero.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(QtTotal)) {
                    resultado = true;
                    editQuantidadeTotal.setError("Campo Quantidade total inválido!");
                    editQuantidadeTotal.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(Ano)) {
                    resultado = true;
                    editAno.setError("Campo Ano inválido!");
                    editAno.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(NumEdicao)) {
                    resultado = true;
                    editEdicao.setError("Campo Número da edição inválido!");
                    editEdicao.requestFocus();
                }
                if (!resultado) {
                    Long isbn = Long.parseLong(editIsbn.getText().toString());
                    int edicao = Integer.parseInt(editEdicao.getText().toString());
                    int ano = Integer.parseInt(editAno.getText().toString());
                    int quantidadeTotal = Integer.parseInt(editQuantidadeTotal.getText().toString());
                    int quanitdadeAlugada = Integer.parseInt(editQuantidadeAlugada.getText().toString());
                    String nome = editNome.getText().toString();
                    String genero = editGenero.getText().toString();
                    String autor = editAutor.getText().toString();
                    LimparTela.clearForm(group);
                    editIsbn.requestFocus();
                    ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
                    livro = buscarLivro.getLivro(isbn);
                    if (livro != null) {
                        livro.setIsbn(isbn);
                        livro.setNumEdicao(edicao);
                        livro.setAno(ano);
                        livro.setQtdTotal(quantidadeTotal);
                        livro.setQtdAlugado(quanitdadeAlugada);
                        livro.setNome(nome);
                        livro.setGenero(genero);
                        livro.setAutor(autor);
                        atualizarLivro.updateLivro(livro);
                        Toast.makeText(TelaEditarLivroAdministrador.this, R.string.AtualizcaoLivro, Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(TelaEditarLivroAdministrador.this, R.string.LvroNaoExiste, Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(TelaEditarLivroAdministrador.this, R.string.CampoInvalido, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}