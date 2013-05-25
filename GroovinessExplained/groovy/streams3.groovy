def baos = new ByteArrayOutputStream()
def dataOut = new DataOutputStream(baos)
dataOut.writeInt(0x40414243)

assert baos.size() == 4
baos.toByteArray().eachWithIndex { value, offset ->
    assert value == 64 + offset
}

dataOut.writeUTF("How many €?")

def dataIn = new DataInputStream(new ByteArrayInputStream(baos.toByteArray()))
assert dataIn.readInt() == 0x40414243
assert dataIn.readUTF() == "How many €?"
