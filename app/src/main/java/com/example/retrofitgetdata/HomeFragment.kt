package com.example.retrofitgetdata

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.databinding.FragmentHomeBinding
import com.example.retrofitgetdata.models.CardModel
import com.example.retrofitgetdata.recycler.CardAdapter
import com.example.retrofitgetdata.recycler.GridAdapter
import com.example.retrofitgetdata.clientApi.ClientApi
import com.example.retrofitgetdata.clientApi.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var universityList: List<UniversityResult>
lateinit var departmentList: List<DepartmentResult>
lateinit var countryList: List<CountryResult>
lateinit var blogList: List<BlogResult>

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val universityRecycler = binding.universityRecycler
        val countryRecycler = binding.countryRecycler
        val blogRecycler = binding.blogRecycler
        val departmentRecycler = binding.departmentRecycler
        val recyclerList =
            listOf(universityRecycler, departmentRecycler, countryRecycler, blogRecycler)

        getFromRestApi(recyclerList)
    }

    private fun getFromRestApi(recyclerList: List<RecyclerView>) {
        val service = ClientApi.getClient().create(Service::class.java)

        service.getUniList().enqueue(object : Callback<UniversityModel> {
            override fun onFailure(call: Call<UniversityModel>, t: Throwable) {
                Log.e("response", "failed", t)
            }

            override fun onResponse(
                call: Call<UniversityModel>,
                response: Response<UniversityModel>
            ) {
                if (response.isSuccessful) {
                    universityList = response.body()?.results_!!
                    val data = arrayListOf<CardModel>()

                    for (result in universityList)
                        data.add(CardModel(result.name_, result.province_.name_, result.thumbnail_))

                    recyclerList[0].apply {
                        layoutManager = LinearLayoutManager(
                            view?.context,
                            RecyclerView.HORIZONTAL,
                            false
                        )
                        adapter = CardAdapter(data, context!!)
                    }
                }
            }
        })

        service.getDepartmentList().enqueue(object : Callback<DepartmentModel> {
            override fun onFailure(call: Call<DepartmentModel>, t: Throwable) {
                Log.e("responseFail", "failed", t)
            }

            override fun onResponse(
                call: Call<DepartmentModel>,
                response: Response<DepartmentModel>
            ) {
                if (response.isSuccessful) {
                    departmentList = response.body()?.results_!!
                    val departmentNameList = arrayListOf<String>()

                    for (result in departmentList)
                        departmentNameList.add(result.name_)

                    recyclerList[1].apply {
                        layoutManager =
                            GridLayoutManager(view?.context, 2, RecyclerView.VERTICAL, false)
                        adapter = GridAdapter(departmentNameList)

                    }
                }
            }
        })

        service.getCountryList().enqueue(object : Callback<CountryModel> {
            override fun onFailure(call: Call<CountryModel>, t: Throwable) {
                Log.e("response", "failed", t)
            }

            override fun onResponse(
                call: Call<CountryModel>,
                response: Response<CountryModel>
            ) {
                if (response.isSuccessful) {
                    countryList = response.body()?.results_!!
                    val data = arrayListOf<CardModel>()

                    for (result in countryList)
                        data.add(CardModel(result.name_, "", result.image_))

                    recyclerList[2].apply {
                        layoutManager = LinearLayoutManager(
                            view?.context,
                            RecyclerView.HORIZONTAL,
                            false
                        )
                        adapter = CardAdapter(data, context!!)
                    }
                }
            }
        })

        service.getBlogList().enqueue(object : Callback<BlogModel> {
            override fun onFailure(call: Call<BlogModel>, t: Throwable) {
                Log.e("response", "failed", t)
            }

            override fun onResponse(
                call: Call<BlogModel>,
                response: Response<BlogModel>
            ) {
                if (response.isSuccessful) {
                    blogList = response.body()?.results_!!
                    val data = arrayListOf<CardModel>()

                    for (result in blogList)
                        data.add(CardModel(result.name_, "", result.thumbnail_))

                    recyclerList[3].apply {
                        layoutManager = LinearLayoutManager(
                            view?.context,
                            RecyclerView.HORIZONTAL,
                            false
                        )
                        adapter = CardAdapter(data, context!!)
                    }
                }
            }
        })

    }

}