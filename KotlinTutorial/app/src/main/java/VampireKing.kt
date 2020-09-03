import kotlin.random.Random

class VampireKing(name: String) : Vampire(name){
    init {
        hitPoints = 140
    }

    override fun takeDamage(damage: Int) {
        super.takeDamage(damage / 2)
    }

    fun runAway(): Boolean {
        return lives < 2
    }

    fun dodges(): Boolean {
        val rand = Random
        val chances = rand.nextInt(6)
        if (chances > 3) {
            println("Dracula dodges")
            return true
        }
        return false
    }
}