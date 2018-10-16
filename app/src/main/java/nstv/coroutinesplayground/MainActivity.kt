package nstv.coroutinesplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.*
import nstv.coroutinesplayground.data.Service
import nstv.coroutinesplayground.list.UserAdapter

class MainActivity : AppCompatActivity() {

    //TODO() inject all of this
    val adapter = UserAdapter()
    val service = Service()
    var parentJob = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        list.adapter = adapter
        GlobalScope.launch(parentJob) {
            loadData()
        }
    }

    suspend fun loadData() {
        //Only angry users
        val angryUsersIds = service.getAngryUserIds()
        val angryUsers = service.getUsers(angryUsersIds)

        withContext(Dispatchers.Main) {
            adapter.updateItems(angryUsers)
        }
    }

    override fun onStop() {
        super.onStop()
        parentJob.cancel()
    }
}
