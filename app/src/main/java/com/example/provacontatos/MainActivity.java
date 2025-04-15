package com.example.provacontatos;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.net.Uri;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.provacontatos.model.Contato;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private String numeroFavorito = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerContatos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Criando um array e preenchendo ele com instâncias de contatos
        List<Contato> contatos = new ArrayList<>();
        contatos.add(new Contato("Ana Silva", "62912345678", "ana@email.com", "https://linkedin.com/in/anasilva", false));
        contatos.add(new Contato("Carlos Souza", "62987654321", "carlos@email.com", "https://linkedin.com/in/carlossouza", true));

        // Descobrir qual o número favorito
        for (Contato c : contatos) {
            if (c.isFavorito()) {
                numeroFavorito = c.getTelefone();
                break;
            }
        }

        ContatoAdapter adapter = new ContatoAdapter(this, contatos); // Enviei os contatos da array para o adapter
        recyclerView.setAdapter(adapter); // Adapter mandou os contatos para a recyclerView em forma de cards

        // Configurando sensor de proximidade
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if (proximitySensor != null) {
                sensorManager.registerListener((SensorEventListener) this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] < proximitySensor.getMaximumRange()) {
                if (numeroFavorito != null) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + numeroFavorito));
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener((SensorListener) this);
    }
}
