package artalejo.com.epg

import artalejo.com.epg.interactor.GetChannelDetailInteractor
import artalejo.com.epg.model.ChannelDetailInfo
import artalejo.com.epg.model.ChannelMetadataInfo
import artalejo.com.epg.model.ImagesInfo
import artalejo.com.epg.repository.ChannelsRepository
import artalejo.com.epg.ui.base.TestContextProvider
import artalejo.com.epg.ui.channelDetail.ChannelDetailPresenter
import artalejo.com.epg.ui.channelDetail.ChannelDetailView
import artalejo.com.epg.ui.entities.toChannelDetailViewEntity
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.coroutines.experimental.AbstractCoroutineContextElement

class EpgDetailTest {

    private lateinit var mockedChannelsRepo : ChannelsRepository
    private lateinit var mockedChannelView : ChannelDetailView
    private lateinit var channelDetailPresenter : ChannelDetailPresenter
    private val testContext : AbstractCoroutineContextElement = TestContextProvider.TestContext

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        mockedChannelsRepo = mock()
        mockedChannelView = mock()
        channelDetailPresenter = setUpPresenter()
    }

    @After
    fun tearDown(){
        reset(mockedChannelsRepo, mockedChannelView)
    }

    @Test
    fun onGetChannelsSuccess() {
        // given
        val channelMeta = ChannelMetadataInfo("year", listOf(), listOf(), listOf())
        val channelDetails = ChannelDetailInfo("id", "title", "start",
                "end", ImagesInfo("logo"), "channelTitle",
                ImagesInfo("channel_logo"), "description", channelMeta)
        val result : Result<ChannelDetailInfo, *> = Result.of { channelDetails }
        whenever(mockedChannelsRepo.getChannelDetail()).thenReturn(result)
        // when
        channelDetailPresenter.getChannelDetails()
        // then
        verify(mockedChannelView).showLoading()
        verify(mockedChannelView).showChannelDetails(channelDetails.toChannelDetailViewEntity())
        verify(mockedChannelView).hideLoading()
    }

    @Test
    fun onGetChannelsFailure() {
        // given
        val failure = Result.Failure()
        whenever(mockedChannelsRepo.getChannelDetail()).thenReturn(failure)
        // when
        channelDetailPresenter.getChannelDetails()
        // then
        verify(mockedChannelView).showLoading()
        verify(mockedChannelView, never()).showChannelDetails(any())
        verify(mockedChannelView).hideLoading()
    }


    private fun setUpPresenter(): ChannelDetailPresenter {
        var getChannelDetailsInteractor = GetChannelDetailInteractor(mockedChannelsRepo)
        getChannelDetailsInteractor.androidContext = testContext
        return ChannelDetailPresenter(mockedChannelView, getChannelDetailsInteractor)
    }
}
