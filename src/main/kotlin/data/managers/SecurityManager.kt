package data.managers

import java.util.Base64

/**
 * The SecurityManager interface provides methods for encoding and decoding strings.
 */
interface SecurityManager {
    
    /**
     * Encodes the input string using the specified encoding scheme.
     *
     * @param it the input string to be encoded
     * @return the encoded version of the input string
     */
    fun encode(it: String): String
    
    /**
     * Decodes the input string using the specified decoding scheme.
     *
     * @param it the input string to be decoded
     * @return the decoded version of the input string
     */
    fun decode(it: String): String

    /**
     * Companion object.
     */
    companion object {
        /**
         * Provides an instance of SecurityManager.
         *
         * @return an implementation of the SecurityManager interface
         */
        operator fun invoke(): SecurityManager = SecurityManagerImpl
    }
    
}

/**
 * Implementation of the SecurityManager interface.
 * Provides methods to encode and decode strings using predefined schemes.
 */
private object SecurityManagerImpl : SecurityManager {
    
    /**
     * Encodes the input string using the specified encoding scheme.
     *
     * @param it the input string to be encoded
     * @return the encoded version of the input string
     */
    override fun encode(it: String): String = String(Base64.getEncoder()
        .encode(it.toByteArray()))

    /**
     * Decodes the input string using the specified decoding scheme.
     *
     * @param it the input string to be decoded
     * @return the decoded version of the input string
     */
    override fun decode(it: String): String = String(Base64.getDecoder().decode(it))

}