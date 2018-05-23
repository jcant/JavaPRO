package compressor;

import com.gmail.gm.jcant.javaPro.ArchiveCreationErrorException;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class SevenZipCompressor implements Compressor {

    @Autowired
    LetterCodeConverter converter;

    @Override
    public ArchiveFile compress(MultipartFile[] files) {

        ArchiveFile archive = new ArchiveFile();
        String orName = files[0].getOriginalFilename();
        archive.setName(converter.convertCodesToLetters(orName.substring(0, orName.lastIndexOf('.')) + ".7z"));

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             SeekableInMemoryByteChannel inMemoryByteChannel = new SeekableInMemoryByteChannel(bos.toByteArray());
             SevenZOutputFile sevenZOutput = new SevenZOutputFile(inMemoryByteChannel)) {

            for (MultipartFile file : files) {
                String fileName = converter.convertCodesToLetters(file.getOriginalFilename());
                SevenZArchiveEntry entry = sevenZOutput.createArchiveEntry(new File(fileName), fileName);
                entry.setSize(file.getSize());
                sevenZOutput.putArchiveEntry(entry);
                sevenZOutput.write(file.getBytes());
                sevenZOutput.closeArchiveEntry();
            }

            sevenZOutput.close();
            archive.setData(inMemoryByteChannel.array());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (archive.getData() == null) {
            throw new ArchiveCreationErrorException();
        }

        return archive;
    }

}
