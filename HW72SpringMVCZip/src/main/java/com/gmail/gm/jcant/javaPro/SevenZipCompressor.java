package com.gmail.gm.jcant.javaPro;

import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class SevenZipCompressor implements Compressor {
    @Override
    public byte[] compress(MultipartFile file){

        byte[] result = new byte[(int)file.getSize()];

        SeekableInMemoryByteChannel inMemoryByteChannel = null;
        SevenZOutputFile out = null;
        try {
            inMemoryByteChannel = new SeekableInMemoryByteChannel(result);
            out = new SevenZOutputFile(inMemoryByteChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }




        SevenZArchiveEntry entry = sevenZFile.createArchiveEntry();


    }
}
