import com.example.moodtrackerproject.app.MviAction

data class EditHealthProps(
    val waterNum: Int = 0,
    val stepsNum: Int = 0,
    val sleepNum: Int = 0,
    val kcalNum: Int = 0,
    val saveEdited: (List<Int>) -> Unit = {},
    val action: EditHealthScreenActions? = null

) {
    sealed class EditHealthScreenActions : MviAction {
        object StartHealthScreen : EditHealthProps.EditHealthScreenActions()
    }
}
