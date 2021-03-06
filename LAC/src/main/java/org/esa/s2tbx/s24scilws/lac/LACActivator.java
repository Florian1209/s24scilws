package org.esa.s2tbx.s24scilws.lac;

import com.bc.ceres.core.ProgressMonitor;
import org.esa.snap.core.util.ResourceInstaller;
import org.esa.snap.core.util.SystemUtils;
import org.esa.snap.runtime.Activator;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by obarrile on 09/01/2020.
 */
public class LACActivator implements Activator {

    @Override
    public void start() {
        Path sourceDirPath = ResourceInstaller.findModuleCodeBasePath(getClass()).resolve("auxdata/s24scilws/lac");
        Path auxdataDirectory = SystemUtils.getAuxDataPath().resolve("s2tbx/s24scilws/lac");;
        if (auxdataDirectory == null) {
            SystemUtils.LOG.severe("LAC configuration error: failed to retrieve auxdata path");
            return;
        }
        final ResourceInstaller resourceInstaller = new ResourceInstaller(sourceDirPath, auxdataDirectory);

        try {
            resourceInstaller.install(".*", ProgressMonitor.NULL);
        } catch (IOException e) {
            SystemUtils.LOG.severe("LAC configuration error: failed to create " + auxdataDirectory);
            return;
        }
    }

    @Override
    public void stop() {
        // Purposely no-op
    }

    public static Path getAuxDataDir() {
        return SystemUtils.getAuxDataPath().resolve("s2tbx/s24scilws/lac");
    }
}