package com.dicomgrid.commons.utils.fragmentedJpeg;

import javax.imageio.*;
import javax.imageio.event.IIOReadProgressListener;
import javax.imageio.event.IIOReadUpdateListener;
import javax.imageio.event.IIOReadWarningListener;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class CLibFragmentedJPEGImageReader extends ImageReader {
    private static final String WRAPPER_READER_CLASS_NAME = "com.sun.media.imageioimpl.plugins.jpeg.CLibJPEGImageReader";
    private ImageReader reader;

    /**
     * Wrapper for the CLibJPEGImageReader that gets the image reader from the ImageIO providers and sets the destination of the jpeg to be a buffered Image.
     * See http://java.net/jira/browse/JAI_IMAGEIO_CORE-191
     * @param originatingProvider
     */
    CLibFragmentedJPEGImageReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
        for (Iterator it = ImageIO.getImageReadersByFormatName("jpeg"); it.hasNext();) {
            ImageReader r = (ImageReader) it.next();
            if (WRAPPER_READER_CLASS_NAME.equals(r.getClass().getName())) {
                reader = r;
            }
        }
        if (reader == null) {
            throw new IllegalStateException("No Image Reader of class com.sun.media.imageioimpl.plugins.jpeg.CLibJPEGImageReader available for format: jpeg");
        }
    }

    /**
     * Creates a destination using the same method used within the CLibJPEGImageReader, but by doing it beforehand, we work around the bug.
     * @param i frame
     * @param imageReadParam reader parameters
     * @return the buffered Image
     * @throws IOException
     */
    @Override
    public BufferedImage read(int i, ImageReadParam imageReadParam) throws IOException {
       if(imageReadParam.getDestination() == null) {
           imageReadParam.setDestination(getDestination(imageReadParam, reader.getImageTypes(i), reader.getWidth(i), reader.getHeight(i)));
       }
       return reader.read(i, imageReadParam);
    }

    @Override
    public int getNumImages(boolean b) throws IOException {
        return reader.getNumImages(b);
    }

    @Override
    public int getWidth(int i) throws IOException {
        return reader.getWidth(i);
    }

    @Override
    public int getHeight(int i) throws IOException {
        return reader.getHeight(i);
    }

    @Override
    public Iterator<ImageTypeSpecifier> getImageTypes(int i) throws IOException {
        return reader.getImageTypes(i);
    }

    @Override
    public IIOMetadata getStreamMetadata() throws IOException {
        return reader.getStreamMetadata();
    }

    @Override
    public IIOMetadata getImageMetadata(int i) throws IOException {
        return reader.getImageMetadata(i);
    }

    @Override
    public String getFormatName() throws IOException {
        return reader.getFormatName();
    }

    @Override
    public ImageReaderSpi getOriginatingProvider() {
        return reader.getOriginatingProvider();
    }

    @Override
    public void setInput(Object o, boolean b, boolean b1) {
        reader.setInput(o, b, b1);
    }

    @Override
    public void setInput(Object o, boolean b) {
        reader.setInput(o, b);
    }

    @Override
    public void setInput(Object o) {
        reader.setInput(o);
    }

    @Override
    public Object getInput() {
        return reader.getInput();
    }

    @Override
    public boolean isSeekForwardOnly() {
        return reader.isSeekForwardOnly();
    }

    @Override
    public boolean isIgnoringMetadata() {
        return reader.isIgnoringMetadata();
    }

    @Override
    public int getMinIndex() {
        return reader.getMinIndex();
    }

    @Override
    public Locale[] getAvailableLocales() {
        return reader.getAvailableLocales();
    }

    @Override
    public void setLocale(Locale locale) {
        reader.setLocale(locale);
    }

    @Override
    public Locale getLocale() {
        return reader.getLocale();
    }

    @Override
    public boolean isRandomAccessEasy(int i) throws IOException {
        return reader.isRandomAccessEasy(i);
    }

    @Override
    public float getAspectRatio(int i) throws IOException {
        return reader.getAspectRatio(i);
    }

    @Override
    public ImageTypeSpecifier getRawImageType(int i) throws IOException {
        return reader.getRawImageType(i);
    }

    @Override
    public ImageReadParam getDefaultReadParam() {
        return reader.getDefaultReadParam();
    }

    @Override
    public IIOMetadata getStreamMetadata(String s, Set<String> strings) throws IOException {
        return reader.getStreamMetadata(s, strings);
    }

    @Override
    public IIOMetadata getImageMetadata(int i, String s, Set<String> strings) throws IOException {
        return reader.getImageMetadata(i, s, strings);
    }

    @Override
    public BufferedImage read(int i) throws IOException {
        return reader.read(i);
    }

    @Override
    public IIOImage readAll(int i, ImageReadParam imageReadParam) throws IOException {
        return reader.readAll(i, imageReadParam);
    }

    @Override
    public Iterator<IIOImage> readAll(Iterator<? extends ImageReadParam> iterator) throws IOException {
        return reader.readAll(iterator);
    }

    @Override
    public boolean canReadRaster() {
        return reader.canReadRaster();
    }

    @Override
    public Raster readRaster(int i, ImageReadParam imageReadParam) throws IOException {
        return reader.readRaster(i, imageReadParam);
    }

    @Override
    public boolean isImageTiled(int i) throws IOException {
        return reader.isImageTiled(i);
    }

    @Override
    public int getTileWidth(int i) throws IOException {
        return reader.getTileWidth(i);
    }

    @Override
    public int getTileHeight(int i) throws IOException {
        return reader.getTileHeight(i);
    }

    @Override
    public int getTileGridXOffset(int i) throws IOException {
        return reader.getTileGridXOffset(i);
    }

    @Override
    public int getTileGridYOffset(int i) throws IOException {
        return reader.getTileGridYOffset(i);
    }

    @Override
    public BufferedImage readTile(int i, int i1, int i2) throws IOException {
        return reader.readTile(i, i1, i2);
    }

    @Override
    public Raster readTileRaster(int i, int i1, int i2) throws IOException {
        return reader.readTileRaster(i, i1, i2);
    }

    @Override
    public RenderedImage readAsRenderedImage(int i, ImageReadParam imageReadParam) throws IOException {
        return reader.readAsRenderedImage(i, imageReadParam);
    }

    @Override
    public boolean readerSupportsThumbnails() {
        return reader.readerSupportsThumbnails();
    }

    @Override
    public boolean hasThumbnails(int i) throws IOException {
        return reader.hasThumbnails(i);
    }

    @Override
    public int getNumThumbnails(int i) throws IOException {
        return reader.getNumThumbnails(i);
    }

    @Override
    public int getThumbnailWidth(int i, int i1) throws IOException {
        return reader.getThumbnailWidth(i, i1);
    }

    @Override
    public int getThumbnailHeight(int i, int i1) throws IOException {
        return reader.getThumbnailHeight(i, i1);
    }

    @Override
    public BufferedImage readThumbnail(int i, int i1) throws IOException {
        return reader.readThumbnail(i, i1);
    }

    @Override
    public void abort() {
        reader.abort();
    }

    @Override
    public void addIIOReadWarningListener(IIOReadWarningListener iioReadWarningListener) {
        reader.addIIOReadWarningListener(iioReadWarningListener);
    }

    @Override
    public void removeIIOReadWarningListener(IIOReadWarningListener iioReadWarningListener) {
        reader.removeIIOReadWarningListener(iioReadWarningListener);
    }

    @Override
    public void removeAllIIOReadWarningListeners() {
        reader.removeAllIIOReadWarningListeners();
    }

    @Override
    public void addIIOReadProgressListener(IIOReadProgressListener iioReadProgressListener) {
        reader.addIIOReadProgressListener(iioReadProgressListener);
    }

    @Override
    public void removeIIOReadProgressListener(IIOReadProgressListener iioReadProgressListener) {
        reader.removeIIOReadProgressListener(iioReadProgressListener);
    }

    @Override
    public void removeAllIIOReadProgressListeners() {
        reader.removeAllIIOReadProgressListeners();
    }

    @Override
    public void addIIOReadUpdateListener(IIOReadUpdateListener iioReadUpdateListener) {
        reader.addIIOReadUpdateListener(iioReadUpdateListener);
    }

    @Override
    public void removeIIOReadUpdateListener(IIOReadUpdateListener iioReadUpdateListener) {
        reader.removeIIOReadUpdateListener(iioReadUpdateListener);
    }

    @Override
    public void removeAllIIOReadUpdateListeners() {
        reader.removeAllIIOReadUpdateListeners();
    }
    
    @Override
    public void reset() {
        reader.reset();
    }

    @Override
    public void dispose() {
        reader.dispose();
    }

    @Override
    protected boolean abortRequested() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void clearAbortRequest() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processSequenceStarted(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processSequenceComplete() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processImageStarted(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processImageProgress(float v) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processImageComplete() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processThumbnailStarted(int i, int i1) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processThumbnailProgress(float v) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processThumbnailComplete() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processReadAborted() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processPassStarted(BufferedImage bufferedImage, int i, int i1, int i2, int i3, int i4, int i5, int i6, int[] ints) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processImageUpdate(BufferedImage bufferedImage, int i, int i1, int i2, int i3, int i4, int i5, int[] ints) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processPassComplete(BufferedImage bufferedImage) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processThumbnailPassStarted(BufferedImage bufferedImage, int i, int i1, int i2, int i3, int i4, int i5, int i6, int[] ints) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processThumbnailUpdate(BufferedImage bufferedImage, int i, int i1, int i2, int i3, int i4, int i5, int[] ints) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processThumbnailPassComplete(BufferedImage bufferedImage) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processWarningOccurred(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void processWarningOccurred(String s, String s1) {
        throw new UnsupportedOperationException();
    }
}
