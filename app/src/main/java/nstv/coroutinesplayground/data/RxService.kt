package nstv.coroutinesplayground.data

import io.reactivex.Single
import nstv.coroutinesplayground.model.User

/**
 * Created by Nicole Terc on 10/16/18.
 */
class RxService {
    private val server = DummyServer()

    fun getUsers(): Single<List<User>> {
        return Single.just(server.getUsers())
    }

    fun getUsers(ids: List<Int>): Single<List<User>> {
        return Single.just(server.getUsers(ids))
    }

    fun getAngryUserIds(): Single<List<Int>> {
        return Single.just(server.getAngryUserIds())
    }

}