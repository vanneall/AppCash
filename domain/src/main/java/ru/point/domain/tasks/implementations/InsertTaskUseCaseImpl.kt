package ru.point.domain.tasks.implementations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.point.data.data.factory.TaskFactory
import ru.point.data.data.repository_interfaces.TasksRepository
import ru.point.domain.tasks.interfaces.InsertTaskUseCase
import java.time.LocalDate
import javax.inject.Inject

class InsertTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository,
    private val factory: TaskFactory
) : InsertTaskUseCase {
    override fun invoke(
        text: String,
        description: String,
        parentTaskId: Long?,
        categoryId: Long?,
        date: LocalDate
    ) {
        CoroutineScope(Dispatchers.Default).launch {

            val task = factory.create(
                text = text,
                description = description,
                parentTaskId = parentTaskId,
                categoryId = categoryId,
                date = date
            )

            repository.createTask(task)
        }
    }
}