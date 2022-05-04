package com.xiangronglin.novel.lambda.get

import java.util.UUID

data class Response(
    val id: UUID,
    val status: String?,
    val url: String?
)