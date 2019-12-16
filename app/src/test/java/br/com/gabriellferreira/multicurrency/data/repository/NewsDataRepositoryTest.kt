package br.com.gabriellferreira.multicurrency.data.repository

class NewsDataRepositoryTest {
//
//    @get:Rule
//    var schedulersOverrideRule: TrampolineSchedulerRule = TrampolineSchedulerRule()
//    @Mock
//    lateinit var newsApi: NewsApi
//    @Mock
//    lateinit var storage: SessionPreferences
//
//    lateinit var repository: NewsDataRepository
//
//    companion object {
//        const val TIME_PERIOD_7_DAYS = 7
//    }
//
//    private val data = NewsData(
//            id = "id",
//            url = "url",
//            countType = "countType",
//            column = "column",
//            section = "section",
//            byline = "byline",
//            title = "title",
//            abstract = "abstract",
//            source = "source",
//            publishedDate = "publishedDate"
//    )
//
//    private val dataV2 = NewsData(
//            id = "id2",
//            url = "url",
//            countType = "countType",
//            column = "column",
//            section = "section",
//            byline = "byline",
//            title = "title",
//            abstract = "abstract",
//            source = "source",
//            publishedDate = "publishedDate"
//    )
//
//    @Suppress("DEPRECATION")
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        val component = DaggerTestAppComponent.builder()
//                .appModule(TestAppModule(AppApplication()))
//                .repositoryModule(TestRepositoryModule())
//                .testServiceModule(TestServiceModule())
//                .build()
//        component.inject(this)
//        repository = NewsDataRepository(newsApi, storage)
//    }
//
//    @Test
//    fun fetchMostPopularNews_validDataFromApiAndCache_oneValidObject() {
//        whenever(newsApi.getMostPopularNews(any())).thenAnswer {
//            Observable.just(data)
//        }
//        whenever(storage.loadNewsData()).thenAnswer {
//            Observable.just(data, data)
//        }
//
//        repository.fetchMostPopularNews(TIME_PERIOD_7_DAYS).test()
//        verify(newsApi).getMostPopularNews(TIME_PERIOD_7_DAYS)
//        verify(storage, times(1)).saveNewsData(any())
//    }
//
//    @Test
//    fun fetchMostPopularNews_validDataFromApi_oneValidObject() {
//        whenever(newsApi.getMostPopularNews(any())).thenAnswer {
//            Observable.just(data)
//        }
//        whenever(storage.loadNewsData()).thenAnswer {
//            Observable.empty<NewsData>()
//        }
//
//        repository.fetchMostPopularNews(TIME_PERIOD_7_DAYS).test()
//        verify(newsApi).getMostPopularNews(TIME_PERIOD_7_DAYS)
//        verify(storage, times(1)).saveNewsData(any())
//    }
//
//    @Test
//    fun fetchMostPopularNews_validDataFromCache_oneValidObject() {
//        whenever(newsApi.getMostPopularNews(any())).thenAnswer {
//            Observable.empty<NewsData>()
//        }
//        whenever(storage.loadNewsData()).thenAnswer {
//            Observable.just(data)
//        }
//
//        repository.fetchMostPopularNews(TIME_PERIOD_7_DAYS).test()
//        verify(newsApi).getMostPopularNews(TIME_PERIOD_7_DAYS)
//        verify(storage, times(1)).saveNewsData(any())
//    }
//
//    @Test
//    fun fetchMostPopularNews_validDataFromApiAndCache_twoValidObject() {
//        whenever(newsApi.getMostPopularNews(any())).thenAnswer {
//            Observable.just(data, dataV2)
//        }
//        whenever(storage.loadNewsData()).thenAnswer {
//            Observable.just(data)
//        }
//
//        repository.fetchMostPopularNews(TIME_PERIOD_7_DAYS).test()
//        verify(newsApi).getMostPopularNews(TIME_PERIOD_7_DAYS)
//        verify(storage, times(2)).saveNewsData(any())
//    }
}