package com.nab.phuong.feature_forecast.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nab.phuong.feature_forecast.data.database.model.CityDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
open class CityDaoTest : ForecastDatabaseTest() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Throws(Exception::class)
    @Test
    fun givenUniqueCities_whenInsertDone_thenGetAllCitiesReturnUniqueCitiesTest() {
        runBlockingTest {

            appDatabase.cityDao().insertCity(
                city = CityDataModel(
                    cityId = TEST_CITY_ID, name = TEST_CITY_NAME
                )
            )
            appDatabase.cityDao().insertCity(
                city = CityDataModel(
                    cityId = TEST_CITY_ID_2, name = TEST_CITY_NAME_2
                )
            )

            val cities = appDatabase.cityDao().getAllCities()

            assertEquals(expected = 2, actual = cities.size)
        }
    }

    @Throws(Exception::class)
    @Test
    fun givenDuplicatedCities_whenInsertDone_ThenReturnOnlyUniqueCity() {
        runBlockingTest {
            appDatabase.cityDao().insertCity(
                city = CityDataModel(
                    cityId = TEST_CITY_ID, name = TEST_CITY_NAME
                )
            )
            appDatabase.cityDao().insertCity(
                city = CityDataModel(
                    cityId = TEST_CITY_ID, name = TEST_CITY_NAME_2
                )
            )

            val cities = appDatabase.cityDao().getAllCities()

            assertEquals(expected = 1, actual = cities.size)
        }
    }

    @Test
    @Throws(Exception::class)
    fun givenCity_whenSearchCityByName_thenReturnCityAdded() {
        runBlockingTest {
            appDatabase.cityDao()
                .insertCity(
                    city = CityDataModel(
                        cityId = TEST_CITY_ID, name = TEST_CITY_NAME
                    )
                )

            val cityDataModel = appDatabase.cityDao().getCityByName(cityName = TEST_CITY_NAME)

            assertNotNull(cityDataModel)
            assertEquals(cityDataModel.cityId, TEST_CITY_ID)
            assertEquals(cityDataModel.name, TEST_CITY_NAME)
        }
    }

    companion object {
        private const val TEST_CITY_ID = 100L
        private const val TEST_CITY_NAME = "First Test City"
        private const val TEST_CITY_ID_2 = 102L
        private const val TEST_CITY_NAME_2 = "Second Test City"
    }
}
