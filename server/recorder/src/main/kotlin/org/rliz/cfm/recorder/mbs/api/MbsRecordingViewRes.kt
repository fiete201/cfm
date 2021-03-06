package org.rliz.cfm.recorder.mbs.api

import java.util.*

data class MbsRecordingViewRes(
        val id: UUID? = null,
        val name: String? = null,
        val length: Long? = null,
        val artists: List<String> = emptyList()
)

data class MbsRecordingViewListRes(
        val elements: List<MbsReleaseGroupViewRes> = emptyList()
)
