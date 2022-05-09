package com.xiangronglin.novel.lambda.get

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

class Handler(
    private val logger: Logger,
    private val config: Config,
    private val dynamoDB: DynamoDbEnhancedClient = DynamoDbEnhancedClient.create(),
) {
    fun handleRequest(request: Request): Response {
        logger.log("Handling request. id ${request.id}")

        val table: DynamoDbTable<Response> = dynamoDB.table(
            config.dynamoDBConfig.tableName, TableSchema.fromBean(
                Response::class.java
            )
        )
        logger.log("Finished handling request. id ${request.id}")
        return table.getItem(Key.builder().partitionValue(request.id.toString()).build())
    }
}