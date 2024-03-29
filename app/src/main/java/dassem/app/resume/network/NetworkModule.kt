package dassem.app.resume.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dassem.app.resume.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun getHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideApiService(httpClient: OkHttpClient): ResumeAPI {
        return Retrofit.Builder()
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_URL)
            .build()
            .create(ResumeAPI::class.java)
    }
}