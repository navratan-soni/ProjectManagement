import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pmgmt.mainapp.R
import com.pmgmt.mainapp.data.model.Project
import com.pmgmt.mainapp.data.network.ApiService
import com.pmgmt.mainapp.data.network.NetworkModule
import com.pmgmt.mainapp.data.repository.ArchitectRepository
import com.pmgmt.mainapp.databinding.FragmentProjectListBinding
import com.pmgmt.mainapp.presentation.ui.adapter.ProjectListAdapter
import com.pmgmt.mainapp.presentation.viewmodel.LoginViewModel
import com.pmgmt.mainapp.presentation.viewmodel.ProjectListViewModel
import com.pmgmt.mainapp.presentation.viewmodel.factory.ProjectListViewModelFactory


class ProjectListFragment : DialogFragment(R.layout.fragment_project_list) {
    /*private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = view.findViewById(R.id.projectListView)
        val projects = (viewModel.loginState.value as? LoginViewModel.LoginState.Success)?.projects

        listView.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            projects?.map { it.projectName } ?: emptyList()
        )

        listView.setOnItemClickListener { _, _, position, _ ->
            projects?.get(position)?.let { project ->
                findNavController().navigate(
                    R.id.action_projectSelection_to_projectDetails,
                    Bundle().apply {
                        putString("projectId", project.projectId)
                    }
                )
            }
        }
    }*/

    private var _binding: FragmentProjectListBinding? = null
    private val binding get() = _binding!!

    private var listener: ProjectSelectionListener? = null

    interface ProjectSelectionListener {
        fun onProjectSelected(project: Project)
    }

    private val apiService: ApiService by lazy {
        NetworkModule.provideApiService(requireContext())
    }

    private val architectRepository: ArchitectRepository by lazy {
        ArchitectRepository(apiService)
    }

    private val viewModel: ProjectListViewModel by lazy {
        ViewModelProvider(this, ProjectListViewModelFactory(architectRepository))
            .get(ProjectListViewModel::class.java)
    }

    private val projectAdapter = ProjectListAdapter { project ->
        /*findNavController().navigate(
            R.id.action_projectList_to_projectDetails,
            Bundle().apply {
                putString("projectId", project.projectId)
            }
        )*/
        listener?.onProjectSelected(project)
        dismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context: Context = requireContext()
        val builder = AlertDialog.Builder(context)
        val projects = viewModel.getProjects() // Mocked data or fetched list

        val projectNames = projects.map { it.projectName }

        builder.setTitle("Select a Project")
            .setAdapter(projectAdapter) { _, which ->
                val selectedProject = projects[which]
                viewModel.selectProject(selectedProject)
                dismiss()
            }
            .setNegativeButton("Cancel") { _, _ -> dismiss() }

        return builder.create()
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val role = arguments?.getString("role") ?: ""
        val token = arguments?.getString("token") ?: ""

        setupRecyclerView()
        observeViewModel()
        viewModel.loadProjects(role, token)
    }

    private fun setupRecyclerView() {
        binding.projectsRecyclerView.apply {
            adapter = projectAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeViewModel() {
        viewModel.projectListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ProjectListViewModel.ProjectListState.Loading -> showLoading()
                is ProjectListViewModel.ProjectListState.Success -> {
                    hideLoading()
                    projectAdapter.submitList(state.projects)
                }

                is ProjectListViewModel.ProjectListState.Error -> showError(state.message)
            }
        }
    }

    private fun showLoading() {
        binding.loadingProgress.visibility = View.VISIBLE
        binding.projectsRecyclerView.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.loadingProgress.visibility = View.GONE
        binding.projectsRecyclerView.visibility = View.VISIBLE
    }

    private fun showError(message: String) {
        binding.loadingProgress.visibility = View.GONE
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}