package com.j0suetm.jgallery.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.nio.IntBuffer;

import javafx.scene.image.Image;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

public class ImageUtils {
  public static Image imgBufToImg(BufferedImage oldImgBuf) {
    BufferedImage lqPhotoBuf = new BufferedImage(
      oldImgBuf.getWidth(),
      oldImgBuf.getHeight(),
      BufferedImage.TYPE_INT_ARGB_PRE
    );

    lqPhotoBuf
      .createGraphics()
      .drawImage(
        oldImgBuf,
        0, 0,
        oldImgBuf.getWidth(),
        oldImgBuf.getHeight(),
        null
      );

    int[] tiARGB = (
      (DataBufferInt)
        lqPhotoBuf
          .getRaster()
          .getDataBuffer()
      )
      .getData();
    IntBuffer intBuf = IntBuffer.wrap(tiARGB);
    PixelFormat<IntBuffer>pxFmt = PixelFormat.getIntArgbPreInstance();
    PixelBuffer<IntBuffer>pxBuf = new PixelBuffer<IntBuffer>(
      lqPhotoBuf.getWidth(),
      lqPhotoBuf.getHeight(),
      intBuf,
      pxFmt
    );

    return new WritableImage(pxBuf);
  }
}
