package com.marllons.mshttp.result

import com.marllons.mshttp.domain.model.MSHttpError
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ResultTest {
    private val successValueMock = mockk<Any>(relaxed = true)
    private val errorValueMock = mockk<MSHttpError>(relaxed = true)

    private val successSubject = Result.success(successValueMock)
    private val errorSubject = Result.failure<Any>(errorValueMock)

    private val actionMock = mockk<suspend (value: Any) -> Unit>(relaxed = true)
    private val mapActionMock = mockk<suspend (value: Any) -> Any>(relaxed = true)
    private val flatMapActionMock = mockk<suspend (value: Any) -> Result<Any>>(relaxed = true)
    private val errorActionMock = mockk<suspend (value: MSHttpError) -> MSHttpError>(relaxed = true)
    private val recoverActionMock = mockk<suspend (value: MSHttpError) -> Result<Any>>(relaxed = true)

    @Before
    fun setupMock() {
        coEvery { actionMock.invoke(successValueMock) } just runs
        coEvery { actionMock.invoke(errorValueMock) } just runs

        coEvery { mapActionMock.invoke(successValueMock) } returns successValueMock
        coEvery { mapActionMock.invoke(errorValueMock) } returns errorValueMock

        coEvery { flatMapActionMock.invoke(successValueMock) } returns successSubject
        coEvery { flatMapActionMock.invoke(errorValueMock) } returns errorSubject

        coEvery { errorActionMock.invoke(errorValueMock) } returns errorValueMock

        coEvery { recoverActionMock.invoke(errorValueMock) } returns successSubject
    }

    @After
    fun clearActionMock() {
        clearMocks(actionMock, mapActionMock)
    }

    @Test
    fun `When call success Then shouldn't value be null`() {
        val result = Result.success(successValueMock)
        assertNotNull(result.getOrNull())
        assertNull(result.errorOrNull())
    }

    @Test
    fun `When call error Then should value be null`() {
        val result = Result.failure<Any>(errorValueMock)
        assertNull(result.getOrNull())
        assertNotNull(result.errorOrNull())
    }

    @Test
    fun `Given a success Result When call onSuccess Then should calls the action`() {
        runTest {
            val result = successSubject.onSuccess(actionMock)
            coVerify(exactly = 1) { actionMock.invoke(successValueMock) }

            assertEquals(successSubject, result)
        }
    }

    @Test
    fun `Given an error Result When call onSuccess Then shouldn't calls the action`() {
        runTest {
            val result = errorSubject.onSuccess(actionMock)
            coVerify(exactly = 0) { actionMock.invoke(any()) }

            assertEquals(errorSubject, result)
        }
    }

    @Test
    fun `Given a success Result When call onFailure Then shouldn't calls the action`() {
        runTest {
            val result = successSubject.onFailure(actionMock)
            coVerify(exactly = 0) { actionMock.invoke(any()) }

            assertEquals(successSubject, result)
        }
    }

    @Test
    fun `Given an error Result When call onFailure Then should calls the action`() {
        runTest {
            val result = errorSubject.onFailure(actionMock)
            coVerify(exactly = 1) { actionMock.invoke(errorValueMock) }

            assertEquals(errorSubject, result)
        }
    }

    @Test
    fun `Given a success Result When call onAny Then should calls the action`() {
        runTest {
            val result = successSubject.onAny(actionMock)
            coVerify(exactly = 1) { actionMock.invoke(successSubject) }

            assertEquals(successSubject, result)
        }
    }

    @Test
    fun `Given an error Result When call onAny Then should calls the action`() {
        runTest {
            val result = errorSubject.onAny(actionMock)
            coVerify(exactly = 1) { actionMock.invoke(errorSubject) }

            assertEquals(errorSubject, result)
        }
    }

    @Test
    fun `Given a success Result When call flatMap Then should calls the action`() {
        runTest {
            val result = successSubject.flatMap(flatMapActionMock)
            coVerify(exactly = 1) { flatMapActionMock.invoke(successValueMock) }

            assertEquals(successSubject, result)
        }
    }

    @Test
    fun `Given an error Result When call flatMap Then shouldn't calls the action`() {
        runTest {
            val result = errorSubject.flatMap(flatMapActionMock)
            coVerify(exactly = 0) { flatMapActionMock.invoke(any()) }

            assertEquals(errorSubject, result)
        }
    }

    @Test
    fun `Given a success Result When call map Then should calls the action`() {
        runTest {
            val result = successSubject.map(mapActionMock)
            coVerify(exactly = 1) { mapActionMock.invoke(successValueMock) }

            assertEquals(Result.success(successValueMock), result)
        }
    }

    @Test
    fun `Given an error Result When call map Then shouldn't calls the action`() {
        runTest {
            val result = errorSubject.map(mapActionMock)
            coVerify(exactly = 0) { mapActionMock.invoke(any()) }

            assertEquals(Result.failure<Any>(errorValueMock), result)
        }
    }

    @Test
    fun `Given a success Result When call mapError Then shouldn't calls the action`() {
        runTest {
            val result = successSubject.mapError(errorActionMock)
            coVerify(exactly = 0) { mapActionMock.invoke(any()) }

            assertEquals(successSubject, result)
        }
    }

    @Test
    fun `Given an error Result When call mapError Then should calls the action`() {
        runTest {
            val result = errorSubject.mapError(errorActionMock)
            coVerify(exactly = 1) { errorActionMock.invoke(errorValueMock) }

            assertEquals(Result.failure<Any>(errorValueMock), result)
        }
    }

    @Test
    fun `Given a success Result When call getOrDefault Then should return the value`() {
        val defaultValue = "defaultValue"
        val result = successSubject.getOrDefault(defaultValue)
        assertEquals(successSubject, result)
    }

    @Test
    fun `Given an error Result When call getOrDefault Then should return the default`() {
        val defaultValue = "defaultValue"
        val result = errorSubject.getOrDefault(defaultValue)
        assertEquals(Result.success(defaultValue), result)
    }

    @Test
    fun `Given a success Result When call getOrNull Then should return not null`() {
        val result = successSubject.getOrNull()
        assertNotNull(result)
    }

    @Test
    fun `Given an error Result When call getOrNull Then should return null`() {
        val result = errorSubject.getOrNull()
        assertNull(result)
    }

    @Test
    fun `Given a success Result When call errorOrNull Then should return null`() {
        val result = successSubject.errorOrNull()
        assertNull(result)
    }

    @Test
    fun `Given an error Result When call errorOrNull Then should return null`() {
        val result = errorSubject.errorOrNull()
        assertNotNull(result)
    }

    @Test
    fun `Given a success Result When call isSuccessful should return true`() {
        val result = successSubject.isSuccessful()
        assertTrue(result)
    }

    @Test
    fun `Given an error Result When call isSuccessful should return false`() {
        val result = errorSubject.isSuccessful()
        assertFalse(result)
    }
}
