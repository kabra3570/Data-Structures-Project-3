/**
 * Author: Kevin Abrahams
 * Date: June 25, 2020
 * Class Description: This exception is thrown if the tree syntax
 * is not valid.
 */

public class InvalidTreeSyntax extends Exception {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4271144365546597233L;

    /**
     * Constructor to set the error message
     *
     * @param message - Error Message
     */
    public InvalidTreeSyntax(String message) {
        super(message);
    }
}