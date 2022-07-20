import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.domain.HealthModel

data class EditHealthProps(
    val listHealth: EditHealthItemProps? = null,
    val saveEdited: (List<HealthModel>) -> Unit = {},
    val action: EditHealthScreenActions? = null

) {
    sealed class EditHealthScreenActions : MviAction {
        object StartHealthScreen : EditHealthProps.EditHealthScreenActions()
    }

    data class EditHealthItemProps(
        val water: Int = 0,
        val steps: Int = 0,
        val sleep: Int = 0,
        val kcal: Int = 0
    )
}
