package com.ktar5.infoboard.scoreboard;

import com.ktar5.infoboard.utils.Messages;
import org.bukkit.entity.Player;

public class ShouldSet {

    private String line;
    private Player player;

    public ShouldSet(Player player, String line) {
        this.line = line;
        this.player = player;
    }

    /**
     * Should the line be set
     *
     * @return true/false
     */
    public boolean getBoolean() {
        // If the variable isn't 0
        if (this.line.contains("~!<")) {
            String l = (this.line.split("~!<")[1]).split(">")[0];
            String l1 = Messages.getLine("%" + l + "%", this.player);
            return !(l1.equalsIgnoreCase("Unknown") || l1.equalsIgnoreCase("false")
                    || l1.equalsIgnoreCase("None") || l1.equalsIgnoreCase("")
                    || l1.equalsIgnoreCase("0") || l1.equalsIgnoreCase("-1"));
        }
        // If the variable is 0
        else if (this.line.contains("~@<")) {
            String l = (this.line.split("~@<")[1]).split(">")[0];
            String l1 = Messages.getLine("%" + l + "%", this.player);
            return l1.equalsIgnoreCase("Unknown") || l1.equalsIgnoreCase("false")
                    || l1.equalsIgnoreCase("None") || l1.equalsIgnoreCase("")
                    || l1.equalsIgnoreCase("0") || l1.equalsIgnoreCase("-1");
        } else
            return true;

    }

    /**
     * Gets the line after removing the boolean variables
     *
     * @return the new line
     */
    public String getLine() {
        if (this.line.contains("~!<")) {
            String l = (this.line.split("~!<")[1]).split(">")[0];

            this.line = this.line.replaceAll("~!<" + l + ">", "");
        } else if (this.line.contains("~@<")) {
            String l = (this.line.split("~@<")[1]).split(">")[0];

            this.line = this.line.replaceAll("~@<" + l + ">", "");
        }
        return this.line;
    }
}
