package com.xiangronglin.novel.lambda.get

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.PrimaryKey

class Handler(
    private val logger: Logger,
    private val config: Config,
    private val dynamoDB: DynamoDB = DynamoDB(AmazonDynamoDBClientBuilder.defaultClient()),
) {
    fun handleRequest(request: Request): Response {
        logger.log("Handling request. id ${request.id}")

        val table = dynamoDB.getTable(config.dynamoDBConfig.tableName)
        logger.log("Getting item from db. id ${request.id}")
        val item = table.getItem(PrimaryKey("id", request.id.toString()))
        logger.log("Successfully got item from db. id ${request.id}")

        logger.log("Finished handling request. id ${request.id}")
        return Response(request.id, item.getString("status"), item.getString("url"))
    }
}