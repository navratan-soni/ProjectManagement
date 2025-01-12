package com.pmgmt.mainapp.presentation.ui.fragment

import ProjectListFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.pmgmt.mainapp.R
import com.pmgmt.mainapp.presentation.viewmodel.ProjectDetailsViewModel
import com.pmgmt.mainapp.data.repository.ArchitectRepository
import com.pmgmt.mainapp.data.network.ApiService
import com.pmgmt.mainapp.data.network.NetworkModule
import com.pmgmt.mainapp.databinding.FragmentDashboardBinding
import com.pmgmt.mainapp.presentation.viewmodel.factory.ProjectDetailsViewModelFactory

class DashBoardFragment : Fragment(R.layout.fragment_dashboard) {
    private var _binding: FragmentDashboardBinding? = null
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
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()

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
                projectImage.load(project.imageUrl)
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

    private fun setupMenu() {
        val menuHost = requireActivity() as androidx.core.view.MenuHost
        menuHost.addMenuProvider(object : androidx.core.view.MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.dashboard_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_switch_project -> {
                        val dialogFragment = ProjectListFragment()
                        dialogFragment.show(parentFragmentManager, "ProjectListDialog")
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}