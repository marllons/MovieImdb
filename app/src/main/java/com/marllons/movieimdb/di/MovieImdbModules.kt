package com.marllons.movieimdb.di

import androidx.room.Room
import com.marllons.movieimdb.Constants.APP_DATABASE_NAME
import com.marllons.movieimdb.data.db.AppDatabase
import com.marllons.movieimdb.data.repository.FavoritesRepository
import com.marllons.movieimdb.data.repository.FavoritesRepositoryImpl
import com.marllons.movieimdb.data.repository.MovieRepository
import com.marllons.movieimdb.data.repository.MovieRepositoryImpl
import com.marllons.movieimdb.data.service.MovieService
import com.marllons.movieimdb.domain.usecase.GetFilmByImdbUseCase
import com.marllons.movieimdb.domain.usecase.GetFilmByImdbUseCaseImpl
import com.marllons.movieimdb.domain.usecase.GetListMoviesUseCase
import com.marllons.movieimdb.domain.usecase.GetListMoviesUseCaseImpl
import com.marllons.movieimdb.presenter.vm.DetailsViewModel
import com.marllons.movieimdb.presenter.vm.MainViewModel
import com.marllons.mshttp.domain.qualifiers.NetworkQualifiers.CUSTOM
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val movieImdbModules = module {
    //service
    single { get<Retrofit>(named(CUSTOM)).create(MovieService::class.java) }
    //repository
    factory<MovieRepository> { MovieRepositoryImpl(result = get(), service = get()) }
    factory<FavoritesRepository> { FavoritesRepositoryImpl(favoriteDao = get()) }
    //useCase
    factory<GetListMoviesUseCase> { GetListMoviesUseCaseImpl(repository = get()) }
    factory<GetFilmByImdbUseCase> { GetFilmByImdbUseCaseImpl(repository = get()) }
    //viewModel
    viewModel { MainViewModel(getListMoviesUseCase = get()) }
    viewModel { DetailsViewModel(getFilmByImdbUseCase = get())}

    //room
    single { Room.databaseBuilder(androidApplication(), AppDatabase::class.java, APP_DATABASE_NAME).build() }

}