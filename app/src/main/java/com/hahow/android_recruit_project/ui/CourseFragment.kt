package com.hahow.android_recruit_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hahow.android_recruit_project.adapter.CourseAdapter
import com.hahow.android_recruit_project.data.CourseRepository
import com.hahow.android_recruit_project.data.JsonFileDataLoader
import com.hahow.android_recruit_project.factory.ViewModelFactory
import com.hahow.android_recruit_project.viewmodel.CourseViewModel
import `in`.hahow.android_recruit_project.databinding.FragmentCourseBinding

class CourseFragment : Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!

    private lateinit var courseAdapter: CourseAdapter

    private lateinit var viewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataLoader = JsonFileDataLoader(requireContext(), "data.json")
        val courseRepository = CourseRepository(dataLoader)
        val viewModelFactory = ViewModelFactory(courseRepository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CourseViewModel::class.java)

        viewModel.courses.observe(viewLifecycleOwner) { courses ->
            courseAdapter.submitList(courses)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        courseAdapter = CourseAdapter()
        binding.coursesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.coursesRecyclerView.adapter = courseAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}