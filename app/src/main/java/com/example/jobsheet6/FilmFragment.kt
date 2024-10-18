package com.example.jobsheet6

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmFragment : Fragment() {
    lateinit var morty: RecyclerView
    lateinit var adapter: MortyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        morty = view.findViewById(R.id.rv_mortin)
        morty.layoutManager = LinearLayoutManager(requireContext())
        morty.setHasFixedSize(true)

        ApiConfig.getService().getMorty().enqueue(object : Callback<ResponseMorty> {
            override fun onResponse(call: Call<ResponseMorty>, response: Response<ResponseMorty>) {
                if (response.isSuccessful) {
                    val responseMorty = response.body()
                    val dataMorty = responseMorty?.results
                    Log.d("API Response", "Response body: ${responseMorty.toString()}")
                    Log.d("API Response", "Data size: ${dataMorty?.size}")

                    dataMorty?.let {
                        adapter = MortyAdapter(it)
                        morty.adapter = adapter
                        Log.d("Adapter", "Adapter set with data size: ${it.size}")
                    }
                } else {
                    Log.e("API Error", "Response is not successful")
                }
            }

            override fun onFailure(call: Call<ResponseMorty>, t: Throwable) {
                Log.e("API Failure", "Failure message: ${t.localizedMessage}")
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}