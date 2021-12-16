package com.montfel.aluraviagens.ui.activity;

import static com.montfel.aluraviagens.util.PacoteActivityConstantes.CHAVE_PACOTE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.montfel.aluraviagens.R;
import com.montfel.aluraviagens.ui.adapter.PacotesAdapter;
import com.montfel.aluraviagens.dao.PacoteDAO;
import com.montfel.aluraviagens.model.Pacote;
import com.montfel.aluraviagens.util.RecyclerItemClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.pacotes));

        configuraRecyclerView();
    }

    private void configuraRecyclerView() {
        RecyclerView rvListaPacotes = findViewById(R.id.lista_pacotes_recyclerview);
        rvListaPacotes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvListaPacotes.setHasFixedSize(true);
        List<Pacote> listaPacotes = new PacoteDAO().lista();
        rvListaPacotes.setAdapter(new PacotesAdapter(listaPacotes, this));
        rvListaPacotes.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                rvListaPacotes,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                    }

                    @Override
                    public void onItemClick(View view, int position) {
                        vaiParaResumoPacote(position, listaPacotes);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                }
        ));
    }

    private void vaiParaResumoPacote(int position, List<Pacote> listaPacotes) {
        Pacote pacote = listaPacotes.get(position);
        Intent intent = new Intent(MainActivity.this, ResumoPacoteActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);
    }
}