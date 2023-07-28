package com.example.newdemo.ui.home

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newdemo.ITestServer
import com.example.newdemo.databinding.FragmentHomeBinding
import com.example.newdemo.service.NewProcessService
import com.example.newdemo.compose.WanAndroidActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val connection by lazy {
        object : ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                Log.d("qw", "onServiceConnected $p0 and $p1")
                serverCenter = ITestServer.Stub.asInterface(p1!!)
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
            }

        }
    }

    private lateinit var serverCenter: ITestServer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.bindService(
            Intent(context, NewProcessService::class.java),
            connection,
            Context.BIND_AUTO_CREATE
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        textView.setOnClickListener {
            serverCenter.sayData("hello")
            textView.text = serverCenter.getData("go")
        }
        binding.buttonCompose.setOnClickListener {
            startActivity(Intent(context, WanAndroidActivity::class.java))
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}