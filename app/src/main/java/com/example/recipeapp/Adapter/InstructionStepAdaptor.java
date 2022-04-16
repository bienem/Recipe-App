package com.example.recipeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.Step;
import com.example.recipeapp.R;

import java.util.List;

public class InstructionStepAdaptor extends RecyclerView.Adapter<InstructionStepViewHolder>{

    Context context;
    List<Step> list;

    public InstructionStepAdaptor(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.instruction_steps, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {
        holder.instruction_step_no.setText(String.valueOf(list.get(position).number));
        holder.instruction_step_item.setText(list.get(position).step);

        holder.instruction_ingredients.setHasFixedSize(true);
        holder.instruction_ingredients.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        InstructionIngredientAdaptor instructionIngredientAdaptor = new InstructionIngredientAdaptor(context,list.get(position).ingredients);
        holder.instruction_ingredients.setAdapter(instructionIngredientAdaptor);

        holder.instruction_equipment.setHasFixedSize(true);
        holder.instruction_equipment.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InstructionEquipmentAdaptor instructionEquipmentAdaptor = new InstructionEquipmentAdaptor(context,list.get(position).equipment);
        holder.instruction_equipment.setAdapter(instructionEquipmentAdaptor);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionStepViewHolder extends RecyclerView.ViewHolder{

    TextView instruction_step_no, instruction_step_item;
    RecyclerView instruction_equipment,instruction_ingredients;

    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);
        instruction_step_no = itemView.findViewById(R.id.instruction_step_no);
        instruction_step_item = itemView.findViewById(R.id.instruction_step_item);
        instruction_equipment = itemView.findViewById(R.id.instruction_equipment);
        instruction_ingredients = itemView.findViewById(R.id.instruction_ingredients);
    }
}
