package dassem.app.resume.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerAppCompatActivity
import dassem.app.resume.R
import dassem.app.resume.model.Resume
import dassem.app.resume.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, modelFactory)[MainViewModel::class.java]
        setContentView(R.layout.activity_scrolling)
//        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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
            main_keyAchievementsDescription.text = it.keyAchievements.replace(";","\n")
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
