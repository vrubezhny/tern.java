/**
 *  Copyright (c) 2013-2014 Angelo ZERR.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *  Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 */
package tern;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tern.server.ITernModule;
import tern.utils.TernModuleHelper;

/**
 * Tern repository implementation.
 *
 */
public class TernRepository implements ITernRepository {

	private final String name;
	private File ternBaseDir;
	private final boolean defaultRepository;
	private ITernModule[] modules;

	public TernRepository(String name, File ternBaseDir) {
		this(name, ternBaseDir, false);
	}

	public TernRepository(String name, File ternFile, boolean defaultRepository) {
		this.name = name;
		this.ternBaseDir = ternFile;
		this.defaultRepository = defaultRepository;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ITernModule[] getModules() throws TernException {
		if (modules == null) {
			modules = loadModules();
		}
		return modules;
	}

	private ITernModule[] loadModules() throws TernException {
		List<ITernModule> modules = new ArrayList<ITernModule>();
		// defs
		loadModules(modules, "defs");
		// plugin
		loadModules(modules, "plugin");
		// node_modules
		loadModules(modules, "node_modules");
		return modules.toArray(ITernModule.EMPTY_MODULE);
	}

	private void loadModules(List<ITernModule> modules, String dir)
			throws TernException {
		File baseDir = new File(getTernBaseDir(), dir);
		if (baseDir.exists()) {
			File[] files = baseDir.listFiles();
			File file = null;
			ITernModule module = null;
			for (int i = 0; i < files.length; i++) {
				file = files[i];
				module = TernModuleHelper.getModule(file.getName());
				if (module != null) {
					modules.add(module);
				}
			}
		}
	}

	@Override
	public void refresh() {
		this.modules = null;
	}

	@Override
	public File getTernBaseDir() {
		return ternBaseDir;
	}

	@Override
	public String getTernBaseDirAsString() {
		try {
			return getTernBaseDir().getCanonicalPath();
		} catch (IOException e) {
			return getTernBaseDir().getPath();
		}
	}

	@Override
	public void setTernBaseDir(File ternBaseDir) {
		this.ternBaseDir = ternBaseDir;
	}

	@Override
	public boolean isDefault() {
		return defaultRepository;
	}
}