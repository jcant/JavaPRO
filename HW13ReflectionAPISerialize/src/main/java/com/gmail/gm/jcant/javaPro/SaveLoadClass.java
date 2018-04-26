package com.gmail.gm.jcant.javaPro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class SaveLoadClass {
	private TargetClass target;

	public SaveLoadClass() {
		super();
		this.target = null;
	}

	public SaveLoadClass(TargetClass target) {
		super();
		this.target = target;
	}

	public void saveClass(String filename) {
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

	public void loadClass(String filename) {
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

	@Override
	public String toString() {
		return "SaveLoadClass{" + "target=" + target + "'}'";
	}
}
