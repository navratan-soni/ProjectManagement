package com.pmgmt.mainapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pmgmt.mainapp.R
import com.pmgmt.mainapp.databinding.FragmentProjectDetailsBinding
import com.pmgmt.mainapp.presentation.viewmodel.ProjectDetailsViewModel
import com.pmgmt.mainapp.data.repository.ArchitectRepository
import com.pmgmt.mainapp.data.network.ApiService
import com.pmgmt.mainapp.data.network.NetworkModule
import com.pmgmt.mainapp.presentation.viewmodel.factory.ProjectDetailsViewModelFactory

class ProjectDetailsFragment : Fragment(R.layout.fragment_project_details) {
    private var _binding: FragmentProjectDetailsBinding? = null
    private val binding get() = _binding!!

    private val apiService: ApiService by lazy {
        NetworkModule.provideApiService(requireContext())
    }

    private val architectRepository: ArchitectRepository by lazy {
        ArchitectRepository(apiService)
    }

    private val viewModel: ProjectDetailsViewModel by lazy {
        ViewModelProvider(this, ProjectDetailsViewModelFactory(architectRepository))
            .get(ProjectDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val projectId = arguments?.getString("projectId") ?: return

        setupObservers()
        setupClickListeners()
        viewModel.loadProjectDetails(projectId)
    }

    private fun setupObservers() {
        viewModel.projectDetails.observe(viewLifecycleOwner) { project ->
            binding.apply {
                projectTitle.text = project.projectName
                projectLocation.text = "Location: ${project.location}"
                projectStartDate.text = "Start Date: ${project.startDate}"
                projectEndDate.text = "End Date: ${project.endDate}"
                projectDescription.text = project.description ?: "No description available."
            }
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            feedButton.setOnClickListener {
                // Handle feed click
            }

            highlightsButton.setOnClickListener {
                // Handle highlights click
            }

            milestonesButton.setOnClickListener {
                // Handle milestones click
            }

            inventoryButton.setOnClickListener {
                // Handle inventory click
            }

            purchasesButton.setOnClickListener {
                // Handle purchases click
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}