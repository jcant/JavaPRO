package compressor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.gmail.gm.jcant.javaPro.ArchiveCreationErrorException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompressor implements Compressor {

	@Autowired
	LetterCodeConverter converter;

	@Override
	public ArchiveFile compress(MultipartFile[] userFile) {

		ArchiveFile archive = new ArchiveFile();
		String orName = userFile[0].getOriginalFilename();
		archive.setName(converter.convertCodesToLetters(orName.substring(0, orName.lastIndexOf('.')) + ".zip"));

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ZipOutputStream zos = new ZipOutputStream(bos)) {

			for (MultipartFile file : userFile) {

				String originName = converter.convertCodesToLetters(file.getOriginalFilename());
				ZipEntry ze = new ZipEntry(originName);
				zos.putNextEntry(ze);
				zos.write(file.getBytes());
				zos.closeEntry();
			}

			zos.close();
			archive.setData(bos.toByteArray());


		} catch (IOException ex) {
			ex.printStackTrace();
			throw new ArchiveCreationErrorException();
		}

		if (archive.getData() == null) {
			throw new ArchiveCreationErrorException();
		}

		return archive;
	}

}
