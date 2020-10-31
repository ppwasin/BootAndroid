package com.boot.projectMgr.createTask

data class Client(
	val id: Int,
	val name: String,
	val logo: String,
)

val mockClients = listOf(
	Client(id = 0, name = "Awsmd team", logo = ""),
	Client(id = 1, name = "Google", logo = ""),
	Client(id = 2, name = "Airbnb", logo = ""),
)
