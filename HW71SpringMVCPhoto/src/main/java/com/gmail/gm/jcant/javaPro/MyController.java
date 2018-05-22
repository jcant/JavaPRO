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

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

@Controller
@RequestMapping("/")
public class MyController {

	private Map<Long, byte[]> photos = new HashMap<Long, byte[]>();
	private Map<Long, byte[]> thumbs = new HashMap<Long, byte[]>();

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

			thumbs.put(id, getTumbnail(photo.getInputStream(), 100));

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
		return photoById(id, photos);
	}

	@RequestMapping("/thumb/{thumb_id}")
	public ResponseEntity<byte[]> onThumb(@PathVariable("thumb_id") long id) {
		return photoById(id, thumbs);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public RedirectView onDelete(Model model,
			@RequestParam(value = "photo_to_delete[]", required = false) long[] paramValues) {
		if (paramValues != null) {
			for (long id : paramValues) {
				if (photos.remove(id) == null) {
					throw new PhotoNotFoundException();
				}
				thumbs.remove(id);
			}
		}
		model.addAttribute("photos_id", photos.keySet());
		RedirectView rv = new RedirectView("/", true);
		rv.setExposeModelAttributes(false);
		return rv;
	}

	private ResponseEntity<byte[]> photoById(long id, Map<Long, byte[]> photos) {
		byte[] bytes = photos.get(id);
		if (bytes == null) {
			throw new PhotoNotFoundException();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}

	private byte[] getTumbnail(InputStream image, int height) throws IOException {
		BufferedImage img = ImageIO.read(image);
		double aspect = (double)img.getWidth()/(double)img.getHeight();
		Image scaled = img.getScaledInstance((int)(height*aspect), height, Image.SCALE_DEFAULT);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(toBufferedImage(scaled), "jpg", bos);
		byte[] data = bos.toByteArray();

		return data;
	}

	private BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), Image.SCALE_REPLICATE);
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		return bimage;
	}
}
