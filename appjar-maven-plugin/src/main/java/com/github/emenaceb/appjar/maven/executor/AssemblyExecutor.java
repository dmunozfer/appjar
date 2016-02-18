package com.github.emenaceb.appjar.maven.executor;

import static org.twdata.maven.mojoexecutor.MojoExecutor.configuration;
import static org.twdata.maven.mojoexecutor.MojoExecutor.element;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.twdata.maven.mojoexecutor.MojoExecutor.Element;

import com.github.emenaceb.appjar.boot.AppJarBoot;
import com.github.emenaceb.appjar.boot.MagicAppJarBoot;

/**
 * Packs app jar.
 * 
 * @author ejmc
 *
 */
public class AssemblyExecutor extends BaseMojoExecutor {

	private static final String APPJAR_ASSEMBLY_NAME = "appjar";
	private static final String APPJAR_ASSEMBLY_ID = "app";
	private static final String APPJAR_ASSEMBLY_EXT = "jar";

	public static final GoalDescriptor GOAL = new GoalDescriptor("org.apache.maven.plugins", "maven-assembly-plugin", "2.6", "single");

	private String mainClass;

	private String finalName;

	private String alternateClassifier;

	public AssemblyExecutor(ExecutorContext context, String finalName, String mainClass, String alternateClassifier) {
		super(context);
		this.mainClass = mainClass;
		this.alternateClassifier = StringUtils.isBlank(alternateClassifier) ? null : alternateClassifier.trim();
		this.finalName = finalName;
	}

	@Override
	public void exec() throws MojoExecutionException {

		packageWithAssembly(APPJAR_ASSEMBLY_NAME, //
				element("attach", "false"), //
				element("archive", //
						element("addMavenDescriptor", "true"), //
						element("manifest", //
								element("mainClass", AppJarBoot.class.getName()), //
								element("addDefaultImplementationEntries", "true"), //
								element("addDefaultSpecificationEntries", "true")), //
						element("manifestEntries", //
								element(MagicAppJarBoot.MF_APPJAR_MAIN_CLASS, mainClass))//
		));

		String classifier = APPJAR_ASSEMBLY_ID;
		File buildDir = new File(context.getProject().getBuild().getDirectory());
		File generated = new File(buildDir, finalName + "-" + APPJAR_ASSEMBLY_ID + "." + APPJAR_ASSEMBLY_EXT);
		if (alternateClassifier != null && !APPJAR_ASSEMBLY_ID.equals(alternateClassifier)) {
			// Enable custom classifiers

			classifier = alternateClassifier;

			File dstArchive = new File(buildDir, finalName + "-" + alternateClassifier + "." + APPJAR_ASSEMBLY_EXT);
			generated.renameTo(dstArchive);
			generated = dstArchive;
		}

		// Attach
		context.getProjectHelper().attachArtifact(context.getProject(), APPJAR_ASSEMBLY_EXT, classifier, generated);

	}

	protected void packageWithAssembly(String ref, Element... elements) throws MojoExecutionException {

		List<Element> config = new ArrayList<Element>();
		config.add(element("descriptorRefs", element("descriptorRef", ref)));
		if (elements != null) {
			config.addAll(Arrays.asList(elements));
		}

		PluginDescriptor currentPlugin = context.getPlugin();
		execMojo(GOAL, //
				configuration(elementArray(config)), //
				singleDependency(currentPlugin.getGroupId(), currentPlugin.getArtifactId(), currentPlugin.getVersion()));

	}

}
