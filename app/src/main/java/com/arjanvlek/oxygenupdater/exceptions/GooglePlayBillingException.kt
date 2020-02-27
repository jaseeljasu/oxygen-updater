package com.arjanvlek.oxygenupdater.exceptions

/**
 * @author [Adhiraj Singh Chauhan](https://github.com/adhirajsinghchauhan)
 * @author Arjan Vlek (github.com/arjanvlek)
 */
class GooglePlayBillingException(message: String?) : OxygenUpdaterException(message) {
    companion object {
        private const val serialVersionUID = 387133325499415924L
    }
}
