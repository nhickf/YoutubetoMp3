package com.grpcx.youtubetomp3.di

import android.content.Context
import androidx.work.WorkManager
import com.grpcx.youtubetomp3.repository.FakeRepository
import com.grpcx.youtubetomp3.repository.FakeRepositoryImpl
import com.grpcx.youtubetomp3.repository.MainRepository
import com.grpcx.youtubetomp3.repository.MainRepositoryImpl
import com.yausername.youtubedl_android.YoutubeDL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideYTDLPInstance(): YoutubeDL {
        return YoutubeDL.getInstance()
    }

    @Provides
    @Singleton
    fun provideFakeRepository() : FakeRepository {
        return FakeRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        youtubeDlp : YoutubeDL,
        workManager: WorkManager
    ): MainRepository {
        return MainRepositoryImpl(youtubeDlp, workManager)
    }


}