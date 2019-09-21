package dassem.app.resume.network

import dassem.app.resume.model.Resume
import io.reactivex.Single
import retrofit2.http.GET


interface ResumeService {
    @GET("bins/6unz9")
    fun getResume(): Single<Resume>
}