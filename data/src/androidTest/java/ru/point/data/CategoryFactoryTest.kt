package ru.point.data

import ru.point.data.data.entities.Category

object CategoryFactoryTest {
    private var id: Long = 1
    private var discriminators = arrayOf(
        Category.Discriminator.NOTES,
        Category.Discriminator.FINANCES,
        Category.Discriminator.FINANCES
    )

    fun init() {
        id = 1
    }

    fun factory(discriminator: Category.Discriminator): Category {
        return Category(
            id = id++,
            name = "test",
            colorIndex = 1,
            discriminator = discriminator,
            icon = "empty"
        )
    }

    fun listFactory(size: Int): List<Category> {
        val mutableList = mutableListOf<Category>()
        repeat(times = size) { i ->
            mutableList.add(factory(discriminators[i % 3]))
        }
        return mutableList
    }
}