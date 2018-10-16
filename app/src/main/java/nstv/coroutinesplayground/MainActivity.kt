package nstv.coroutinesplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import nstv.coroutinesplayground.data.RxService
import nstv.coroutinesplayground.list.UserAdapter

class MainActivity : AppCompatActivity() {

    //TODO() inject all of this
    val adapter = UserAdapter()
    val service = RxService()
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        list.adapter = adapter
        loadData()
    }

    fun loadData() {
        //Only angry users
        compositeDisposable.add(
            service.getAngryUserIds()
                .flatMap(service::getUsers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::updateItems)
        )
    }

    override fun onStop() {
        super.onStop()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
