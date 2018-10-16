package nstv.coroutinesplayground

import nstv.coroutinesplayground.model.User

/**
 * Created by Nicole Terc on 10/16/18.
 */
class DummyServer() {

    fun getUsers(): List<User> {
        val users = ArrayList<User>()

        for (i in 0..10) {
            users.add(User(i, "name #$i", ""))
        }

        Thread.sleep(1000)
        return users
    }

    fun getAngryUserIds(): List<Int> {
        return IntArray(10).toList().filter { it % 3 == 0 }
    }
}