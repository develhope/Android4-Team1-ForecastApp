package co.develhope.meteoapp.di

import android.content.Context
import co.develhope.meteoapp.RetrofitIstanceInterface
import co.develhope.meteoapp.network.OffsetDateTimeTypeAdapter
import co.develhope.meteoapp.network.RetrofitInstance
import co.develhope.meteoapp.sharedpreferences.MySharedPrefsInterface
import co.develhope.meteoapp.sharedpreferences.SharedImplementation
import co.develhope.meteoapp.ui.MainActivity
import co.develhope.meteoapp.viewmodel.HomeViewModel
import co.develhope.meteoapp.viewmodel.SearchViewModel
import co.develhope.meteoapp.viewmodel.TodayViewModel
import co.develhope.meteoapp.viewmodel.TomorrowViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.threeten.bp.OffsetDateTime

val viewModelsModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        SearchViewModel(get(), get())
    }
    viewModel {
        TodayViewModel(get(), get())
    }
    viewModel {
        TomorrowViewModel(get(), get())
    }
}  //koin può creare un'istanza di gson e passarla sia alla shared e a retrofit(single)

val androidModule = module {
    single<MySharedPrefsInterface> { //istanziato solo 1 volta
        SharedImplementation(get(), get())//get context, gli stiamo dicendo ogni volta che una classe ti chiede l'interfaccia tu dagli l'implementation
    }
    single {
        GsonBuilder()
            .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeTypeAdapter())
            .create()
    }
    single {
        RetrofitInstance(get())
    }
}

fun startKoin(context: Context) {
    org.koin.core.context.startKoin {
        androidLogger(Level.ERROR)
        androidContext(context)
        modules(
            listOf(
                viewModelsModule, androidModule
            )
        )
    }
}
