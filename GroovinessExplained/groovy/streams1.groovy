// Open a Stream by calling a constructor
def baos = new ByteArrayOutputStream()
// Basic operation on any OutputStream: Write a single byte
baos.write(65)
// Output an entire array of bytes
baos.write([66,67,10] as byte[])
// Accessing the accumulated bytes in the ByteArrayOutputStream
assert baos.toByteArray() == [65,66,67,10]
// Converting them to a String, specifying Charset, just to be sure
assert baos.toString("US-ASCII") == "ABC\n"

// Open a Stream for creating a new file
def fos = new FileOutputStream("groovytut.txt")
// Output the ByteArrayOutputStream buffer to it
baos.writeTo(fos)
// Close the stream und underlying file
fos.close()

// Open the file again, this time for appending
fos = new FileOutputStream("groovytut.txt", true)
// Write more bytes, Groovy-style
fos << ([90,89,88,10] as byte[])
// Close it again
fos.close()

// Now open a file for input
def fis = new FileInputStream("groovytut.txt")
// Allocate a small input buffer
def buf = new byte[6]
// Read bytes into the buffer
def cnt = fis.read(buf)

// Test for expected content
assert buf[4] == 90
// The buffer was filled entirely: There might be more in the stream
assert cnt == buf.size()

// Read into the buffer again
cnt = fis.read(buf)

// Just two more bytes and the expected content
assert cnt == 2
assert buf[0] == 88

fis.close()

// Copy an entire Stream, Groovy-style
fis = new FileInputStream("groovytut.txt")
fos = new FileOutputStream("copytut.txt")
fos << fis
fis.close()
fos.close()

fis = new FileInputStream("groovytut.txt")
// This method automatically closes the stream
// after handing every single byte to the closure
fis.eachByte {
    baos.write(it)
}
// Check the result of the ByteArrayOutputStream
buf = baos.toByteArray()
assert buf == [65, 66, 67, 10, 65, 66, 67, 10, 90, 89, 88, 10]

// Groovy JDK provides an Iterator for InputStreams enabling all kinds of functional magic
// Can you fathom, what this usage of .inject() does?
assert new ByteArrayInputStream(buf).inject(0, { lcnt, it -> it == 10 ? lcnt+1 : lcnt }) == 3
assert new ByteArrayInputStream(buf).findLastIndexOf({ it == 67 }) == 6
// Careful: The Iterator does not close the InputStream at the end!
// Fortunately, there is no need to close a ByteArrayInputStream.

fis = new FileInputStream("groovytut.txt")
// .eachByte(size) fills a buffer of specified size,
// provides the count of bytes received during each loop,
// and closes the stream at the end
fis.eachByte(4) { buffer, count ->
    assert buffer[3] == 10
    assert count == 4
}

// This appends another copy of "groovytut.txt" to "copytut.txt".
// .withStream() closes the stream after calling a closure with it
new FileOutputStream("copytut.txt", true).withStream {
    // .getBytes() reads the entire InputStream into a byte[] and closes it
    it.write(new FileInputStream("groovytut.txt").getBytes())
}

// .getText reads the entire InputStream into a String
// using a specific charset or the system default encoding, if not specified.
// Again the file is closed automatically. This is pretty groovy, isn't it?
def text = new FileInputStream("copytut.txt").getText("UTF-8")
assert text == "ABC\nZYX\nABC\nZYX\n"
