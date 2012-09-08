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

public class Main {
	
	public static final String FILE_OPTION = "-f";
	public static final String DOC_ROOT_OPTION = "-d";
	public static final String HELP_OPTION = "?";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		String path = ".";
		String cacheFile = cacheFile(args); 
		String docRoot = docRoot(args);
		
		if (args.length > 0) {
			if (args[0].equals("?")) {
				System.out.println(Constants.INFO);
				System.out.println(Constants.HELP);
				return;
			}
		}
		File file = new File(path);
		FileCollector collector = new FileCollector(docRoot,file);
		List<String> filesToCache = new ArrayList<String>();	
		String p = null;
		try {
			p = file.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log("Scanning for resource files in: "+p);
		
		collector.collect(filesToCache);
		log("Files collected in docroot folder: "+collector.docRoot);
		log("Collected "+filesToCache.size()+" files with extensions of "+display(Constants.extensions));
		ManifestWriter writer = new ManifestWriter(collector.docRoot,cacheFile);
		writer.write(filesToCache);
		log("Cache File: "+cacheFile+" written");

	}
	
	private static String cacheFile(String[] args) {
		
		String result = "manifest.appcache";
		if (args.length > 0) {	
			int index = findIndex(FILE_OPTION,args);
			if (index >= 0) {			
				result = args[index+1];
			}		
		}
		return result;
		
	}
	
	private static int findIndex(String param,String[] args)  {
		
		int index = 0;
		for (String s : args) {
			
			if (args[index].equals(param)) {
				return index;
			}
			
			index++;
			
		}

		return -1;
	}
	
	private static String display(String[] args) {
		String result = "";
		for (String s  : args) {
			result += s+",";
		}
		return result;		
	}
	
	private static String docRoot(String[] args) {
		
        	String result = null;
			if (args.length > 0) {	
				int index = findIndex(DOC_ROOT_OPTION,args);
				if (index >= 0) {			
					result = args[index+1];
				}		
			}
			return result;
			
		}
		
	
	
	private static void write(List<String> files) {
		
		for (String s : files) {
			String docroot = "webapp";
			int pos = s.indexOf(docroot);
			String f = s.substring(pos + docroot.length());
			System.out.println(f);
		}
		
	}
	
	
	private static void log(String string) {
		
		System.out.println("MANIFEST-CACHE-"+string);
		
	}
		
		
	
	

}
