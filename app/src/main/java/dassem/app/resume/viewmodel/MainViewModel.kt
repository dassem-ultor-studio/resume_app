package dassem.app.resume.viewmodel

import dassem.app.resume.model.Resume
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dassem.app.resume.extensions.addTo
import dassem.app.resume.network.ResumeService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(resumeService: ResumeService) : ViewModel() {
    private val disposables = CompositeDisposable()

    private val _resume = MutableLiveData<Resume>()
    val resume: LiveData<Resume>
        get() = _resume

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _sendEmail = MutableLiveData<String>()
    val sendEmail: LiveData<String>
        get() = _sendEmail

    init {
        resumeService.getResume()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .addTo(disposables)
    }

    fun onEmailButtonClicked() {
        _sendEmail.value = _resume.value?.email
    }

    private fun onSuccess(it: Resume) {
        _resume.postValue(it)
    }

    private fun onError(error: Throwable) {
        _errorMessage.postValue(error.message)
    }


    override fun onCleared() {
        super.onCleared()

        disposables.clear()
    }
}