package br.com.codecall.codecall.controller.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import br.com.codecall.codecall.R
import br.com.codecall.codecall.model.*
import com.google.firebase.firestore.FirebaseFirestore

class ProfessorAdapter(private var onItemClicked: OnItemClicked, private var myDataset: HashMap<String,Materia>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ProfessorAdapter.MyViewHolder>() {

    interface OnItemClicked{fun onItemClicked(idMateria: String)}

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)

    lateinit var text_materia_nome: TextView
    lateinit var text_materia_sigla: TextView
    lateinit var text_materia_periodo: TextView
    lateinit var botao_gerar_chamada: Button
    val db = FirebaseFirestore.getInstance()
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_view_professor, parent, false) as View
        // set the view's size, margins, paddings and layout parameters
        text_materia_nome = view.findViewById(R.id.text_materia_nome)
        text_materia_sigla = view.findViewById(R.id.text_materia_sigla)
        text_materia_periodo = view.findViewById(R.id.text_materia_periodo)
        botao_gerar_chamada = view.findViewById(R.id.botao_gerar_chamada)
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val key = myDataset.keys.elementAt(position)
        text_materia_nome.text = myDataset[key]?.nome
        text_materia_sigla.text = myDataset[key]?.sigla
        text_materia_periodo.text = myDataset[key]?.periodo
        botao_gerar_chamada.setOnClickListener { onItemClicked.onItemClicked(key) }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    fun setValue(listaMateria: HashMap<String,Materia>) {
        myDataset = listaMateria
    }
}