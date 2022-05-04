# NovelLambdaGet
An AWS Lambda function which serves as HTTP gateway to fetch the status of jobs from a dynamoDB.

# Configuration
## Runtime settings
```
Runtime=Java 11 (Corretto)
Handler=com.xiangronglin.novel.lambda.get.AwsHandler::handleRequest
Architecture=x86_64
```

## Environment Variables
Set these values as environment variables for the lambda function. 
```
DYNAMO_DB_TABLE_NAME=<your-table-name>
```

## Role
The lambda needs a role with permission:
- "dynamodb:GetItem"

And optionally for logging
- Policy "AWSLambdaBasicExecutionRole"