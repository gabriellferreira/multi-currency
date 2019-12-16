package br.com.gabriellferreira.multicurrency.presentation.util.analytics.tracking.tracker

import br.com.gabriellferreira.multicurrency.presentation.util.analytics.tracking.event.base.Event

interface EventTracker {

    fun track(event: Event)
}
