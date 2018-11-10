package artalejo.com.epg.async

interface PostExecutionThread {

    fun <T> submit(function: () -> T?)
}