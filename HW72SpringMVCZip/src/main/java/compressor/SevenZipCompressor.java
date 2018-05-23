package compressor;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class SevenZipCompressor implements Compressor {
	@Override
	public byte[] compress(MultipartFile[] files) {

		System.out.println("Use 7Zip method");

		byte[] bytes = null;

		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();

			try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
					SevenZOutputFile sevenZOutput = new SevenZOutputFile(new File(fileName))) {

				SevenZArchiveEntry entry = sevenZOutput.createArchiveEntry(new File(fileName), fileName);

				entry.setSize(file.getSize());
				sevenZOutput.putArchiveEntry(entry);
				sevenZOutput.write(file.getBytes());
				sevenZOutput.closeArchiveEntry();

				bytes = baos.toByteArray();

			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		return bytes;
	}

}

/*
 * public byte[] compressData(FileCompressor fileCompressor) throws Exception {
 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); SevenZOutputFile
 * sevenZOutput = new SevenZOutputFile(new File(
 * fileCompressor.getCompressedPath())); try { for (BinaryFile binaryFile :
 * fileCompressor.getMapBinaryFile() .values()) { SevenZArchiveEntry entry =
 * sevenZOutput.createArchiveEntry( new File(binaryFile.getSrcPath()),
 * binaryFile.getDesPath()); entry.setSize(binaryFile.getActualSize());
 * sevenZOutput.putArchiveEntry(entry);
 * sevenZOutput.write(binaryFile.getData()); sevenZOutput.closeArchiveEntry(); }
 * sevenZOutput.finish(); } catch (Exception e) {
 * FileCompressor.LOGGER.error("Error on compress data", e); } finally {
 * sevenZOutput.close(); baos.close(); } return baos.toByteArray(); }
 * 
 */