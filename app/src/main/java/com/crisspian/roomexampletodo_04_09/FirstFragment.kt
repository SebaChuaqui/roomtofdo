package com.crisspian.roomexampletodo_04_09

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.crisspian.roomexampletodo_04_09.database.Task
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(),TaskAdapter.PassTheData {

    lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_first, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var task1 = Task(1,"test de prueba de test 1",false)
        viewModel.insertTask(task1)
        var task2 = Task(2,"test de prueba de test 2",false)
        viewModel.insertTask(task2)
        var task3 = Task(3,"test de prueba de test 3",false)
        viewModel.insertTask(task3)

        var mRecyclerView = recyclerView
        var mAdapter = TaskAdapter(this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            Log.d("OBJETO", it.toString())
            mAdapter.updateDataList(it)
        })

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun passTheData(mTask: Task) {
        Toast.makeText(context, mTask.task, Toast.LENGTH_LONG).show()
    }
}