class Weapon(val name: String, var damageInflicted: Int) {

    override fun toString(): String {
        return """
            name: $name
            damage inflicted: $damageInflicted
            """
    }
}