package com.gmail.gm.jcant.javaPro;

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
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class MyController {


    //private Compressor compressor = new ZipCompressor();
    private Compressor compressor = new SevenZipCompressor();

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/get_zip", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onGetZip(@RequestParam MultipartFile[] userFile) {

    	if(userFile == null) {
    		throw new ArchiveCreationErrorException();
    	}

        byte[] bytes = compressor.compress(userFile);

        String zipName = convertCodesToLetters(userFile[0].getOriginalFilename().substring(0, userFile[0].getOriginalFilename().lastIndexOf('.')) + ".zip");
        try {
			zipName = new String(zipName.getBytes(),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/zip"));
        headers.set("Content-Disposition", "attachment; filename=\"" + zipName + "\"");
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
    
    private String convertCodesToLetters(String nameCoded) {
      Pattern p = Pattern.compile("&#\\d*;");
      Matcher m = p.matcher(nameCoded);
      while (m.find()) {
          String gr = m.group();
          char ch = (char) Integer.parseInt(gr.substring(2, gr.length() - 1));
          nameCoded = nameCoded.replaceFirst(gr, "" + ch);
      }

      return nameCoded;
  }
}
