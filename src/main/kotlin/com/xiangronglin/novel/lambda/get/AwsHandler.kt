package com.xiangronglin.novel.lambda.get

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import java.io.InputStream
import java.io.OutputStream

class AwsHandler : RequestStreamHandler {
    companion object {
        private val gson = GsonBuilder().create()
        private val config = ConfigManager.config
        private val dynamoTable = DynamoDbEnhancedClient.create()
            .table(config.dynamoDBConfig.tableName, TableSchema.fromBean(Response::class.java))
    }

    override fun handleRequest(input: InputStream, output: OutputStream, context: Context) {
        val reader = input.reader()
        val writer = output.writer()

        try {
            val event = JsonParser.parseReader(reader)
            context.logger.log("function called with event $event")

            val request = gson.fromJson(event.asJsonObject.get("body").asString, Request::class.java)
            context.logger.log("Handling request. id ${request.id}")

            val response = dynamoTable.getItem(Key.builder().partitionValue(request.id.toString()).build())
            context.logger.log("Retrieved from db. id ${request.id}")

            writer.write(gson.toJson(response))
        } catch (e: java.lang.Exception) {
            context.logger.log("ERROR occurred: $e")
        } finally {
            reader.close()
            writer.close()
        }
    }
}
