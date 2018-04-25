package com.gmail.gm.jcant.javaPro;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@SaveTo(file = "./textFile.txt")
public class TextContainer {
	private String text = null;

	public TextContainer() {
		super();
	}

	public TextContainer(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Saver
	public void saveText(String file) {
		try (PrintWriter pw = new PrintWriter(file)) {
			pw.print(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
