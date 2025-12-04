package com.Elm.PersonalMedicineTracker.aws;

import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import org.junit.jupiter.api.Test;
import me.paulschwarz.springdotenv.*;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.S3Client;


public class AwsConfigTest {

    @Value("${SENDER_EMAIL}")
    private String from;

    @Value("${ses.to}")
    private String[] toAddresses;

    @Test
    void printAwsRegion() {
        AwsCredentialsProvider provider = DefaultCredentialsProvider.create();
        AwsCredentials creds = provider.resolveCredentials();
        System.out.println("AWS Access Key ID: " + creds.accessKeyId());
        System.out.println("AWS Secret Access Key: " + creds.secretAccessKey());
    }

    @Test
    void testIdentity() {
        StsClient sts = StsClient.builder().build();
        GetCallerIdentityResponse identity = sts.getCallerIdentity();

        System.out.println("Connected as AWS ARN:");
        System.out.println(identity.arn());
    }

    @Test
    void testS3Connection() {
        S3Client s3 = S3Client.builder().build();

        ListBucketsResponse response = s3.listBuckets();
        System.out.println("Connected to AWS S3.");
        System.out.println("Bucket count: " + response.buckets().size());
    }

    @Test
    void testSESEmailsEnvVars() {
        System.out.println("Sender Email: " + (from));
        System.out.println("RECIPIENT_EMAIL: " + System.getenv("RECIPIENT_EMAIL"));
    }

}
