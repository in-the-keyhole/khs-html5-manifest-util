package com.khs.appcache;

/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ManifestWriter {

	private static final String DOCROOT = "webapp";
	private String fileName = "MANIFEST.APPCACHE";
	private String path = "src/main/webapp/";

	private List<String> fileEntries = new ArrayList<String>();

	public ManifestWriter(String docRoot, String fileName) {
		this.path = checkForSeparator(docRoot);
		this.fileName = path + fileName;
	}

	private String checkForSeparator(String path) {
		char sep = '/';
		return path.charAt(path.length() - 1) == sep ? path : path + sep;
	}

	private void read() {

		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String str;
			while ((str = in.readLine()) != null) {
				fileEntries.add(str);
			}
			in.close();
		} catch (IOException e) {
		}

	}

	public void write(List<String> files) {

		if (!new File(fileName).exists()) {
			writeNew(files);
		} else {
			writeMerge(files);
		}

	}

	private void writeMerge(List<String> files) {

		read();

		try {
			FileWriter writer = new FileWriter(fileName);

			boolean cacheBegin = false;
			boolean cacheEnd = false;
			boolean filesWritten = false;
			for (String str : fileEntries) {

				if (str.equals(Constants.END)) {
					cacheBegin = false;
				}

				if (!cacheBegin) {
					writer.write(str + "\n");
				}
				if (str.equals(Constants.BEGIN)) {
					cacheBegin = true;
				}

				if (cacheBegin) {
					if (!filesWritten) {
						for (String s : files) {
							writer.write(strip(s) + "\n");
						}
						filesWritten = true;
					}
				}

			}

			writer.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void writeNew(List<String> files) {
		try {
			FileWriter writer = new FileWriter(fileName);
			writer.write(Constants.HEADER);
			writer.write("# " + dateTimeVersion() + "\n");
			writer.write(Constants.CACHE);
			writer.write(Constants.COMMENT);
			writer.write(Constants.BEGIN + "\n");
			for (String s : files) {
				writer.write(strip(s) + "\n");
			}
			writer.write(Constants.END + "\n");
			writer.write(Constants.NETWORK);
			writer.write(Constants.FALLBACK);

			writer.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String dateTimeVersion() {
		Calendar cal = Calendar.getInstance();

		return "" + cal.get(Calendar.YEAR) + "-"
				+ (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH) + ":v "
				+ cal.get(Calendar.HOUR_OF_DAY) + "."
				+ cal.get(Calendar.MINUTE) + "."+
				+ cal.get(Calendar.SECOND);
	}

	private String strip(String file) {

		String docroot = DOCROOT;
		int pos = file.indexOf(docroot);
		String result = file.substring(pos + docroot.length() + 1);
		return result;

	}

}
