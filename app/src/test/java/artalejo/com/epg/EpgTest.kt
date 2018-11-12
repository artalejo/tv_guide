package artalejo.com.epg

import artalejo.com.epg.interactor.FavoriteChannelInteractor
import artalejo.com.epg.interactor.GetChannelsInteractor
import artalejo.com.epg.interactor.UnfavoriteChannelInteractor
import artalejo.com.epg.model.ChannelInfo
import artalejo.com.epg.repository.ChannelsRepository
import artalejo.com.epg.ui.base.TestContextProvider
import artalejo.com.epg.ui.epg.EpgPresenter
import artalejo.com.epg.ui.epg.EpgView
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.coroutines.experimental.AbstractCoroutineContextElement

class EpgTest {

    private lateinit var mockedChannelsRepo : ChannelsRepository
    private lateinit var mockedEpgView : EpgView
    private lateinit var epgPresenter : EpgPresenter
    private val testContext : AbstractCoroutineContextElement = TestContextProvider.TestContext

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        mockedChannelsRepo = mock()
        mockedEpgView = mock()
        epgPresenter = setUpPresenter()
    }

    @After
    fun tearDown(){
        reset(mockedChannelsRepo, mockedEpgView)
    }

    @Test
    fun onGetChannelsSuccess() {
        // given
        val listOfChannels = arrayListOf<ChannelInfo>()
        val result : Result<ArrayList<ChannelInfo>, *> = Result.of { listOfChannels }
        whenever(mockedChannelsRepo.getChannels()).thenReturn(result)
        // when
        epgPresenter.getChannels()
        // then
        verify(mockedEpgView).showLoading()
        verify(mockedEpgView).showChannels(any())
        verify(mockedEpgView).hideLoading()
    }

    @Test
    fun onGetChannelsFailure() {
        // given
        whenever(mockedChannelsRepo.getChannels()).thenReturn(Result.Failure())
        // when
        epgPresenter.getChannels()
        // then
        verify(mockedEpgView).showLoading()
        verify(mockedEpgView).hideLoading()
    }

    @Test
    fun onFavoriteChannelSuccess() {
        // given
        val channelID = "1"
        val success = Result.Success(true)
        whenever(mockedChannelsRepo.setFavoriteStatus(channelID, true)).thenReturn(success)
        // when
        epgPresenter.onChannelFavoriteStatusChanged(channelID, true)
        // then
        verify(mockedEpgView).favoriteStatusUpdated()
    }


    @Test
    fun onUnfavoriteChannelSuccess() {
        // given
        val channelID = "1"
        val success = Result.Success(true)
        whenever(mockedChannelsRepo.setFavoriteStatus(channelID, false)).thenReturn(success)
        // when
        epgPresenter.onChannelFavoriteStatusChanged(channelID, false)
        // then
        verify(mockedEpgView).favoriteStatusUpdated()
    }

    @Test
    fun onFavoriteChannelFailure() {
        // given
        val channelID = "1"
        val failure = Result.Failure()
        whenever(mockedChannelsRepo.setFavoriteStatus(channelID, true)).thenReturn(failure)
        // when
        epgPresenter.onChannelFavoriteStatusChanged(channelID, true)
        // then
        verify(mockedEpgView, times(0)).favoriteStatusUpdated()
    }


    private fun setUpPresenter(): EpgPresenter {
        var getChannelsInteractor = GetChannelsInteractor(mockedChannelsRepo)
        getChannelsInteractor.androidContext = testContext
        var favoriteChannelInteractor = FavoriteChannelInteractor(mockedChannelsRepo)
        favoriteChannelInteractor.androidContext = testContext
        var unfavoriteChannelInteractor = UnfavoriteChannelInteractor(mockedChannelsRepo)
        unfavoriteChannelInteractor.androidContext = testContext
        return EpgPresenter(mockedEpgView, getChannelsInteractor,
                favoriteChannelInteractor, unfavoriteChannelInteractor)
    }
}
