package dassem.app.resume.network

import dassem.app.resume.model.Resume
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface ResumeAPI {
    @GET("bins/{id}")
    fun getResume(@Path("id") path: String): Single<Resume>
}