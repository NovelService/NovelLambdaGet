package com.xiangronglin.novel.lambda.get

data class Config(
    val dynamoDBConfig: DynamoDBConfig
)

data class DynamoDBConfig(
    val tableName: String
)
