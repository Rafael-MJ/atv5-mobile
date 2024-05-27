package com.rafaelmj.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafaelmj.myapplication.R;
import com.rafaelmj.myapplication.models.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private List<Aluno> alunos;

    public AlunoAdapter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno, parent, false);
        return new AlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = alunos.get(position);
        holder.tvNome.setText(aluno.getNome());
        holder.tvRa.setText(String.valueOf(aluno.getRa()));
        holder.tvEndereco.setText(String.format("%s, %s, %s, %s - %s", aluno.getLogradouro(), aluno.getComplemento(), aluno.getBairro(), aluno.getCidade(), aluno.getUf()));
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvRa, tvEndereco;

        AlunoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvRa = itemView.findViewById(R.id.tvRa);
            tvEndereco = itemView.findViewById(R.id.tvEndereco);
        }
    }
}
