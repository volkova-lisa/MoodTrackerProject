import com.example.moodtrackerproject.app.MviAction

data class EditHealthProps(
    val listHealth: List<Int> = listOf(),
    val saveEdited: (List<Int>) -> Unit = {},
    val action: EditHealthScreenActions? = null

) {
    sealed class EditHealthScreenActions : MviAction {
        object StartHealthScreen : EditHealthProps.EditHealthScreenActions()
    }
}
