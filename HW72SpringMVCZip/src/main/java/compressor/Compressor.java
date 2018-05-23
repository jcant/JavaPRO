package compressor;

import org.springframework.web.multipart.MultipartFile;

public interface Compressor {
    public ArchiveFile compress(MultipartFile[] file);
}
