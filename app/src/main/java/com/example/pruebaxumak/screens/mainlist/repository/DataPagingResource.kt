package com.example.pruebaxumak.screens.mainlist.repository

import androidx.paging.PagingSource
import com.example.pruebaxumak.remote.DataApi
import com.example.pruebaxumak.screens.mainlist.model.MainResponse
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class DataPagingResource (
    private val movieApi: DataApi
): PagingSource<Int, MainResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MainResponse> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = movieApi.getAllList()
            val movies = response

            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position-1,
                nextKey = if (movies.isEmpty()) null else position+1
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}