package com.pmgmt.mainapp.presentation.ui.fragment
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.pmgmt.mainapp.R
import com.pmgmt.mainapp.presentation.viewmodel.LoginViewModel
import com.pmgmt.mainapp.presentation.viewmodel.ProjectDetailsViewModel

class ProjectDetailsFragment : Fragment(R.layout.fragment_project_details) {
    private val viewModel: ProjectDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val projectId = arguments?.getString("projectId") ?: return
        viewModel.loadProjectDetails(projectId)

        viewModel.projectDetails.observe(viewLifecycleOwner) { project ->
            view.findViewById<TextView>(R.id.projectTitle).text = project.projectName
            view.findViewById<TextView>(R.id.projectLocation).text = project.location
            view.findViewById<TextView>(R.id.projectDates).text = "${project.startDate} - ${project.endDate}"
            view.findViewById<ImageView>(R.id.projectImage).load(project.imageUrl)
        }
    }
}