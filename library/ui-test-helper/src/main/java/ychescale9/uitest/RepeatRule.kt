package ychescale9.uitest

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * TestRule to run tests multiple times.
 * Can be used to debug flaky tests.
 */
class RepeatRule(private val iterations: Int) : TestRule {

    init {
        if (iterations < 1) throw IllegalArgumentException("iterations < 1: $iterations")
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                for (i in 0 until iterations) {
                    base.evaluate()
                }
            }
        }
    }
}
