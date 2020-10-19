package com.boot.projectMgr

enum class Status {
    New,
    InProgress,
    Review,
    Done,
}

class Project(
    val id: Int,
    val title: String,
    val date: String,
    val days: Int,
    val status: Status,
    val users: List<User>,
    val tasks: List<Task>,
)

class User(
    val id: Int,
    val name: String,
) {
    fun imageUrlForSize(size: Int) = "https://www.gravatar.com/avatar/$id"
}

class Task(
    val id: Int,
    val timeCode: String,
    val title: String,
    val tag: String,
    val status: Status,
    val assignees: List<User>,
    val commentCount: Int,
    val attachmentCount: Int,
)

val zachary = User(
    id = 2,
    name = "Zachary Butler",
)
val mary = User(
    id = 3,
    name = "Mary Brown",
)
val sarah = User(
    id = 4,
    name = "Sarah Murphy"
)
val mockProject = Project(
    id = 1,
    title = "Create additional pages",
    date = "Dec 18, 2019",
    days = 3,
    status = Status.InProgress,
    users = listOf(mary, sarah, zachary),
    tasks = listOf(
        Task(
            id = 163,
            timeCode = "24.19",
            title = "Contact page",
            tag = "#Design",
            status = Status.InProgress,
            assignees = listOf(zachary),
            commentCount = 3,
            attachmentCount = 5,
        ),
        Task(
            id = 158,
            timeCode = "24.19",
            title = "Calculator page",
            tag = "#Design",
            status = Status.Done,
            assignees = listOf(sarah, mary),
            commentCount = 8,
            attachmentCount = 2,
        ),
        Task(
            id = 157,
            timeCode = "23.19",
            title = "Technical Task",
            tag = "#Frontend",
            status = Status.Review,
            assignees = listOf(zachary),
            commentCount = 4,
            attachmentCount = 8,
        ),
        Task(
            id = 159,
            timeCode = "23.19",
            title = "Calculator page",
            tag = "#Backend",
            status = Status.Done,
            assignees = listOf(mary),
            commentCount = 4,
            attachmentCount = 6,
        ),
        Task(
            id = 163,
            timeCode = "22.19",
            title = "Contact page",
            tag = "#Design",
            status = Status.InProgress,
            assignees = listOf(zachary),
            commentCount = 3,
            attachmentCount = 5,
        ),
        Task(
            id = 158,
            timeCode = "22.19",
            title = "Calculator page",
            tag = "#Design",
            status = Status.Done,
            assignees = listOf(sarah, mary),
            commentCount = 8,
            attachmentCount = 2,
        ),
        Task(
            id = 157,
            timeCode = "21.19",
            title = "Technical Task",
            tag = "#Frontend",
            status = Status.Review,
            assignees = listOf(zachary),
            commentCount = 4,
            attachmentCount = 8,
        ),
        Task(
            id = 159,
            timeCode = "21.19",
            title = "Calculator page",
            tag = "#Backend",
            status = Status.Done,
            assignees = listOf(mary),
            commentCount = 4,
            attachmentCount = 6,
        ),
    )
)