package compressor;

import org.springframework.web.multipart.MultipartFile;

import com.gmail.gm.jcant.javaPro.ArchiveCreationErrorException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompressor implements Compressor {

	@Override
	public byte[] compress(MultipartFile[] userFile) {

		System.out.println("Use Zip method");

		byte[] bytes = null;

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ZipOutputStream zos = new ZipOutputStream(bos)) {

			for (MultipartFile file : userFile) {

				String originName = convertCodesToLetters(file.getOriginalFilename());
				ZipEntry ze = new ZipEntry(originName);
				zos.putNextEntry(ze);
				zos.write(file.getBytes());
				zos.closeEntry();
			}

			bytes = bos.toByteArray();

		} catch (IOException ex) {
			ex.printStackTrace();
			throw new ArchiveCreationErrorException();
		}

		if (bytes == null) {
			throw new ArchiveCreationErrorException();
		}

		return bytes;
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
