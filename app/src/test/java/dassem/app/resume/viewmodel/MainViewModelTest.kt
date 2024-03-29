package dassem.app.resume.viewmodel

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import dassem.app.resume.model.Education
import dassem.app.resume.model.Resume
import dassem.app.resume.network.ResumeAPI
import dassem.app.resume.viewmodel.MainViewModel.Companion.dataSourceId
import io.reactivex.Single
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class, TestSchedulerExtension::class)
class MainViewModelTest {
    private lateinit var resumeAPI: ResumeAPI

    private lateinit var viewModel: MainViewModel

    @Test
    fun `when data is loaded progress is set to false`() {
        dataSetup()
        assert(viewModel.resume.value == resume)
        assert(viewModel.progress.value == false)
    }

    @Test
    fun `when error happens error message is set`() {
        errorSetup()
        assert(viewModel.resume.value == null)
        assert(viewModel.progress.value == true)
        assert(viewModel.errorMessage.value == errorMessage)
    }

    @Test
    fun `when user clicks on the email button send mail live data is updated`() {
        dataSetup()
        viewModel.onEmailButtonClicked()
        assert(viewModel.sendEmail.value == emailAddress)
    }

    private fun dataSetup() {
        resumeAPI = mock {
            whenever(it.getResume(dataSourceId)) doReturn Single.just(resume)
        }
        viewModel = MainViewModel(resumeAPI)
    }

    private fun errorSetup() {
        resumeAPI = mock {
            whenever(it.getResume(dataSourceId)) doReturn Single.error(Throwable(errorMessage))
        }
        viewModel = MainViewModel(resumeAPI)
    }

    companion object {
        private const val emailAddress = "example@google.com"
        private const val errorMessage = "Not internet connection"
        private val education = Education(dates = "", university = "", course = "", keyModules = "")
        private val resume =
            Resume(
                name = "",
                email = emailAddress,
                phone = "",
                profileImage = "",
                profile = "",
                keyAchievements = "",
                education = education,
                employment = emptyList(),
                interests = ""
            )
    }
}