package com.xiangronglin.novel.lambda.get

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.util.UUID

@DynamoDbBean
data class Response(
    @get:DynamoDbPartitionKey
    var id: UUID = UUID.randomUUID(),
    var status: String? = null,
    var url: String? = null
)