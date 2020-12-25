package com.example.itunessearch.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(
        tableName = "results"
)
data class Result(
        //add id and make it null bcz it will be only assigned to the result entity
        //which will be made as favourite and will be saved in room persistence lib
        @PrimaryKey(autoGenerate = true)
        var id:Int?=null,

        val artistId: Int?,
        val artistName: String?,
        val artistViewUrl: String?,
        val artworkUrl100: String?,
        val artworkUrl30: String?,
        val artworkUrl60: String?,
        val collectionArtistId: Int?,
        val collectionArtistName: String?,
        val collectionArtistViewUrl: String?,
        val collectionCensoredName: String?,
        val collectionExplicitness: String?,
        val collectionId: Int?,
        val collectionName: String?,
        val collectionPrice: Double?,
        val collectionViewUrl: String?,
        val country: String?,
        val currency: String?,
        val discCount: Int?,
        val discNumber: Int?,
        val isStreamable: Boolean?,
        val kind: String?,
        val previewUrl: String?,
        val primaryGenreName: String?,
        val releaseDate: String?,
        val trackCensoredName: String?,
        val trackCount: Int?,
        val trackExplicitness: String?,
        val trackId: Int?,
        val trackName: String?,
        val trackNumber: Int?,
        val trackPrice: Double?,
        val trackTimeMillis: Int?,
        val trackViewUrl: String?,
        val wrapperType: String?
): Serializable