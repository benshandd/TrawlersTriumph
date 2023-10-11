package nz.ac.wgtn.swen225.lc.persistency;

import java.util.ServiceLoader;

/**
 * Loads custom actors using a plugin-based design.
 */
public class PluginLoader<T> {
    private static final String PLUGIN_PACKAGE = "nz.ac.wgtn.swen225.lc.plugins";
    private ServiceLoader<T> serviceLoader;

    public PluginLoader(Class<T> type) {
        this.serviceLoader = ServiceLoader.load(type);
    }

    /**
     * Get a list of loaded plugins.
     *
     * @return Iterable of plugins.
     */
    public Iterable<T> getPlugins() {
        return serviceLoader;
    }
}
