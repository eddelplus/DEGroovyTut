// Consistently using system encoding here
// The charsetName parameter can be omitted, with the same result
def charsetName = System.getProperty("file.encoding")

// Create/overwrite "new.txt"
def pw = new PrintWriter("new.txt", charsetName)

// System.in is a BufferedInputStream
// We want to read from the console line by line
def inp = System.in.newReader(charsetName)
def line
// The loop will end with either "" or null(eof)
while (line = inp.readLine()) {
  pw.println line
}
pw.close()

// Append to "new.txt"
pw = new FileOutputStream("new.txt", true).withWriter(charsetName) {
    it.println("That's all folks!")
}

// Iterate through a stream line by line
// closing it at the end
new FileInputStream("new.txt").eachLine(charsetName) {
    println '"' + it + '"'
}
// A line number (starting at 1) is provided
// when using a two-argument closure
new FileInputStream("new.txt").eachLine(charsetName) {
    it, lnr -> printf("%3d: %s\n", lnr, it)
}

// The whole text can be read with a single method call
def text = new FileInputStream("new.txt").getText(charsetName)
print text.toLowerCase()

// Groovy is flexible with regard to line separators in the input
text = "ABC\nZYX\r\nAYC\rZBX\r\n"

// All lines can be read into a list with a single method call
// No character set to specify when the input is a Reader
def linelist = new StringReader(text).readLines()
assert linelist == ['ABC','ZYX','AYC','ZBX']

// Read lines and split each line with RegEx
new StringReader(text).splitEachLine(/[BY]/) {
    // The split result is a List<String>
    assert it[0] in ['A','Z']
    assert it.join("*") in ['A*C','Z*X']
    assert it.size() == 2
}

// Force Linux line ending
System.setProperty("line.separator", "\n")

// Transfom each line, outputting to a Writer
def strw = new StringWriter()
new StringReader(text).transformLine(strw) { it.toLowerCase() }
assert strw.toString() == "abc\nzyx\nayc\nzbx\n"

// Force Windows line ending
System.setProperty("line.separator", "\r\n")

// Filter each line, outputting to a Writer
// The line separator is used in the output
strw = new StringWriter()
new StringReader(text).filterLine(strw) { it =~ /B/ }
assert strw.toString() == "ABC\r\nZBX\r\n"

// Proof of the issue with line endings
strw = new StringWriter()
strw.printf("%s\n", "A")
strw.println("B")
assert strw.toString() == "A\nB\r\n"