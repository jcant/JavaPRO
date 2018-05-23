package compressor;


import org.springframework.web.multipart.MultipartFile;

import net.sf.sevenzipjbinding.IOutCreateArchiveZip;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.impl.RandomAccessFileOutStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class SevenZipCompressor implements Compressor {
    @Override
    public byte[] compress(MultipartFile[] files){

    	System.out.println("Use 7Zip method");
    	
    	byte[] bytesToCompress = null;
    	
    	try {
			bytesToCompress = files[0].getBytes();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
    	
    	
    	RandomAccessFile raf = null;
        IOutCreateArchiveZip outArchive = null;
        try {
            raf = new RandomAccessFile(args[0], "rw");

            outArchive = SevenZip.openOutArchiveZip();
            outArchive.setLevel(5);
            outArchive.createArchive(new RandomAccessFileOutStream(raf), 1, 
                    new MyCreateCallback(bytesToCompress));

            System.out.println("Compression operation succeeded");
        
        
        }
    	
    	
    	return null;

    }
    
   
}

public class CompressMessage {
    /**
     * The callback provides information about archive items
     */
    static class MyCreateCallback implements IOutCreateCallback<IOutItemZip> {
        private final byte[] bytesToCompress;

        private MyCreateCallback(byte[] bytesToCompress) {
            this.bytesToCompress = bytesToCompress;
        }

        public void setOperationResult(boolean operationResultOk) {
            // called for each archive item
        }

        public void setTotal(long total) {
            // Track operation progress here
        }

        public void setCompleted(long complete) {
            // Track operation progress here
        }

        public IOutItemZip getItemInformation(int index,
                OutItemFactory<IOutItemZip> outItemFactory) {
            IOutItemZip outItem = outItemFactory.createOutItem();

            outItem.setDataSize((long) bytesToCompress.length);

            // Set name of the file in the archive
            outItem.setPropertyPath("message.txt");
            outItem.setPropertyCreationTime(new Date());

            // To get u+rw permissions on linux, if extracting with "unzip"
            // outItem.setPropertyAttributes(Integer.valueOf(0x81808000));

            return outItem;
        }

        public ISequentialInStream getStream(int index) {
            return new ByteArrayStream(bytesToCompress, true);
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java CompressMessage <archive> <msg>");
            return;
        }

        final byte[] bytesToCompress = args[1].getBytes();

        RandomAccessFile raf = null;
        IOutCreateArchiveZip outArchive = null;
        try {
            raf = new RandomAccessFile(args[0], "rw");

            outArchive = SevenZip.openOutArchiveZip();
            outArchive.setLevel(5);
            outArchive.createArchive(new RandomAccessFileOutStream(raf), 1, 
                    new MyCreateCallback(bytesToCompress));

            System.out.println("Compression operation succeeded");
        
        
        } catch (SevenZipException e) {
            System.err.println("7-Zip-JBinding-Error:");
            // Extended stack trace prints more information
            e.printStackTraceExtended();
        } catch (Exception e) {
            System.err.println("Error occurs: " + e);
        } finally {
            if (outArchive != null) {
                try {
                    outArchive.close();
                } catch (IOException e) {
                    System.err.println("Error closing archive: " + e);
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e);
                }
            }
        }
    }
}
