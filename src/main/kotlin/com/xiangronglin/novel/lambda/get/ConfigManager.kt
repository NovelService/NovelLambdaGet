package com.xiangronglin.novel.lambda.get


object ConfigManager {
    private const val DYNAMO_DB_TABLE_NAME_KEY = "DYNAMO_DB_TABLE_NAME"

    val config: Config = Config(
        dynamoDBConfig = DynamoDBConfig(System.getenv(DYNAMO_DB_TABLE_NAME_KEY))
    )

}

