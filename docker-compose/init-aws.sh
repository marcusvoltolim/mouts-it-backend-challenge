#!/bin/bash

export AWS_ACCESS_KEY_ID=moutis AWS_SECRET_ACCESS_KEY=moutis

awslocal sqs create-queue --queue-name order-input
awslocal sqs create-queue --queue-name order-output
