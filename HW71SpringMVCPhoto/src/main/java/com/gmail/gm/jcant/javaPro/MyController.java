package com.gmail.gm.jcant.javaPro;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MyController {

	private Map<Long, byte[]> photos = new HashMap<Long, byte[]>();

	@RequestMapping("/")
	public String onIndex(Model model) {
		model.addAttribute("photos_id", photos.keySet());
		return "index";
	}

	@RequestMapping(value = "/add_photo", method = RequestMethod.POST)
	public RedirectView onAddPhoto(Model model, @RequestParam MultipartFile photo) {
		if (photo.isEmpty()) {
			throw new PhotoErrorException();
		}
		try {
			long id = System.currentTimeMillis();
			photos.put(id, photo.getBytes());
			model.addAttribute("photos_id", photos.keySet());
			RedirectView rv = new RedirectView("/", true);
			rv.setExposeModelAttributes(false);
			return rv;
		} catch (IOException e) {
			throw new PhotoErrorException();
		}
	}

	@RequestMapping("/photo/{photo_id}")
	public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
		return photoById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public RedirectView onDelete(Model model,	@RequestParam(value = "photo_to_delete[]", required = false) long[] paramValues) {
		if (paramValues != null) {
			for (long id : paramValues) {
				if (photos.remove(id) == null) {
					throw new PhotoNotFoundException();
				}
			}
		}
		model.addAttribute("photos_id", photos.keySet());
		RedirectView rv = new RedirectView("/", true);
		rv.setExposeModelAttributes(false);
		return rv;
	}

	private ResponseEntity<byte[]> photoById(long id) {
		byte[] bytes = photos.get(id);
		if (bytes == null) {
			throw new PhotoNotFoundException();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}
}
