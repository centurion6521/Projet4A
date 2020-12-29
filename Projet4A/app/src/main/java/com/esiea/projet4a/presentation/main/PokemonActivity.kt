package com.esiea.projet4a.presentation.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.esiea.projet4a.R
import com.esiea.projet4a.presentation.main.Common.Common
import kotlinx.android.synthetic.main.activity_pokedex.*


class PokemonActivity : AppCompatActivity() {

    private val showDetail = object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent!!.action!!.toString()== Common.KEY_ENABLE_HOME){
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setDisplayShowHomeEnabled(true)

                val detailFragment= PokemonDetail.getInstance()
                val position = intent.getIntExtra("position",-1)
                val bundle =Bundle()
                bundle.putInt("position",position)
                detailFragment.arguments = bundle

                val fragmentTransaction=supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.list_pokemon_fragment,detailFragment)
                fragmentTransaction.addToBackStack("detail")
                fragmentTransaction.commit()

                val pokemon = Common.pokemonList[position]
                toolbar.title = pokemon.name
            }
        }


    }

    private val showEvolution = object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent!!.action!!.toString()== Common.KEY_NUM_EVOLUTION){


                val detailFragment= PokemonDetail.getInstance()
                val bundle =Bundle()
                val num = intent.getStringExtra("num")
                bundle.putString("num",num)
                detailFragment.arguments = bundle

                val fragmentTransaction=supportFragmentManager.beginTransaction()
                fragmentTransaction.remove(detailFragment)//on enleve le fragment courant
                fragmentTransaction.replace(R.id.list_pokemon_fragment,detailFragment)
                fragmentTransaction.addToBackStack("detail")
                fragmentTransaction.commit()

                val pokemon = Common.findPokemonByNum(num)
                toolbar.title = pokemon!!.name
            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        toolbar.title="POKEMON LIST"

        //Broadcast register
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showDetail, IntentFilter(Common.KEY_ENABLE_HOME))
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showEvolution, IntentFilter(Common.KEY_NUM_EVOLUTION))

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                toolbar.title = "POKEMON LIST"
                supportFragmentManager.popBackStack("detail",FragmentManager.POP_BACK_STACK_INCLUSIVE)
                supportActionBar!!.setDisplayShowHomeEnabled(false)
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            }
        }
        return true
    }

    companion object {
        internal var instance:PokemonActivity?=null

        fun getInstance():PokemonActivity{
            if(instance==null)
                instance = PokemonActivity()
            return instance!!

        }
    }

}