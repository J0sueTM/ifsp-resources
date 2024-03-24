package com.j0suetm.jgallery.components;

import java.net.URI;
import java.net.URISyntaxException;
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

  public static void connect(
    String endpoint,
    String accessKey,
    String secretKey,
    String region
  ) {
    URI endpointURI;
    try {
      endpointURI = new URI(endpoint);
    } catch (URISyntaxException ex) {
      logger.log(
        Level.SEVERE,
        "failed to build s3 endpoint uri from {0}",
        endpoint
      );

      return;
    }

    client = S3Client
      .builder()
      .endpointOverride(endpointURI)
      .credentialsProvider(
        StaticCredentialsProvider.create(
          AwsBasicCredentials.create(accessKey, secretKey)
        )
      )
      .region(Region.of(region))
      .build();
  }

  public static void setup(String bucketName) {
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
    HeadBucketRequest hbr = HeadBucketRequest
      .builder()
      .bucket(bucketName)
      .build();

    try {
      client.headBucket(hbr);
    } catch (NoSuchBucketException ex) {
      return false;
    }

    return true;
  }

  public static S3Client getClient() {
    return client;
  }
}