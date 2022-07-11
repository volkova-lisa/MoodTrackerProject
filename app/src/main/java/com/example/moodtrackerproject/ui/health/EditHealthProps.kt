import com.example.moodtrackerproject.app.MviAction

data class EditHealthProps(
    val save: Boolean = false,
    val waterNum: Int = 0,
    val stepsNum: Int = 0,
    val sleepNum: Float = 0.0f,
    val kcalNum: Int = 0,

) {
    sealed class EditHealthScreenActions : MviAction {
        object StartHealthScreen : EditHealthProps.EditHealthScreenActions()
    }
}
