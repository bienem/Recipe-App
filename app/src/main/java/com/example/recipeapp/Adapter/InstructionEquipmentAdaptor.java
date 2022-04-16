package com.example.recipeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.Equipment;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionEquipmentAdaptor extends RecyclerView.Adapter<InstructionEquipmentsViewHolder>{

    Context context;
    List<Equipment> list;

    public InstructionEquipmentAdaptor(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEquipmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEquipmentsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEquipmentsViewHolder holder, int position) {
        holder.TextView_instruction_step_item.setText(list.get(position).name);
        holder.TextView_instruction_step_item.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/"+list.get(position).image).into(holder.imageView_instruction_step_item);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class  InstructionEquipmentsViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView_instruction_step_item;
    TextView TextView_instruction_step_item;
    public InstructionEquipmentsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_instruction_step_item = itemView.findViewById(R.id.imageView_instruction_step_item);
        TextView_instruction_step_item = itemView.findViewById(R.id.TextView_instruction_step_item);

    }
}
