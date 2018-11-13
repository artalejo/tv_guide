package artalejo.com.epg

import artalejo.com.epg.interactor.GetChannelDetailInteractor
import artalejo.com.epg.interactor.GetChannelsInteractor
import artalejo.com.epg.model.ChannelDetailInfo
import artalejo.com.epg.model.ChannelMetadataInfo
import artalejo.com.epg.model.ImagesInfo
import artalejo.com.epg.repository.ChannelsRepository
import artalejo.com.epg.ui.base.TestContextProvider
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.coroutines.experimental.AbstractCoroutineContextElement

class GetChannelDataInteractorsTest {

    private lateinit var mockedChannelsRepo : ChannelsRepository
    private lateinit var getChannelsInteractor : GetChannelsInteractor
    private lateinit var getChannelDetailInteractor: GetChannelDetailInteractor
    private val testContext : AbstractCoroutineContextElement = TestContextProvider.TestContext

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        mockedChannelsRepo = mock()
    }

    @After
    fun tearDown(){
        reset(mockedChannelsRepo)
    }

    @Test
    fun getChannelsInteractorSuccess() {
        // given
        getChannelsInteractor = GetChannelsInteractor(mockedChannelsRepo)
        getChannelsInteractor.androidContext = testContext
        val id = "id"
        whenever(mockedChannelsRepo.getChannels()).thenReturn(Result.Success(arrayListOf()))
        // when
        getChannelsInteractor.execute(Unit) {}
        // then
        verify(mockedChannelsRepo).getChannels()
    }

    @Test
    fun getChannelsDetailsInfoSuccess() {
        // given
        val channelMeta = ChannelMetadataInfo("year", listOf(), listOf(), listOf())
        val channelDetails = ChannelDetailInfo("id", "title", "start",
                "end", ImagesInfo("logo"), "channelTitle",
                ImagesInfo("channel_logo"), "description", channelMeta)
        getChannelDetailInteractor = GetChannelDetailInteractor(mockedChannelsRepo)
        getChannelDetailInteractor.androidContext = testContext
        val id = "id"
        whenever(mockedChannelsRepo.getChannelDetail()).thenReturn(Result.Success(channelDetails))
        // when
        getChannelDetailInteractor.execute(Unit) {}
        // then
        verify(mockedChannelsRepo).getChannelDetail()
    }


}
