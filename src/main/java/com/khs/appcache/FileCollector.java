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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileCollector {

	File file = null;
	String docRoot = null;

	static String[] extensions = Constants.extensions;

	public FileCollector(String dr,File f) {
		file = f;
		this.docRoot = dr.toLowerCase();
	}

	public void collect(List<String> files) {

		collect(files, file);
	}

	private void collect(List<String> files, File f) {

		if (f.isDirectory()) {
			for (File file : f.listFiles())
				collect(files, file);
		} else {
			try {
				String path = f.getCanonicalPath().toLowerCase();
				if (hasExtension(f.getName()) && path.indexOf(docRoot) >=0   ) {
					files.add(path);
				}
			} catch (IOException e) {
				throw new RuntimeException();
			}

		}

	}

	private boolean hasExtension(String file) {

		String lower = file.toLowerCase();
		int last = lower.lastIndexOf(".");
		if (last < 0) { return false;}
		String ext = lower.substring(last);
		for (String s : extensions) {
			if (ext.equals(s)) {
				return true;
			}

		}

		return false;
	}

}
