package com.esiea.projet4a.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esiea.projet4a.R
import com.esiea.projet4a.presentation.main.Adapter.PokemonListAdapter
import com.esiea.projet4a.presentation.main.Common.Common
import com.esiea.projet4a.presentation.main.Common.ItemOffsetDecoration
import com.esiea.projet4a.presentation.main.Retrofit.IPokemonList
import com.esiea.projet4a.presentation.main.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_pokedex.*
import kotlinx.android.synthetic.main.fragment_poke_list.*


class PokemonList:Fragment() {

    internal var compositeDisposable= CompositeDisposable()
    internal var iPokemonList:IPokemonList

    internal lateinit var recycler_view:RecyclerView

    init{
        var retrofit = RetrofitClient.instance
        iPokemonList=retrofit.create(IPokemonList::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?): View? {
        val itemView = inflater.inflate(R.layout.fragment_poke_list,container,false)

        recycler_view = itemView.findViewById(R.id.pokemon_recyclerview) as RecyclerView
        recycler_view.setHasFixedSize((true))
        recycler_view.layoutManager = GridLayoutManager(activity, 2)
        val itemDecoration = ItemOffsetDecoration(activity!!,R.dimen.spacing)
        recycler_view.addItemDecoration(itemDecoration)
        fetchData()




        return itemView
    }

    private fun fetchData(){
        compositeDisposable.add(iPokemonList.listPokemon
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { pokemonDex ->
                Common.pokemonList = pokemonDex.pokemon!!
                val adapter = PokemonListAdapter(activity!!, Common.pokemonList)

                recycler_view.adapter=adapter
            }
        )
    }



}