package compressor;

import org.springframework.web.multipart.MultipartFile;

public interface Compressor {
    public byte[] compress(MultipartFile[] file);
}
