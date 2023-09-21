package com.example.project3flixsterpart1

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

data class Movie (
    val movieId: Int,
    private val posterPath: String,
    val title: String,
    val overview: String,
) {
    val posterImageUrl = "https://image.tmdb.org/t/p/w500/$posterPath"
    companion object{

        fun fromJsonObject(jsonObject: JSONObject): Movie {
            val title = jsonObject.optString("title", "")
            val movieId = jsonObject.optInt("id", 0)
            val posterPath = jsonObject.optString("poster_path", "")
            val overview = jsonObject.optString("overview", "")

            return Movie(movieId, posterPath, title, overview)
        }

        fun fromJsonArray(jsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until jsonArray.length()) {
                try {
                    val jsonObject = jsonArray.getJSONObject(i)
                    movies.add(fromJsonObject(jsonObject))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            Log.d("Movie", "fromJSONArray: $movies")
            return movies
        }
    }

}