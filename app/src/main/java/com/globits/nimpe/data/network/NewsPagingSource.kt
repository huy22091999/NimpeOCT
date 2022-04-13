package com.globits.nimpe.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.globits.nimpe.data.model.Category
import com.globits.nimpe.data.model.News
import com.globits.nimpe.data.model.NewsFilter
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val api:CategoryApi,
    private val language:Int,
    private val category:Category
) : PagingSource<Int, News>() {

    companion object {
        private const val INITIAL_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        return try {

            val nextPageNumber = params.key ?: INITIAL_PAGE
            val filter = NewsFilter(category,language,nextPageNumber, 10)
            val response = api.getNews(filter)

            LoadResult.Page(
                data = response.content!!,
                prevKey = if (nextPageNumber == INITIAL_PAGE) null else nextPageNumber - 1,
                nextKey = if (response.content.isEmpty()) null else nextPageNumber + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

}