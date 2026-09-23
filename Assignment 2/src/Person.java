class Person {
    private String name;

    public Person() {
        this.name = null;
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person { name = " + name + " }";
    }
}