package com.jesusbadenas.ibermaticacodechallenge.pokemonlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.jesusbadenas.ibermaticacodechallenge.R
import com.jesusbadenas.ibermaticacodechallenge.common.UIUtils
import com.jesusbadenas.ibermaticacodechallenge.di.ApplicationContext
import com.jesusbadenas.ibermaticacodechallenge.entities.Pokemon
import javax.inject.Inject

/**
 * Adapter that manages a collection of {@link PokemonModel}.
 */
class PokemonAdapter
@Inject
constructor(@ApplicationContext context: Context) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    interface OnItemClickListener {
        fun onPokemonItemClicked(pokemon: Pokemon)
    }

    private var pokemons: List<Pokemon> = emptyList()
    private val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var onItemClickListener: OnItemClickListener? = null

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = layoutInflater.inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val context = holder.itemView.context
        val pokemon = pokemons[position]
        UIUtils.loadImageUrl(
            context, holder.pokemonImage,
            "https://i.pinimg.com/originals/f5/1d/08/f51d08be05919290355ac004cdd5c2d6.png"
        )
        holder.textViewTitle.text = pokemon.name
        holder.itemView.setOnClickListener {
            onItemClickListener?.onPokemonItemClicked(pokemon)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setPokemons(pokemons: List<Pokemon>) {
        this.pokemons = pokemons
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.image_pokemon)
        lateinit var pokemonImage: ImageView

        @BindView(R.id.text_name)
        lateinit var textViewTitle: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}
