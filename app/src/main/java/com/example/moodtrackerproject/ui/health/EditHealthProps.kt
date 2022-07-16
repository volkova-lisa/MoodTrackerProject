import com.example.moodtrackerproject.app.MviAction

data class EditHealthProps(
    val waterNum: Int = 0,
    val stepsNum: Int = 0,
    val sleepNum: Float = 0.0f,
    val kcalNum: Int = 0,
    val saveEdited: (Array<Any>) -> Unit = {},
    val action: EditHealthScreenActions? = null

) {
    sealed class EditHealthScreenActions : MviAction {
        object StartHealthScreen : EditHealthProps.EditHealthScreenActions()
    }
}
