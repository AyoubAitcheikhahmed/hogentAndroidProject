package com.banibegood.hogentproject

import android.app.Application
import com.banibegood.hogentproject.database.friend.UlteamDatabase
import com.banibegood.hogentproject.network.*
import com.banibegood.hogentproject.repository.UlteamRepository
import com.banibegood.hogentproject.repository.UlteamRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContainer
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*
import org.kodein.di.generic.instance


class UlteamApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@UlteamApplication))

        //bind an instance of DB
        bind() from singleton { UlteamDatabase(instance()) }
        bind() from singleton { instance<UlteamDatabase>().gamesDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { GameApiService(instance()) }
        bind<GameNetworkDatasource>() with singleton { GameNetworkDatasourceImpl(instance()) }
        bind<UlteamRepository>() with singleton { UlteamRepositoryImpl(instance(),instance()) }

    }

    override fun onCreate() {
        super.onCreate()
    }
    }
