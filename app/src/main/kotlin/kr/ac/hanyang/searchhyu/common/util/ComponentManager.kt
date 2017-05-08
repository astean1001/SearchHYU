package kr.ac.hanyang.searchhyu.common.util

import android.os.Bundle
import com.google.common.cache.CacheBuilder
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

object ComponentManager {
    private val COMPONENT_ID = "component_id"
    private val currentId = AtomicLong()
    private val components = CacheBuilder
            .newBuilder()
            .maximumSize(10)
            .expireAfterWrite(30, TimeUnit.SECONDS)
            .build<Long, Any>()

    @Suppress("UNCHECKED_CAST")
    fun <C> restoreComponent(savedInstanceState: Bundle): C {
        val componentId = savedInstanceState.getLong(COMPONENT_ID)
        val component = components.getIfPresent(componentId) as C
        return component
    }

    fun saveComponent(component: Any, outState: Bundle) {
        val componentId = currentId.incrementAndGet()
        components.put(componentId, component)
        outState.putLong(COMPONENT_ID, componentId)
    }
}
