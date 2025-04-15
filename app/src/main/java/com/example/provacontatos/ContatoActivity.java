package com.example.provacontatos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContatoActivity extends AppCompatActivity {

    TextView nome, telefone;
    ImageView  telefoneButton, linkedinButton, emailButton, voltarButton ,starIcon;
    String numeroTelefone, emailContato, linkedinUrl;
    boolean favorito;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        voltarButton = findViewById(R.id.voltarImgButton);
        nome = findViewById(R.id.nomeTxt);
        telefone = findViewById(R.id.numeroTxt);
        telefoneButton = findViewById(R.id.telefoneImgButton);
        linkedinButton = findViewById(R.id.linkedinImgButton);
        emailButton = findViewById(R.id.emailImgButton);
        starIcon = findViewById(R.id.starIcon);

        // Pegando os dados da intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nome.setText(extras.getString("nome"));
            telefone.setText(extras.getString("telefone"));

            numeroTelefone = extras.getString("telefone");
            emailContato = extras.getString("email");
            linkedinUrl = extras.getString("linkedin");

            favorito = getIntent().getBooleanExtra("favorito", false);
            // Se o contato for favorito, a estrelinha estará preenchida. Caso contrário, será uma estrela vazia.
            if (favorito){
                starIcon.setImageResource(R.drawable.star_on);
            } else {
                starIcon.setImageResource(R.drawable.star_off);
            }
        }


        // As ImageView possuem a função de button:

        // Botão para voltar a tela
        voltarButton.setOnClickListener(v -> {
            Intent intent = new Intent(ContatoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });


        // Botão para abrir o telefone
        telefoneButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + numeroTelefone));
            startActivity(intent);
        });

        // Botão para abrir o email
        emailButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + emailContato));
            startActivity(intent);
        });

        // Botão para abrir o linkedin
        linkedinButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(linkedinUrl));
            startActivity(intent);
        });

    }
}
