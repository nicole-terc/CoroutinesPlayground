package nstv.coroutinesplayground.data

import nstv.coroutinesplayground.model.User

/**
 * Created by Nicole Terc on 10/16/18.
 */
class Service {
    private val server = DummyServer()

    suspend fun getUsers(): List<User> {
        return server.getUsers()
    }

    suspend fun getUsers(ids: List<Int>): List<User> {
        return server.getUsers(ids)
    }

    suspend fun getAngryUserIds(): List<Int> {
        return server.getAngryUserIds()
    }
}