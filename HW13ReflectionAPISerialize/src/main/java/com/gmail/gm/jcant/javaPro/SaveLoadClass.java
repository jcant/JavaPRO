package com.gmail.gm.jcant.javaPro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class SaveLoadClass {
	private TargetClass target;
	private String filename;

	public SaveLoadClass() {
		super();
		this.target = null;
		this.filename = null;
	}

	public SaveLoadClass(TargetClass target, String filename) {
		super();
		this.target = target;
		this.filename = filename;
	}

	public void saveClass() {
		if (target == null) {
			throw new IllegalArgumentException("Target is null");
		}
		if (filename == null) {
			throw new IllegalArgumentException("Filename not set");
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(TargetClass.class, new JSONTargetWorker());
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();

		try (PrintWriter pw = new PrintWriter(filename)) {
			gson.toJson(target, pw);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void loadClass() {
		if (filename == null) {
			throw new IllegalArgumentException("Filename not set");
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(TargetClass.class, new JSONTargetWorker());

		Gson gson = gsonBuilder.create();

		try (FileReader fr = new FileReader(new File(filename))) {
			target = gson.fromJson(fr, TargetClass.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TargetClass getTarget() {
		return target;
	}

	public void setTarget(TargetClass target) {
		this.target = target;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "SaveLoadClass{" + "target=" + target + ", filename='" + filename + '\'' + '}';
	}
}
