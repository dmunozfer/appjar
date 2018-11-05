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
package com.github.emenaceb.appjar.maven.executor;

import static org.twdata.maven.mojoexecutor.MojoExecutor.configuration;
import static org.twdata.maven.mojoexecutor.MojoExecutor.element;

import org.apache.maven.plugin.MojoExecutionException;

import com.github.emenaceb.appjar.boot.MagicAppJarBoot;
import com.github.emenaceb.appjar.maven.MagicAppJarPlugin;
import com.github.emenaceb.appjar.maven.MagicGoals;

/**
 * Adds dependencies to app jar.
 * 
 * @author emenaceb
 *
 */
public class UnpackDependenciesExecutor extends BaseMojoExecutor {

	public UnpackDependenciesExecutor(ExecutorContext context) {
		super(context);
	}

	@Override
	public void exec() throws MojoExecutionException {

		execMojo(MagicGoals.DEPENDENCIES_UNPACK_DEPS, //
				configuration(//
						element("excludeTypes", "pom"),
						element("outputDirectory", MagicAppJarPlugin.APPJAR_BUILD_DIR + "/" + MagicAppJarBoot.LIB_PREFIX), //
						element("useSubDirectoryPerArtifact", "true")));
	}
}
