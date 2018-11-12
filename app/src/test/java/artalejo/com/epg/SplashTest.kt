package artalejo.com.epg

import artalejo.com.epg.interactor.GetChannelsInteractor
import artalejo.com.epg.interactor.SaveChannelDetailDataInteractor
import artalejo.com.epg.interactor.SaveChannelsDataInteractor
import artalejo.com.epg.model.ChannelInfo
import artalejo.com.epg.model.ImagesInfo
import artalejo.com.epg.repository.ChannelsRepository
import artalejo.com.epg.ui.base.TestContextProvider
import artalejo.com.epg.ui.splash.SplashPresenter
import artalejo.com.epg.ui.splash.SplashView
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.coroutines.experimental.AbstractCoroutineContextElement

class SplashTest {

    private lateinit var mockedChannelsRepo : ChannelsRepository
    private lateinit var mockedSplashView : SplashView
    private lateinit var splashPresenter : SplashPresenter
    private val testContext : AbstractCoroutineContextElement = TestContextProvider.TestContext

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        mockedChannelsRepo = mock()
        mockedSplashView = mock()
        splashPresenter = setUpPresenter()
    }

    @After
    fun tearDown(){
        reset(mockedChannelsRepo, mockedSplashView)
    }

    @Test
    fun isChannelDataAlreadySavedSuccess() {
        // given
        val channelInfo = ChannelInfo("id", "title", true, ImagesInfo("logo"), listOf())
        val listOfChannels = arrayListOf(channelInfo)
        val result : Result<ArrayList<ChannelInfo>, *> = Result.of { listOfChannels }
        whenever(mockedChannelsRepo.getChannels()).thenReturn(result)
        // when
        splashPresenter.isChannelDataAlreadySaved()
        // then
        verify(mockedSplashView).isDataSaved(true)
    }

    @Test
    fun isChannelDataAlreadySavedWhenEmptyListFailure() {
        // given
        val listOfChannels = arrayListOf<ChannelInfo>()
        val result : Result<ArrayList<ChannelInfo>, *> = Result.of { listOfChannels }
        whenever(mockedChannelsRepo.getChannels()).thenReturn(result)
        // when
        splashPresenter.isChannelDataAlreadySaved()
        // then
        verify(mockedSplashView).isDataSaved(false)
    }



    private fun setUpPresenter(): SplashPresenter {
        var getChannelsInteractor = GetChannelsInteractor(mockedChannelsRepo)
        getChannelsInteractor.androidContext = testContext
        var saveChannelDataInteractor = SaveChannelsDataInteractor(mockedChannelsRepo)
        saveChannelDataInteractor.androidContext = testContext
        var saveChannelDetailInteractor = SaveChannelDetailDataInteractor(mockedChannelsRepo)
        saveChannelDetailInteractor.androidContext = testContext
        return SplashPresenter(mockedSplashView, getChannelsInteractor, saveChannelDataInteractor, saveChannelDetailInteractor)
    }
}
