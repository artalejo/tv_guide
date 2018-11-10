package artalejo.com.epg.interactor

import artalejo.com.epg.Result
import artalejo.com.epg.async.doAsync
import artalejo.com.epg.async.onComplete
import artalejo.com.epg.async.PostExecutionThread

abstract class Interactor<out SuccessValue, in Parameters > constructor(val postExecutionThread: PostExecutionThread) {

    fun execute(parameters: Parameters, delegate: (result: Result<SuccessValue, *>) -> Unit) = doAsync {
        val result = run(parameters)

        onComplete(postExecutionThread) {
            delegate(result)
        }
    }

    abstract fun run(params: Parameters): Result<SuccessValue, *>
}