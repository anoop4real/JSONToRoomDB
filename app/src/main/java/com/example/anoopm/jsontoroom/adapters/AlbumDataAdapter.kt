package com.example.anoopm.jsontoroom.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anoopm.jsontoroom.R
import com.example.anoopm.jsontoroom.database.AlbumItem
import com.example.anoopm.jsontoroom.models.MusicAlbums
import kotlinx.android.synthetic.main.album_cell_layout.view.*


class AlbumDataAdapter(val items: List<AlbumItem>): RecyclerView.Adapter<AlbumDataAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.bind(items[p1])
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(p0!!.context)
        val row = layoutInflater.inflate(R.layout.album_cell_layout, p0, false)

        return ViewHolder(row)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(albumListCell: View) : RecyclerView.ViewHolder(albumListCell) {

        val itemVw = albumListCell

        fun bind(item: AlbumItem) {
            itemVw.mainText.text = item.title
            itemVw.detailedText.text = item.artist
            //itemView.setOnClickListener { Toast.makeText(it.context, "$item.countryName", Toast.LENGTH_SHORT).show() }
            itemVw.setOnClickListener {  }
        }

    }
}