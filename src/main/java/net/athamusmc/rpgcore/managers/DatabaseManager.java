package net.athamusmc.rpgcore.managers;

import net.athamusmc.rpgcore.Main;
import net.athamusmc.rpgcore.character.Character;
import net.athamusmc.rpgcore.util.UUIDFormater;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;

public class DatabaseManager {

    private final Main main;

    private final String username;
    private final String password;

    private final String jdbcUrl;

    private Connection connection;

    public DatabaseManager(Main main){
        this.main = main;
        this.username = main.getConfigManager().getFile().getString("settings.modules.database.username");
        this.password = main.getConfigManager().getFile().getString("settings.modules.database.password");
        this.jdbcUrl = main.getConfigManager().getFile().getString("settings.modules.database.jdbc");
    }

    public Boolean hasTable(Player player) throws SQLException{
        DatabaseMetaData meta = getConnection().getMetaData();
        ResultSet resultSet = meta.getTables(null, null, player.getUniqueId().toString().toUpperCase(),new String[]{"TABLE"});
        return resultSet.next();
    }

    public Connection getConnection() throws SQLException {
        if (this.connection != null) {
            return connection;
        }
            Connection con = DriverManager.getConnection(jdbcUrl,username,password);
            this.connection = con;
        return connection;
    }

    public void initializeDatabase(Player player) throws SQLException {
        Statement statement = getConnection().createStatement();
        String uuid = UUIDFormater.UUIDToSQLString(player.getUniqueId());
        String sql = "CREATE TABLE IF NOT EXISTS " + uuid +
                "(CharacterID int primary key,Class varchar(100), Level int, Exp int, Balance int, Inventory varchar(10000), WoodcuttingLevel int, WoodcuttingExp int, WoodworkingLevel int, WoodworkingExp int," +
                "FarmingLevel int, FarmingExp int, FishingLevel int, FishingExp int, MiningLevel int, MiningExp int, SmithingLevel int, SmithingExp int, CookingLevel int, " +
                "CookingExp int, ThievingLevel int, ThievingExp int, Con int, Dex int, Str int, Wis int, Intl int,Location varchar(10000),SkillLevel int,SkillPoints int)";
        statement.execute(sql);
        statement.close();
    }

    public void createCharacter(Character character) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO " + character.getUuid() + "(CharacterID," +
                "Class,Level,Exp,Balance,Inventory,WoodcuttingLevel,WoodcuttingExp,WoodworkingLevel,WoodworkingExp,FarmingLevel,FarmingExp," +
                "FishingLevel,FishingExp,MiningLevel,MiningExp,SmithingLevel,SmithingExp,CookingLevel,CookingExp,ThievingLevel,ThievingExp," +
                "Con,Dex,Str,Wis,Intl,Location,SkillLevel,SkillPoints) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        statement.setInt(1, character.getCharacterID());
        statement.setString(2,character.getClassName());
        statement.setInt(3, character.getLevel());
        statement.setInt(4, character.getXp());
        statement.setInt(5, character.getBalance());
        statement.setString(6, character.getInventoryString());
        statement.setInt(7, character.getWclvl());
        statement.setInt(8, character.getWcxp());
        statement.setInt(9,character.getWwlvl());
        statement.setInt(10, character.getWwxp());
        statement.setInt(11, character.getFarmlvl());
        statement.setInt(12, character.getFarmxp());
        statement.setInt(13, character.getFishlvl());
        statement.setInt(14, character.getFishxp());
        statement.setInt(15, character.getMinelvl());
        statement.setInt(16, character.getMinexp());
        statement.setInt(17, character.getSmithlvl());
        statement.setInt(18, character.getSmithxp());
        statement.setInt(19, character.getCooklvl());
        statement.setInt(20, character.getCookxp());
        statement.setInt(21, character.getThieflvl());
        statement.setInt(22, character.getThiefxp());
        statement.setInt(23, character.getCon());
        statement.setInt(24, character.getDex());
        statement.setInt(25, character.getStr());
        statement.setInt(26, character.getWis());
        statement.setInt(27, character.getIntl());
        statement.setString(28, character.getLocation());
        statement.setInt(29, character.getSkillvl());
        statement.setInt(30, character.getSkillpts());

        statement.executeUpdate();

        statement.close();

    }

    public void updateCharacter(Character character) throws SQLException{

        PreparedStatement statement = getConnection().prepareStatement("UPDATE " + character.getUuid() + " SET (CharacterID=?,Class=?" +
                "Level=?,Exp=?,Balance=?,Iventory=?,WoodcuttingLevel=?,WoodcuttingExp=?,WoodworkingLevel=?,WoodworkingExp=?,FarmingLevel=?,FarmingExp=?," +
                "FishingLevel=?,FishingExp=?,MiningLevel=?,MiningExp=?,SmithingLevel=?,SmithingExp=?,CookingLevel=?,CookingExp=?,ThievingLevel=?,ThievingExp=?," +
                "Con=?,Dex=?,Str=?,Wis=?,Intl=?,Location=?,SkillLevel=?,SkillPoints=? WHERE CharacterID=?");

        statement.setInt(1, character.getCharacterID());
        statement.setString(2,character.getClassName());
        statement.setInt(3, character.getLevel());
        statement.setInt(4, character.getXp());
        statement.setInt(5, character.getBalance());
        statement.setString(6, character.getInventoryString());
        statement.setInt(7, character.getWclvl());
        statement.setInt(8, character.getWcxp());
        statement.setInt(9,character.getWwlvl());
        statement.setInt(10, character.getWwxp());
        statement.setInt(11, character.getFarmlvl());
        statement.setInt(12, character.getFarmxp());
        statement.setInt(13, character.getFishlvl());
        statement.setInt(14, character.getFishxp());
        statement.setInt(15, character.getMinelvl());
        statement.setInt(16, character.getMinexp());
        statement.setInt(17, character.getSmithlvl());
        statement.setInt(18, character.getSmithxp());
        statement.setInt(19, character.getCooklvl());
        statement.setInt(20, character.getCookxp());
        statement.setInt(21, character.getThieflvl());
        statement.setInt(22, character.getThiefxp());
        statement.setInt(23, character.getCon());
        statement.setInt(24, character.getDex());
        statement.setInt(25, character.getStr());
        statement.setInt(26, character.getWis());
        statement.setInt(27, character.getIntl());
        statement.setString(28, character.getLocation());
        statement.setInt(29, character.getSkillvl());
        statement.setInt(30, character.getSkillpts());

        statement.executeUpdate();

        statement.close();
    }

    public void deletePlayerStats(Character character) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM " + character.getUuid() + " WHERE CharacterID=?");
        statement.setInt(1, character.getCharacterID());

        statement.executeUpdate();

        statement.close();
    }


    public int playersCharacters(Player player) throws SQLException {
        int amount = 0;
       PreparedStatement statement = getConnection().prepareStatement("SELECT COUNT(CharacterID) FROM " + UUIDFormater.UUIDToSQLString(player.getUniqueId()));
       ResultSet resultSet = statement.executeQuery();
       while (resultSet.next()){
           amount = resultSet.getInt(1);
       }
       return amount;
    }
    public Character findCharacterByID(Player player, int characterID) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM " + UUIDFormater.UUIDToSQLString(player.getUniqueId()) + " WHERE characterID = ?");
        statement.setInt(1, characterID);

        ResultSet resultSet = statement.executeQuery();

        Character character;

        if(resultSet.next()){

            character = new Character(characterID, player.getUniqueId(),main,resultSet.getString("Class"),resultSet.getInt("Level"),resultSet.getInt("Exp"),resultSet.getInt("Balance"),
                    resultSet.getString("Inventory"), resultSet.getInt("WoodcuttingLevel"),resultSet.getInt("WoodcuttingExp"),resultSet.getInt("WoodworkingLevel"),
                    resultSet.getInt("WoodworkingExp"), resultSet.getInt("FarmingLevel"),resultSet.getInt("FarmingExp"), resultSet.getInt("FishingLevel"),
                    resultSet.getInt("FishingExp"),resultSet.getInt("MiningLevel"), resultSet.getInt("MiningExp"), resultSet.getInt("SmithingLevel"),
                    resultSet.getInt("SmithingExp"), resultSet.getInt("CookingLevel"), resultSet.getInt("CookingExp"), resultSet.getInt("ThievingLevel"),
                    resultSet.getInt("ThievingExp"), resultSet.getInt("Con"),resultSet.getInt("Dex"), resultSet.getInt("Str"), resultSet.getInt("Wis"),
                    resultSet.getInt("Intl"),resultSet.getString("Location"),resultSet.getInt("SkillLevel"),resultSet.getInt("SkillPoints"));

            statement.close();

            return character;
        }

        statement.close();

        return null;
    }



}
