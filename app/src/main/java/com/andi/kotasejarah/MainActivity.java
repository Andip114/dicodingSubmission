package com.andi.kotasejarah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvKota;
    private ArrayList<Kota> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvKota = findViewById(R.id.rv_kota);
        rvKota.setHasFixedSize(true);

        list.addAll(KotaData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvKota.setLayoutManager(new LinearLayoutManager(this));
        ListKotaAdapter listKotaAdapter = new ListKotaAdapter(list);
        rvKota.setAdapter(listKotaAdapter);

        listKotaAdapter.setOnItemClickCallback(new ListKotaAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Kota data) {
                showSelectedData(data);
            }
        });
    }

    public void showSelectedData(Kota l) {
        Intent detail = new Intent(MainActivity.this, DetailKota.class);
        detail.putExtra(DetailKota.EXTRA_NAMAKOTA, l.getName());
        detail.putExtra(DetailKota.EXTRA_BERDIRI, l.getBerdiri());
        detail.putExtra(DetailKota.EXTRA_PENGERTIAN, l.getDetail());
        detail.putExtra(DetailKota.EXTRA_SEJARAH, l.getSejarah());
        detail.putExtra(DetailKota.EXTRA_IMG, l.getPhoto());
        startActivity(detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent about = new Intent(MainActivity.this, About.class);
        startActivity(about);
        return super.onOptionsItemSelected(item);
    }
}