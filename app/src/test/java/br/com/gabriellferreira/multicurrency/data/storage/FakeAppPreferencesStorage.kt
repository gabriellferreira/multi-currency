package br.com.gabriellferreira.multicurrency.data.storage

class FakeAppPreferencesStorage : AppPreferences {

    private val map = hashMapOf<String, Any>()

    override fun setFCMToken(token: String) {
        map[AppPreferences.SHARED_FCM_TOKEN] = token
    }

    override fun getFCMToken(): String? {
        return map[AppPreferences.SHARED_FCM_TOKEN] as String?
    }
}