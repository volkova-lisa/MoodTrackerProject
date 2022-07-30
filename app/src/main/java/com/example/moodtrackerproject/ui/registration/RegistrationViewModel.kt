package com.example.moodtrackerproject.ui.registration

import com.example.moodtrackerproject.app.*
import com.example.moodtrackerproject.app.Store.appState
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.registration.RegistrationProps.RegistrationAction
import com.example.moodtrackerproject.ui.registration.RegistrationProps.RegistrationError
import com.example.moodtrackerproject.utils.NAME
import com.example.moodtrackerproject.utils.PROFILE
import com.example.moodtrackerproject.utils.isEmailValid
import com.example.moodtrackerproject.utils.isPasswordValid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel : BaseViewModel<RegistrationProps>() {

    private val props = RegistrationProps(
        action = null,
        checkRegistrationData = ::checkRegistrationData,
        openLogin = ::openLogin,
        fetchName = ::fetchName,
        saveAccData = ::saveAccData
    )

    init {
        setState(Store.appState.registrationState)
        liveData.value = props
    }

    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private var databaseReference: DatabaseReference? = null

    private fun checkRegistrationData(
        email: String,
        password: String,
        fullName: String
    ) {
        when {
            fullName.isNotEmpty() && email.isEmailValid() && password.isPasswordValid() -> {
                registerUserWithEmailAndPassword(fullName, email, password)
            }
            fullName.isEmpty() ->
                liveData.value =
                    props.copy(error = RegistrationError.ShowNameInvalid)
            !email.isEmailValid() || email.isEmpty() ->
                liveData.value =
                    props.copy(error = RegistrationError.ShowEmailInvalid)
            !password.isPasswordValid() || password.isEmpty() ->
                liveData.value =
                    props.copy(error = RegistrationError.ShowPasswordInvalid)
        }
    }

    private fun openLogin() {
        liveData.value = props.copy(action = RegistrationAction.StartLogInScreen)
    }

    private fun registerUserWithEmailAndPassword(name: String, email: String, password: String) {
        databaseReference = database.reference.child(PROFILE)
        liveData.value = props.copy(isLoading = true)

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                liveData.value = props.copy(isLoading = false)
                if (it.isSuccessful) {
                    val currentUser = auth.currentUser
                    val currentUserDb = databaseReference?.child(currentUser?.uid!!)
                    currentUserDb?.child(NAME)?.setValue(name)
                    liveData.value =
                        props.copy(action = RegistrationAction.StartLogInScreen)
                } else {
                    liveData.value =
                        props.copy(error = RegistrationError.ShowRegistrationError)
                }
            }
    }

    override fun map(appState: AppState, action: MviAction?): RegistrationProps {
        return RegistrationProps(
            action = action as? RegistrationAction,
            saveAccData = ::saveAccData,
            checkRegistrationData = ::checkRegistrationData,
            openLogin = ::openLogin,
            fetchName = ::fetchName
        )
    }

    private fun saveAccData(name: String, email: String) {
        DataBaseRepository.saveName(name)
        DataBaseRepository.saveEmail(email)

        val currName = DataBaseRepository.getName()
        val currEmail = DataBaseRepository.getEmail()
        Store.setState(appState.homeState.copy(name = currName))
        Store.setState(appState.settingsState.copy(name = currName))
        Store.setState(appState.homeState.copy(email = currEmail))
        Store.setState(appState.settingsState.copy(email = currEmail))
    }

    private fun fetchName() {
        launch {
            val name = withContext(Dispatchers.IO) {
                DataBaseRepository.getName()
            }
            Store.setState(appState.registrationState.copy(name = name))
        }
    }

    private fun setState(state: RegistrationState, action: RegistrationAction? = null) {
        setState(appState.copy(registrationState = state), action)
    }
}
