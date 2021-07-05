import com.sun.javafx.PlatformUtil
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.OS

class UTest {

    @Test
    fun OsTest() {
        println(System.getProperty("os.name"))
        println(PlatformUtil.isWindows())
        println(OS.WINDOWS.isCurrentOs)
        print(System.getenv("APPDATA"))
    }
}