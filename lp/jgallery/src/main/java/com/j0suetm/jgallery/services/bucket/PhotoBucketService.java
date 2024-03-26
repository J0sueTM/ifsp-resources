package com.j0suetm.jgallery.services.bucket;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.j0suetm.jgallery.components.BucketConnector;
import com.j0suetm.jgallery.models.ResultModel;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception; 

public class PhotoBucketService
  implements BucketService
{
  private static final Logger logger =
    Logger.getLogger(PhotoBucketService.class.getName());

  @Override
  public ResultModel put(Object o, String key)
    throws IllegalArgumentException
  {
    if (!(o instanceof BufferedImage)) {
      throw new IllegalArgumentException("expected instance of File");
    }
    BufferedImage img = (BufferedImage)o;

    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(img, "png", baos);
      byte[] imgBuf = baos.toByteArray();
      InputStream is = new ByteArrayInputStream(imgBuf);

      Map<String, String> md = new HashMap<>();
      PutObjectRequest por = PutObjectRequest
        .builder()
        .bucket(BucketConnector.getBucketName())
        .key(key)
        .metadata(md)
        .build();

      BucketConnector.getClient().putObject(
        por,
        RequestBody.fromInputStream(is, imgBuf.length)
      );
    } catch (Exception ex) {
      logger.log(
        Level.SEVERE,
        "failed to upload file -- {0}",
        ex.getMessage()
      );

      return new ResultModel(
        "ERROR",
        "failed to upload file",
        null
      );
    }

    return new ResultModel(
      "INFO",
      "uploaded file succesfully",
      key
    );
  }

  @Override
  public ResultModel get(String key) {
    return new ResultModel(
      "ERROR",
      "Not implemented yet",
      null
    );
  }

  @Override
  public ResultModel delete(String key) {
    return new ResultModel(
      "ERROR",
      "Not implemented yet",
      null
    );
  }
}
