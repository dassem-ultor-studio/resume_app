package dassem.app.resume.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerAppCompatActivity
import dassem.app.resume.R
import dassem.app.resume.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_main_education_section.*
import kotlinx.android.synthetic.main.content_main_employment_section.*
import kotlinx.android.synthetic.main.content_main_key_achievements_section.*
import kotlinx.android.synthetic.main.content_main_profile_section.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private val employmentAdapter by lazy { EmploymentAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, modelFactory)[MainViewModel::class.java]
        setContentView(R.layout.activity_scrolling)
//        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        main_employmentList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = employmentAdapter
        }

        addObservers()
    }

    private fun addObservers() {
        addResumeDataObserver()
        addErrorMessageObserver()
    }

    private fun addErrorMessageObserver() {
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun addResumeDataObserver() {
        viewModel.resume.observe(this, Observer {
            displayImage(it.profileImage)

            main_profileName.text = it.name
            main_profileDescription.text = it.profile
            main_keyAchievementsDescription.text = it.keyAchievements.replace(";", "\n")

            employmentAdapter.items = it.employment

            main_educationDate.text = it.education.dates
            main_educationUniversity.text = it.education.university
            main_educationCourse.text = it.education.course
            main_educationKeyModules.text = it.education.keyModules
        })
    }

    private fun displayImage(profileImage: String) {
        Glide.with(this)
            .load(profileImage)
            .apply(RequestOptions.circleCropTransform())
            .transition(withCrossFade())
            .into(main_profileImage)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
