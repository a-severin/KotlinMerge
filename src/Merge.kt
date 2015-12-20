class Merge(source : Array<String>, version1 : Array<String>, version2 : Array<String>) {
    private val _source : Array<SourceLine> = source.map{it -> SourceLine(it)}.toTypedArray();
    private val _version1 : Array<VersionLine> = version1.map{it -> VersionLine(it)}.toTypedArray();
    private val _version2 : Array<VersionLine> = version2.map{it -> VersionLine(it)}.toTypedArray();

    public fun Merge(outputPath : String) {

    }

    public fun Merge() {

    }
}
