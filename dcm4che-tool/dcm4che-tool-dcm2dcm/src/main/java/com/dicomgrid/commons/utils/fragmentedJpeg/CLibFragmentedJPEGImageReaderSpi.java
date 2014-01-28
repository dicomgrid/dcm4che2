package com.dicomgrid.commons.utils.fragmentedJpeg;

import com.sun.media.imageioimpl.common.PackageUtil;
import com.sun.media.imageioimpl.plugins.jpeg.CLibJPEGImageReaderSpi;

import javax.imageio.*;
import javax.imageio.spi.ImageReaderSpi;

import java.io.IOException;
import java.util.Locale;

public class CLibFragmentedJPEGImageReaderSpi extends ImageReaderSpi {
    //The new identifier for this image reader type
    private static final String[] names = {"frag-jpeg"};

    private static final String[] suffixes = {"jpeg", "jpg", "jfif", "jls"};

    private static final String[] MIMETypes = {"image/jpeg"};

    private static final String readerClassName = "com.dicomgrid.commons.utils.fragmentedJpeg.CLibFragmentedJPEGImageReader";

    private static final String[] writerSpiNames = {"com.sun.media.imageioimpl.plugins.jpeg.CLibJPEGImageWriterSpi"};

    private static final String NATIVE_FORMAT = "javax_imageio_jpeg_image_1.0";

    private static final String NATIVE_FORMAT_CLASS = "com.sun.imageio.plugins.jpeg.JPEGImageMetadataFormat";

    private static final String TIFF_FORMAT = "com_sun_media_imageio_plugins_tiff_image_1.0";

    private static final String TIFF_FORMAT_CLASS ="com.sun.media.imageioimpl.plugins.tiff.TIFFImageMetadataFormat";

    public CLibFragmentedJPEGImageReaderSpi() {
        //These are all the same as CLibJPEGImageReaderSpi
        super(PackageUtil.getVendor(),
              PackageUtil.getVersion(),
              names,
              suffixes,
              MIMETypes,
              readerClassName,
              STANDARD_INPUT_TYPE,
              writerSpiNames,
              false, // supportsStandardStreamMetadataFormat
              null,  // nativeStreamMetadataFormatName
              null,  // nativeStreamMetadataFormatClassName
              null,  // extraStreamMetadataFormatNames
              null,  // extraStreamMetadataFormatClassNames
              true,  // supportsStandardImageMetadataFormat
              NATIVE_FORMAT,
              NATIVE_FORMAT_CLASS,
              new String[] {TIFF_FORMAT},
              new String[] {TIFF_FORMAT_CLASS});
    }

    @Override
    public String getDescription(Locale locale) {
        return PackageUtil.getSpecificationTitle() + " wrapper for natively-accelerated JPEG Image Reader that reads 12-Bit Lossy JPEGs correctly";
    }

    @Override
    public boolean canDecodeInput(Object source) throws IOException {
        return new CLibJPEGImageReaderSpi().canDecodeInput(source);
    }

    public ImageReader createReaderInstance(Object extension) throws IIOException {
        return new CLibFragmentedJPEGImageReader(this);
    }
}
