package com.example.covidapp

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MockTests {
    val list = mock(MutableList::class.java)

    @Test
    fun testMockList() {
        list.size

        verify(list).size
    }
}