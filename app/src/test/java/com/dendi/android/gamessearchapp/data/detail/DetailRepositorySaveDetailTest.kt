package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.data.detail.cache.DetailCacheDataSource
import com.dendi.android.gamessearchapp.data.detail.cache.DetailCache
import com.dendi.android.gamessearchapp.data.detail.cache.DetailDataToCacheMapper
import com.dendi.android.gamessearchapp.data.detail.cloud.DetailCloud
import com.dendi.android.gamessearchapp.data.detail.cloud.DetailCloudDataSource
import com.dendi.android.gamessearchapp.data.detail.cloud.ScreenshotCloud
import com.dendi.android.gamessearchapp.data.detail.cloud.SystemRequirementsCloud
import com.dendi.android.gamessearchapp.domain.detail.BaseDetailDataToDomainMapper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */
class DetailRepositorySaveDetailTest : BaseDetailRepositoryTest() {

    @Test
    fun test_save_detail() = runBlocking {
        val testCloudDataSource = TestDetailCloudDataSource()
        val testCacheDataSource = TestDetailCacheDataSource()
        val repository = BaseDetailRepository(
            testCloudDataSource,
            testCacheDataSource,
            DetailDataMapper.Base()
        )

        val actualCloud = repository.readId(452)
        val expectedCloud = DataDetailState.Success(DetailData.Base(
            description = "Call of Duty: Warzone is both a standalone free-to-play...",
            developer = "Infinity Ward",
            freetogameProfileUrl = "https://www.freetogame.com/call-of-duty-warzone",
            gameUrl = "https://www.freetogame.com/open/call-of-duty-warzone",
            genre = "Shooter",
            id = 452,
            systemRequirements = SystemRequirementsData.Base(
                1,
                graphics = "NVIDIA GeForce GTX 670 / GeForce GTX 1650 or Radeon HD 7950",
                memory = "8GB RAM",
                os = "Windows 7 64-Bit (SP1) or Windows 10 64-Bit",
                processor = "Intel Core i3-4340 or AMD FX-6300",
                storage = "175GB HD space"
            ),
            platform = "Windows",
            publisher = "Activision",
            releaseDate = "2020-03-10",
            screenshots = listOf(
                ScreenshotData.Base(1124,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-1.jpg"),
                ScreenshotData.Base(1125,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-2.jpg"),
                ScreenshotData.Base(1126,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-3.jpg"),
                ScreenshotData.Base(1127,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-4.jpg"),

                ),
            shortDescription = "A standalone free-to-pla…f Duty: Modern Warfare.",
            status = "Live",
            thumbnail = "https://www.freetogame.com/g/452/thumbnail.jpg",
            title = "Call Of Duty: Warzone"
        ))

        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.readId(452)
        val expectedCache = DataDetailState.Success(DetailData.Base(
            description = "Call of Duty: Warzone is both a standalone free-to-play...",
            developer = "Infinity Ward",
            freetogameProfileUrl = "https://www.freetogame.com/call-of-duty-warzone",
            gameUrl = "https://www.freetogame.com/open/call-of-duty-warzone",
            genre = "Shooter",
            id = 452,
            systemRequirements = SystemRequirementsData.Base(
                1,
                graphics = "NVIDIA GeForce GTX 670 / GeForce GTX 1650 or Radeon HD 7950",
                memory = "8GB RAM",
                os = "Windows 7 64-Bit (SP1) or Windows 10 64-Bit",
                processor = "Intel Core i3-4340 or AMD FX-6300",
                storage = "175GB HD space"
            ),
            platform = "Windows",
            publisher = "Activision",
            releaseDate = "2020-03-10",
            screenshots = listOf(
                ScreenshotData.Base(1124,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-1.jpg"),
                ScreenshotData.Base(1125,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-2.jpg"),
                ScreenshotData.Base(1126,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-3.jpg"),
                ScreenshotData.Base(1127,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-4.jpg"),

                ),
            shortDescription = "A standalone free-to-pla…f Duty: Modern Warfare.",
            status = "Live",
            thumbnail = "https://www.freetogame.com/g/452/thumbnail.jpg",
            title = "Call Of Duty: Warzone"
        ))

        assertEquals(expectedCache, actualCache)
    }

    private inner class TestDetailCloudDataSource : DetailCloudDataSource {
        override suspend fun readId(id: Int) = DetailCloud.Base(
            description = "Call of Duty: Warzone is both a standalone free-to-play...",
            developer = "Infinity Ward",
            freetogameProfileUrl = "https://www.freetogame.com/call-of-duty-warzone",
            gameUrl = "https://www.freetogame.com/open/call-of-duty-warzone",
            genre = "Shooter",
            id = 452,
            systemRequirements = SystemRequirementsCloud.Base(
                graphics = "NVIDIA GeForce GTX 670 / GeForce GTX 1650 or Radeon HD 7950",
                memory = "8GB RAM",
                os = "Windows 7 64-Bit (SP1) or Windows 10 64-Bit",
                processor = "Intel Core i3-4340 or AMD FX-6300",
                storage = "175GB HD space"
            ),
            platform = "Windows",
            publisher = "Activision",
            releaseDate = "2020-03-10",
            screenshots = listOf(
                ScreenshotCloud.Base(1124,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-1.jpg"),
                ScreenshotCloud.Base(1125,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-2.jpg"),
                ScreenshotCloud.Base(1126,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-3.jpg"),
                ScreenshotCloud.Base(1127,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-4.jpg"),

                ),
            shortDescription = "A standalone free-to-pla…f Duty: Modern Warfare.",
            status = "Live",
            thumbnail = "https://www.freetogame.com/g/452/thumbnail.jpg",
            title = "Call Of Duty: Warzone"
        )
    }

    private inner class TestDetailCacheDataSource : DetailCacheDataSource {

        private val detail = DetailData.Base(
            description = "Call of Duty: Warzone is both a standalone free-to-play...",
            developer = "Infinity Ward",
            freetogameProfileUrl = "https://www.freetogame.com/call-of-duty-warzone",
            gameUrl = "https://www.freetogame.com/open/call-of-duty-warzone",
            genre = "Shooter",
            id = 452,
            systemRequirements = SystemRequirementsData.Base(
                id = 1,
                graphics = "NVIDIA GeForce GTX 670 / GeForce GTX 1650 or Radeon HD 7950",
                memory = "8GB RAM",
                os = "Windows 7 64-Bit (SP1) or Windows 10 64-Bit",
                processor = "Intel Core i3-4340 or AMD FX-6300",
                storage = "175GB HD space"
            ),
            platform = "Windows",
            publisher = "Activision",
            releaseDate = "2020-03-10",
            screenshots = listOf(
                ScreenshotData.Base(1124,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-1.jpg"),
                ScreenshotData.Base(1125,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-2.jpg"),
                ScreenshotData.Base(1126,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-3.jpg"),
                ScreenshotData.Base(1127,
                    "https://www.freetogame.com/g/452/Call-of-Duty-Warzone-4.jpg"),

                ),
            shortDescription = "A standalone free-to-pla…f Duty: Modern Warfare.",
            status = "Live",
            thumbnail = "https://www.freetogame.com/g/452/thumbnail.jpg",
            title = "Call Of Duty: Warzone"
        )

        override suspend fun readId(id: Int): DetailCache {
            return detail.map(DetailDataToCacheMapper.Base())
        }

        override suspend fun save(detail: DetailData) {
            detail
        }
    }
}