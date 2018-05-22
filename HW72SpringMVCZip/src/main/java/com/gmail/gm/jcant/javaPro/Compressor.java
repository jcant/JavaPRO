package com.gmail.gm.jcant.javaPro;

import org.springframework.web.multipart.MultipartFile;

public interface Compressor {
    public byte[] compress(MultipartFile file);
}
