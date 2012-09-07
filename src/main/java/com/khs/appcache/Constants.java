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

public class Constants {
	public static final String HEADER = "CACHE MANIFEST ";
    public static final String CACHE = "# Explicitly cached 'master entries \nCACHE:\n";
    public static final String NETWORK = "NETWORK:\n";
    public static final String FALLBACK = "FALLBACK:\n";
    public static final String COMMENT = "# Generated, entries don't edit between {begin}..{end} markers\n";
    public static final String BEGIN = "#{begin}";
	public static final String END = "#{end}";
	public static final String INFO = "Scans current directory for HTML5 resources and\ngenerates manifest application cache file\n";
	public static final String HELP = "Usage Options: \n -f <manifest file name> | default - manifest.appcache \n -d <doc root folder> | default - /src/main/webapp";
	public static String[] extensions = { ".html", ".js", ".css", ".png", ".jpeg",
		".jpg", ".gif" };


}
