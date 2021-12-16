package com.montfel.aluraviagens.ui.activity;

import static com.montfel.aluraviagens.util.PacoteActivityConstantes.CHAVE_PACOTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.montfel.aluraviagens.R;
import com.montfel.aluraviagens.model.Pacote;
import com.montfel.aluraviagens.util.MoedaUtil;

public class PagamentoActivity extends AppCompatActivity {

    private TextView preco;
    private EditText numeroCartao, mesCartao, anoCartap, cvc, nomeCartao;
    private Button finalizaCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle("Pagamento");
        carregaPacoteRecebido();
    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);
            mostraPreco(pacote);
            configuraBotao(pacote);
        }
    }

    private void configuraBotao(Pacote pacote) {
        finalizaCompra = findViewById(R.id.pagamento_botao_finaliza_compra);
        finalizaCompra.setOnClickListener(view -> {
            vaiParaResumoCompra(pacote);
        });
    }

    private void vaiParaResumoCompra(Pacote pacote) {
        Intent intentProxtela = new Intent(PagamentoActivity.this, ResumoCompraActivity.class);
        intentProxtela.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intentProxtela);
    }

    private void mostraPreco(Pacote pacote) {
        preco = findViewById(R.id.pagamento_preco_pacote);
        String moedaBrasileira = MoedaUtil.formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }
}