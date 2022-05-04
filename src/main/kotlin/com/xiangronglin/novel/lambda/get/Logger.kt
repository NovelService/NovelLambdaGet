package com.xiangronglin.novel.lambda.get

import com.amazonaws.services.lambda.runtime.LambdaLogger

class Logger(
    private val awsLogger: LambdaLogger
) {

    fun log(message: String) {
        awsLogger.log(message)
    }
}