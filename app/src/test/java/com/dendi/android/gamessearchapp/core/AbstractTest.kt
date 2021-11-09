package com.dendi.android.gamessearchapp.core

import okio.IOException
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

/**
 * Test for [Abstract]
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class AbstractTest {

    @Test
    fun test_success() {
        val dataObject = TestDataObject.Success("a", "b", "c")
        val domainObject = dataObject.map(DataMapper.Base())
        val expected = DomainObject.Success("a b c")
        Assert.assertEquals(expected, domainObject)
    }

    @Test
    fun test_fail() {
        val dataObject = TestDataObject.Fail(IOException())
        val domainObject = dataObject.map(DataMapper.Base())
        Assert.assertTrue(domainObject is DomainObject.Fail)
    }

    sealed class TestDataObject : Abstract.Object<DomainObject, DataMapper> {

        class Success(
            private val textOne: String,
            private val textTwo: String,
            private val textThree: String,
        ) : TestDataObject() {
            override fun map(mapper: DataMapper): DomainObject {
                return mapper.map(textOne, textTwo, textThree)
            }
        }

        class Fail(private val exception: Exception) : TestDataObject() {
            override fun map(mapper: DataMapper): DomainObject {
                return mapper.map(exception)
            }

        }
    }

    interface DataMapper : Abstract.Mapper {
        fun map(
            textOne: String,
            textTwo: String,
            textThree: String,
        ): DomainObject

        fun map(exception: Exception): DomainObject

        class Base : DataMapper {
            override fun map(textOne: String, textTwo: String, textThree: String): DomainObject {
                return DomainObject.Success("$textOne $textTwo $textThree")
            }

            override fun map(exception: Exception): DomainObject {
                return DomainObject.Fail
            }

        }
    }

    sealed class DomainObject : Abstract.Object<UiObject, DomainToUiMapper> {
        data class Success(private val textCombined: String) : DomainObject() {
            override fun map(mapper: DomainToUiMapper): UiObject {
                throw IllegalStateException("not implemented yet")
            }
        }

        object Fail : DomainObject() {
            override fun map(mapper: DomainToUiMapper): UiObject {
                throw IllegalStateException("not implemented yet")
            }
        }
    }

    interface DomainToUiMapper : Abstract.Mapper

    sealed class UiObject : Abstract.Object<Unit, Abstract.Mapper.Empty>
}