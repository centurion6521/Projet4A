package com.esiea.projet4a.presentation.main.Retrofit

import com.esiea.projet4a.presentation.main.Model.Pokedex
import io.reactivex.Observable
import retrofit2.http.GET

interface IPokemonList {
    @get:GET("pokedex.json")
    val listPokemon:Observable<Pokedex>

}