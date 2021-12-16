package com.montfel.aluraviagens.ui.activity;

import static com.montfel.aluraviagens.util.PacoteActivityConstantes.CHAVE_PACOTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.montfel.aluraviagens.R;
import com.montfel.aluraviagens.model.Pacote;
import com.montfel.aluraviagens.util.DataUtil;
import com.montfel.aluraviagens.util.MoedaUtil;
import com.montfel.aluraviagens.util.ResourceUtil;

public class ResumoCompraActivity extends AppCompatActivity {

    private ImageView imagem;
    private TextView local, data, preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);
        setTitle("Resumo da compra");

        carregaPacoteRecebido();
    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)) {
            Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);
            incializaCampos(pacote);
        }
    }

    private void incializaCampos(Pacote pacote) {
        mostraImagem(pacote);
        mostraLocal(pacote);
        mostraData(pacote);
        mostraPreco(pacote);
    }

    private void mostraPreco(Pacote pacote) {
        preco = findViewById(R.id.resumo_compra_preco_pacote);
        String moedaBrasileira = MoedaUtil.formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }

    private void mostraData(Pacote pacote) {
        data = findViewById(R.id.resumo_compra_data_pacote);
        String periodoEmTexto = DataUtil.periodoEmTexto(pacote.getDias());
        data.setText(periodoEmTexto);
    }

    private void mostraLocal(Pacote pacote) {
        local = findViewById(R.id.resumo_compra_local_pacote);
        local.setText(pacote.getLocal());
    }

    private void mostraImagem(Pacote pacote) {
        imagem = findViewById(R.id.resumo_compra_imagem_pacote);
        Drawable drawable = ResourceUtil.devolveDrawable(this, pacote.getImagem());
        imagem.setImageDrawable(drawable);
    }
}