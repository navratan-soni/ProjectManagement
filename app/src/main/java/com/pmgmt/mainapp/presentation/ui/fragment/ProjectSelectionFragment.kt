import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pmgmt.mainapp.R
import com.pmgmt.mainapp.presentation.viewmodel.LoginViewModel




class ProjectSelectionFragment : Fragment(R.layout.fragment_project_selection) {
    private val viewModel: LoginViewModel by viewModels()

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
    }
}