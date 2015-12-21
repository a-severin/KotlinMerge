import com.vvv.merge.SourceLine
import org.junit.Assert.*
import org.junit.Test

class SourceLineTest{
    @Test
    fun testConstructor() {
        val source = SourceLine("test");
        assertTrue(source.existsFlag == 0);
    }
}
