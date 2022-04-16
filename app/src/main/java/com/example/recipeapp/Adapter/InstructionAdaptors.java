package com.example.recipeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.InstructionResponse;
import com.example.recipeapp.R;

import java.util.List;

public class InstructionAdaptors extends RecyclerView.Adapter<InstructionViewHolder> {

    Context context;
    List<InstructionResponse> list;

    public InstructionAdaptors(Context context, List<InstructionResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {

        holder.instruction_name.setText(list.get(position).name);
        holder.instruction_steps.setHasFixedSize(true);

        holder.instruction_steps.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        InstructionStepAdaptor instructionStepAdaptor= new InstructionStepAdaptor(context,list.get(position).steps);
        holder.instruction_steps.setAdapter(instructionStepAdaptor);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionViewHolder extends RecyclerView.ViewHolder{

    TextView instruction_name;
    RecyclerView instruction_steps;
    public InstructionViewHolder(@NonNull View itemView) {
        super(itemView);
        instruction_name = itemView.findViewById(R.id.instruction_name);
        instruction_steps = itemView.findViewById(R.id.instruction_steps);
    }
}
