package com.example.retrofitgetdata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitgetdata.clientApi.ClientApi
import com.example.retrofitgetdata.clientApi.Service
import com.example.retrofitgetdata.models.CardModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

const val REQUEST_NUM = 4

class HomeViewModel : ViewModel() {

    private lateinit var universityResult: List<UniversityResult>
    private lateinit var departmentResult: List<DepartmentResult>
    private lateinit var countryResult: List<CountryResult>
    private lateinit var blogResult: List<BlogResult>

    private var _universityContent = MutableLiveData<ArrayList<CardModel>>()
    val universityContent: LiveData<ArrayList<CardModel>>
        get() = _universityContent

    private var _countryContent = MutableLiveData<ArrayList<CardModel>>()
    val countryContent: LiveData<ArrayList<CardModel>>
        get() = _countryContent

    private var _blogContent = MutableLiveData<ArrayList<CardModel>>()
    val blogContent: LiveData<ArrayList<CardModel>>
        get() = _blogContent

    private var _departmentContent = MutableLiveData<ArrayList<String>>()
    val departmentContent: LiveData<ArrayList<String>>
        get() = _departmentContent

    private var fetchControl: Int by Delegates.observable(0) { _, _, newValue ->
        if (newValue == REQUEST_NUM) initializeContent()
    }

    private fun initializeContent() {
        val tempContent = arrayListOf<CardModel>()
        val tempDepartmentList = arrayListOf<String>()

        for (result in universityResult) {
            tempContent.add(CardModel(result.name_, result.province_.name_, result.thumbnail_))
        }
        _universityContent.value = tempContent
        tempContent.clear()

        for (result in countryResult) {
            tempContent.add(CardModel(result.name_, "", result.image_))
        }
        _countryContent.value = tempContent
        tempContent.clear()

        for (result in blogResult) {
            tempContent.add(CardModel(result.name_, "", result.thumbnail_))
        }
        _blogContent.value = tempContent
        tempContent.clear()

        for (result in departmentResult)
            tempDepartmentList.add(result.name_)

        _departmentContent.value = tempDepartmentList
    }

    init {
        getContentFromRestApi()
    }

    private fun getContentFromRestApi() {
        val service = ClientApi.getClient().create(Service::class.java)

        service.getUniList().enqueue(object : Callback<UniversityModel> {
            override fun onFailure(call: Call<UniversityModel>, t: Throwable) {
                Log.e("response", "failed", t)
            }

            override fun onResponse(
                call: Call<UniversityModel>,
                response: Response<UniversityModel>
            ) {
                if (response.isSuccessful)
                    universityResult = response.body()?.results_!!
                ++fetchControl

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
                if (response.isSuccessful)
                    departmentResult = response.body()?.results_!!
                ++fetchControl
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
                if (response.isSuccessful)
                    countryResult = response.body()?.results_!!
                ++fetchControl
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
                if (response.isSuccessful)
                    blogResult = response.body()?.results_!!
                ++fetchControl
            }
        })

    }

}