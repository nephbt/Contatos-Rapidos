package com.example.provacontatos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.provacontatos.ContatoActivity;
import com.example.provacontatos.R;
import com.example.provacontatos.model.Contato;

import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    private Context context;
    private List<Contato> listaContatos;

    public ContatoAdapter(Context context, List<Contato> listaContatos) {
        this.context = context;
        this.listaContatos = listaContatos;
    }

    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.contatos_cards, parent, false);
        return new ContatoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder holder, int position) {
        Contato contato = listaContatos.get(position);
        holder.nomeContato.setText(contato.getNome());

        // Clique no card abre ContatoActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ContatoActivity.class);
            intent.putExtra("nome", contato.getNome());
            intent.putExtra("telefone", contato.getTelefone());
            intent.putExtra("email", contato.getEmail());
            intent.putExtra("linkedin", contato.getLinkedin());
            intent.putExtra("favorito", contato.isFavorito());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaContatos.size();
    }

    public static class ContatoViewHolder extends RecyclerView.ViewHolder {

        TextView nomeContato;
        ImageView imageView;

        public ContatoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeContato = itemView.findViewById(R.id.nomeContato);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
