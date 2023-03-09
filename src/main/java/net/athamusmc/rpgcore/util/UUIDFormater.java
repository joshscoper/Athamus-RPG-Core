package net.athamusmc.rpgcore.util;

import java.util.UUID;

public class UUIDFormater {

    public static String UUIDToSQLString(UUID uuid){
        return uuid.toString().replace("-", "").replace("e", "$");
    }

    public static UUID SQLStringToUUID(String sqlUUIDString){
        char[] uuidStringChars = sqlUUIDString.toCharArray();
        String newUUIDString = "";
        for(int i = 0; i < uuidStringChars.length; i++){
            newUUIDString = newUUIDString + uuidStringChars[i];
            if(i == 7 || i == 11 || i == 15 || i == 19) {
                newUUIDString = newUUIDString + "-";
            }
        }
        newUUIDString = newUUIDString.replace("$", "e");
        return UUID.fromString(newUUIDString);
    }
}
