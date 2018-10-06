package com.example.ydi1_feelsbook;

//Below class is a message pops when ever a exception rised
public class OptionalCommentTooLongException extends Exception {
    OptionalCommentTooLongException(){
        super("The optional comment is too long! Please keep your comment within 100 characters.");
    }
}
