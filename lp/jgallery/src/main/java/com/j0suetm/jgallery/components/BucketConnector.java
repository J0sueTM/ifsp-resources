package com.j0suetm.jgallery.components;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class BucketConnector {
  private static final Logger logger =
    Logger.getLogger(DBConnector.class.getName());

  private static S3Client client;

  private static String bucketName;

  public BucketConnector(String bn) {
    bucketName = bn;
  }

  public static void connect(
    String endpoint,
    String accessKey,
    String secretKey,
    String region
  ) {
    client = S3Client
      .builder()
      .forcePathStyle(true)
      .endpointOverride(URI.create(endpoint))
      .credentialsProvider(
        StaticCredentialsProvider.create(
          AwsBasicCredentials.create(
            accessKey,
            secretKey
          )
        )
      )
      .region(Region.of(region))
      .build();
  }

  public void setup() {
    if (doesBucketExist(bucketName)) {
      return;
    }

    try {
      CreateBucketRequest cbr = CreateBucketRequest
        .builder()
        .bucket(bucketName)
        .build();
      
      client.createBucket(cbr);
    } catch (S3Exception ex) {
      logger.log(
        Level.SEVERE,
        "failed to create bucket -- {0}",
        ex.getMessage()
      );

      client = null;
    }
  }

  public static boolean doesBucketExist(String bucketName) {
    try {
      HeadBucketRequest hbr = HeadBucketRequest
        .builder()
        .bucket(bucketName)
        .build();

      client.headBucket(hbr);
    } catch (NoSuchBucketException ex) {
      return false;
    }

    return true;
  }

  public static S3Client getClient() {
    return client;
  }

  public static String getBucketName() {
    return bucketName;
  }
}