package com.pmgmt.mainapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmgmt.mainapp.data.model.Project
import com.pmgmt.mainapp.databinding.ItemProjectBinding

class ProjectListAdapter(
    private val onProjectClick: (Project) -> Unit
) : RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder>() {

    private var projects: List<Project> = emptyList()

    class ProjectViewHolder(private val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(project: Project, onProjectClick: (Project) -> Unit) {
            binding.apply {
                projectName.text = project.projectName
                projectLocation.text = project.location
                projectDates.text = "${project.startDate} - ${project.endDate}"
                root.setOnClickListener { onProjectClick(project) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = ItemProjectBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projects[position], onProjectClick)
    }

    override fun getItemCount() = projects.size

    fun submitList(newProjects: List<Project>) {
        projects = newProjects
        notifyDataSetChanged()
    }
}
