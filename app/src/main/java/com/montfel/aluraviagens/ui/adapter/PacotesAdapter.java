package com.montfel.aluraviagens.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.montfel.aluraviagens.model.Pacote;
import com.montfel.aluraviagens.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PacotesAdapter extends RecyclerView.Adapter<PacotesAdapter.MyViewHolder> {

    private final List<Pacote> listaPacote;
    private final Context context;

    public PacotesAdapter(List<Pacote> listaPacote, Context context) {
        this.listaPacote = listaPacote;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pacote, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pacote pacote = listaPacote.get(position);

        mostraImagem(holder, pacote);
        mostraLocal(holder, pacote);
        mostraDias(holder, pacote);
        mostraPreco(holder, pacote);
    }

    private void mostraLocal(@NonNull MyViewHolder holder, Pacote pacote) {
        holder.local.setText(pacote.getLocal());
    }

    private void mostraPreco(@NonNull MyViewHolder holder, Pacote pacote) {
        BigDecimal precoPacote = pacote.getPreco();
        NumberFormat formatoBrasileiro = DecimalFormat.getCurrencyInstance(new Locale("pt", "br"));
        String moedaBrasileira = formatoBrasileiro.format(precoPacote);
        holder.preco.setText(moedaBrasileira);
    }

    private void mostraDias(@NonNull MyViewHolder holder, Pacote pacote) {
        String diasEmTexto = pacote.getDias() + (pacote.getDias() == 1 ? " dia" : " dias");
        holder.dias.setText(diasEmTexto);
    }

    private void mostraImagem(@NonNull MyViewHolder holder, Pacote pacote) {
        Resources resources = context.getResources();
        int idDrawable = resources.getIdentifier(pacote.getImagem(), "drawable",
                context.getPackageName());
        Drawable drawable = resources.getDrawable(idDrawable);
        holder.imagem.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return this.listaPacote.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final ImageView imagem;
        final TextView local, dias, preco;

        public MyViewHolder(View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.item_pacote_imagem);
            local = itemView.findViewById(R.id.item_pacote_local);
            dias = itemView.findViewById(R.id.item_pacote_dias);
            preco = itemView.findViewById(R.id.item_pacote_preco);
        }
    }
}
