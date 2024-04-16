package ru.point.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.point.data.data.datasource.local.dao.CategoryDao
import ru.point.data.data.datasource.local.database.AppCashDatabase
import ru.point.data.data.entity.entities.Category


@RunWith(AndroidJUnit4::class)
class CategoryDaoTest {

    private lateinit var database: AppCashDatabase
    private lateinit var categoryDao: CategoryDao

    @Before
    fun initDatabase() {
        CategoryFactoryTest.init()

        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppCashDatabase::class.java
        ).build()

        categoryDao = database.getCategoryDao()
    }

    @Test
    fun shouldCreateAndReturnByIdCategory() = runBlocking {
        val expectedData = CategoryFactoryTest.factory(discriminator = Category.Discriminator.NOTES)

        categoryDao.create(expectedData)
        val actualData = categoryDao.readById(1)

        assertEquals(expectedData, actualData.first())
    }

    @Test
    fun shouldReturnListOfCategories() = runBlocking {
        val times = 10
        val data = CategoryFactoryTest.listFactory(times)

        repeat(times) { time ->
            categoryDao.create(data[time])
        }
        val expectedData =
            data.filter { category -> category.discriminator == Category.Discriminator.NOTES }
        val actualData = categoryDao.readByDiscriminator(Category.Discriminator.NOTES).first()

        assertEquals(expectedData, actualData)
    }

    @Test
    fun shouldDeleteCategoryById() = runBlocking {
        val expectedCount = 0
        val category = CategoryFactoryTest.factory(Category.Discriminator.NOTES)

        categoryDao.create(category)
        categoryDao.deleteById(category.id)
        val actualCount = categoryDao.readByDiscriminator(Category.Discriminator.NOTES).first().size

        assertEquals(expectedCount, actualCount)
    }

    @After
    fun clear() {
        database.close()
    }
}