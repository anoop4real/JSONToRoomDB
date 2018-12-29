package com.example.anoopm.jsontoroom

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.anoopm.jsontoroom.adapters.AlbumDataAdapter
import com.example.anoopm.jsontoroom.database.AlbumDatabase
import com.example.anoopm.jsontoroom.database.AlbumItem
import com.example.anoopm.jsontoroom.models.MusicAlbums
import com.example.anoopm.jsontoroom.store.DataStore
import com.example.anoopm.jsontoroom.webservice.Result
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {

    internal var db:AlbumDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        retrieveAlbums()
    }

    private fun initialize(){

        db = AlbumDatabase.getDatabase(applicationContext)

    }

    // Refresh the UI with fetched data
    private fun refreshUIWith(musicAlbums: List<AlbumItem>){

        val albumsList = albumListView
        var layoutManager = LinearLayoutManager(this)
        albumsList.layoutManager = layoutManager
        albumsList.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        val adapter = AlbumDataAdapter(musicAlbums)
        albumsList.adapter = adapter
    }

    // Method to save the data to database
    private fun saveWith(albums: Array<MusicAlbums>){

        doAsync {
            val currentDBPath = getDatabasePath("albums_database").absolutePath
            println("DBPath is " + currentDBPath)

            var items = ArrayList<AlbumItem>()

            for (album in albums){
                val item = AlbumItem()
                item.title = album.title
                item.artist = album.artist
                item.image  = album.image
                item.url    = album.url
                item.thumbnail_image = album.thumbnail_image
                items.add(item)
            }
            db?.albumDAO()?.insert(items)

            val musicAlbums = db?.albumDAO()?.fetchall()
            activityUiThread {
                longToast("Data Got saved")
                refreshUIWith(musicAlbums!!)
            }
        }

    }

    // Retrieve albums from the API and save it to Database
    fun retrieveAlbums(){

        DataStore.getMusicAlbumDataWith { result ->

            if(result!= null){

                when (result.status){

                    Result.Status.ERROR->{
                        Toast.makeText(this,"Error:"+ result.exception?.message, Toast.LENGTH_LONG).show()
                    }
                    Result.Status.SUCCESS ->{
                        val albums = result.data
                        albums?.let {
                            saveWith(it)
                        }
                    }
                }

            }
        }
    }
}
