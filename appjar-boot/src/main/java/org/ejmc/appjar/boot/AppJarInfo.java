package org.ejmc.appjar.boot;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

/**
 * Holds App information.
 * 
 * @author ejmc
 *
 */
public class AppJarInfo {

	private static AppJarInfo instance = new AppJarInfo();

	private JarFile jarFile;

	private List<URL> libs = new ArrayList<URL>();

	private String mainClass;

	public static AppJarInfo getInstance() {

		return instance;
	}

	private URLClassLoader classLoader;

	public URLClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(URLClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public JarFile getJarFile() {
		return jarFile;
	}

	public void setJarFile(JarFile jarFile) {
		this.jarFile = jarFile;
	}

	public List<URL> getLibraryURLs() {
		return libs;
	}

	public void addLibrary(URL url) {
		this.libs.add(url);
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

}
