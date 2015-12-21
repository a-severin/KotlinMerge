package com.vvv.merge;

import java.util.*

class Merger(source: Array<String>, version1: Array<String>, version2: Array<String>) {
    private val _source: Array<SourceLine> = source.map { it -> SourceLine(it) }.toTypedArray();
    private val _version1: Array<VersionLine> = version1.map { it -> VersionLine(it) }.toTypedArray();
    private val _version2: Array<VersionLine> = version2.map { it -> VersionLine(it) }.toTypedArray();

    public fun Merge(outputPath: String) {
        val file = java.io.File(outputPath);
        val writer = file.writer();
        Merge().forEach { it -> writer.write(it) }
        writer.close()
    }

    public fun Merge(): Array<String> {
        FindPositions(_source, _version1);
        FindPositions(_source, _version2);

        val removeActions = LinkedList<MergeAction>();
        val insertActions = LinkedList<MergeAction>();

        CollectActions(_source, _version1, _version2, removeActions, insertActions);
        DeduplicateActions(insertActions);

        data class Result(val index: Int, val lines: MutableList<String>);
        val results = LinkedList<Result>();

        for (i in _source.indices) {
            results.add(Result(i, linkedListOf(_source[i].content)));
        }

        for (action in removeActions) {
            results[action.mergePosition].lines.clear();
        }

        for (action in insertActions) {
            results[action.mergePosition].lines.addAll(action.lines.map { it -> it.content });
        }

        return results.flatMap { it -> it.lines }.toTypedArray();
    }
}
