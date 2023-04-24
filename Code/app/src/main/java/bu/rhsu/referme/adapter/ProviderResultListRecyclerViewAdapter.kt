package bu.rhsu.referme.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import bu.rhsu.referme.datalayer.Provider

import bu.rhsu.referme.databinding.FragmentSearchItemBinding


class ProviderResultListRecyclerViewAdapter(
    private val onProjectClick: (Provider)->Unit)
    : RecyclerView.Adapter<ProviderResultListRecyclerViewAdapter.ViewHolder>() {

    private val providers = mutableListOf<Provider>()

    //changes data source content
    fun replaceItems(providerList: List<Provider>) {
        providers.clear()
        providers.addAll(providerList)
        notifyDataSetChanged()
    }


    interface OnProjectClickListener {
        fun onProjectClick(provider: Provider);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(FragmentSearchItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val provider = providers[position]
        holder.idView.text = position.toString()
        holder.contentView.text = provider.name
        holder.cardView.setOnClickListener{
            onProjectClick(provider)
        }
    }

    override fun getItemCount(): Int = providers.size

    fun getProject(pos: Int): Provider {
        if (providers.size > 0)
            return providers[pos]
        else
            return Provider(0,"",0)
    }


    inner class ViewHolder(binding: FragmentSearchItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.searchId
        val contentView: TextView = binding.searchContent
        val cardView: CardView = binding.projectCard

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}