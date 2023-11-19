package com.hahow.android_recruit_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hahow.android_recruit_project.model.Course
import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.databinding.ItemCoursesBinding

class CourseAdapter :
    ListAdapter<Course, RecyclerView.ViewHolder>(
        CourseCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CourseViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        if (holder is CourseViewHolder) {
            holder.bind(item)
        }
    }

    class CourseViewHolder(private var binding: ItemCoursesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Course) {

            setTitle(item)

            val isSoldOut = item.numSoldTickets > item.successCriteria.numSoldTickets

            setNumSoldTicketText(item, isSoldOut)

            setProgressBar(item, isSoldOut)

            setStatusText(item)

            setCoverImg(item)

            setCountdownVisibility(item)

        }

        private fun setCountdownVisibility(item: Course) {

            binding.courseCountDown.visibility = if (item.proposalDueTime != null) View
                .VISIBLE else View.GONE
            binding.courseCountDownImageView.visibility = if (item.proposalDueTime != null) View
                .VISIBLE else View.GONE

        }

        private fun setNumSoldTicketText(item: Course, isSoldOut: Boolean) {
            val courseTicketsText = if (isSoldOut) "100%" else "${item.numSoldTickets}/${
                item.successCriteria
                    .numSoldTickets
            } "

            binding.courseProgressTextView.text = courseTicketsText
        }

        private fun setProgressBar(item: Course, isSoldOut: Boolean) {
            val soldOutPercent: Float =
                if (isSoldOut) 100F else (item.numSoldTickets.toFloat() / item
                    .successCriteria.numSoldTickets.toFloat()) * 100
            binding.courseProgressBar.progress = soldOutPercent.toInt()
        }

        private fun setTitle(item: Course) {
            binding.courseTitleTextView.text = item.title
        }

        private fun setCoverImg(item: Course) {
            Glide.with(itemView)
                .load(item.coverImageUrl)
                .into(binding.courseImageView)

        }

        private fun setStatusText(item: Course) {

            val statusType: StatusType = when (item.status) {
                StatusType.SUCCESS.value -> {
                    StatusType.SUCCESS
                }
                StatusType.PUBLISHED.name -> {
                    StatusType.PUBLISHED
                }
                StatusType.INCUBATING.name -> {
                    StatusType.INCUBATING
                }
                else -> {
                    StatusType.INCUBATING
                }
            }

            binding.courseStatusTextView.text = statusType.text

            binding.courseStatusTextView.setBackgroundResource(statusType.backGround)
        }

        companion object {
            fun from(parent: ViewGroup): CourseViewHolder {
                val order =
                    ItemCoursesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return CourseViewHolder(order)
            }
        }
    }
}

class CourseCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}

enum class StatusType(val value: String, val text: String, val backGround: Int) {
    SUCCESS("SUCCESS", "已達標", R.drawable.status_success),
    PUBLISHED("PUBLISHED", "已開課", R.drawable.status_published),
    INCUBATING("INCUBATING", "募資中", R.drawable.status_incubating)
}