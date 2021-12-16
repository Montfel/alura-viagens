package com.montfel.aluraviagens.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.montfel.aluraviagens.R;
import com.montfel.aluraviagens.ui.adapter.PacotesAdapter;
import com.montfel.aluraviagens.dao.PacoteDAO;
import com.montfel.aluraviagens.model.Pacote;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.pacotes));

        configuraRecyclerView();
        Intent intent = new Intent(this, ResumoPacoteActivity.class);
        startActivity(intent);

    }

    private void configuraRecyclerView() {
        RecyclerView rvListaPacotes = findViewById(R.id.lista_pacotes_recyclerview);
        rvListaPacotes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvListaPacotes.setHasFixedSize(true);
        List<Pacote> listaPacotes = new PacoteDAO().lista();
        rvListaPacotes.setAdapter(new PacotesAdapter(listaPacotes, this));
    }
}