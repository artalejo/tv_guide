package artalejo.com.epg

import artalejo.com.epg.interactor.FavoriteChannelInteractor
import artalejo.com.epg.interactor.UnfavoriteChannelInteractor
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

class FavoriteInteractorsTest {

    private lateinit var mockedChannelsRepo : ChannelsRepository
    private lateinit var favoriteChannelInteractor: FavoriteChannelInteractor
    private lateinit var unFavoriteChannelInteractor: UnfavoriteChannelInteractor
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
    fun onFavoriteInteractorSuccess() {
        // given
        favoriteChannelInteractor = FavoriteChannelInteractor(mockedChannelsRepo)
        favoriteChannelInteractor.androidContext = testContext
        val id = "id"
        whenever(mockedChannelsRepo.setFavoriteStatus(id, true)).thenReturn(Result.Success(true))
        // when
        favoriteChannelInteractor.execute(id, {})
        // then
        verify(mockedChannelsRepo).setFavoriteStatus(id, true)
    }

    @Test
    fun onUnFavoriteInteractorSuccess() {
        // given
        unFavoriteChannelInteractor = UnfavoriteChannelInteractor(mockedChannelsRepo)
        unFavoriteChannelInteractor.androidContext = testContext
        val id = "id"
        whenever(mockedChannelsRepo.setFavoriteStatus(id, false)).thenReturn(Result.Success(true))
        // when
        unFavoriteChannelInteractor.execute(id, {})
        // then
        verify(mockedChannelsRepo).setFavoriteStatus(id, false)
    }


}
