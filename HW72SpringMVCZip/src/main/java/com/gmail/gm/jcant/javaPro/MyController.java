package com.gmail.gm.jcant.javaPro;

import compressor.ArchiveFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import compressor.Compressor;
import compressor.SevenZipCompressor;
import compressor.ZipCompressor;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/")
public class MyController {

    @Autowired
    @Qualifier("compressorZip")
    //@Qualifier("compressorSevenZip")
    private Compressor compressor;

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/get_zip", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onGetZip(@RequestParam MultipartFile[] userFile) {

    	if(userFile == null) {
    		throw new ArchiveCreationErrorException();
    	}

    	String extension = "file";
    	if (compressor instanceof ZipCompressor){
    	    extension = "zip";
        }
        if (compressor instanceof SevenZipCompressor){
            extension = "7z";
        }

        ArchiveFile archive = compressor.compress(userFile);
        String zipName = archive.getName();
        try {
			zipName = new String(zipName.getBytes(),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/zip"));
        headers.set("Content-Disposition", "attachment; filename=\"" + zipName + "\"");
        return new ResponseEntity<byte[]>(archive.getData(), headers, HttpStatus.OK);
    }
}
