package reactivecircus.releaseprobe.persistence

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before

abstract class BaseDaoTest {

    protected lateinit var database: ReleaseProbeDatabase

    @Before
    open fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ReleaseProbeDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    open fun tearDown() {
        database.close()
    }
}
