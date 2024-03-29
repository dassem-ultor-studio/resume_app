package dassem.app.resume.viewmodel

import dassem.app.resume.model.Resume
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dassem.app.resume.extensions.addTo
import dassem.app.resume.network.ResumeAPI
import dassem.app.resume.utility.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(resumeAPI: ResumeAPI) : ViewModel() {
    private val disposables = CompositeDisposable()

    private val _resume = MutableLiveData<Resume>()
    val resume: LiveData<Resume>
        get() = _resume

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _sendEmail = SingleLiveEvent<String>()
    val sendEmail: LiveData<String>
        get() = _sendEmail

    private val _progress = MutableLiveData<Boolean>(true)
    val progress: LiveData<Boolean>
        get() = _progress

    init {
        resumeAPI.getResume(dataSourceId)
            .subscribeOn(Schedulers.single())
            .subscribe(::onSuccess, ::onError)
            .addTo(disposables)
    }

    fun onEmailButtonClicked() {
        _sendEmail.postValue(resume.value?.email)
    }

    override fun onCleared() {
        super.onCleared()

        disposables.clear()
    }

    private fun onSuccess(it: Resume) {
        _resume.postValue(it)
        _progress.postValue(false)
    }

    private fun onError(error: Throwable) {
        _errorMessage.postValue(error.message)
    }

    companion object {
        const val dataSourceId = "18sht5"
    }
}