package com.example.helloworld

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.helloworld.R
import com.example.helloworld.data.viewmodels.UserViewModel
import com.example.helloworld.fragments.list.ListAdapter
import com.example.helloworld.workers.SyncWorker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        // User view model
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner) { users ->
            adapter.setData(users)
        }


        mUserViewModel.getPost()
        mUserViewModel.posts.observe(viewLifecycleOwner) {post ->
            Toast.makeText(requireContext(), post.toString(), Toast.LENGTH_LONG).show()
        }

        val floating_action_button = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floating_action_button.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        syncPosts()
        return view
    }

    fun syncPosts(){
        val sync_request: WorkRequest = OneTimeWorkRequestBuilder<SyncWorker>().build()
        WorkManager.getInstance(requireContext()).enqueue(sync_request)
    }
}