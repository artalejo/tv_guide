package artalejo.com.epg

import android.os.Handler
import android.os.Looper
import artalejo.com.epg.async.PostExecutionThread

class UiThread constructor(val handler: Handler = Handler(Looper.getMainLooper())) : PostExecutionThread
{
    override fun <T> submit(function: () -> T?) {
        handler.post {
            function.invoke()
        }
    }

}