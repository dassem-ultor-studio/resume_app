package dassem.app.resume.network

import dassem.app.resume.model.Resume
import io.reactivex.Single
import retrofit2.http.GET


interface ResumeService {
    @GET("bins/1gbznh")
    fun getResume(): Single<Resume>
}