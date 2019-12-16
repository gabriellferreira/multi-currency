[![Build Status](https://travis-ci.com/gabriellferreira/multi-currency.svg?branch=master)](https://travis-ci.com/gabriellferreira/multi-currency)

# MulticurrencyProject

This app list all currencies from the endpoint (one per row). Each row has an input where you
can enter any amount of money. When you tap on currency row it slide to top and its
input becomes first responder. When the amount is changed the app simultaneously
update the corresponding value for other currencies.

The app download and update rates every 1 second using [API](https://revolut.duckdns.org/latest?base=EUR).

* minSdkVersion: 23
* targetSdkVersion: 29

## Dependencies:
* Kotlin
* AndroidX
* Retrofit
* Dagger2
* RxJava
* Glide
* Gson
* Rounded ImageView
* DetektCheck
* TravisCI

## References
* https://github.com/bufferapp/android-clean-architecture-boilerplate
