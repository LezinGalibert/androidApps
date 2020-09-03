open class Enemy(val name: String, var hitPoints: Int, var lives:Int) {

    open fun takeDamage(damage: Int) {
        val remaingHitPoints = hitPoints - damage
        if (remaingHitPoints > 0) {
            hitPoints = remaingHitPoints
            println("$name took $damage points of damage, and has $hitPoints left.")
        } else {
            lives -= 1
            println("$name lost a life.")

            if (lives == 0) {
                println("$name is dead.")
            }
        }
    }

    override fun toString(): String {
        return "Name: $name, Hitpoints: $hitPoints, Lives: $lives"
    }
}