package com.projeto.bookfast.bookfast.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.Pessoa;
import com.projeto.bookfast.bookfast.persistencia.BancoDados;
import com.projeto.bookfast.bookfast.persistencia.PessoaDao;

public class TelaCadastrar extends AppCompatActivity {
    EditText editNovoUsuario, editNovaSenha, editNovoEmail, editNovoNome, editNovoNasc, editNovoId;
    Button btRegistrar, btCancelarRegistro;
    PessoaDao pessoaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastrar);
        final PessoaDao pessoaDao = new PessoaDao(this);
        editNovoNome=(EditText) findViewById(R.id.editNovoNome);
        editNovoEmail=(EditText) findViewById(R.id.editNovoEmail);
        editNovoUsuario = (EditText) findViewById(R.id.editNovoUsuario);
        editNovaSenha = (EditText) findViewById(R.id.editNovaSenha);
        //editNovoNasc = (EditText) findViewById(R.id.editNovoNasc);

        btRegistrar = (Button) findViewById(R.id.btRegistrar);
        btCancelarRegistro = (Button) findViewById(R.id.btCancelarRegistro);

        btCancelarRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();

            }

        });

        btRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String cpf = editNovoUsuario.getText().toString();
                String nome = editNovoNome.getText().toString();
                String email = editNovoEmail.getText().toString();
                String senha = editNovaSenha.getText().toString();
                //String nasc = editNovoNasc.getText().toString();

                //Falta Validar os campos antes de adcionar
                Pessoa pessoa = new Pessoa(Long.parseLong(cpf),nome,email,senha);
                pessoaDao.addPessoa(pessoa);


            }

        });


    }
}
