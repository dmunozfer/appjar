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
package com.github.emenaceb.appjar.maven;

/**
 * Constant class for plugin
 * 
 * @author emenaceb
 *
 */
public final class MagicAppJarPlugin {

	public static final String APPJAR_BUILD_DIR = "${project.build.directory}/appjar";

	public static final String PRJ_PROP_APPJAR_BANNERFILE = "appjar.bannerfile";

	public static final String PRJ_PROP_APPJAR_BOOT_INFO_PATH = "appjar.bootInfoPath";

	public static final String PRJ_PROP_APPJAR_MAIN_LIB_PATH = "appjar.mainLibPath";

	public static final String PRJ_PROP_APPJAR_BUILD_DIR = "appjar.buildDir";

	private MagicAppJarPlugin() {
	}
}
