package br.com.gabriellferreira.multicurrency.presentation.view.presenter

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CurrencyListPresenterTest {
//
//    @get:Rule
//    var schedulersOverrideRule: TrampolineSchedulerRule = TrampolineSchedulerRule()
//    @Inject
//    lateinit var presenter: NewsListPresenter
//    @Inject
//    lateinit var newsRepository: NewsRepository
//    @Mock
//    lateinit var view: NewsListContract.View
//
//    @Suppress("DEPRECATION")
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        val component = DaggerTestAppComponent.builder()
//                .appModule(TestAppModule(AppApplication()))
//                .repositoryModule(TestRepositoryModule())
//                .build()
//        component.inject(this)
//        presenter.attachView(view)
//        successScenario()
//    }
//
//    //on init presenter
//    @Test
//    fun onInitialize_validData_properPresenterInitialization() {
//        presenter.onInitialize()
//        verify(view).initViews()
//        verify(view).setupToolbar()
//    }
//
//    @Test
//    fun loadMostPopularNews_validData_test_validObservableSubscription() {
//        presenter.loadMostPopularNews()
//        verify(view, atLeastOnce()).hideContent()
//        verify(view).showLoading()
//        verify(view, atLeastOnce()).hideError()
//        verify(view).clearAdapter()
//    }
//
//    @Test
//    fun loadMostPopularNews_validData_successScenario() {
//        presenter.loadMostPopularNews()
//        verify(view, atLeastOnce()).hideError()
//        verify(view).hideLoading()
//        verify(view).onRefreshFinished()
//        verify(view, times(2)).addNews(any())
//        verify(view, never()).showError()
//        verify(view).showContent()
//        verify(view, never()).showEmptyView()
//    }
//
//    @Test
//    fun loadMostPopularNews_validData_genericErrorScenario() {
//        errorScenario()
//        presenter.loadMostPopularNews()
//        verify(view, atLeastOnce()).hideContent()
//        verify(view).hideLoading()
//        verify(view).showError()
//        verify(view).onRefreshFinished()
//        verify(view, never()).showContent()
//        verify(view, never()).showEmptyView()
//    }
//
//    @Test
//    fun loadMostPopularNews_emptyData_emptyValidScenario(){
//        emptySuccessScenario()
//        presenter.loadMostPopularNews()
//        verify(view, atLeastOnce()).hideError()
//        verify(view).hideLoading()
//        verify(view).onRefreshFinished()
//        verify(view, never()).addNews(any())
//        verify(view, never()).showError()
//        verify(view, never()).showContent()
//        verify(view).showEmptyView()
//    }
//
//    private fun successScenario() {
//        whenever(newsRepository.fetchMostPopularNews(any())).thenReturn(
//                Observable.just(NewsData(), NewsData())
//        )
//    }
//
//    private fun emptySuccessScenario() {
//        whenever(newsRepository.fetchMostPopularNews(any())).thenReturn(
//                Observable.empty()
//        )
//    }
//
//    private fun errorScenario() {
//        whenever(newsRepository.fetchMostPopularNews(any())).thenReturn(
//                Observable.error(GenericException())
//        )
//    }
}