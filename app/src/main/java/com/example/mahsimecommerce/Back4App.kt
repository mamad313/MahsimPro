package com.example.mahsimecommerce

import com.parse.Parse;
import android.app.Application;


class Back4App : Application() {
    override fun onCreate() {
        super.onCreate()

        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("RpUXWkJvNqLNYismNBwZYPh1nXxVsx9vvudU0ZdK")
                .clientKey("cWTwf21xE4hrlyPv8MwqIEukvwFKVrEIVdKqAxh8")
                .server("https://parseapi.back4app.com")
                .build()
        )
    }
}