import sample.Container

def container = new Container(
    list: ["A", new Date(), [ blue: "Grover", red: "Elmo" ] ],
    map:  [ yellow: "BigBird", size: 72d, traits: ["friendly", "tall"] ]
)

new FileOutputStream("object.bin").withObjectOutputStream { objOut ->
    objOut << container
    objOut << container.map
    objOut << new Date()
}

def objects = []
def fis = new FileInputStream("object.bin")
fis.newObjectInputStream(Container.classLoader).eachObject { obj ->
    objects << obj
}

assert objects.size() == 3

def cont = objects[0]
assert !cont.is(container)
assert cont == container

def map = objects[1]
assert cont.map.is(objects[1])
cont.map.yellow = "Tweety"
assert objects[1].yellow == "Tweety"

assert objects[2] instanceof Date
assert !objects[2].is(cont.list[1])