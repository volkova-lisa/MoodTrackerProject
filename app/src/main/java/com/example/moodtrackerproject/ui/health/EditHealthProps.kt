import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.domain.HealthModel

data class EditHealthProps(
    val listHealth: EditHealthItemProps? = null,
    val saveEdited: (HealthModel) -> Unit = {},
    val action: EditHealthScreenActions? = null,
    val startHealth: () -> Unit = {},
    val fetchMaxHealth: () -> Unit = {}

) {
    sealed class EditHealthScreenActions : MviAction {
        object StartHealthScreen : EditHealthProps.EditHealthScreenActions()
    }

    data class EditHealthItemProps(
        val water: Int = 0,
        val steps: Int = 0,
        val sleep: Int = 0,
        val kcal: Int = 0,
        val waterMax: Int = 0,
        val stepsMax: Int = 0,
        val sleepMax: Int = 0,
        val kcalMax: Int = 0,
    )
}
