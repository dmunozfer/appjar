/**
 * Copyright (C) 2016 emenaceb (emenaceb@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.emenaceb.appjar.boot.apploader;

import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.github.emenaceb.appjar.boot.MagicAppJarBoot;

/**
 * UrlConnection to load appjar resources.
 * 
 * @author emenaceb
 *
 */
public final class AppLoaderUrlConnection extends URLConnection {

	private JarFile file;

	public AppLoaderUrlConnection(URL url, JarFile file) {
		super(url);
		this.file = file;
	}

	@Override
	public void connect() {
	}

	public boolean exists() {
		String path = getInnerResource(getURL());
		return file.getJarEntry(path) != null;
	}

	@Override
	public String getContentType() {
		FileNameMap fileNameMap = java.net.URLConnection.getFileNameMap();
		String contentType = fileNameMap.getContentTypeFor(url.getPath());
		if (contentType == null)
			contentType = "text/plain";
		return contentType;
	}

	private String getInnerResource(URL u) {
		String innerJar = u.getHost();
		innerJar = MagicAppJarBoot.LIB_PREFIX + innerJar;
		String relativePath = u.getPath();
		String path = innerJar + relativePath;
		return path;
	}

	@Override
	public InputStream getInputStream() throws IOException {

		String path = getInnerResource(getURL());

		InputStream is = null;
		JarEntry entry = file.getJarEntry(path);
		if (entry != null) {
			is = file.getInputStream(entry);
		}
		if (is == null) {
			throw new IOException("Unable to get InputStream for " + getURL().toString());
		}
		return is;

	}

}