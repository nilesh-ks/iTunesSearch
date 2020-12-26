package com.example.itunessearch.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunessearch.models.SearchResponse
import com.example.itunessearch.repository.SearchRepository
import com.example.itunessearch.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel (
    val searchRepository: SearchRepository
): ViewModel(){
/*init {
    searchITunes("shakira")
}*/
    val songs: MutableLiveData<Resource<SearchResponse>> = MutableLiveData()
    fun searchITunes(searchQuery: String) = viewModelScope.launch {
        //viewModelScope makes sure that this coroutine stays alive as long as our viewModel is alive
        songs.postValue(Resource.Loading())
        val response=searchRepository.searchITune(searchQuery)
        songs.postValue(handleSongResponse(response))
        //Emitting loading state to LiveData
        //safeSearchITune(searchQuery)
    }
    private fun handleSongResponse(response: Response<SearchResponse>): Resource<SearchResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}