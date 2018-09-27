package ychescale9.releaseprobe.persistence.artifact.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.After
import org.junit.Before
import ychescale9.releaseprobe.persistence.ReleaseProbeDatabase

abstract class BaseDaoTest {

    protected lateinit var database: ReleaseProbeDatabase

    @Before
    open fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
                getInstrumentation().targetContext,
                ReleaseProbeDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    open fun tearDown() {
        database.close()
    }
}
