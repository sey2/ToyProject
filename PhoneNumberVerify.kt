fun main() {
    Solution().solution("010-1234-5678")
}

class Solution {
    fun solution(phone_number: String): Int {
        val viewModel = PhoneViewModel(VerifyPhoneNumber(PhoneRepositoryImpl()))
        viewModel.checkPhoneNumber(phone_number)

        return viewModel.uiState.phoneType
    }
}

class PhoneRepositoryImpl : PhoneRepository {
    override fun verifyPhoneNumber(phoneNumber: String): Int = when {
        Regex("""010-\d{4}-\d{4}""").matches(phoneNumber) -> 1
        Regex("""010\d{8}""").matches(phoneNumber) -> 2
        Regex("""\+82-10-\d{4}-\d{4}""").matches(phoneNumber) -> 3
        else -> 1
    }
}

class PhoneViewModel(
    private val verifyPhoneNumber: VerifyPhoneNumber
) : BaseViewModel<PhoneState, PhoneEvent>(
    PhoneReducer(PhoneState.init())
) {
    fun checkPhoneNumber(phoneNumber: String) {
        sendEvent(PhoneEvent.OnSuccessCheckPhoneNumber(verifyPhoneNumber(phoneNumber)))
    }
}

class VerifyPhoneNumber(
    private val repository: PhoneRepository
) : BaseResultUseCase<String, Int>() {

    override fun execute(params: String): Int = repository.verifyPhoneNumber(params)
}

class PhoneReducer(state: PhoneState) : Reducer<PhoneState, PhoneEvent>(state) {
    override fun reduce(oldState: PhoneState, event: PhoneEvent) {
        when (event) {
            is PhoneEvent.OnSuccessCheckPhoneNumber -> setState(
                oldState.copy(
                    isLoading = false,
                    isError = false,
                    phoneType = event.phoneType
                )
            )
        }
    }
}

abstract class BaseViewModel<S : State, E : Event>(
    private val reducer: Reducer<S, E>
) {
    var uiState = reducer.uiState

    protected fun sendEvent(event: E) {
        reducer.sendEvent(event)
        uiState = reducer.uiState
    }
}

interface State
interface Event

abstract class Reducer<S : State, E : Event>(initialState: S) {
    var uiState = initialState

    fun sendEvent(event: E) {
        reduce(uiState, event)
    }

    fun setState(newState: S) {
        uiState = newState
    }

    abstract fun reduce(oldState: S, event: E)
}

abstract class BaseResultUseCase<P, R> {

    abstract fun execute(params: P): R

    operator fun invoke(params: P): R = execute(params)

}

sealed class PhoneEvent : Event {
    data class OnSuccessCheckPhoneNumber(val phoneType: Int) : PhoneEvent()
}

interface PhoneRepository {
    fun verifyPhoneNumber(phoneNumber: String): Int
}

data class PhoneState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val phoneType: Int = -1
) : State {
    companion object {
        fun init() = PhoneState()
    }
}
