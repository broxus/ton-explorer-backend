package com.broxus.ton;

import java.io.*;
import java.util.UUID;

public class TonNativeLib {

    private static boolean isLoaded = false;

    public static void load() throws Exception {
        if(!isLoaded) {
            InputStream libStream = null;
            try {
                String ext;
                if(OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
                    libStream = TonNativeLib.class.getClassLoader().getResourceAsStream("nativelib/libnative-lib.so");
                    ext = ".so";
                } else if (OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS) {
                    libStream = TonNativeLib.class.getClassLoader().getResourceAsStream("nativelib/libnative-lib.dylib");
                    ext = ".dylib";
                } else {
                    throw new Exception("Unsupported operation system " + OsCheck.getOperatingSystemType());
                }

                String tmpDir = System.getProperty("java.io.tmpdir");
                String fileName = "libnative-lib-" + UUID.randomUUID().toString() + ext;
                File file = new File(tmpDir, fileName);
                file.deleteOnExit();

                try (
                    BufferedInputStream bis = new BufferedInputStream(libStream);
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))
                ) {
                    byte[] buffer = new byte[32 * 1024];
                    int bytesRead;
                    while((bytesRead = bis.read(buffer)) != -1) {
                        bos.write(buffer, 0, bytesRead);
                    }
                }

                System.load(file.getAbsolutePath());

                isLoaded = true;
            } finally {
                if(libStream != null) { libStream.close(); }
            }
        }
    }

}
