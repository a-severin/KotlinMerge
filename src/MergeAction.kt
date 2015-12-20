import java.util.*

class MergeAction(val mergePosition : Int){
    val lines : MutableList<Line> = LinkedList();

    public fun isEqualContents(comp : MergeAction): Boolean{
        if (mergePosition != comp.mergePosition){
            return false;
        }
        if (lines.size != comp.lines.size){
            return false;
        }

        for (i in lines.indices){
            if (lines[i].trimedContent != comp.lines[i].trimedContent){
                return false;
            }
        }

        return true;
    }
}
