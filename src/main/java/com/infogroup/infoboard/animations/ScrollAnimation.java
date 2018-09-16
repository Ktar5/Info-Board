package com.infogroup.infoboard.animations;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;

public class ScrollAnimation extends BaseAnimation {

    private int position;
    private ArrayList<String> list;
    private ChatColor color = ChatColor.RESET;

    private int width, length, row;
    private String message;

    /*
    Settings contains Keys:
    -width
    -length
    -single
    -message
    -row
     */
    public ScrollAnimation(HashMap<String, String> settings) {
        this.loadSettings(settings);
        list = new ArrayList<>();
        // String is too short for window?
        if (message.length() < width) {
            StringBuilder sb = new StringBuilder(message);
            while (sb.length() < width)
                sb.append(" ");
            message = sb.toString();
        }
        // Allow for colours which add 2 to the width
        width -= 2;
        // Invalid width/space size
        if (width < 1) {
            width = 1;
        }
        if (length < 0) {
            length = 0;
        }
        // Add substrings
        for (int i = 0; i < message.length() - width; i++)
            list.add(message.substring(i, i + width));
        // Add space between repeats
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            list.add(message.substring(message.length() - width + (i > width ? width : i)) + space);
            if (space.length() < width) {
                space.append(" ");
            }
        }
        // Wrap
        for (int i = 0; i < width - length; ++i)
            list.add(message.substring(message.length() - width + length + i) + space + message.substring(0, i));
        // Join up
        for (int i = 0; i < length; i++) {
            if (i > space.length())
                break;
            list.add(space.substring(0, space.length() - i) + message.substring(0, width - (length > width ? width : length) + i));
        }
    }


    /**
     * get the next possible message
     *
     * @return String
     */
    public String next() {
        StringBuilder sb = getNext();
        if (sb.charAt(sb.length() - 1) == ChatColor.COLOR_CHAR) {
            sb.setCharAt(sb.length() - 1, ' ');
        }
        if (sb.charAt(0) == ChatColor.COLOR_CHAR) {
            ChatColor c = ChatColor.getByChar(sb.charAt(1));
            if (c != null) {
                color = c;
                sb = getNext();
                if (sb.charAt(0) != ' ')
                    sb.setCharAt(0, ' ');
            }
        }
        return color + sb.toString();
    }


    private StringBuilder getNext() {
        return new StringBuilder(list.get(position++ % list.size()));
    }

    /**
     * Load settings
     *
     * @param settings
     */
    private void loadSettings(HashMap<String, String> settings) {
        this.length = Integer.parseInt(settings.get("length"));
        this.width = Integer.parseInt(settings.get("width"));
        this.message = settings.get("message");
        this.row = Integer.parseInt(settings.get("row"));
    }
    /**
     * get the animation's row
     *
     * @return Integer
     */
    public Integer getRow() {
        return this.row;
    }

    /**
     * @return
     */
    public String name() {
        return "scroll";
    }
}
