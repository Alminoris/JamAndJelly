package net.alminoris.jamandjelly.util.helper;

import net.alminoris.jamandjelly.JamJelly;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonHelper
{
    public static void createJarBlockModel(String jsonContent, String colorName, String insideName, boolean isOpen, int variant)
    {
        String projectPath = System.getProperty("user.dir");

        String filePath = projectPath.replace("build\\datagen", "src\\main\\resources") + "/assets/"+ JamJelly.MOD_ID+"/models/block/" + insideName;

        File directory = new File(filePath);
        if (!directory.exists())
            directory.mkdirs();

        String fileName = "jar_" + colorName + "_"+ variant + (isOpen ? "_open" : "") + ".json";
        File modelFile = new File(directory, fileName);

        jsonContent = jsonContent.replace("COLOR_NAME_VALUE", colorName).replace("INSIDE_NAME_VALUE", insideName);

        try (FileWriter writer = new FileWriter(modelFile))
        {
            writer.write(jsonContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void createJamBlockModel(String jsonContent, String name)
    {
        String projectPath = System.getProperty("user.dir");

        String filePath = projectPath.replace("build\\datagen", "src\\main\\resources") + "/assets/"+ JamJelly.MOD_ID+"/models/block";

        File directory = new File(filePath);
        if (!directory.exists())
            directory.mkdirs();

        String fileName = name + "_jam_block.json";
        File modelFile = new File(directory, fileName);

        jsonContent = jsonContent.replace("NAME_VALUE", name);

        try (FileWriter writer = new FileWriter(modelFile))
        {
            writer.write(jsonContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void createJammingPotBlockModel(String jsonContent, String variantName, String insideName, boolean isSupport)
    {
        String projectPath = System.getProperty("user.dir");

        String filePath = projectPath.replace("build\\datagen", "src\\main\\resources") + "/assets/"+ JamJelly.MOD_ID+"/models/block/" + insideName;

        File directory = new File(filePath);
        if (!directory.exists())
            directory.mkdirs();

        String fileName = "jamming_pot" + (variantName.equals("") ? "" : ("_"+variantName)) + (isSupport ? "_support" : "") + ".json";
        File modelFile = new File(directory, fileName);

        jsonContent = jsonContent.replace("INSIDE_NAME_VALUE", insideName);

        try (FileWriter writer = new FileWriter(modelFile))
        {
            writer.write(jsonContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void createChoppingBoardModel(String jsonContent, String name, boolean isKnife)
    {
        String projectPath = System.getProperty("user.dir");

        String filePath = projectPath.replace("build\\datagen", "src\\main\\resources") + "/assets/"+ JamJelly.MOD_ID+"/models/block";

        File directory = new File(filePath);
        if (!directory.exists())
            directory.mkdirs();

        String fileName = "chopping_board_" + (isKnife ? "knife_" : "") + name + ".json";
        File modelFile = new File(directory, fileName);

        jsonContent = jsonContent.replace("WOOD_NAME_VALUE", name);

        try (FileWriter writer = new FileWriter(modelFile))
        {
            writer.write(jsonContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void createChoppingBoardBlockState(String jsonContent, String name)
    {
        String projectPath = System.getProperty("user.dir");

        String filePath = projectPath.replace("build\\datagen", "src\\main\\resources") + "/assets/"+ JamJelly.MOD_ID+"/blockstates";

        File directory = new File(filePath);
        if (!directory.exists())
            directory.mkdirs();

        String fileName = "chopping_board_" + name + ".json";
        File modelFile = new File(directory, fileName);

        jsonContent = jsonContent.replace("WOOD_NAME_VALUE", name);

        try (FileWriter writer = new FileWriter(modelFile))
        {
            writer.write(jsonContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
