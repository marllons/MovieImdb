package com.marllons.mshttp.result

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.marllons.mshttp.domain.model.MSHttpError
import com.marllons.mshttp.domain.model.MSHttpErrorBodyException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import java.io.IOException
import java.net.UnknownHostException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RetrofitResultImplTest {

    private val requestMock = mockk<suspend () -> Response<Any>>()
    private val gsonMock = mockk<Gson>()

    private val errorMock = mockk<MSHttpError>(relaxed = true)
    private val responseMock = mockk<Response<Any>>(relaxed = true)
    private val responseBodyMock = mockk<ResponseBody>(relaxed = true)

    private val httpCodeStub = 400

    private val subject = RetrofitResultImpl(gsonMock)

    @Before
    fun setupMocks() {
        every { gsonMock.fromJson(any<String>(), MSHttpError::class.java) } returns errorMock
        coEvery { requestMock.invoke() } returns responseMock

        every { responseMock.errorBody() } returns responseBodyMock
        every { responseMock.body() } returns responseMock
        every { responseMock.code() } returns httpCodeStub
        every { responseMock.isSuccessful } returns true
    }

    @Test
    fun `Given a request When isSuccessful and body isn't null Then should return success`() {
        runTest {
            val body = "body"
            every { responseMock.isSuccessful } returns true
            every { responseMock.body() } returns body

            val result = subject.getResult(requestMock)
            assertEquals(Result.success(body), result)
        }
    }

    @Test
    fun `Given a request When isSuccessful is false and errorBody is null Then should return GENERIC_ERROR`() {
        runTest {
            every { responseMock.isSuccessful } returns false
            every { responseMock.body() } returns "body"
            every { responseMock.errorBody() } returns null

            val result = subject.getResult(requestMock)
            assertEquals(Result.failure<Any>(GENERIC_ERROR), result)
        }
    }

    @Test
    fun `Given a request When body is null and errorBody is null Then should return GENERIC_ERROR`() {
        runTest {
            every { responseMock.isSuccessful } returns true
            every { responseMock.body() } returns null
            every { responseMock.errorBody() } returns null

            val result = subject.getResult(requestMock)
            assertEquals(Result.failure<Any>(GENERIC_ERROR), result)
        }
    }

    @Test
    fun `Given a request When is valid Then should return the object`() {
        runTest {
            every { errorMock.isValid() } returns true
            val result = subject.getResult(requestMock)

            assertEquals(Result.success<Any>(responseMock), result)
        }
    }

    @Test
    fun `Given a request When fromJson throws JsonSyntaxException Then should return GENERIC_ERROR`() {
        runTest {
            val exception = JsonSyntaxException("")
            val jsonBody = "Error body"
            val expectedCause = MSHttpErrorBodyException(jsonError = jsonBody, code = httpCodeStub)
            every { responseBodyMock.string() } returns jsonBody
            every { responseMock.isSuccessful } returns false
            every {
                gsonMock.fromJson(any<String>(), ErrorResponse::class.java)
            } throws exception

            val result = subject.getResult(requestMock)

            val error = GENERIC_ERROR.copy(cause = expectedCause)
            assertEquals(Result.failure<Any>(error), result)
        }
    }

    @Test
    fun `Given a request When fromJson return null Then should return GENERIC_ERROR`() {
        runTest {
            every { responseMock.isSuccessful } returns false
            val jsonBody = "Error body"
            val expectedCause = MSHttpErrorBodyException(jsonError = jsonBody, code = httpCodeStub)
            every { responseBodyMock.string() } returns jsonBody
            every {
                gsonMock.fromJson(any<String>(), ErrorResponse::class.java)
            } returns null

            val result = subject.getResult(requestMock)

            val error = GENERIC_ERROR.copy(cause = expectedCause)
            assertEquals(Result.failure<Any>(error), result)
        }
    }

    @Test
    fun `Given a request When isValid is false Then should return GENERIC_ERROR`() {
        runTest {
            every { responseMock.isSuccessful } returns false
            every { errorMock.isValid() } returns false
            val result = subject.getResult(requestMock)

            assertEquals(Result.failure<Any>(GENERIC_ERROR), result)
        }
    }

    @Test
    fun `Given a request When throws a RuntimeException Then should return GENERIC_ERROR`() {
        runTest {
            val exception = java.lang.RuntimeException("")
            every { responseMock.body() } throws exception
            val result = subject.getResult(requestMock)

            assertEquals(Result.failure<Any>(GENERIC_ERROR), result)
        }
    }

    @Test
    fun `Given a request When throws an IOException Then should return GENERIC_ERROR`() {
        runTest {
            val exception = IOException()
            every { responseMock.body() } throws exception
            val result = subject.getResult(requestMock)

            assertEquals(Result.failure<Any>(GENERIC_ERROR), result)
        }
    }

    @Test
    fun `Given a request When throws an UnknownHostException Then should return CONNECTION_ERROR`() {
        runTest {
            val exception = UnknownHostException()
            every { responseMock.body() } throws exception
            val result = subject.getResult(requestMock)

            assertEquals(Result.failure<Any>(CONNECTION_ERROR), result)
        }
    }

}