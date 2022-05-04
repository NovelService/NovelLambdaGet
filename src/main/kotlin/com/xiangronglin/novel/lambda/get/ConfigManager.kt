package com.xiangronglin.novel.lambda.get

class ConfigManager {

    companion object {
        private const val DYNAMO_DB_TABLE_NAME_KEY = "DYNAMO_DB_TABLE_NAME"
    }

    val config: Config

    init {
        config = Config(
            dynamoDBConfig = DynamoDBConfig(System.getenv(DYNAMO_DB_TABLE_NAME_KEY))
        )
    }

}

