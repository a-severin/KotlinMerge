import org.junit.Assert.*
import org.junit.Test

class VersionLineTest{
    @Test
    fun testConstructor() {
        val version = VersionLine("test");
        assertTrue(version.sourcePosition == -1);
    }
}