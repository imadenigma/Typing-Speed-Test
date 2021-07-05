package me.imadenigma.wpm.utils

import com.sun.javafx.PlatformUtil
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun getCachePath(): File {
    return when {
        PlatformUtil.isWindows() -> {
            File(System.getenv("APPDATA") + File.separator + ".speedTyping").apply {
                if (!this.exists()) this.mkdir()
            }
        }
        PlatformUtil.isUnix() -> {
            File("usr${File.pathSeparator}local${File.pathSeparator}share${File.pathSeparator}speedTyping").apply {
                if (!this.exists()) this.mkdir()
            }
        }
        else -> File(File.listRoots()[0], "speedTyping").apply {
            if (!this.exists()) this.mkdir()
        }
    }
}