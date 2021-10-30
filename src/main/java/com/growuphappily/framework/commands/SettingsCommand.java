package com.growuphappily.framework.commands;


import com.growuphappily.framework.items.ItemAbility;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;

import java.util.Objects;

public class SettingsCommand extends CommandBase {
    @Override
    public String getName() {
        return "framework";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "Usage:\n" +
                "/framework height <number> Set the wall height.(default = 3)\n" +
                "/framework width <number> Set the wall width. (default = 5)" +
                "/framework underground <number> Set the wall underground height. (default = 2)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length <= 1){
            sender.sendMessage(new TextComponentString(getUsage(sender)));
        }
        if(args[0].equals("height")){
            ItemAbility.height = Integer.parseInt(args[1]);
        } else if(args[0].equals("width")){
            ItemAbility.width = Integer.parseInt(args[1]);
        } else if (args[0].equals("underground")) {
            ItemAbility.under = Integer.parseInt(args[1]);
        } else{
            sender.sendMessage(new TextComponentString("Unknown command"));
        }
    }
}
