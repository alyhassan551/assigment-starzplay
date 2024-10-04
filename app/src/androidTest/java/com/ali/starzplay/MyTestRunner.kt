package com.ali.starzplay

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner

class MyTestRunner : AndroidJUnitRunner() {
    override fun onCreate(arguments: Bundle?) {
        System.setProperty("debug.atrace.app_number_cpus", "0")
        super.onCreate(arguments)
    }
}
