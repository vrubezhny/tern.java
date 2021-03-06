/**
 *  Copyright (c) 2015 Genuitec LLC.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *  Piotr Tomiak <piotr@genuitec.com> - initial API and implementation
 */
package tern.eclipse.ide.debugger.webclipse;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class WebclipseDebuggerCorePlugin extends Plugin {

	public static final String PLUGIN_ID = "tern.eclipse.ide.debugger.webclipse"; //$NON-NLS-1$

	// The shared instance.
	private static WebclipseDebuggerCorePlugin plugin;

	/**
	 * The constructor.
	 */
	public WebclipseDebuggerCorePlugin() {
		super();
		plugin = this;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		plugin = this;
		super.start(context);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static WebclipseDebuggerCorePlugin getDefault() {
		return plugin;
	}

}
