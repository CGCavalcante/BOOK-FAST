package com.projeto.bookfast.book_fast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TelaRecuperarSenha extends Activity {
    EditText editCPF, editNovaSenha;
    Button btAlterarSenha, btCancelarAlteracao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__recuperar__senha);
        editCPF = (EditText) findViewById(R.id.editCPF);
        editNovaSenha = (EditText) findViewById(R.id.editNovaSenha);
        btAlterarSenha = (Button) findViewById(R.id.btAlterarSenha);
        btCancelarAlteracao = (Button) findViewById(R.id.btCancelarAlteracao);

        btAlterarSenha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ///Falta decidir
            }

        });
        btCancelarAlteracao.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });
        }

    }

