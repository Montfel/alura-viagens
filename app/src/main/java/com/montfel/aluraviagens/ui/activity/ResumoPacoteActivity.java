package com.montfel.aluraviagens.ui.activity;

import static com.montfel.aluraviagens.util.PacoteActivityConstantes.CHAVE_PACOTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.montfel.aluraviagens.R;
import com.montfel.aluraviagens.model.Pacote;
import com.montfel.aluraviagens.util.DataUtil;
import com.montfel.aluraviagens.util.DiasUtil;
import com.montfel.aluraviagens.util.MoedaUtil;
import com.montfel.aluraviagens.util.ResourceUtil;

public class ResumoPacoteActivity extends AppCompatActivity {

    private TextView local, dias, preco, data;
    private ImageView imagem;
    private Button realizaPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);
        setTitle("Resumo do pacote");

        carregaPacoteRecebido();
    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);
            incializaCampos(pacote);
            configuraBotao(pacote);
        }
    }

    private void configuraBotao(Pacote pacote) {
        realizaPagamento = findViewById(R.id.resumo_pacote_botao_realiza_pagamento);
        realizaPagamento.setOnClickListener(view -> {
            vaiParaPagamento(pacote);
        });
    }

    private void vaiParaPagamento(Pacote pacote) {
        Intent intentProxTela = new Intent(ResumoPacoteActivity.this, PagamentoActivity.class);
        intentProxTela.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intentProxTela);
    }

    private void incializaCampos(Pacote pacote) {
        mostraLocal(pacote);
        mostraImagem(pacote);
        mostraDias(pacote);
        mostraPreco(pacote);
        mostraData(pacote);
    }

    private void mostraData(Pacote pacote) {
        data = findViewById(R.id.resumo_pacote_data);
        String dataFormatadaViagem = DataUtil.periodoEmTexto(pacote.getDias());
        data.setText(dataFormatadaViagem);
    }

    private void mostraPreco(Pacote pacote) {
        preco = findViewById(R.id.resumo_pacote_preco);
        String moedaBrasileira = MoedaUtil.formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }

    private void mostraDias(Pacote pacote) {
        dias = findViewById(R.id.resumo_pacote_dias);
        String diasEmTexto = DiasUtil.formataEmTexto(pacote.getDias());
        dias.setText(diasEmTexto);
    }

    private void mostraImagem(Pacote pacote) {
        imagem = findViewById(R.id.resumo_pacote_imagem);
        Drawable drawableDoPacote = ResourceUtil.devolveDrawable(this, pacote.getImagem());
        imagem.setImageDrawable(drawableDoPacote);
    }

    private void mostraLocal(Pacote pacote) {
        local = findViewById(R.id.resumo_pacote_local);
        local.setText(pacote.getLocal());
    }
}