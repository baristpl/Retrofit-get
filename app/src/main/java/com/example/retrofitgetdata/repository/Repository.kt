package com.example.retrofitgetdata.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.retrofitgetdata.*
import com.example.retrofitgetdata.clientApi.ClientApi
import com.example.retrofitgetdata.clientApi.Service
import com.example.retrofitgetdata.database.CardDatabaseDao
import com.example.retrofitgetdata.database.CardEntity
import com.example.retrofitgetdata.database.DepartmentEntity
import com.example.retrofitgetdata.models.CardModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

const val REQUEST_NUM = 4

class Repository(private val databaseDao: CardDatabaseDao) {
    private lateinit var universityResult: List<UniversityResult>
    private lateinit var departmentResult: List<DepartmentResult>
    private lateinit var countryResult: List<CountryResult>
    private lateinit var blogResult: List<BlogResult>

    val universityContent: LiveData<List<CardModel>> =
        Transformations.map(databaseDao.getUniversityCards()) {
            it.map {
                CardModel(
                    title = it.title,
                    subTitle = it.subTitle,
                    thumbnail = it.thumbnail
                )
            }
        }

    val blogContent: LiveData<List<CardModel>> = Transformations.map(databaseDao.getBlogCards()) {
        it.map {
            CardModel(
                title = it.title,
                subTitle = it.subTitle,
                thumbnail = it.thumbnail
            )

        }
    }

    val countryContent: LiveData<List<CardModel>> =
        Transformations.map(databaseDao.getCountryCards()) {
            it.map {
                CardModel(
                    title = it.title,
                    subTitle = it.subTitle,
                    thumbnail = it.thumbnail
                )
            }
        }

   val departmentContent: LiveData<List<String>> =
       Transformations.map(databaseDao.getDepartments()) {
           it.map {
               it.name
           }
       }

    private var fetchControl: Int by Delegates.observable(0) { _, _, newValue ->
        if (newValue == REQUEST_NUM)
            GlobalScope.launch {
                refreshDatabase()
            }
    }

    private fun refreshDatabase() {
        for (result in universityResult) {
            databaseDao.insertCard(
                CardEntity(
                    title = result.name_,
                    subTitle = result.province_.name_,
                    thumbnail = result.thumbnail_,
                    type = "university"
                )
            )

        }

        for (result in countryResult) {
            databaseDao.insertCard(
                CardEntity(
                    title = result.name_,
                    subTitle = "",
                    thumbnail = result.image_,
                    type = "country"
                )
            )
        }

        for (result in blogResult) {
            databaseDao.insertCard(
                CardEntity(
                    title = result.name_,
                    subTitle = "",
                    thumbnail = result.thumbnail_,
                    type = "blog"
                )
            )
        }

        for (result in departmentResult) {
            databaseDao.insertDepartment(DepartmentEntity(
                name = result.name_
            ))
        }
    }

    fun refreshContent() {
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
