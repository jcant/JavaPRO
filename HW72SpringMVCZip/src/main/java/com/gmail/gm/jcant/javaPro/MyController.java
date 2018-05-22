package com.gmail.gm.jcant.javaPro;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/")
public class MyController {

	@RequestMapping("/")
	public String onIndex(Model model) {
		return "index";
	}

	@RequestMapping(value = "/get_zip", method = RequestMethod.POST)
	public ResponseEntity<byte[]> onGetZip(@RequestParam MultipartFile userFile) throws UnsupportedEncodingException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		
		char[] name = userFile.getOriginalFilename().toCharArray();
		System.out.println(Arrays.toString(name));
		
		String originName = new String(userFile.getOriginalFilename());
		String zipName = originName.substring(0, originName.lastIndexOf('.')) + ".zip";

		System.out.println("origin name: " + originName);
		System.out.println("zipName: " + zipName);

		try {
			ZipOutputStream zos = new ZipOutputStream(bos);
			ZipEntry ze = new ZipEntry(originName);
			zos.putNextEntry(ze);
			zos.write(userFile.getBytes());
			zos.closeEntry();
			zos.close();
			System.out.println("Done");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		byte[] bytes = bos.toByteArray();

		if (bytes == null) {
			throw new ArchiveCreationErrorException();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/zip"));
		headers.set("Content-Disposition", "attachment; filename=\"" + zipName + "\"");
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}

}
