package com.grpcx.youtubetomp3.di

import android.content.Context
import android.view.ContextMenu
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.grpcx.youtubetomp3.repository.FakeRepository
import com.grpcx.youtubetomp3.work.GetVideoInfoWorker
import com.yausername.youtubedl_android.YoutubeDL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkModule : Initializer<WorkManager> {

    @Provides
    @Singleton
    override fun create(@ApplicationContext context: Context): WorkManager {
        val configuration = Configuration.Builder().build()
        WorkManager.initialize(context, configuration)
        return WorkManager.getInstance(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        // No dependencies on other libraries.
        return emptyList()
    }

}