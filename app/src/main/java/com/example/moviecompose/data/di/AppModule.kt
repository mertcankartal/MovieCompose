package com.example.moviecompose.data.di

import com.example.moviecompose.data.MovieApi
import com.example.moviecompose.data.remote.datasource.MovieDataSource
import com.example.moviecompose.data.repository.MovieRepository
import com.example.moviecompose.data.repository.MovieRepositoryImpl
import com.example.moviecompose.data.usecase.GetMovieDetailUseCase
import com.example.moviecompose.data.usecase.GetMoviesUseCase
import com.example.moviecompose.util.const.Const.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(dataSource: MovieDataSource) : MovieRepository {
        return MovieRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideGetMovieDetailUseCase(repository: MovieRepository) : GetMovieDetailUseCase {
        return GetMovieDetailUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetMoviesUseCase(repository: MovieRepository) : GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }

}