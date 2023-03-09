package net.athamusmc.rpgcore.character;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.util.InventoryHandler;
import net.athamusmc.rpgcore.util.UUIDFormater;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;


public class Character {

    private int characterID;
    private String className;
    private UUID uuid;
    private int level;
    private int xp;
    private int balance;
    private String inventoryString;
    private int dex;
    private int str;
    private int con;
    private int intl;
    private int wis;
    private int wclvl;
    private int wcxp;
    private int wwlvl;
    private int wwxp;
    private int fishlvl;
    private int fishxp;
    private int farmlvl;
    private int farmxp;
    private int minelvl;
    private int minexp;
    private int smithlvl;
    private int smithxp;
    private int cooklvl;
    private int cookxp;
    private int thieflvl;
    private int thiefxp;
    private String location;

    private int skillvl;
    private int skillpts;

    public Character(int characterID, UUID UUID, Main main,String className, int level, int xp, int balance, String inventoryString, int wclvl, int wcxp, int wwlvl, int wwxp, int farmlvl, int farmxp,
                     int fishlvl, int fishxp, int minelvl, int minexp, int smithlvl, int smithxp, int cooklvl, int cookxp, int thieflvl, int thiefxp, int con, int dex, int str, int wis, int intl,
                     String location, int skillvl, int skillpts){
        this.characterID = characterID;
        this.uuid = UUID;
        this.className = className;
        this.level = level;
        this.xp = xp;
        this.balance = main.getConfigManager().getFile().getInt("settings.modules.economy.starting-balance");
        this.inventoryString = InventoryHandler.saveInventory(Bukkit.getPlayer(UUID));
        this.con = con;
        this.dex = dex;
        this.str = str;
        this.wis = wis;
        this.intl = intl;
        this.wclvl = wclvl;
        this.wcxp = wcxp;
        this.wwlvl = wwlvl;
        this.wwxp = wwxp;
        this.cooklvl = cooklvl;
        this.cookxp = cookxp;
        this.farmlvl = farmlvl;
        this.farmxp = farmxp;
        this.fishlvl = fishlvl;
        this.fishxp = fishxp;
        this.minelvl = minelvl;
        this.minexp = minexp;
        this.smithlvl = smithlvl;
        this.smithxp = smithxp;
        this.thieflvl = thieflvl;
        this.thiefxp = thiefxp;
        this.location = main.getConfigManager().getFile().getString("settings.modules.character.spawn-world");
        this.skillpts = skillpts;
        this.skillvl = skillvl;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public String getUuid() {
        return UUIDFormater.UUIDToSQLString(uuid);
    }

    public void setUuid(String uuid) {
        this.uuid = UUIDFormater.SQLStringToUUID(uuid);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getInventoryString() {
        return inventoryString;
    }

    public void setInventoryString(String inventoryString) {
        this.inventoryString = inventoryString;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getIntl() {
        return intl;
    }

    public void setIntl(int intl) {
        this.intl = intl;
    }

    public int getWis() {
        return wis;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getWclvl() {
        return wclvl;
    }

    public void setWclvl(int wclvl) {
        this.wclvl = wclvl;
    }

    public int getWcxp() {
        return wcxp;
    }

    public void setWcxp(int wcxp) {
        this.wcxp = wcxp;
    }

    public int getWwlvl() {
        return wwlvl;
    }

    public void setWwlvl(int wwlvl) {
        this.wwlvl = wwlvl;
    }

    public int getWwxp() {
        return wwxp;
    }

    public void setWwxp(int wwxp) {
        this.wwxp = wwxp;
    }

    public int getFishlvl() {
        return fishlvl;
    }

    public void setFishlvl(int fishlvl) {
        this.fishlvl = fishlvl;
    }

    public int getFishxp() {
        return fishxp;
    }

    public void setFishxp(int fishxp) {
        this.fishxp = fishxp;
    }

    public int getFarmlvl() {
        return farmlvl;
    }

    public void setFarmlvl(int farmlvl) {
        this.farmlvl = farmlvl;
    }

    public int getFarmxp() {
        return farmxp;
    }

    public void setFarmxp(int farmxp) {
        this.farmxp = farmxp;
    }

    public int getMinelvl() {
        return minelvl;
    }

    public void setMinelvl(int minelvl) {
        this.minelvl = minelvl;
    }

    public int getMinexp() {
        return minexp;
    }

    public void setMinexp(int minexp) {
        this.minexp = minexp;
    }

    public int getSmithlvl() {
        return smithlvl;
    }

    public void setSmithlvl(int smithlvl) {
        this.smithlvl = smithlvl;
    }

    public int getSmithxp() {
        return smithxp;
    }

    public void setSmithxp(int smithxp) {
        this.smithxp = smithxp;
    }

    public int getCooklvl() {
        return cooklvl;
    }

    public void setCooklvl(int cooklvl) {
        this.cooklvl = cooklvl;
    }

    public int getCookxp() {
        return cookxp;
    }

    public void setCookxp(int cookxp) {
        this.cookxp = cookxp;
    }

    public int getThieflvl() {
        return thieflvl;
    }

    public void setThieflvl(int thieflvl) {
        this.thieflvl = thieflvl;
    }

    public int getThiefxp() {
        return thiefxp;
    }

    public void setThiefxp(int thiefxp) {
        this.thiefxp = thiefxp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSkillvl() {
        return skillvl;
    }

    public void setSkillvl(int skillvl) {
        this.skillvl = skillvl;
    }

    public int getSkillpts() {
        return skillpts;
    }

    public void setSkillpts(int skillpts) {
        this.skillpts = skillpts;
    }
}



