package com.ktar5.infoboard.scroll;

import com.ktar5.infoboard.utils.Messages;
import org.bukkit.ChatColor;

public class Scroll {

    private String message;
    private String origionalMessage;

    private ChatColor color = ChatColor.RESET;

    private int width;
    private int position = 0;
    private int pause = 0;
    private int row;

    /**
     * Create a new scroller
     *
     * @param message
     * @param row
     * @param width
     */
    public Scroll(String message, int row, int width) {
        this.row = row;
        this.width = width;
        this.origionalMessage = message;
        StringBuilder builder = new StringBuilder(message);
        while (builder.length() <= (width * 2))
            builder.append("          ").append(message);

        String string = builder.toString();

        string = Messages.getColored(string);

        this.message = string;
    }

    /**
     * Get the scrolled message
     *
     * @return message
     */
    public String getMessage() {

        String message = this.message.substring(
                this.position,
                Math.min(this.message.length(), (this.width - 2)
                        + this.position));
        char COLORCHAR = '&';
        if (message.charAt(0) == COLORCHAR) {
            this.color = ChatColor.getByChar(message.charAt(1));
        } else {
            message = message.substring(1, message.length());
            message = "" + this.color + message;
        }

        if (message.charAt(message.length() - 1) == COLORCHAR) {
            message = message.substring(0, message.length() - 2);
            message = message + " ";
        }
        return message;

    }

    /**
     * @return the row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Move position up one unless it's being paused for 3 counts first
     */
    public void next() {

        if ((this.position == 0) && (this.pause != 3))
            this.pause++;
        else {
            this.position++;
            this.pause = 0;

            if (this.position == (this.origionalMessage.length() + 10))
                this.position = 0;

        }

    }

}
