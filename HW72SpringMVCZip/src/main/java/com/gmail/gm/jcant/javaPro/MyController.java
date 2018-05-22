package com.gmail.gm.jcant.javaPro;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/")
public class MyController {


    private Compressor compressor = new ZipCompressor();

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/get_zip", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onGetZip(@RequestParam MultipartFile userFile) {


        byte[] bytes = compressor.compress(userFile);

        String zipName = (userFile.getOriginalFilename().substring(0, userFile.getOriginalFilename().lastIndexOf('.')) + ".zip");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/zip"));
        //headers.setAcceptCharset();
        headers.set("Content-Disposition", "attachment; filename=\"" + zipName + "\"");
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }


}
