package com.vvv.merge;

fun FindPositions(source : Array<SourceLine>, version : Array<VersionLine>) {
    var j = source.size - 1;
    for (i in (version.size - 1) downTo 0){
        val ver = version[i];

        if (j < 0){
            continue;
        }

        if (source[j].trimedContent == ver.trimedContent){
            ver.sourcePosition = j;
            source[j].existsFlag += 1;
            j--;
        } else {
            var k = j;
            k--;
            while (k >= 0){
                if (source[k].trimedContent == ver.trimedContent){
                    j = k;
                    ver.sourcePosition = j;
                    source[j].existsFlag += 1;
                    j--;
                    break;
                }
                k--;
            }
        }
    }
}

fun DeduplicateActions(actions : MutableList<MergeAction>) {
    var i = 0;
    while (i < actions.size - 1){
        val ins1 = actions[i];
        val ins2 = actions[i + 1];
        if (ins1.isEqualContents(ins2)){
            actions.removeAt(i + 1);
        }
        i++;
    }
}

fun CollectActions(
        source : Array<SourceLine>,
        version1 : Array<VersionLine>,
        version2: Array<VersionLine>,
        removeActions : MutableList<MergeAction>,
        insertActions : MutableList<MergeAction>){
    var si = 0; var v1 = 0; var v2 = 0;
    while (si < source.size || v1 < version1.size || v2 < version2.size){
        if (v1 < version1.size) {
            if (version1[v1].sourcePosition >= 0) {
                v1++;
            } else {
                var sp = if (v1 - 1 >= 0) version1[v1 - 1].sourcePosition else 0;
                var mergeAction = MergeAction(sp);
                while (v1 < version1.size && version1[v1].sourcePosition < 0) {
                    mergeAction.lines.add(version1[v1]);
                    v1++;
                }

                insertActions.add(mergeAction);
            }
        }

        if (v2 < version2.size) {
            if (version2[v2].sourcePosition >= 0) {
                v2++;
            } else {
                var sp = if(v2 - 1 >= 0) version2[v2 - 1].sourcePosition else 0;
                var mergeAction = MergeAction(sp);
                while (v2 < version2.size && version2[v2].sourcePosition < 0) {
                    mergeAction.lines.add(version2[v2]);
                    v2++;
                }
                insertActions.add(mergeAction);
            }
        }

        if (si < source.size) {
            if (source[si].existsFlag != 2) {
                var mergeAction = MergeAction(si);
                mergeAction.lines.add(source[si]);
                removeActions.add(mergeAction);
            }
            si++;
        }
    }

}
