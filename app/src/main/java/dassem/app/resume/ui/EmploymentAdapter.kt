package dassem.app.resume.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import dassem.app.resume.R
import dassem.app.resume.extensions.autoNotify
import dassem.app.resume.model.Employment
import kotlinx.android.synthetic.main.employment_list_item.view.*
import kotlin.properties.Delegates

class EmploymentAdapter : RecyclerView.Adapter<EmploymentAdapter.ViewHolder>() {
    var items: List<Employment> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id == n.id }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employment = items[position]
        holder.updateUI(employment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.employment_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun updateUI(employment: Employment) {
            itemView.employmentItem_date.text = employment.dates
            itemView.employmentItem_company.text = employment.company
            itemView.employmentItem_title.text = employment.jobTitle
            itemView.employmentItem_responsibilities.text =
                HtmlCompat.fromHtml(employment.responsibilities, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}