package com.saiful.testingplayground.unittest.util

import android.content.Context

class ResourceValidation {

    fun isResourceEqual(context: Context, resId: Int, string: String): Boolean {
        return context.getString(resId) == string
    }
}