package com.mattg.smartcivics.utils

import android.content.Context
import com.mattg.smartcivics.models.Representative
import com.mattg.smartcivics.models.civicapi.Official
import org.junit.Assert.assertEquals


import org.junit.Test
import org.mockito.Mockito.mock

class UtilityFunctionsTest {

    val context = mock(Context::class.java)


    val testRep = Representative(
        "Test D Rep",
        "Test",
        "D",
        "Rep",
        "U.S. Senator",
        "Senate",
        "Phoenix",
        "14 west street",
        "Arizona",
        "85029",
        "http://www.photofakesite.com/",
        "Independent",
        "www.fakesite.com",
        "1234458896",
        "F100021",
        null,
        null,
        null,
        null,
        "1532456",
        "11111111",
        "2024",
        null,
        null
    )


    var testOfficial = Official(
        null,
        null,
        null,
        null,
        null,
        null,
        "http://somesite",
        null
    )

    @Test
    fun _rep_photo_fixed_from_http(){
        //testing that an http: photo url is changed to https:
        val result = fixRepPhoto(testOfficial)
        assertEquals(result, "https://somesite")
        //testing that an https: photo url does not change
        testOfficial.photoUrl = "https://somesite"
        var result2 = fixRepPhoto(testOfficial)
        assertEquals(result2, "https://somesite")
    }

    @Test
    fun _rep_address_returns_string(){
        //should return a string representing combination of address city, state zip
        var result = addressText(testRep)
        assertEquals(result, "14 west street Phoenix, Arizona 85029")
        //making sure null returns to account for blank fields
        testRep.address = null
        result = addressText(testRep)
        assertEquals(result, null)
    }
}